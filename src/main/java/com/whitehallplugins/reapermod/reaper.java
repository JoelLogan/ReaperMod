package com.whitehallplugins.reapermod;

import com.whitehallplugins.reapermod.commands.admincommands.getHealthCommand;
import com.whitehallplugins.reapermod.commands.admincommands.setHealthCommand;
import com.whitehallplugins.reapermod.commands.admincommands.adminReviveCommand;
import com.whitehallplugins.reapermod.commands.playercommands.reviveCommand;
import com.whitehallplugins.reapermod.commands.playercommands.withdrawCommand;
import com.whitehallplugins.reapermod.events.*;
import com.whitehallplugins.reapermod.items.heart_item;
import com.whitehallplugins.reapermod.items.revive_crystal_item;
import com.whitehallplugins.reapermod.networking.networkingConstants;

import com.whitehallplugins.reapermod.playerManagement.playerPunishmentManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class reaper implements ModInitializer {

    public static final heart_item HEART_ITEM = new heart_item(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.EPIC));
    public static final revive_crystal_item REVIVE_CRYSTAL_ITEM = new revive_crystal_item(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.RARE).maxCount(1));
    public static final List<PlayerEntity> authenticatingPlayers = new ArrayList<>();
    public static final List<PlayerEntity> reapers = new ArrayList<>();
    private final Identifier[] itemIdentifiers = new Identifier[2];

    @Override
    public void onInitialize() {
        itemIdentifiers[0] = new Identifier("reapermod", "heart");
        itemIdentifiers[1] = new Identifier("reapermod", "revive_crystal");

        Registry.register(Registry.ITEM, itemIdentifiers[0], HEART_ITEM);
        Registry.register(Registry.ITEM, itemIdentifiers[1], REVIVE_CRYSTAL_ITEM);

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> reviveCommand.register(dispatcher));
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> withdrawCommand.register(dispatcher));
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> setHealthCommand.register(dispatcher)));
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> getHealthCommand.register(dispatcher)));
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> adminReviveCommand.register(dispatcher)));

        ServerPlayNetworking.registerGlobalReceiver(networkingConstants.MOD_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            if (buf.isReadable()) {
                int[] versionArray = buf.readIntArray();
                int[] expectedVersionArray = networkingConstants.modVersion();
                assert expectedVersionArray != null;
                server.execute(() -> {
                    authenticatingPlayers.remove(player);
                    if (versionArray.length == expectedVersionArray.length && Arrays.equals(versionArray, expectedVersionArray)) {
                        handler.getPlayer().unlockRecipes(itemIdentifiers);
                        player.sendResourcePackUrl("https://raw.githubusercontent.com/JoelLogan/ReaperMod/master/reaperpack.zip", "65DE5ED3AB4018B95E7606757767C885A7709263", false, Text.literal("ReaperSMP themed texturing").formatted(Formatting.DARK_PURPLE));
                    }
                    else {
                        playerPunishmentManager.kickPlayer(player, Text.translatable("reapermod.error.modversion3").getString());
                    }
                });
            }
            else {
                server.execute(() -> playerPunishmentManager.kickPlayer(player, Text.translatable("reapermod.error.modversion3").getString()));
            }
        });

        ServerLifecycleEvents.SERVER_STARTED.register(new serverStartCallback());
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(new entityDeathByEntityCallback());
        ServerPlayerEvents.COPY_FROM.register(new playerRespawnCopyDataCallback());
        ServerPlayerEvents.AFTER_RESPAWN.register(new playerAfterRespawnCallback());
        ServerPlayConnectionEvents.JOIN.register(new playerJoinCallback());
        ServerPlayConnectionEvents.DISCONNECT.register(new playerDisconnectCallback());
    }

}
