package com.whitehallplugins.reapermod;

import com.whitehallplugins.reapermod.commands.ReviveCommand;
import com.whitehallplugins.reapermod.commands.WithdrawCommand;
import com.whitehallplugins.reapermod.events.*;
import com.whitehallplugins.reapermod.items.heart_item;
import com.whitehallplugins.reapermod.items.revive_crystal_item;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class Reaper implements ModInitializer {

    public static final heart_item HEART_ITEM = new heart_item(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.EPIC));
    public static final revive_crystal_item REVIVE_CRYSTAL_ITEM = new revive_crystal_item(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.RARE).maxCount(1));


    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("reapermod", "heart"), HEART_ITEM);
        Registry.register(Registry.ITEM, new Identifier("reapermod", "revive_crystal"), REVIVE_CRYSTAL_ITEM);

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> ReviveCommand.register(dispatcher));
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> WithdrawCommand.register(dispatcher));

        ServerLifecycleEvents.SERVER_STARTED.register(new serverStartCallback());
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(new entityDeathByEntityCallback());
        ServerPlayerEvents.COPY_FROM.register(new playerRespawnCopyDataCallback());
        ServerPlayerEvents.AFTER_RESPAWN.register(new playerAfterRespawnCallback());
        ServerPlayConnectionEvents.JOIN.register(new playerJoinCallback());
    }

}
