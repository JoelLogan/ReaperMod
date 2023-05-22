package com.whitehallplugins.reapermod.items;

import com.whitehallplugins.reapermod.punishments.playerPunishmentManager;
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

import static com.whitehallplugins.reapermod.punishments.playerPunishmentManager.getBannedPlayers;

public class revive_crystal_item extends Item {

    public revive_crystal_item(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            player.getStackInHand(hand).decrement(1);
            for (String name : getBannedPlayers()){
                // Add to gui and send to player
            }

            /**
             * Check out making a GUI. A gui wouldn't have to have the player run the command
             */

            player.sendMessage(Text.literal((String.valueOf(getBannedPlayers().size()))));
            player.sendMessage(Text.literal(playerPunishmentManager.getBannedPlayers().toString()));
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.reapermod.revive_crystal.tooltip1").formatted(Formatting.DARK_GRAY));
        tooltip.add(Text.translatable("item.reapermod.revive_crystal.tooltip2").formatted(Formatting.RED).formatted(Formatting.BOLD));
        tooltip.add(Text.translatable("item.reapermod.revive_crystal.tooltip3").formatted(Formatting.DARK_GRAY));
        tooltip.add(Text.translatable("item.reapermod.revive_crystal.tooltip4").formatted(Formatting.DARK_GRAY));

    }
}
