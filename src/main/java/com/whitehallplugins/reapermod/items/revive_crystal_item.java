package com.whitehallplugins.reapermod.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class revive_crystal_item extends Item {

    public revive_crystal_item(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            player.sendMessage(Text.translatable("item.reapermod.revive_crystal.usecommand1").formatted(Formatting.AQUA));
            ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/revive ");
            HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Click to autofill...").formatted(Formatting.GOLD));
            player.sendMessage(Text.translatable("item.reapermod.revive_crystal.usecommand2").formatted(Formatting.AQUA).setStyle(Style.EMPTY.withClickEvent(clickEvent).withHoverEvent(hoverEvent)));
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.reapermod.revive_crystal.tooltip1").formatted(Formatting.DARK_GRAY));
    }
}
