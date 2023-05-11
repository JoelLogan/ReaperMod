package com.whitehallplugins.reapermod.events;

import com.whitehallplugins.reapermod.Reaper;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents.AllowDeath;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class entityPossibleDeathCallback implements AllowDeath {


    /**
     * Called when a living entity takes fatal damage (before totems of undying can take effect).
     *
     * @param entity the entity
     * @param damageSource the source of the fatal damage
     * @param damageAmount the amount of damage that has killed the entity
     * @return true if the death should go ahead, false to cancel the death.
     */
    @Override
    public boolean allowDeath(LivingEntity entity, DamageSource damageSource, float damageAmount) {
        if (entity instanceof PlayerEntity player) {
            Reaper.previousMaxHealth.put(player.getName().getString(), player.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH));
        }
        return true;
    }
}
