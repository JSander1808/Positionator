package de.rembel.Listener;

import de.rembel.Config.NormalConfig;
import de.rembel.General.Command;
import de.rembel.Language.LanguageManager;
import de.rembel.Menus.Confirmation;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class ConfirmationListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        LanguageManager language = new LanguageManager((Player) event.getWhoClicked());
        Player player = (Player) event.getWhoClicked();
        if(event.getView().getTitle().equalsIgnoreCase(language.transalte(97))){
            if(event.getCurrentItem() == null){
                event.setCancelled(true);
                return;
            }
            switch(event.getCurrentItem().getType()){
                case GREEN_WOOL:
                    ((Command) Confirmation.Confirm.get(player.getUniqueId().toString())).execute();
                    Confirmation.removeConfirmation(player);
                    break;
                case RED_WOOL:
                    ((Command) Confirmation.Cancel.get(player.getUniqueId().toString())).execute();
                    Confirmation.removeConfirmation(player);
                    break;
                default:
                    break;
            }
            event.setCancelled(true);
            NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
            if(normalConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, Float.valueOf(normalConfig.get("clickSoundPitch")));
        }
    }

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        LanguageManager language = new LanguageManager(player);
        if(event.getView().getTitle().equalsIgnoreCase(language.transalte(97))){
            Confirmation.removeConfirmation(player);
        }
    }
}
