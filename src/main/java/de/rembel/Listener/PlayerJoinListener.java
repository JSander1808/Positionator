package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Config playerConfig = new Config("plugins//Positionator//"+event.getPlayer().getUniqueId()+".conf");
        playerConfig.init();

        NormalConfig config = new NormalConfig("plugins//Positionator//config.yml");
        config.init();
        System.out.println(config.get("firstUse"));
        if(!config.existdata("firstUse")){
            config.set("firstUse","false");
            event.getPlayer().sendMessage(ChatColor.GREEN+"Thanks for using "+ChatColor.GOLD+"Positionator"+ChatColor.GREEN+".");
            event.getPlayer().sendMessage(ChatColor.BLUE+"/pos "+ChatColor.GREEN+"Open the Positionator Main Menu");
            event.getPlayer().sendMessage(ChatColor.BLUE+"/pos <positon name>"+ChatColor.GREEN+"Create a new Position");
        }else if(config.get("firstUse").equalsIgnoreCase("true")){
            event.getPlayer().sendMessage(ChatColor.GREEN+"Thanks for using "+ChatColor.GOLD+"Positionator"+ChatColor.GREEN+".");
            event.getPlayer().sendMessage(ChatColor.BLUE+"/pos "+ChatColor.GREEN+"Open the Positionator Main Menu");
            event.getPlayer().sendMessage(ChatColor.BLUE+"/pos <positon name>"+ChatColor.GREEN+"Create a new Position");
            config.set("firstUse","false");
        }
    }
}
