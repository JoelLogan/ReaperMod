package com.whitehallplugins.reapermod.commands.admincommands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Objects;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;
import static com.whitehallplugins.reapermod.playerManagement.playerHeartManager.getMaxHearts;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class getHealthCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("gethealth")
                .requires(source -> source.hasPermissionLevel(4))
                .then(argument("player", string())
                        .suggests((context, builder) -> {
                            for (String player : context.getSource().getServer().getPlayerNames()){
                                builder.suggest(player);
                            }
                            return builder.buildFuture();
                        })
                        .executes(ctx -> sendHealth(ctx.getSource(), getString(ctx, "player")))));
    }

    private static int sendHealth(ServerCommandSource ctx, String player){
        try {
            ctx.sendMessage(Text.translatable("reapermod.gethealth.message", player, (getMaxHearts(Objects.requireNonNull(ctx.getServer().getPlayerManager().getPlayer(player))) / 2)).formatted(Formatting.GREEN));
        }
        catch (NullPointerException exception){
            ctx.sendMessage(Text.translatable("reapermod.gethealth.errormessage", player).formatted(Formatting.RED));
        }
        return 1;
    }
}
