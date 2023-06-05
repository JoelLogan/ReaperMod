package com.whitehallplugins.reapermod.playerManagement;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class playerEffectManager {

    public static void applyEffects(PlayerEntity player, double maxHealth){
        if (maxHealth == 9) {
            effectNine(player, true);
        } else if (maxHealth == 8) {
            effectNine(player, true);
            effectEight(player, true);
        } else if (maxHealth == 7) {
            effectNine(player, true);
            effectEight(player, true);
            effectSeven(player, true);
        } else if (maxHealth == 6) {
            effectNine(player, true);
            effectEight(player, true);
            effectSeven(player, true);
            effectSix(player, true);
        } else if (maxHealth == 5) {
            effectNine(player, true);
            effectEight(player, true);
            effectSeven(player, true);
            effectSix(player, true);
            effectFive(player, true);
        } else if (maxHealth == 4) {
            effectNine(player, true);
            effectEight(player, true);
            effectSeven(player, true);
            effectSix(player, true);
            effectFive(player, true);
            effectFour(player, true);
        } else if (maxHealth == 3) {
            effectNine(player, true);
            effectEight(player, true);
            effectSeven(player, true);
            effectSix(player, true);
            effectFive(player, true);
            effectFour(player, true);
            effectThree(player, true);
        } else if (maxHealth == 2) {
            effectNine(player, true);
            effectEight(player, true);
            effectSeven(player, true);
            effectSix(player, true);
            effectFive(player, true);
            effectFour(player, true);
            effectThree(player, true);
            effectTwo(player, true);
        } else if (maxHealth == 1) {
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

    public static void removeEffects(PlayerEntity player, double maxHealth){
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
        else if (maxHealth == 9) {
            effectEight(player, false);
            effectSeven(player, false);
            effectSix(player, false);
            effectFive(player, false);
            effectFour(player, false);
            effectThree(player, false);
            effectTwo(player, false);
            effectOne(player, false);
        } else if (maxHealth == 8) {
            effectSeven(player, false);
            effectSix(player, false);
            effectFive(player, false);
            effectFour(player, false);
            effectThree(player, false);
            effectTwo(player, false);
            effectOne(player, false);
        } else if (maxHealth == 7) {
            effectSix(player, false);
            effectFive(player, false);
            effectFour(player, false);
            effectThree(player, false);
            effectTwo(player, false);
            effectOne(player, false);
        } else if (maxHealth == 6) {
            effectFive(player, false);
            effectFour(player, false);
            effectThree(player, false);
            effectTwo(player, false);
            effectOne(player, false);
        } else if (maxHealth == 5) {
            effectFour(player, false);
            effectThree(player, false);
            effectTwo(player, false);
            effectOne(player, false);
            effectFive(player, true);
        } else if (maxHealth == 4) {
            effectThree(player, false);
            effectTwo(player, false);
            effectOne(player, false);
            effectFour(player, true);
            effectFive(player, true);
        } else if (maxHealth == 3) {
            effectTwo(player, false);
            effectOne(player, false);
            effectFour(player, true);
        } else if (maxHealth == 2) {
            effectOne(player, false);
        }
    }

    private static void effectNine(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance9 = new StatusEffectInstance(StatusEffects.NIGHT_VISION, 999999999, 0, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance9);
        }
        else {
            player.removeStatusEffect(statusEffectInstance9.getEffectType());
        }
    }
    private static void effectEight(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance8 = new StatusEffectInstance(StatusEffects.ABSORPTION, 999999999, 0, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance8);
        }
        else {
            player.removeStatusEffect(statusEffectInstance8.getEffectType());
        }
    }
    private static void effectSeven(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance7 = new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 999999999, 0, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance7);
        }
        else {
            player.removeStatusEffect(statusEffectInstance7.getEffectType());
        }
    }
    private static void effectSix(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance6 = new StatusEffectInstance(StatusEffects.RESISTANCE, 999999999, 0, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance6);
        }
        else {
            player.removeStatusEffect(statusEffectInstance6.getEffectType());
        }
    }
    private static void effectFive(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance5 = new StatusEffectInstance(StatusEffects.SPEED, 999999999, 0, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance5);
        }
        else {
            player.removeStatusEffect(statusEffectInstance5.getEffectType());
        }
    }
    private static void effectFour(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance4 = new StatusEffectInstance(StatusEffects.HASTE, 999999999, 0, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance4);
        }
        else {
            player.removeStatusEffect(statusEffectInstance4.getEffectType());
        }
    }
    private static void effectThree(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance3 = new StatusEffectInstance(StatusEffects.SPEED, 999999999, 1, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance3);
        }
        else {
            player.removeStatusEffect(statusEffectInstance3.getEffectType());
        }
    }
    private static void effectTwo(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance21 = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 999999999, 0, false, false, true);
        StatusEffectInstance statusEffectInstance22 = new StatusEffectInstance(StatusEffects.HASTE, 999999999, 1, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance21);
            player.addStatusEffect(statusEffectInstance22);
        }
        else {
            player.removeStatusEffect(statusEffectInstance21.getEffectType());
            player.removeStatusEffect(statusEffectInstance22.getEffectType());
        }
    }
    private static void effectOne(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance11 = new StatusEffectInstance(StatusEffects.STRENGTH, 999999999, 2, false, false, true);
        StatusEffectInstance statusEffectInstance12 = new StatusEffectInstance(StatusEffects.SATURATION, 999999999, 0, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance11);
            player.addStatusEffect(statusEffectInstance12);
        }
        else {
            player.removeStatusEffect(statusEffectInstance11.getEffectType());
            player.removeStatusEffect(statusEffectInstance12.getEffectType());
        }
    }
}
