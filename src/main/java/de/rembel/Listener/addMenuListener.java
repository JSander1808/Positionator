package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.Position;
import de.rembel.General.PositionType;
import de.rembel.Language.LanguageManager;
import org.bukkit.Bukkit;
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
                            Position publicPosition = new Position(positionName, new String[]{String.valueOf(player.getLocation().getBlockX()), String.valueOf(player.getLocation().getBlockY()), String.valueOf(player.getLocation().getBlockZ())}, player.getName(), player.getWorld().getEnvironment().name(), PositionType.CHESTPOSITION);
                            Config publicconfig = new Config("plugins//Positionator//Data//public.conf");
                            if(!publicconfig.existPosition(publicPosition)){
                                publicconfig.set(publicPosition);
                                player.closeInventory();
                                for(Player onlinePlayer : Bukkit.getOnlinePlayers()){
                                    NormalConfig config = new NormalConfig("plugins//Positionator//Data//User//"+onlinePlayer.getUniqueId().toString()+"//config.yml");
                                    if(config.getBoolean("broadcaseWhenPositionAdded")){
                                        onlinePlayer.sendMessage(ChatColor.GOLD+player.getName()+language.transalte(104)+ChatColor.GOLD+positionName+language.transalte(105));
                                    }
                                }
                            }else{
                                player.sendMessage(language.transalte(106)+positionName+language.transalte(107));
                            }
                            break;
                        case ENDER_CHEST:
                            Position privatePosition = new Position(positionName, new String[]{String.valueOf(player.getLocation().getBlockX()), String.valueOf(player.getLocation().getBlockY()), String.valueOf(player.getLocation().getBlockZ())}, player.getName(), player.getWorld().getEnvironment().name(), PositionType.CHESTPOSITION);
                            Config privateconfig = new Config("plugins//Positionator//Data//User//"+ player.getUniqueId().toString()+"//data.conf");
                            if(!privateconfig.existPosition(privatePosition)){
                                privateconfig.set(privatePosition);
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
