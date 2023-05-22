package com.whitehallplugins.reapermod;

import com.whitehallplugins.reapermod.events.playerAfterRespawnCallback;
import com.whitehallplugins.reapermod.events.playerRespawnCopyDataCallback;
import com.whitehallplugins.reapermod.events.entityDeathByEntityCallback;
import com.whitehallplugins.reapermod.items.heart_item;
import com.whitehallplugins.reapermod.items.revive_crystal_item;
import static com.whitehallplugins.reapermod.commands.WithdrawCommand.register;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class Reaper implements ModInitializer {

    public static final heart_item HEART_ITEM = new heart_item(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.EPIC));
    public static final revive_crystal_item REVIVE_CRYSTAL_ITEM = new revive_crystal_item(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.RARE).maxCount(1));

    private static final Text TITLE = Text.of("Item Click");

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("reapermod", "heart"), HEART_ITEM);
        Registry.register(Registry.ITEM, new Identifier("reapermod", "revive_crystal"), REVIVE_CRYSTAL_ITEM);

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> register(dispatcher));

        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(new entityDeathByEntityCallback());
        ServerPlayerEvents.COPY_FROM.register(new playerRespawnCopyDataCallback());
        ServerPlayerEvents.AFTER_RESPAWN.register(new playerAfterRespawnCallback());

    }

}
