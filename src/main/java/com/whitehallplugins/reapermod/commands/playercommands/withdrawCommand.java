package com.whitehallplugins.reapermod.commands.playercommands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.whitehallplugins.reapermod.playerManagement.playerHeartManager.getMaxHearts;
import static com.whitehallplugins.reapermod.playerManagement.playerHeartManager.removeHeart;
import static com.whitehallplugins.reapermod.playerManagement.playerEffectManager.applyEffects;
import static com.whitehallplugins.reapermod.playerManagement.playerTeamManager.checkReaper;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;

public final class withdrawCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("withdraw")
            .then(argument("amount", integer())
                .executes(ctx -> giveHeart(ctx.getSource(), getInteger(ctx, "amount"))))
            .executes(ctx -> giveHeart(ctx.getSource(), 1)));
    }

    private static int giveHeart(ServerCommandSource ctx, int amount) throws CommandSyntaxException {
        if (ctx.isExecutedByPlayer()) {
            final PlayerEntity self = ctx.getPlayer();
            assert self != null;
            int maxHealth = getMaxHearts(self);
            if (maxHealth > (amount * 2) + 4) {
                if (!self.getInventory().insertStack(new ItemStack(Registry.ITEM.get(new Identifier("reapermod", "heart")), amount))) {
                    throw new SimpleCommandExceptionType(Text.translatable("inventory.isfull")).create();
                }
                removeHeart(self, amount);
                self.damage(DamageSource.FALL, 0.1f);
                self.heal(0.1f);
                maxHealth = getMaxHearts(self)/2;
                applyEffects(self, maxHealth);
                checkReaper(self, maxHealth);

            } else {
                throw new SimpleCommandExceptionType(Text.translatable("withdraw.toomanyhearts")).create();
            }
        }
        return 1;
    }
}
