package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.Command;
import de.rembel.General.General;
import de.rembel.Language.LanguageManager;
import de.rembel.Main.PositionatorMain;
import de.rembel.Menus.Confirmation;
import de.rembel.Menus.PrivateMenu;
import de.rembel.Menus.PrivateSettingsMenu;
import de.rembel.Menus.PublicMenu;
import de.rembel.TextInput.TextInputService;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PrivateSettingsMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        LanguageManager language = new LanguageManager((Player) event.getWhoClicked());
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            Config config = new Config("plugins//Positionator//Data//User//"+ player.getUniqueId().toString()+"//data.conf");
            if(event.getView().getTitle().split(" ").length==4){
                if(event.getView().getTitle().equalsIgnoreCase(language.transalte(30)+event.getView().getTitle().split(" ")[3])){
                    if(event.getCurrentItem() == null){
                        event.setCancelled(true);
                        return;
                    }
                    String positionName = event.getView().getTitle().split(" ")[3].replace(ChatColor.GOLD+"","").replace(ChatColor.RED+"","");
                    switch(event.getCurrentItem().getType()){
                        case NAME_TAG:
                            event.setCancelled(true);
                            player.sendMessage(language.transalte(58)+ChatColor.GOLD+"'cancel'"+language.transalte(59));

                            new AnvilGUI.Builder()
                                    .onClick((slot, stateSnapshot) -> { // Either use sync or async variant, not both
                                        if(slot != AnvilGUI.Slot.OUTPUT) {
                                            return Collections.emptyList();
                                        }

                                        if(stateSnapshot.getText().equalsIgnoreCase("cancel")){
                                            return Arrays.asList(AnvilGUI.ResponseAction.close());
                                        }

                                        if(stateSnapshot.getText() != null && stateSnapshot.getText() != ""){
                                            String oldName = event.getView().getTitle().split(" ")[3].replace(ChatColor.GOLD+"","").replace(ChatColor.RED+"","");
                                            String message = stateSnapshot.getText();
                                            String newName = message.split(" ")[0];
                                            for(int i = 1;i< message.split(" ").length;i++){
                                                newName +="-"+message.split(" ")[i];
                                            }
                                            for(int i = 0;i<100;i++){
                                                newName = newName.replace("->","");
                                            }
                                            if(config.existdata(newName)) return Arrays.asList(AnvilGUI.ResponseAction.replaceInputText(language.transalte(50)));
                                            if(config.existdata(oldName)) config.rename(oldName, newName);
                                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                                            player.sendMessage(ChatColor.GOLD+oldName+ChatColor.GREEN+" -> "+ChatColor.GOLD+newName+language.transalte(51));
                                            return Arrays.asList(AnvilGUI.ResponseAction.close());
                                        }else{
                                            return Arrays.asList(AnvilGUI.ResponseAction.replaceInputText(language.transalte(52)));
                                        }
                                    })
                                    .preventClose()
                                    .text(event.getView().getTitle().split(" ")[3].replace(ChatColor.GOLD+"", "").replace(ChatColor.RED+"", ""))                              //sets the text the GUI should start with
                                    .title(language.transalte(53))                                       //set the title of the GUI (only works in 1.14+)
                                    .plugin(PositionatorMain.getPlugin())                                          //set the plugin instance
                                    .open(player);


                            break;
                        case RED_WOOL:
                            Command confirm = ()-> {
                                config.remove(positionName);
                                player.closeInventory();
                                player.sendMessage(language.transalte(54)+positionName+language.transalte(55));
                                new PrivateMenu(player, 1);
                            };
                            Command cancel = ()-> {
                                new PrivateMenu(player, 1);
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
                                BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), language.transalte(44)+ChatColor.GREEN+config.get(positionName)[3],BarColor.GREEN,BarStyle.SOLID);
                                bar.setProgress(1.0);
                                bar.addPlayer(player);
                            }else{
                                String[] position = config.get(positionName)[1].split(" ");
                                Location target = new Location(player.getWorld(), Integer.valueOf(position[0]), Integer.valueOf(position[1]), Integer.valueOf(position[2]));
                                BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), language.transalte(45)+ChatColor.GREEN+config.get(positionName)[1]+language.transalte(46)+ChatColor.GREEN+Math.round(player.getLocation().distance(target)),BarColor.GREEN,BarStyle.SOLID);
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
                                Config publicconfig = new Config("plugins//Positionator//Data//public.conf");
                                if(publicconfig.existdata(positionName)){
                                    player.sendMessage(language.transalte(60));
                                }else{
                                    publicconfig.set(positionName,config.get(positionName)[1],config.get(positionName)[2],config.get(positionName)[3],Integer.valueOf(config.get(positionName)[4]));
                                    player.sendMessage(ChatColor.GREEN+positionName+language.transalte(61));
                                }
                            }else if(event.getClick() == ClickType.RIGHT){
                                new PublicMenu(player, 1);
                            }
                            break;
                        case SPRUCE_DOOR:
                            new PrivateMenu(player, 1);
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
                                }else if(config.get(positionName)[3].equalsIgnoreCase("world_the_end")){
                                    Location target = new Location(Bukkit.getWorld("world_the_end"), Integer.valueOf(cords[0]), Integer.valueOf(cords[1]), Integer.valueOf(cords[2]));
                                    player.teleport(target);
                                }
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
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
