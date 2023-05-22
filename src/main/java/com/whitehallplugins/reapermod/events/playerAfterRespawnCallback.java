package com.whitehallplugins.reapermod.events;


import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents.AfterRespawn;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class playerAfterRespawnCallback implements AfterRespawn {


    @Override
    public void afterRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        newPlayer.setHealth(newPlayer.getMaxHealth());
        double currentMaxHealth = newPlayer.getAttributes().getBaseValue(EntityAttributes.GENERIC_MAX_HEALTH) / 2;
            if (currentMaxHealth == 9) {
                effectNine(newPlayer);
            } else if (currentMaxHealth == 8) {
                effectNine(newPlayer);
                effectEight(newPlayer);
            } else if (currentMaxHealth == 7) {
                effectNine(newPlayer);
                effectEight(newPlayer);
                effectSeven(newPlayer);
            } else if (currentMaxHealth == 6) {
                effectNine(newPlayer);
                effectEight(newPlayer);
                effectSeven(newPlayer);
                effectSix(newPlayer);
            } else if (currentMaxHealth == 5) {
                effectNine(newPlayer);
                effectEight(newPlayer);
                effectSeven(newPlayer);
                effectSix(newPlayer);
                effectFive(newPlayer);
            } else if (currentMaxHealth == 4) {
                effectNine(newPlayer);
                effectEight(newPlayer);
                effectSeven(newPlayer);
                effectSix(newPlayer);
                effectFive(newPlayer);
                effectFour(newPlayer);
            } else if (currentMaxHealth == 3) {
                effectNine(newPlayer);
                effectEight(newPlayer);
                effectSeven(newPlayer);
                effectSix(newPlayer);
                effectFive(newPlayer);
                effectFour(newPlayer);
                effectThree(newPlayer);
            } else if (currentMaxHealth == 2) {
                effectNine(newPlayer);
                effectEight(newPlayer);
                effectSeven(newPlayer);
                effectSix(newPlayer);
                effectFive(newPlayer);
                effectFour(newPlayer);
                effectThree(newPlayer);
                effectTwo(newPlayer);
            } else if (currentMaxHealth == 1) {
                effectNine(newPlayer);
                effectEight(newPlayer);
                effectSeven(newPlayer);
                effectSix(newPlayer);
                effectFive(newPlayer);
                effectFour(newPlayer);
                effectThree(newPlayer);
                effectTwo(newPlayer);
                effectOne(newPlayer);
            }
        }

        /*potionEffects.put(9, "nv"); // Night Vision
        potionEffects.put(8, "ab"); // Absorption
        potionEffects.put(7, "dg"); // Dolphin's Grace
        potionEffects.put(6, "rs"); // Resistance
        potionEffects.put(5, "sp1"); // Speed 1
        potionEffects.put(4, "hs1"); // Haste 1
        potionEffects.put(3, "sp2"); // Speed 2
        potionEffects.put(2, "fr+hs2"); // Fire Resistance + Haste 2
        potionEffects.put(1, "st3+st"); // Strength 3 + Saturation
         */


        private static void effectNine(PlayerEntity player){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 999999999, 0, false, false, true));
        }
        private static void effectEight(PlayerEntity player){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 999999999, 0, false, false, true));
        }
        private static void effectSeven(PlayerEntity player){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 999999999, 0, false, false, true));
        }
        private static void effectSix(PlayerEntity player){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 999999999, 0, false, false, true));
        }
        private static void effectFive(PlayerEntity player){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 999999999, 0, false, false, true));
        }
        private static void effectFour(PlayerEntity player){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 999999999, 0, false, false, true));
        }
        private static void effectThree(PlayerEntity player){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 999999999, 1, false, false, true));// 2
        }
        private static void effectTwo(PlayerEntity player){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 999999999, 0, false, false, true));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 999999999, 1, false, false, true)); // 2
        }
        private static void effectOne(PlayerEntity player){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 999999999, 2, false, false, true)); // 3
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 999999999, 0, false, false, true));
        }
    }
