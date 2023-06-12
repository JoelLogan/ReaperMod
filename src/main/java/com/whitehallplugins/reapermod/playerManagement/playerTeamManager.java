package com.whitehallplugins.reapermod.playerManagement;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Objects;

public class playerTeamManager {
    private static ServerScoreboard scoreboard;

    public static void createTeam(MinecraftServer server){
        scoreboard = server.getScoreboard();
        if (!scoreboard.getTeamNames().contains("Reaper")) {
            scoreboard.addTeam("Reaper");
            Objects.requireNonNull(scoreboard.getTeam("Reaper")).setColor(Formatting.DARK_PURPLE);
        }
    }

    public static void checkReaper(PlayerEntity player, int health) {
        if (health == 1){
            if (!Objects.requireNonNull(scoreboard.getTeam("Reaper")).getPlayerList().contains(player.getName().getString())) {
                scoreboard.addPlayerToTeam(player.getName().getString(), scoreboard.getTeam("Reaper"));
                for (PlayerEntity players : Objects.requireNonNull(player.getServer()).getPlayerManager().getPlayerList()) {
                    players.sendMessage(player.getName().copy().append(Text.translatable("reapermod.announce.1").append(Text.translatable("reapermod.announce.2").formatted(Formatting.DARK_PURPLE))));
                }
            }
        }
        else {
            if (Objects.requireNonNull(scoreboard.getTeam("Reaper")).getPlayerList().contains(player.getName().getString())){
                scoreboard.removePlayerFromTeam(player.getName().getString(), scoreboard.getTeam("Reaper"));
            }
        }
    }

}
