package com.whitehallplugins.reapermod.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

import static com.whitehallplugins.reapermod.playerManagement.playerEffectManager.removeEffects;
import static com.whitehallplugins.reapermod.playerManagement.playerHeartManager.addHeart;
import static com.whitehallplugins.reapermod.playerManagement.playerHeartManager.getMaxHearts;
import static com.whitehallplugins.reapermod.playerManagement.playerTeamManager.checkReaper;

public class heart_item extends Item {

    public heart_item(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            int totalMaxHealth = 40;
            int currentMaxHealth = getMaxHearts(player);
            if (currentMaxHealth != totalMaxHealth) {
                ItemStack itemInHand = player.getStackInHand(hand);
                player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_CHIME, 1.0F, 1.0F);
                player.sendMessage(Text.translatable("item.reapermod.heart.used").formatted(Formatting.GOLD));
                addHeart(player, 1);
                currentMaxHealth = getMaxHearts(player)/2;
                removeEffects(player, currentMaxHealth);
                checkReaper(player, currentMaxHealth);
                itemInHand.decrement(1);
                return TypedActionResult.success(itemInHand);
            } else {
                player.sendMessage(Text.translatable("item.reapermod.heart.maxhearts").formatted(Formatting.RED));
            }
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }


    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.reapermod.heart.tooltip1").formatted(Formatting.DARK_GRAY));
    }
}
