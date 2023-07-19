package com.whitehallplugins.reapermod.events;

import com.whitehallplugins.reapermod.reaper;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents.Disconnect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

public class playerDisconnectCallback implements Disconnect {

    @Override
    public void onPlayDisconnect(ServerPlayNetworkHandler handler, MinecraftServer server) {
        reaper.reapers.remove(handler.getPlayer());
    }
}
