package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.Language.LanguageManager;
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
            LanguageManager language = new LanguageManager(player);
            if(event.getView().getTitle().split(" ").length==2){
                if(event.getView().getTitle().equalsIgnoreCase(language.transalte(101)+event.getView().getTitle().split(" ")[1])){
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
                                player.sendMessage(language.transalte(104)+ChatColor.GREEN+positionName+language.transalteDefaultEnglish(105));
                            }else{
                                player.sendMessage(language.transalte(106)+positionName+language.transalte(107));
                            }
                            break;
                        case ENDER_CHEST:
                            Config privateconfig = new Config("plugins//Positionator//Data//User//"+ player.getUniqueId().toString()+"//data.conf");
                            String privateposition = (int) player.getLocation().getX()+" "+(int) player.getLocation().getY()+" "+(int) player.getLocation().getZ();
                            if(!privateconfig.existdata(positionName)){
                                privateconfig.set(positionName,privateposition,player.getName(),player.getWorld().getEnvironment().name(),0);
                                player.closeInventory();
                                player.sendMessage(language.transalte(104)+ChatColor.GREEN+positionName+ChatColor.GOLD+language.transalte(105));
                            }else{
                                player.sendMessage(language.transalte(106)+positionName+language.transalte(108));
                            }
                            break;
                        default:
                            break;
                    }
                    event.setCancelled(true);
                    NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
                    if(normalConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, Float.valueOf(normalConfig.get("clickSoundPitch")));
                }
            }
        }
    }
}
