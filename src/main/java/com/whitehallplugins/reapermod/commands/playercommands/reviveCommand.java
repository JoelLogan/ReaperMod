package com.whitehallplugins.reapermod.commands.playercommands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;
import static com.whitehallplugins.reapermod.playerManagement.playerPunishmentManager.getBannedPlayers;
import static com.whitehallplugins.reapermod.playerManagement.playerPunishmentManager.unbanPlayer;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public final class reviveCommand {

    private static final List<PlayerEntity> cooldown = new ArrayList<>();
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("revive")
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

    private static int revivePlayer(ServerCommandSource ctx, String player) {
        if (ctx.isExecutedByPlayer()) {
            final PlayerEntity self = ctx.getPlayer();
            assert self != null;
            if (!cooldown.contains(self)) {
                cooldown.add(self);
                if (self.getStackInHand(self.preferredHand).getItem().equals(Registry.ITEM.get(new Identifier("reapermod", "revive_crystal")))) {
                    if (getBannedPlayers().contains(player)) {
                        self.getStackInHand(self.preferredHand).decrement(1);
                        unbanPlayer(self, player);
                        self.sendMessage(Text.translatable("item.reapermod.revive.success").formatted(Formatting.GREEN));
                    } else {
                        self.sendMessage(Text.translatable("item.reapermod.revive.notbanned").formatted(Formatting.RED));
                    }
                } else {
                    self.sendMessage(Text.translatable("item.reapermod.revive.noitem").formatted(Formatting.RED));
                }
                removeCooldown(self);
            }
            else {
                self.sendMessage(Text.translatable("item.reapermod.revive.oncooldown").formatted(Formatting.RED));
            }
        }
        return 1;
    }

    private static void removeCooldown(PlayerEntity player) {
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                cooldown.remove(player);
            } catch (InterruptedException ignored) {}
        }, executor);
    }

    private static int noPlayerError(ServerCommandSource ctx) {
        if (ctx.isExecutedByPlayer()) {
            final PlayerEntity self = ctx.getPlayer();
            assert self != null;
            if (self.getStackInHand(self.preferredHand).getItem().equals(Registry.ITEM.get(new Identifier("reapermod", "revive_crystal")))){
                self.sendMessage(Text.translatable("item.reapermod.revive.noplayer").formatted(Formatting.RED));
            }
            else {
                self.sendMessage(Text.translatable("item.reapermod.revive.noitem").formatted(Formatting.RED));
            }
        }
        return 1;
    }
}
