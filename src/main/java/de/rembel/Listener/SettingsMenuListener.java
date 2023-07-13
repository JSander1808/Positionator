package de.rembel.Listener;

import de.rembel.Config.NormalConfig;
import de.rembel.Menus.PrivateSettingsMenu;
import de.rembel.Menus.StartMenu;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SettingsMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD+"Settings")){
            if(event.getCurrentItem() == null){
                event.setCancelled(true);
                return;
            }
            Player player = (Player) event.getWhoClicked();
            NormalConfig config = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
            switch(event.getCurrentItem().getType()){
                case OBSERVER:
                    if(config.getBoolean("showDeathPositionInList")){
                        config.set("showDeathPositionInList","false");
                    }else{
                        config.set("showDeathPositionInList","true");
                    }
                    new PrivateSettingsMenu(player);
                    break;
                case NETHER_STAR:
                    if(config.getBoolean("setDeathPositionInBossbar")){
                        config.set("setDeathPositionInBossbar","false");
                    }else{
                        config.set("setDeathPositionInBossbar","true");
                    }
                    new PrivateSettingsMenu(player);
                    break;
                case PAPER:
                    if(config.getBoolean("enableFilter")){
                        config.set("enableFilter","false");
                    }else{
                        config.set("enableFilter","true");
                    }
                    new PrivateSettingsMenu(player);
                    break;
                case EXPERIENCE_BOTTLE:
                    if(config.getBoolean("enableMenuClickSound")){
                        config.set("enableMenuClickSound","false");
                    }else{
                        config.set("enableMenuClickSound","true");
                    }
                    new PrivateSettingsMenu(player);
                    break;
                case SPRUCE_DOOR:
                    new StartMenu(player);
                    break;
                case BARRIER:
                    player.closeInventory();
                    break;
                default:
                    break;
            }
            event.setCancelled(true);
            NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
            if(normalConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
        }
    }

}
