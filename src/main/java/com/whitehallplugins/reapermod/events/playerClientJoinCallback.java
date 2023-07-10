package com.whitehallplugins.reapermod.events;

import com.whitehallplugins.reapermod.networking.networkingConstants;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents.Join;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class playerClientJoinCallback implements Join {

    @Override
    public void onPlayReady(ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) {
        ClientPlayNetworking.send(networkingConstants.MOD_PACKET_ID, PacketByteBufs.empty());
    }
}
