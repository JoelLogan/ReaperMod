package com.whitehallplugins.reapermod.events;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents.Join;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

import java.util.Objects;

public class playerJoinCallback implements Join {

    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        if (handler.getPlayer().getAttributes().getBaseValue(EntityAttributes.GENERIC_MAX_HEALTH) < 2) {
            Objects.requireNonNull(handler.getPlayer().getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(20);
        }
    }

}
