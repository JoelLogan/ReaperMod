package com.whitehallplugins.reapermod.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStarted;
import net.minecraft.server.MinecraftServer;

import static com.whitehallplugins.reapermod.playerManagement.playerTeamManager.createTeam;

public class serverStartCallback implements ServerStarted {
    @Override
    public void onServerStarted(MinecraftServer server) {
        createTeam(server);
    }
}
