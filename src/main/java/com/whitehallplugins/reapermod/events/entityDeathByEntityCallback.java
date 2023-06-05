package com.whitehallplugins.reapermod.events;

import com.whitehallplugins.reapermod.playerManagement.playerEffectManager;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents.AfterKilledOtherEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

import static com.whitehallplugins.reapermod.playerManagement.playerHeartManager.*;
import static com.whitehallplugins.reapermod.playerManagement.playerPunishmentManager.kickPlayer;
import static com.whitehallplugins.reapermod.playerManagement.playerPunishmentManager.banPlayer;

public class entityDeathByEntityCallback implements AfterKilledOtherEntity {

    @Override
    public void afterKilledOtherEntity(ServerWorld world, Entity entity, LivingEntity killedEntity) {
        if (entity instanceof PlayerEntity player && killedEntity instanceof PlayerEntity killedPlayer){
            if (!player.isCreative() && !player.isSpectator()){
                int previousKillerMaxHealth = getMaxHearts(player);
                if (getMaxHearts(killedPlayer) < 2 || previousKillerMaxHealth == 2){
                    if (previousKillerMaxHealth == 2){
                        addHeart(player, (getMaxHearts(killedPlayer)/2)-1);
                    }
                    removeHeart(killedPlayer, getMaxHearts(killedPlayer));
                    kickPlayer(killedPlayer);
                    banPlayer(killedPlayer);
                }
                else {
                    removeHeart(killedPlayer, 1);
                    addHeart(player, 1);
                }
                playerEffectManager.removeEffects(player, getMaxHearts(player)/2);
            }
        }
    }
}
