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

    public static void kickPlayer(PlayerEntity player){
        Objects.requireNonNull(Objects.requireNonNull(player.getServer()).getPlayerManager().getPlayer(player.getUuid())).networkHandler.disconnect(Text.literal("You ran out of lives"));
    }

    public static void banPlayer(PlayerEntity player){
        Objects.requireNonNull(player.getServer()).getCommandManager().executeWithPrefix(player.getServer().getCommandSource(), "/ban " + player.getName().getString() + " You ran out of lives");
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
                if (reason.equals("You ran out of lives")){
                    String name = playerEntry.get("name").getAsString();
                    players.add(name);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return players;
    }

}
