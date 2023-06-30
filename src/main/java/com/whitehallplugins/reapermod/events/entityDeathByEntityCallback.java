package com.whitehallplugins.reapermod.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents.AfterKilledOtherEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.whitehallplugins.reapermod.playerManagement.playerHeartManager.*;
import static com.whitehallplugins.reapermod.playerManagement.playerPunishmentManager.kickPlayer;
import static com.whitehallplugins.reapermod.playerManagement.playerPunishmentManager.banPlayer;
import static com.whitehallplugins.reapermod.playerManagement.playerEffectManager.removeEffects;
import static com.whitehallplugins.reapermod.playerManagement.playerTeamManager.checkReaper;

public class entityDeathByEntityCallback implements AfterKilledOtherEntity {

    @Override
    public void afterKilledOtherEntity(ServerWorld world, Entity entity, LivingEntity killedEntity) {
        if (entity instanceof PlayerEntity player && killedEntity instanceof PlayerEntity killedPlayer){
            if (!player.isCreative() && !player.isSpectator()){
                int previousKillerMaxHealth = getMaxHearts(player);
                if (getMaxHearts(killedPlayer) <= 2 || previousKillerMaxHealth == 2){
                    if (previousKillerMaxHealth == 2){
                        addHeart(player, (getMaxHearts(killedPlayer)/2)-1);
                    }
                    removeHeart(killedPlayer, getMaxHearts(killedPlayer));
                    kickPlayer(killedPlayer);
                    banPlayer(killedPlayer);
                }
                else {
                    removeHeart(killedPlayer, 1);
                    if ((getMaxHearts(player)/2) == 20){
                        player.getInventory().insertStack(new ItemStack(Registry.ITEM.get(new Identifier("reapermod", "heart")), 1));
                    }
                    else {
                        addHeart(player, 1);
                    }
                }
                int maxHearts = getMaxHearts(player)/2;
                removeEffects(player, maxHearts);
                checkReaper(player, maxHearts);
            }
        }
    }
}
