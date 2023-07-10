package com.whitehallplugins.reapermod.client;

import com.whitehallplugins.reapermod.events.playerClientJoinCallback;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.entity.attribute.EntityAttributes;

public class reaperClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register(new playerClientJoinCallback());
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
