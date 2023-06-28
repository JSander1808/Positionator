package de.rembel.General;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class General {

    public static HashMap<String, String> BossBarPosition = new HashMap<String, String>();
    public static HashMap<String, PositionFilter> PublicFilter = new HashMap<String, PositionFilter>();
    public static HashMap<String, PositionFilter> PrivateFilter = new HashMap<String, PositionFilter>();

    public static ArrayList<String> getRegisteredPlayers(){
        ArrayList<String> playerList = new ArrayList<String>();

        for(Player tempPlayer : Bukkit.getOnlinePlayers()){
            if(!playerList.contains(tempPlayer.getName())) playerList.add(tempPlayer.getName());
        }
        for(OfflinePlayer player : Bukkit.getOfflinePlayers()){
            if(!playerList.contains(player.getName())) playerList.add(player.getName());
        }
        return playerList;
    }
}
