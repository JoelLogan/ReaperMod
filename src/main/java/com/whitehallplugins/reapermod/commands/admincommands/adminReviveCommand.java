package com.whitehallplugins.reapermod.commands.admincommands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Objects;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;
import static com.whitehallplugins.reapermod.playerManagement.playerPunishmentManager.getBannedPlayers;
import static com.whitehallplugins.reapermod.playerManagement.playerPunishmentManager.unbanPlayer;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class adminReviveCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("adminrevive")
                .requires(source -> source.hasPermissionLevel(4))
                .then(argument("player", string())
                        .suggests((context, builder) -> {
                            for (String name : getBannedPlayers()){
                                builder.suggest(name);
                            }
                            return builder.buildFuture();
                        })
                        .executes(ctx -> revivePlayer(ctx.getSource(), getString(ctx, "player"))))
                .executes(ctx -> noPlayerError(ctx.getSource())));
    }

    private static int revivePlayer(ServerCommandSource ctx, String player){
        if (getBannedPlayers().contains(player)) {
            unbanPlayer(Objects.requireNonNull(ctx.getPlayer()), player);
            ctx.sendMessage(Text.translatable("item.reapermod.revive.success").formatted(Formatting.GREEN));
        } else {
            ctx.sendMessage(Text.translatable("item.reapermod.revive.notbanned").formatted(Formatting.RED));
        }
        return 1;
    }

    private static int noPlayerError(ServerCommandSource ctx) {
        ctx.sendMessage(Text.translatable("item.reapermod.revive.noplayer").formatted(Formatting.RED));
        return 1;
    }
}
