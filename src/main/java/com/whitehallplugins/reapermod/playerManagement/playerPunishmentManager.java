package com.whitehallplugins.reapermod.playerManagement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.PlayerManager;
import net.minecraft.text.Text;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class playerPunishmentManager {

    public static void kickPlayer(PlayerEntity player, String reason){
        Objects.requireNonNull(Objects.requireNonNull(player.getServer()).getPlayerManager().getPlayer(player.getUuid())).networkHandler.disconnect(Text.literal(reason));
    }

    public static void banPlayer(PlayerEntity player, String reason){
        Objects.requireNonNull(player.getServer()).getCommandManager().executeWithPrefix(player.getServer().getCommandSource(), "/ban " + player.getName().getString() + " " + reason);
    }

    public static void unbanPlayer(PlayerEntity player, String bannedPlayer){
        Objects.requireNonNull(player.getServer()).getCommandManager().executeWithPrefix(player.getServer().getCommandSource(), "/pardon " + bannedPlayer);
    }

    public static List<String> getBannedPlayers(){
        List<String> players = new ArrayList<>();
        try {
            JsonArray jsonArray = JsonParser.parseReader(new FileReader(PlayerManager.BANNED_PLAYERS_FILE)).getAsJsonArray();
            for (Object o : jsonArray) {
                JsonObject playerEntry = (JsonObject) o;
                String reason = playerEntry.get("reason").getAsString();
                if (reason.equals(Text.translatable("reapermod.banreason").getString())){
                    String name = playerEntry.get("name").getAsString();
                    players.add(name);
                }
            }
        }
        catch (Exception e){
            System.out.println("Get Banned Players Error: " + e.getMessage());
        }
        return players;
    }

}
