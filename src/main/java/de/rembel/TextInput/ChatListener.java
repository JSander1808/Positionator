package de.rembel.TextInput;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.util.Consumer;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if(TextInputService.textInputActiveSession.contains(player.getUniqueId())){
            event.setCancelled(true);
            TextInputService.textInputSessionConntainer.get(player.getUniqueId()).setMessage(event.getMessage());
            TextInputService.textInputSessionConntainer.get(player.getUniqueId()).getCommand().execute();
            TextInputService.textInputSessionConntainer.remove(player.getUniqueId());
            TextInputService.textInputActiveSession.remove(player.getUniqueId());
        }
    }
}
