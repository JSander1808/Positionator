package de.rembel.Listener;

import de.rembel.Config.NormalConfig;
import de.rembel.General.Command;
import de.rembel.Language.LanguageManager;
import de.rembel.Menus.AdminSettingsMenu;
import de.rembel.Menus.Confirmation;
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
        LanguageManager language = new LanguageManager((Player) event.getWhoClicked());
        if(event.getView().getTitle().equalsIgnoreCase(language.transalte(83))){
            if(event.getCurrentItem() == null){
                event.setCancelled(true);
                return;
            }
            Player player = (Player) event.getWhoClicked();
            NormalConfig config = new NormalConfig("plugins//Positionator//config.yml");
            switch(event.getCurrentItem().getType()){
                default:
                    break;
                case RESPAWN_ANCHOR:
                    if(player.isOp()){
                        if(config.getBoolean("createBackUpByServerRestart")){
                            Command confirm = () -> {
                                config.set("createBackUpByServerRestart", "false");
                                new AdminSettingsMenu(player);
                            };
                            Command cancel = () -> {
                                new AdminSettingsMenu(player);
                            };
                            new Confirmation(player, confirm, cancel);
                        }else{
                            config.set("createBackUpByServerRestart", "true");
                            new AdminSettingsMenu(player);
                        }
                    }else{
                        player.sendMessage(language.transalteDefaultEnglish(100));
                    }
                    break;
                case COMPARATOR:
                    if(player.isOp()){
                        if(config.getBoolean("enableEditPositionsFromOtherPlayer")){
                            config.set("enableEditPositionsFromOtherPlayer","false");
                        }else{
                            config.set("enableEditPositionsFromOtherPlayer","true");
                        }
                        new AdminSettingsMenu(player);
                    }else{
                        player.sendMessage(language.transalteDefaultEnglish(100));
                    }
                    break;
                case BELL:
                    if(player.isOp()){
                        if(config.getBoolean("sendUpdateMessages")){
                            Command confirm = () -> {
                                config.set("sendUpdateMessages","false");
                                new AdminSettingsMenu(player);
                            };
                            Command cancel = () -> {
                                new AdminSettingsMenu(player);
                            };
                            new Confirmation(player, confirm, cancel);
                        }else{
                            config.set("sendUpdateMessages","true");
                            new AdminSettingsMenu(player);
                        }
                    }else{
                        player.sendMessage(language.transalteDefaultEnglish(100));
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
                        player.sendMessage(language.transalteDefaultEnglish(100));
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
