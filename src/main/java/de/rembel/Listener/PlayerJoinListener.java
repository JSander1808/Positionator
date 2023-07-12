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
        Config playerData = new Config("plugins//Positionator//Data//User//"+ event.getPlayer().getUniqueId().toString()+"//data.conf");
        playerData.init();

        NormalConfig playerConfig = new NormalConfig("plugins//Positionator//Data//User//"+event.getPlayer().getUniqueId().toString()+"//config.yml");
        playerConfig.init();
        if(!playerConfig.existdata("showDeathPositionInList")) playerConfig.set("showDeathPositionInList","true");
        if(!playerConfig.existdata("setDeathPositionInBossbar")) playerConfig.set("setDeathPositionInBossbar","true");
        if(!playerConfig.existdata("enableFilter")) playerConfig.set("enableFilter","true");
        if(!playerConfig.existdata("enableMenuClickSound")) playerConfig.set("enableMenuClickSound","true");

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
                        TextComponent pluginWebsite = new TextComponent(ChatColor.GOLD+"Check out what's new!");
                        pluginWebsite.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://www.spigotmc.org/resources/positionator.110375/"));
                        pluginWebsite.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(ChatColor.UNDERLINE+""+ChatColor.GOLD+"Click me")));
                        event.getPlayer().spigot().sendMessage(pluginWebsite);
                    }
                });
            }
        }
    }
}
