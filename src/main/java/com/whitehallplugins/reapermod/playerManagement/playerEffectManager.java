package com.whitehallplugins.reapermod.playerManagement;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class playerEffectManager {

    public static void applyEffects(PlayerEntity player, int maxHealth){
        switch (maxHealth) {
            case 9:
                effectNine(player, true);
            case 8:
                effectNine(player, true);
                effectEight(player, true);
            case 7:
                effectNine(player, true);
                effectEight(player, true);
                effectSeven(player, true);
            case 6:
                effectNine(player, true);
                effectEight(player, true);
                effectSeven(player, true);
                effectSix(player, true);
            case 5:
                effectNine(player, true);
                effectEight(player, true);
                effectSeven(player, true);
                effectSix(player, true);
                effectFive(player, true);
            case 4:
                effectNine(player, true);
                effectEight(player, true);
                effectSeven(player, true);
                effectSix(player, true);
                effectFive(player, true);
                effectFour(player, true);
            case 3:
                effectNine(player, true);
                effectEight(player, true);
                effectSeven(player, true);
                effectSix(player, true);
                effectFive(player, true);
                effectFour(player, true);
                effectThree(player, true);
            case 2:
                effectNine(player, true);
                effectEight(player, true);
                effectSeven(player, true);
                effectSix(player, true);
                effectFive(player, true);
                effectFour(player, true);
                effectThree(player, true);
                effectTwo(player, true);
            case 1:
                effectNine(player, true);
                effectEight(player, true);
                effectSeven(player, true);
                effectSix(player, true);
                effectFive(player, true);
                effectFour(player, true);
                effectThree(player, true);
                effectTwo(player, true);
                effectOne(player, true);
        }
    }

    public static void removeEffects(PlayerEntity player, int maxHealth){
        if (maxHealth > 9){
            effectNine(player, false);
            effectEight(player, false);
            effectSeven(player, false);
            effectSix(player, false);
            effectFive(player, false);
            effectFour(player, false);
            effectThree(player, false);
            effectTwo(player, false);
            effectOne(player, false);
        }
        switch (maxHealth) {
            case 9:
                effectEight(player, false);
                effectSeven(player, false);
                effectSix(player, false);
                effectFive(player, false);
                effectFour(player, false);
                effectThree(player, false);
                effectTwo(player, false);
                effectOne(player, false);
            case 8:
                effectSeven(player, false);
                effectSix(player, false);
                effectFive(player, false);
                effectFour(player, false);
                effectThree(player, false);
                effectTwo(player, false);
                effectOne(player, false);
            case 7:
                effectSix(player, false);
                effectFive(player, false);
                effectFour(player, false);
                effectThree(player, false);
                effectTwo(player, false);
                effectOne(player, false);
            case 6:
                effectFive(player, false);
                effectFour(player, false);
                effectThree(player, false);
                effectTwo(player, false);
                effectOne(player, false);
            case 5:
                effectFour(player, false);
                effectThree(player, false);
                effectTwo(player, false);
                effectOne(player, false);
                effectFive(player, true);
            case 4:
                effectThree(player, false);
                effectTwo(player, false);
                effectOne(player, false);
                effectFour(player, true);
                effectFive(player, true);
            case 3:
                effectTwo(player, false);
                effectOne(player, false);
                effectFour(player, true);
            case 2:
                effectOne(player, false);
        }
    }

    private static void effectNine(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance9 = new StatusEffectInstance(StatusEffects.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance9);
        }
        else {
            if (player.getActiveStatusEffects().containsKey(statusEffectInstance9.getEffectType())) {
                if (player.getActiveStatusEffects().get(statusEffectInstance9.getEffectType()).getDuration() > 18000) {
                    player.removeStatusEffect(statusEffectInstance9.getEffectType());
                }
            }
        }
    }
    private static void effectEight(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance8 = new StatusEffectInstance(StatusEffects.ABSORPTION, Integer.MAX_VALUE, 0, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance8);
        }
        else {
            if (player.getActiveStatusEffects().containsKey(statusEffectInstance8.getEffectType())) {
                if (player.getActiveStatusEffects().get(statusEffectInstance8.getEffectType()).getDuration() > 18000) {
                    player.removeStatusEffect(statusEffectInstance8.getEffectType());
                }
            }
        }
    }
    private static void effectSeven(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance7 = new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, Integer.MAX_VALUE, 0, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance7);
        }
        else {
            if (player.getActiveStatusEffects().containsKey(statusEffectInstance7.getEffectType())) {
                if (player.getActiveStatusEffects().get(statusEffectInstance7.getEffectType()).getDuration() > 18000) {
                    player.removeStatusEffect(statusEffectInstance7.getEffectType());
                }
            }
        }
    }
    private static void effectSix(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance6 = new StatusEffectInstance(StatusEffects.RESISTANCE, Integer.MAX_VALUE, 0, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance6);
        }
        else {
            if (player.getActiveStatusEffects().containsKey(statusEffectInstance6.getEffectType())) {
                if (player.getActiveStatusEffects().get(statusEffectInstance6.getEffectType()).getDuration() > 18000) {
                    player.removeStatusEffect(statusEffectInstance6.getEffectType());
                }
            }
        }
    }
    private static void effectFive(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance5 = new StatusEffectInstance(StatusEffects.SPEED, Integer.MAX_VALUE, 0, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance5);
        }
        else {
            if (player.getActiveStatusEffects().containsKey(statusEffectInstance5.getEffectType())) {
                if (player.getActiveStatusEffects().get(statusEffectInstance5.getEffectType()).getDuration() > 18000) {
                    player.removeStatusEffect(statusEffectInstance5.getEffectType());
                }
            }
        }
    }
    private static void effectFour(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance4 = new StatusEffectInstance(StatusEffects.HASTE, Integer.MAX_VALUE, 0, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance4);
        }
        else {
            if (player.getActiveStatusEffects().containsKey(statusEffectInstance4.getEffectType())) {
                if (player.getActiveStatusEffects().get(statusEffectInstance4.getEffectType()).getDuration() > 18000) {
                    player.removeStatusEffect(statusEffectInstance4.getEffectType());
                }
            }
        }
    }
    private static void effectThree(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance3 = new StatusEffectInstance(StatusEffects.SPEED, Integer.MAX_VALUE, 1, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance3);
        }
        else {
            if (player.getActiveStatusEffects().containsKey(statusEffectInstance3.getEffectType())) {
                if (player.getActiveStatusEffects().get(statusEffectInstance3.getEffectType()).getDuration() > 18000) {
                    player.removeStatusEffect(statusEffectInstance3.getEffectType());
                }
            }
        }
    }
    private static void effectTwo(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance21 = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, false, false, true);
        StatusEffectInstance statusEffectInstance22 = new StatusEffectInstance(StatusEffects.HASTE, Integer.MAX_VALUE, 1, false, false, true);
        addDoubleEffects(player, add, statusEffectInstance21, statusEffectInstance22);
    }

    private static void effectOne(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance11 = new StatusEffectInstance(StatusEffects.STRENGTH, Integer.MAX_VALUE, 2, false, false, true);
        StatusEffectInstance statusEffectInstance12 = new StatusEffectInstance(StatusEffects.SATURATION, Integer.MAX_VALUE, 0, false, false, true);
        addDoubleEffects(player, add, statusEffectInstance11, statusEffectInstance12);
    }

    private static void addDoubleEffects(PlayerEntity player, boolean add, StatusEffectInstance statusEffectInstance1, StatusEffectInstance statusEffectInstance2) {
        if (add) {
            player.addStatusEffect(statusEffectInstance1);
            player.addStatusEffect(statusEffectInstance2);
        }
        else {
            if (player.getActiveStatusEffects().containsKey(statusEffectInstance1.getEffectType())) {
                if (player.getActiveStatusEffects().get(statusEffectInstance1.getEffectType()).getDuration() > 18000) {
                    player.removeStatusEffect(statusEffectInstance1.getEffectType());
                }
            }
            if (player.getActiveStatusEffects().containsKey(statusEffectInstance2.getEffectType())) {
                if (player.getActiveStatusEffects().get(statusEffectInstance2.getEffectType()).getDuration() > 18000) {
                    player.removeStatusEffect(statusEffectInstance2.getEffectType());
                }
            }
        }
    }
}
