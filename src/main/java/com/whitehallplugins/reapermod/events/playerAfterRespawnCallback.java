package com.whitehallplugins.reapermod.events;


import com.whitehallplugins.reapermod.playerManagement.playerEffectManager;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents.AfterRespawn;
import net.minecraft.server.network.ServerPlayerEntity;

import static com.whitehallplugins.reapermod.playerManagement.playerHeartManager.getMaxHearts;

public class playerAfterRespawnCallback implements AfterRespawn {

    @Override
    public void afterRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        newPlayer.setHealth(newPlayer.getMaxHealth());
        playerEffectManager.applyEffects(newPlayer, getMaxHearts(newPlayer)/2);
    }
}
