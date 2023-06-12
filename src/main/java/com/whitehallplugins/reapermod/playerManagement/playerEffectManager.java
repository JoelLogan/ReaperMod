package com.whitehallplugins.reapermod.playerManagement;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class playerEffectManager {

    public static void applyEffects(PlayerEntity player, int maxHealth){
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
        if (add) {
            player.addStatusEffect(statusEffectInstance21);
            player.addStatusEffect(statusEffectInstance22);
        }
        else {
            if (player.getActiveStatusEffects().containsKey(statusEffectInstance21.getEffectType())) {
                if (player.getActiveStatusEffects().get(statusEffectInstance21.getEffectType()).getDuration() > 18000) {
                    player.removeStatusEffect(statusEffectInstance21.getEffectType());
                }
            }
            if (player.getActiveStatusEffects().containsKey(statusEffectInstance22.getEffectType())) {
                if (player.getActiveStatusEffects().get(statusEffectInstance22.getEffectType()).getDuration() > 18000) {
                    player.removeStatusEffect(statusEffectInstance22.getEffectType());
                }
            }
        }
    }
    private static void effectOne(PlayerEntity player, boolean add){
        StatusEffectInstance statusEffectInstance11 = new StatusEffectInstance(StatusEffects.STRENGTH, Integer.MAX_VALUE, 2, false, false, true);
        StatusEffectInstance statusEffectInstance12 = new StatusEffectInstance(StatusEffects.SATURATION, Integer.MAX_VALUE, 0, false, false, true);
        if (add) {
            player.addStatusEffect(statusEffectInstance11);
            player.addStatusEffect(statusEffectInstance12);
        }
        else {
            if (player.getActiveStatusEffects().containsKey(statusEffectInstance11.getEffectType())) {
                if (player.getActiveStatusEffects().get(statusEffectInstance11.getEffectType()).getDuration() > 18000) {
                    player.removeStatusEffect(statusEffectInstance11.getEffectType());
                }
            }
            if (player.getActiveStatusEffects().containsKey(statusEffectInstance12.getEffectType())) {
                if (player.getActiveStatusEffects().get(statusEffectInstance12.getEffectType()).getDuration() > 18000) {
                    player.removeStatusEffect(statusEffectInstance12.getEffectType());
                }
            }
        }
    }
}
