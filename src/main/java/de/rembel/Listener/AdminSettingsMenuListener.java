package de.rembel.Listener;

import de.rembel.Config.NormalConfig;
import de.rembel.Menus.AdminSettingsMenu;
import de.rembel.Menus.StartMenu;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AdminSettingsMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.RED+"Admin Settings")){
            if(event.getCurrentItem() == null){
                event.setCancelled(true);
                return;
            }
            Player player = (Player) event.getWhoClicked();
            NormalConfig config = new NormalConfig("plugins//Positionator//config.yml");
            switch(event.getCurrentItem().getType()){
                default:
                    break;
                case COMPARATOR:
                    if(player.isOp()){
                        if(config.getBoolean("enableDeletePositionsFromOtherPlayer")){
                            config.set("enableDeletePositionsFromOtherPlayer","false");
                        }else{
                            config.set("enableDeletePositionsFromOtherPlayer","true");
                        }
                        new AdminSettingsMenu(player);
                    }else{
                        player.sendMessage(ChatColor.RED+"You are not a Operator");
                    }
                    break;
                case BELL:
                    if(player.isOp()){
                        if(config.getBoolean("sendUpdateMessages")){
                            config.set("sendUpdateMessages","false");
                        }else{
                            config.set("sendUpdateMessages","true");
                        }
                        new AdminSettingsMenu(player);
                    }else{
                        player.sendMessage(ChatColor.RED+"You are not a Operator");
                    }
                    break;
                case ENDER_PEARL:
                    if(player.isOp()){
                        if(!config.getBoolean("allowOpToTeleport") && !config.getBoolean("allowPlayerToTeleport")){
                            config.set("allowOpToTeleport","true");
                            config.set("allowPlayerToTeleport","false");
                        }else if(config.getBoolean("allowOpToTeleport") && !config.getBoolean("allowPlayerToTeleport")){
                            config.set("allowOpToTeleport","true");
                            config.set("allowPlayerToTeleport","true");
                        }else if(config.getBoolean("allowOpToTeleport") && config.getBoolean("allowPlayerToTeleport")){
                            config.set("allowOpToTeleport","false");
                            config.set("allowPlayerToTeleport","false");
                        }
                        new AdminSettingsMenu(player);
                    }else{
                        player.sendMessage(ChatColor.RED+"You are not a Operator");
                    }
                    break;
                case SPRUCE_DOOR:
                    new StartMenu(player);
                    break;
                case BARRIER:
                    player.closeInventory();
                    break;
            }
            event.setCancelled(true);
            NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
            if(normalConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
        }
    }
}
