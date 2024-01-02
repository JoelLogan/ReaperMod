package com.whitehallplugins.reapermod.events;

import com.whitehallplugins.reapermod.networking.networkingConstants;
import com.whitehallplugins.reapermod.playerManagement.playerPunishmentManager;
import com.whitehallplugins.reapermod.reaper;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents.Join;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.whitehallplugins.reapermod.playerManagement.playerHeartManager.getMaxHearts;
import static com.whitehallplugins.reapermod.playerManagement.playerHeartManager.setMaxHearts;
import static com.whitehallplugins.reapermod.playerManagement.playerTeamManager.checkReaper;

public class playerJoinCallback implements Join {

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        if (getMaxHearts(handler.getPlayer()) < 2) {
            setMaxHearts(handler.getPlayer(), 3);
        }
        checkReaper(handler.getPlayer(), getMaxHearts(handler.getPlayer())/2);
        reaper.authenticatingPlayers.add(handler.getPlayer());
        sendPacket(handler.getPlayer());
        kickPlayerLater(handler.getPlayer());
    }

    private static void sendPacket(ServerPlayerEntity player){
        ServerPlayNetworking.send(player, networkingConstants.MOD_PACKET_ID, PacketByteBufs.empty());
    }

    private static void kickPlayerLater(PlayerEntity player) {
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(15);
                if (reaper.authenticatingPlayers.contains(player)) {
                    playerPunishmentManager.kickPlayer(player, Text.translatable("reapermod.error.modversion2").getString());
                }
            } catch (InterruptedException e) {
                System.out.println("Kick Player Later Error: " + e.getMessage());
            }
        }, executor);
    }
}
