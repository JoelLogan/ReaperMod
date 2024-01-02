package com.whitehallplugins.reapermod.client;

import com.whitehallplugins.reapermod.networking.networkingConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.network.PacketByteBuf;

public class reaperClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(networkingConstants.MOD_PACKET_ID, (client, handler, buf, responseSender) -> {
            PacketByteBuf modVersionPacket = PacketByteBufs.create().writeIntArray(networkingConstants.modVersion());
            client.execute(() -> responseSender.sendPacket(networkingConstants.MOD_PACKET_ID, modVersionPacket));
        });
        HudRenderCallback.EVENT.register((matrixStack,tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player != null && (client.player.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH) / 2.0F) < 2) {
                TextRenderer renderer = client.textRenderer;
                renderer.draw(matrixStack, "You are a", 4, 2, 0xffffff);
                renderer.draw(matrixStack, "REAPER", 11, 12, 0xAA00AA);
            }
        });
    }

}
