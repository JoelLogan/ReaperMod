package com.whitehallplugins.reapermod.events;


import com.whitehallplugins.reapermod.playerManagement.playerEffectManager;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents.AfterRespawn;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;

public class playerAfterRespawnCallback implements AfterRespawn {

    @Override
    public void afterRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        newPlayer.setHealth(newPlayer.getMaxHealth());
        playerEffectManager.applyEffects(newPlayer, newPlayer.getAttributes().getBaseValue(EntityAttributes.GENERIC_MAX_HEALTH)/2);
    }
}
