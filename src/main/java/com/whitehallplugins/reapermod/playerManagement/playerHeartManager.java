package com.whitehallplugins.reapermod.playerManagement;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Objects;

public class playerHeartManager {

    public static void addHeart(PlayerEntity player, int amount) {
        if ((getMaxHearts(player)/2)+amount <= 20) {
            getHealthAttributeInstance(player).setBaseValue(getMaxHearts(player) + (amount * 2));
        }
        else {
            setMaxHearts(player, 20);
        }
    }

    public static void removeHeart(PlayerEntity player, int amount) {
        getHealthAttributeInstance(player).setBaseValue(getMaxHearts(player) - (amount * 2));
    }

    public static void setMaxHearts(PlayerEntity player, int amount) {
        if (amount <= 20) {
            getHealthAttributeInstance(player).setBaseValue(amount * 2);
        }
        else {
            getHealthAttributeInstance(player).setBaseValue(40);
        }
    }

    /**
     * getMaxHearts is double the actual hearts
     */
    public static int getMaxHearts(PlayerEntity player) {
        return (int) getHealthAttributeInstance(player).getBaseValue();
    }

    private static EntityAttributeInstance getHealthAttributeInstance(PlayerEntity player) {
        return Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH));
    }

}
