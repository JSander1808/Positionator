package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class addMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            if(event.getView().getTitle().split(" ").length==2){
                if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD+"Add "+event.getView().getTitle().split(" ")[1])){
                    if(event.getCurrentItem() == null){
                        event.setCancelled(true);
                        return;
                    }
                    String positionName = event.getView().getTitle().split(" ")[1];
                    switch(event.getCurrentItem().getType()){
                        case CHEST:
                            Config publicconfig = new Config("plugins//Positionator//Data//public.conf");
                            String publicposition = (int) player.getLocation().getX()+" "+(int) player.getLocation().getY()+" "+(int) player.getLocation().getZ();
                            if(!publicconfig.existdata(positionName)){
                                publicconfig.set(positionName,publicposition,player.getName(),player.getWorld().getEnvironment().name(),0);
                                player.closeInventory();
                                player.sendMessage(ChatColor.GOLD+"Position "+ChatColor.GREEN+positionName+ChatColor.GOLD+" has been successfully added");
                            }else{
                                player.sendMessage(ChatColor.RED+"The Position "+positionName+" allready exist in the Public List. Please delete first the existed Position.");
                            }
                            break;
                        case ENDER_CHEST:
                            Config privateconfig = new Config("plugins//Positionator//Data//User//"+ player.getUniqueId().toString()+"//data.conf");
                            String privateposition = (int) player.getLocation().getX()+" "+(int) player.getLocation().getY()+" "+(int) player.getLocation().getZ();
                            if(!privateconfig.existdata(positionName)){
                                privateconfig.set(positionName,privateposition,player.getName(),player.getWorld().getEnvironment().name(),0);
                                player.closeInventory();
                                player.sendMessage(ChatColor.GOLD+"Position "+ChatColor.GREEN+positionName+ChatColor.GOLD+" has been successfully added");
                            }else{
                                player.closeInventory();
                                player.sendMessage(ChatColor.RED+"The Position "+positionName+" allready exist in the Private List. Please delete first the existed Position.");
                            }
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
}
