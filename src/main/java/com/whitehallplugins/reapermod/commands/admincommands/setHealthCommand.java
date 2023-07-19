package com.whitehallplugins.reapermod.commands.admincommands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;
import static com.whitehallplugins.reapermod.playerManagement.playerEffectManager.applyEffects;
import static com.whitehallplugins.reapermod.playerManagement.playerEffectManager.removeEffects;
import static com.whitehallplugins.reapermod.playerManagement.playerHeartManager.getMaxHearts;
import static com.whitehallplugins.reapermod.playerManagement.playerHeartManager.setMaxHearts;
import static com.whitehallplugins.reapermod.playerManagement.playerTeamManager.checkReaper;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public final class setHealthCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("sethealth")
            .requires(source -> source.hasPermissionLevel(4))
            .then(argument("player", string())
                    .suggests((context, builder) -> {
                        for (String player : context.getSource().getServer().getPlayerNames()){
                            builder.suggest(player);
                        }
                        return builder.buildFuture();
                    })
                    .executes(ctx -> noHealthError(ctx.getSource()))
            .then(argument("health", integer())
                    .executes(ctx -> setHealth(ctx.getSource(), getString(ctx, "player"), getInteger(ctx, "health"))))));
    }

    private static int noHealthError(ServerCommandSource ctx){
        ctx.sendMessage(Text.translatable("reapermod.sethealth.nohealth").formatted(Formatting.RED));
        return 1;
    }

    private static int setHealth(ServerCommandSource ctx, String enteredPlayer, int amount){
        if (amount > 20){
            ctx.sendMessage(Text.translatable("reapermod.sethealth.bignumber").formatted(Formatting.RED));
            amount = 20;
        }
        else if (amount < 1){
            ctx.sendMessage(Text.translatable("reapermod.sethealth.smallnumber").formatted(Formatting.RED));
            amount = 1;
        }
        try {
            PlayerEntity player = ctx.getServer().getPlayerManager().getPlayer(enteredPlayer);
            assert player != null;
            int oldMaxHealth = getMaxHearts(player)/2;
            if (oldMaxHealth != amount) {
                setMaxHearts(player, amount);
                player.damage(DamageSource.FALL, 0.1f);
                player.heal(0.1f);
                if (amount > oldMaxHealth) {
                    removeEffects(player, amount);
                } else {
                    applyEffects(player, amount);
                }
                checkReaper(player, amount);
                ctx.sendMessage(Text.translatable("reapermod.sethealth.message", enteredPlayer, amount).formatted(Formatting.GREEN));
            }
            else {
                ctx.sendMessage(Text.translatable("reapermod.sethealth.samehealth", enteredPlayer, amount).formatted(Formatting.RED));
            }
        }
        catch(NullPointerException exception){
            ctx.sendMessage(Text.translatable("reapermod.sethealth.errormessage", enteredPlayer).formatted(Formatting.RED));
        }
        return 1;
    }
}
