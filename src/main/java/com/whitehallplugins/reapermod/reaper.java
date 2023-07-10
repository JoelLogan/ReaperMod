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
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class reaper implements ModInitializer {

    public static final heart_item HEART_ITEM = new heart_item(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.EPIC));
    public static final revive_crystal_item REVIVE_CRYSTAL_ITEM = new revive_crystal_item(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.RARE).maxCount(1));
    public static final List<PlayerEntity> authenticatingPlayers = new ArrayList<>();

    /**
     * TODO: Add moderation commands
     * TODO: Add more resource pack stuff
     * TODO: Add more fun visual Reaper effects
     * TODO: Make sure that players have the mod using packets
     */

    @Override
    public void onInitialize() {
        ServerPlayNetworking.registerGlobalReceiver(networkingConstants.MOD_PACKET_ID, (server, player, handler, buf, responseSender) -> server.execute(() -> {
            authenticatingPlayers.remove(player);
            buf.release();
        }));
        Registry.register(Registry.ITEM, new Identifier("reapermod", "heart"), HEART_ITEM);
        Registry.register(Registry.ITEM, new Identifier("reapermod", "revive_crystal"), REVIVE_CRYSTAL_ITEM);

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> reviveCommand.register(dispatcher));
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> withdrawCommand.register(dispatcher));
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> setHealthCommand.register(dispatcher)));
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> getHealthCommand.register(dispatcher)));
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> adminReviveCommand.register(dispatcher)));


        ServerLifecycleEvents.SERVER_STARTED.register(new serverStartCallback());
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(new entityDeathByEntityCallback());
        ServerPlayerEvents.COPY_FROM.register(new playerRespawnCopyDataCallback());
        ServerPlayerEvents.AFTER_RESPAWN.register(new playerAfterRespawnCallback());
        ServerPlayConnectionEvents.JOIN.register(new playerJoinCallback());
    }

}
