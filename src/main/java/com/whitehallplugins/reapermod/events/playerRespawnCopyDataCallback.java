package com.whitehallplugins.reapermod.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents.CopyFrom;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Objects;


public class playerRespawnCopyDataCallback implements CopyFrom {

    @Override
    public void copyFromPlayer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        Objects.requireNonNull(newPlayer.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(oldPlayer.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH));
        newPlayer.setHealth(newPlayer.getMaxHealth());
    }
}
