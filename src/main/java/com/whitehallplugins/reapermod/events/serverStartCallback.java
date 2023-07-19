package com.whitehallplugins.reapermod.events;

import com.whitehallplugins.reapermod.reaper;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStarted;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.whitehallplugins.reapermod.playerManagement.playerTeamManager.createTeam;

public class serverStartCallback implements ServerStarted {

    @Override
    public void onServerStarted(MinecraftServer server) {
        createTeam(server);
        startParticleThread(server);
    }

    private void startParticleThread(MinecraftServer server){
        new Thread(() -> {
            double radius = 0.1;
            while (server.isRunning()) {
                try {
                    for (PlayerEntity player : reaper.reapers){
                        Vec3d playerPos = player.getPos();
                        ServerWorld world = Objects.requireNonNull(server.getWorld(player.getWorld().getRegistryKey()));
                        for (double t = 0; t <= 2*Math.PI*radius; t += 0.1) {
                            double x = (radius * Math.cos(t)) + playerPos.getX();
                            double y = (playerPos.getY() - 0.1);
                            double z = (playerPos.getZ() + radius * Math.sin(t));
                            world.spawnParticles(ParticleTypes.ENTITY_EFFECT, x, y, z, 1, 0.5, 0, 0.5, 0);
                        }
                    }
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}
