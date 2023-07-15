package de.rembel.Listener;

import de.rembel.Config.NormalConfig;
import de.rembel.General.BackUpManager;
import de.rembel.General.Command;
import de.rembel.Menus.BackUpMenu;
import de.rembel.Menus.Confirmation;
import de.rembel.Menus.PrivateMenu;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.File;

public class BackUpMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getView().getTitle().split(" ").length==6){
            if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD+"BackUps - Page "+event.getView().getTitle().split(" ")[3]+" / "+((new File("plugins//Positionator_BackUp//").listFiles().length/(9*5))+1))){
                if(event.getCurrentItem() == null){
                    event.setCancelled(true);
                    return;
                }
                int page = Integer.valueOf(event.getView().getTitle().split(" ")[3]);
                int pagemax = Integer.valueOf(event.getView().getTitle().split(" ")[5]);
                Player player = (Player) event.getWhoClicked();
                switch(event.getCurrentItem().getType()){
                    case BARRIER:
                        player.closeInventory();
                        break;
                    case SPRUCE_SIGN:
                        if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD+"Previous Page")&&page>1){
                            new BackUpMenu(player, Integer.valueOf(event.getView().getTitle().split(" ")[3])-1);
                        }else{
                            event.setCancelled(true);
                        }
                        if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD+"Next Page")&&page<pagemax){
                            new BackUpMenu(player, Integer.valueOf(event.getView().getTitle().split(" ")[3])+1);
                        }else{
                            event.setCancelled(true);
                        }
                        break;
                    case SMITHING_TABLE:
                        if(event.getClick() == ClickType.LEFT){
                            BackUpManager backUpManager = new BackUpManager();
                            backUpManager.loadBackUp(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", ""));
                        }else if(event.getClick() == ClickType.RIGHT){
                            Command confirm = () -> {
                                BackUpManager backUpManager = new BackUpManager();
                                backUpManager.deleteBackUp(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", ""));
                                event.setCancelled(true);
                                new BackUpMenu(player, page);
                            };
                            Command cancel = () -> {
                                new BackUpMenu(player, page);
                            };
                            new Confirmation(player, confirm, cancel);
                        }
                        break;
                    case RESPAWN_ANCHOR:
                        BackUpManager backUpManager = new BackUpManager();
                        backUpManager.createBackUp(player.getName(), "Triggert by user");
                        new BackUpMenu(player, page);
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
}
