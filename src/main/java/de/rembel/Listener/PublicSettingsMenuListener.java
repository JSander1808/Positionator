package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.Command;
import de.rembel.General.General;
import de.rembel.Menus.Confirmation;
import de.rembel.Menus.PrivateMenu;
import de.rembel.Menus.PublicMenu;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PublicSettingsMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            Config config = new Config("plugins//Positionator//Data//public.conf");
            if(event.getView().getTitle().split(" ").length==4){
                if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD+"Public Settings - "+event.getView().getTitle().split(" ")[3])){
                    if(event.getCurrentItem() == null){
                        event.setCancelled(true);
                        return;
                    }
                    String positionName = event.getView().getTitle().split(" ")[3].replace(ChatColor.GOLD+"", "").replace(ChatColor.RED+"","");
                    switch(event.getCurrentItem().getType()){
                        case RED_WOOL:
                            NormalConfig normalConfig = new NormalConfig("plugins//Positionator//config.yml");
                            if(!normalConfig.getBoolean("enableDeletePositionsFromOtherPlayer")){
                                if(!player.getName().equalsIgnoreCase(event.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[1])){
                                    player.sendMessage(ChatColor.RED+"You cannot delete this position because someone else created it.");
                                    break;
                                }
                            }
                            Command confirm = ()-> {
                                config.remove(positionName);
                                player.closeInventory();
                                player.sendMessage(ChatColor.RED+"Position "+positionName+" was successfully removed");
                                new PublicMenu(player, 1);
                            };
                            Command cancel = ()-> {
                                new PublicMenu(player, 1);
                            };
                            new Confirmation(player, confirm, cancel);
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
                        case ENDER_CHEST:
                            if(event.getClick() == ClickType.LEFT){
                                Config publicconfig = new Config("plugins//Positionator//Data//User//"+ player.getUniqueId().toString()+"//data.conf");
                                publicconfig.set(positionName,config.get(positionName)[1],config.get(positionName)[2],config.get(positionName)[3],Integer.valueOf(config.get(positionName)[4]));
                                player.sendMessage(ChatColor.GREEN+positionName+ChatColor.GOLD+" has been successfully added to your private list");
                            }else if(event.getClick() == ClickType.RIGHT){
                                new PrivateMenu(player, 1);
                            }
                            break;
                        case ENDER_PEARL:
                            NormalConfig mainConfig = new NormalConfig("plugins//Positionator//config.yml");
                            if(mainConfig.getBoolean("allowPlayerToTeleport") || (player.isOp() && mainConfig.getBoolean("allowOpToTeleport"))){
                                String[] cords = config.get(positionName)[1].split(" ");
                                if(config.get(positionName)[3].equalsIgnoreCase("NORMAL")){
                                    Location target = new Location(Bukkit.getWorld("world"), Integer.valueOf(cords[0]), Integer.valueOf(cords[1]), Integer.valueOf(cords[2]));
                                    player.teleport(target);
                                }else if(config.get(positionName)[3].equalsIgnoreCase("NETHER")){
                                    Location target = new Location(Bukkit.getWorld("world_nether"), Integer.valueOf(cords[0]), Integer.valueOf(cords[1]), Integer.valueOf(cords[2]));
                                    player.teleport(target);
                                }else if(config.get(positionName)[3].equalsIgnoreCase("THE_END")){
                                    Location target = new Location(Bukkit.getWorld("world_the_end"), Integer.valueOf(cords[0]), Integer.valueOf(cords[1]), Integer.valueOf(cords[2]));
                                    player.teleport(target);
                                }
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            }
                            break;
                        case SPRUCE_DOOR:
                            new PublicMenu(player, 1);
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
