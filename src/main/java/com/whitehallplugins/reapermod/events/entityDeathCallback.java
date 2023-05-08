package com.whitehallplugins.reapermod.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents.AfterKilledOtherEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Objects;

public class entityDeathCallback implements AfterKilledOtherEntity {

    @Override
    public void afterKilledOtherEntity(ServerWorld world, Entity entity, LivingEntity killedEntity) {
        if (entity instanceof PlayerEntity player && killedEntity instanceof PlayerEntity killedPlayer){
            if (!player.isCreative() && !player.isSpectator()){
                double maxHealth = (killedPlayer.getAttributes().getBaseValue(EntityAttributes.GENERIC_MAX_HEALTH));
                if (maxHealth <= 2){
                    Objects.requireNonNull(Objects.requireNonNull(killedPlayer.getServer()).getPlayerManager().getPlayer(killedPlayer.getUuid())).networkHandler.disconnect(Text.literal("You ran out of lives").formatted(Formatting.RED));
                }
            }
        }
    }
}
