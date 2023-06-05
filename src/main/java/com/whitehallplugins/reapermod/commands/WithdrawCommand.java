package com.whitehallplugins.reapermod.commands;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.whitehallplugins.reapermod.playerManagement.playerEffectManager;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Objects;

public final class WithdrawCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("withdraw")
            .then(argument("amount", integer())
                .executes(ctx -> giveHeart(ctx.getSource(), getInteger(ctx, "amount"))))
            .executes(ctx -> giveHeart(ctx.getSource(), 1)));
    }

    private static int giveHeart(ServerCommandSource ctx, int amount) throws CommandSyntaxException {
        if (ctx.getPlayer() != null) {
            final PlayerEntity self = ctx.getPlayer();
            assert self != null;
            double maxHealth = (self.getAttributes().getBaseValue(EntityAttributes.GENERIC_MAX_HEALTH));
            if (maxHealth > (amount * 2)) {
                if (!self.getInventory().insertStack(new ItemStack(Registry.ITEM.get(new Identifier("reapermod", "heart")), amount))) {
                    throw new SimpleCommandExceptionType(Text.translatable("inventory.isfull")).create();
                }
                Objects.requireNonNull(self.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(maxHealth - (amount * 2));
                self.damage(DamageSource.FALL, 0.1f);
                self.heal(0.1f);
                playerEffectManager.applyEffects(self, self.getAttributes().getBaseValue(EntityAttributes.GENERIC_MAX_HEALTH)/2);
            } else {
                throw new SimpleCommandExceptionType(Text.translatable("withdraw.notenoughhearts")).create();
            }
        }
        return 1;
    }
}
