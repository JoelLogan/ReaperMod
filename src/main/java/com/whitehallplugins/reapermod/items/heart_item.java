package com.whitehallplugins.reapermod.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.attribute.EntityAttributes;
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
import java.util.Objects;

public class heart_item extends Item {

    public heart_item(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            double totalMaxHealth = 40;
            double currentMaxHealth = player.getAttributes().getBaseValue(EntityAttributes.GENERIC_MAX_HEALTH);
            if (currentMaxHealth != totalMaxHealth) {
                ItemStack itemInHand = player.getStackInHand(hand);
                player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_CHIME, 1.0F, 1.0F);
                player.sendMessage(Text.translatable("item.reapermod.heart.used").formatted(Formatting.GOLD));
                Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(currentMaxHealth + 2);
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
