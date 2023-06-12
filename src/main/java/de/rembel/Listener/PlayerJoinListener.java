package de.rembel.Listener;

import de.rembel.Config.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Config config = new Config("plugins//Positionator//"+event.getPlayer().getUniqueId()+".conf");
        config.init();
    }
}
