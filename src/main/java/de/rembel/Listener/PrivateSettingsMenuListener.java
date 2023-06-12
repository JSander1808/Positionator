package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.General.General;
import de.rembel.Menus.PrivateMenu;
import de.rembel.Menus.PublicMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PrivateSettingsMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            Config config = new Config("plugins//Positionator//"+player.getUniqueId()+".conf");
            if(event.getView().getTitle().split(" ").length==4){
                if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD+"Private Settings - "+event.getView().getTitle().split(" ")[3])){
                    String positionName = event.getView().getTitle().split(" ")[3].replace(ChatColor.GOLD+"","").replace(ChatColor.RED+"","");;
                    switch(event.getCurrentItem().getType()){
                        case RED_WOOL:
                            config.remove(positionName);
                            player.closeInventory();
                            player.sendMessage(ChatColor.RED+"Position "+positionName+" was successfully removed");
                            new PrivateMenu(player, 1);
                            break;
                        case BEACON:
                            if(Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString()))!=null) {
                                Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString())).removeAll();
                                Bukkit.removeBossBar(NamespacedKey.fromString(player.getUniqueId().toString()));
                            }

                            General.BossBarPosition.remove(player.getUniqueId().toString());

                            if(!config.get(positionName)[3].equalsIgnoreCase(player.getLocation().getWorld().getEnvironment().name())){
                                BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), ChatColor.GOLD+"Dimension: "+ChatColor.GREEN+config.get(positionName)[3],BarColor.GREEN,BarStyle.SOLID);
                                bar.setProgress(1.0);
                                bar.addPlayer(player);
                            }else{
                                String[] position = config.get(positionName)[1].split(" ");
                                Location target = new Location(player.getWorld(), Integer.valueOf(position[0]), Integer.valueOf(position[1]), Integer.valueOf(position[2]));
                                BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), ChatColor.GOLD+"Coordinates: "+ChatColor.GREEN+config.get(positionName)[1]+ChatColor.GOLD+"     Distance: "+ChatColor.GREEN+Math.round(player.getLocation().distance(target)),BarColor.GREEN,BarStyle.SOLID);
                                bar.setProgress(1.0);
                                bar.addPlayer(player);
                            }

                            General.BossBarPosition.put(player.getUniqueId().toString(), config.get(positionName)[3]+"->"+config.get(positionName)[1]);

                            player.closeInventory();
                            break;
                        case BARRIER:
                            player.closeInventory();
                            break;
                        case CHEST:
                            if(event.getClick() == ClickType.LEFT){
                                Config publicconfig = new Config("plugins//Positionator//public.conf");
                                publicconfig.set(positionName,config.get(positionName)[1],config.get(positionName)[2],config.get(positionName)[3],Integer.valueOf(config.get(positionName)[4]));
                                player.sendMessage(ChatColor.GREEN+positionName+ChatColor.GOLD+" has been successfully added to your public list");
                            }else if(event.getClick() == ClickType.RIGHT){
                                new PublicMenu(player, 1);
                            }
                            break;
                        case SPRUCE_DOOR:
                            new PrivateMenu(player, 1);
                            break;
                        default:
                            break;
                    }
                    event.setCancelled(true);
                }
            }
        }
    }
}
