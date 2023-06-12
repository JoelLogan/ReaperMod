package com.whitehallplugins.reapermod.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents.AfterRespawn;
import net.minecraft.server.network.ServerPlayerEntity;

import static com.whitehallplugins.reapermod.playerManagement.playerHeartManager.getMaxHearts;
import static com.whitehallplugins.reapermod.playerManagement.playerTeamManager.checkReaper;
import static com.whitehallplugins.reapermod.playerManagement.playerEffectManager.applyEffects;

public class playerAfterRespawnCallback implements AfterRespawn {

    @Override
    public void afterRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        int maxHearts = getMaxHearts(newPlayer)/2;
        applyEffects(newPlayer, maxHearts);
        checkReaper(newPlayer, maxHearts);
    }
}
