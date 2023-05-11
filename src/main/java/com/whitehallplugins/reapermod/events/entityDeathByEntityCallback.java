package com.whitehallplugins.reapermod.events;

import com.whitehallplugins.reapermod.Reaper;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents.AfterKilledOtherEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

import java.util.Objects;

public class entityDeathByEntityCallback implements AfterKilledOtherEntity {

    @Override
    public void afterKilledOtherEntity(ServerWorld world, Entity entity, LivingEntity killedEntity) {
        if (entity instanceof PlayerEntity player && killedEntity instanceof PlayerEntity killedPlayer){
            if (!player.isCreative() && !player.isSpectator()){
                double previousMaxHealth = killedPlayer.getAttributes().getBaseValue(EntityAttributes.GENERIC_MAX_HEALTH);
                Objects.requireNonNull(killedPlayer.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(previousMaxHealth - 2);
                double currentMaxHealth = killedPlayer.getAttributes().getBaseValue(EntityAttributes.GENERIC_MAX_HEALTH);
                Reaper.previousMaxHealthStolen.put(player.getName().getString(), currentMaxHealth);
                if (previousMaxHealth == 2){
                    Objects.requireNonNull(Objects.requireNonNull(killedPlayer.getServer()).getPlayerManager().getPlayer(killedPlayer.getUuid())).networkHandler.disconnect(Text.literal("You ran out of lives"));
                    Objects.requireNonNull(killedPlayer.getServer()).getCommandManager().executeWithPrefix(killedPlayer.getServer().getCommandSource(), "/ban " + killedPlayer.getName().getString() + " You ran out of lives");
                }
            }
        }
    }
}
