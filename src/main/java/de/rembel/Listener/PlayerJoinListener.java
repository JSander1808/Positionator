package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.General.UpdateChecker;
import de.rembel.Main.PositionatorMain;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Content;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Config playerConfig = new Config("plugins//Positionator//"+ event.getPlayer().getUniqueId().toString()+".conf");
        playerConfig.init();

        NormalConfig config = new NormalConfig("plugins//Positionator//config.yml");
        config.init();
        if(!config.existdata("firstUse")){
            config.set("firstUse","false");
            event.getPlayer().sendMessage(ChatColor.GREEN+"Thanks for using "+ChatColor.GOLD+"Positionator"+ChatColor.GREEN+".");
            event.getPlayer().sendMessage(ChatColor.BLUE+"/pos "+ChatColor.GREEN+"Open the Positionator Main Menu");
            event.getPlayer().sendMessage(ChatColor.BLUE+"/pos <positon name> "+ChatColor.GREEN+"Create a new Position");
        }else if(config.get("firstUse").equalsIgnoreCase("true")){
            event.getPlayer().sendMessage(ChatColor.GREEN+"Thanks for using "+ChatColor.GOLD+"Positionator"+ChatColor.GREEN+".");
            event.getPlayer().sendMessage(ChatColor.BLUE+"/pos "+ChatColor.GREEN+"Open the Positionator Main Menu");
            event.getPlayer().sendMessage(ChatColor.BLUE+"/pos <positon name> "+ChatColor.GREEN+"Create a new Position");
            config.set("firstUse","false");
        }

        if(event.getPlayer().isOp()){
            if(config.getBoolean("sendUpdateMessages")){
                new UpdateChecker(PositionatorMain.getJavaPlugin(), 110375).getVersion(version -> {
                    if (!PositionatorMain.plugin.getDescription().getVersion().equals(version)) {
                        event.getPlayer().sendMessage(ChatColor.GREEN+"Positionator released a Update - "+ChatColor.GOLD+"V"+version);
                        event.getPlayer().sendMessage(ChatColor.GREEN+"Check out what's new.");
                    }
                });
            }
        }
    }
}
