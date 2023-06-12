package com.whitehallplugins.reapermod.events;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents.Join;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.Identifier;

import static com.whitehallplugins.reapermod.playerManagement.playerHeartManager.getMaxHearts;
import static com.whitehallplugins.reapermod.playerManagement.playerHeartManager.setMaxHearts;
import static com.whitehallplugins.reapermod.playerManagement.playerTeamManager.checkReaper;

public class playerJoinCallback implements Join {

    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        if (getMaxHearts(handler.getPlayer()) < 2) {
            setMaxHearts(handler.getPlayer(), 3);
        }
        Identifier[] identifiers = new Identifier[2];
        identifiers[0] = new Identifier("reapermod", "heart");
        identifiers[1] = new Identifier("reapermod", "revive_crystal");
        handler.getPlayer().unlockRecipes(identifiers);
        checkReaper(handler.getPlayer(), 1);
    }
}
