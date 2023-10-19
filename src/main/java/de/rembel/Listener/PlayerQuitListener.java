package de.rembel.Listener;

import de.rembel.CBossbar.CBossbar;
import de.rembel.Config.NormalConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        NormalConfig playerConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
        if(CBossbar.getByPlayer(player)!=null) playerConfig.set("compassSave", CBossbar.getByPlayer(player).toString()); else playerConfig.set("compassSave","");
    }
}
