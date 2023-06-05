package com.whitehallplugins.reapermod.events;

import com.whitehallplugins.reapermod.playerManagement.playerEffectManager;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents.AfterKilledOtherEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.Objects;

import static com.whitehallplugins.reapermod.playerManagement.playerPunishmentManager.kickPlayer;
import static com.whitehallplugins.reapermod.playerManagement.playerPunishmentManager.banPlayer;

public class entityDeathByEntityCallback implements AfterKilledOtherEntity {

    @Override
    public void afterKilledOtherEntity(ServerWorld world, Entity entity, LivingEntity killedEntity) {
        if (entity instanceof PlayerEntity player && killedEntity instanceof PlayerEntity killedPlayer){
            if (!player.isCreative() && !player.isSpectator()){
                double currentKilledMaxHealth = (killedPlayer.getAttributes().getBaseValue(EntityAttributes.GENERIC_MAX_HEALTH) - 2);
                Objects.requireNonNull(killedPlayer.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(currentKilledMaxHealth);
                double previousKillerMaxHealth = player.getAttributes().getBaseValue(EntityAttributes.GENERIC_MAX_HEALTH);
                if (previousKillerMaxHealth < 40){
                    Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(previousKillerMaxHealth + 2);
                    playerEffectManager.removeEffects(player, player.getAttributes().getBaseValue(EntityAttributes.GENERIC_MAX_HEALTH)/2);
                }
                if (currentKilledMaxHealth == 0 || previousKillerMaxHealth == 2){
                    kickPlayer(killedPlayer);
                    banPlayer(killedPlayer);
                }
            }
        }
    }
}
