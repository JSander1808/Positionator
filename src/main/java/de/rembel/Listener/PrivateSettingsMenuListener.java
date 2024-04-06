package de.rembel.Listener;

import de.rembel.Bossbar.BossbarService;
import de.rembel.CBossbar.CBossbar;
import de.rembel.CBossbar.CPosition;
import de.rembel.CBossbar.CSmoothProfile;
import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.Command;
import de.rembel.General.General;
import de.rembel.General.Position;
import de.rembel.General.PositionType;
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
import java.util.Random;

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
                                            if(config.existPosition(new Position(newName))) return Arrays.asList(AnvilGUI.ResponseAction.replaceInputText(language.transalte(50)));
                                            if(config.existPosition(new Position(oldName))) config.rename(new Position(oldName), new Position(newName));
                                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                                            player.sendMessage(ChatColor.GOLD+oldName+ChatColor.GREEN+" -> "+ChatColor.GOLD+newName+language.transalte(51));
                                            return Arrays.asList(AnvilGUI.ResponseAction.close());
                                        }else{
                                            return Arrays.asList(AnvilGUI.ResponseAction.replaceInputText(language.transalte(52)));
                                        }
                                    })
                                    .text(event.getView().getTitle().split(" ")[3].replace(ChatColor.GOLD+"", "").replace(ChatColor.RED+"", ""))                              //sets the text the GUI should start with
                                    .title(language.transalte(53))                                       //set the title of the GUI (only works in 1.14+)
                                    .plugin(PositionatorMain.getPlugin())                                          //set the plugin instance
                                    .open(player);


                            break;
                        case RED_WOOL:
                            Command confirm = ()-> {
                                config.remove(new Position(positionName));
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
                            event.setCancelled(true);
                            Config tempCompassConfig = new Config("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//data.conf");
                            CBossbar compass = CBossbar.getByPlayer(player);
                            Position position = tempCompassConfig.get(positionName);
                            ChatColor color = getRandomColor();
                            String symbol = "⌖";
                            if(position.getType()== PositionType.DEATHPOSITION) symbol = "\uD83D\uDC80";
                            CPosition cPosition = new CPosition(symbol, color, position.getLocation(), position.getName());

                            if(compass==null){
                                compass = new CBossbar(PositionatorMain.getPlugin());
                                compass.createBossbar(player);
                                //compass.setSmoothProfile(CSmoothProfile.MIDDLE);
                            }
                            if(!compass.existPosition(cPosition)){
                                if(!compass.addPosition(cPosition)){
                                    break;
                                }
                                player.sendMessage(language.transalte(138)+color+positionName+language.transalte(139)+color+symbol+language.transalte(140));
                            }else{
                                player.sendMessage(language.transalte(141));
                            }
                            General.loadCompassData(compass);
                            //new BossbarService(player, positionName, new Config("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//data.conf"));
                            break;
                        case BARRIER:
                            player.closeInventory();
                            break;
                        case CHEST:
                            if(event.getClick() == ClickType.LEFT){
                                Config publicconfig = new Config("plugins//Positionator//Data//public.conf");
                                if(publicconfig.existPosition(positionName)){
                                    player.sendMessage(language.transalte(60));
                                }else{
                                    Position publicPosition = config.get(positionName);
                                    publicconfig.set(publicPosition);
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
                                String[] cords = config.get(positionName).getPositionAsString().split(" ");
                                if(config.get(positionName).getDimension().equalsIgnoreCase("NORMAL")){
                                    Location target = new Location(Bukkit.getWorld("world"), Integer.valueOf(cords[0]), Integer.valueOf(cords[1]), Integer.valueOf(cords[2]));
                                    player.teleport(target);
                                }else if(config.get(positionName).getDimension().equalsIgnoreCase("NETHER")){
                                    Location target = new Location(Bukkit.getWorld("world_nether"), Integer.valueOf(cords[0]), Integer.valueOf(cords[1]), Integer.valueOf(cords[2]));
                                    player.teleport(target);
                                }else if(config.get(positionName).getDimension().equalsIgnoreCase("world_the_end")){
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

    public ChatColor getRandomColor(){
        ChatColor color = null;
        int random = 1 + (int)(Math.random() * ((8 - 1) + 1));
        if(random==1) color = ChatColor.RED;
        if(random==2) color = ChatColor.BLUE;
        if(random==3) color = ChatColor.YELLOW;
        if(random==4) color = ChatColor.DARK_PURPLE;
        if(random==5) color = ChatColor.LIGHT_PURPLE;
        if(random==6) color = ChatColor.RED;
        if(random==7) color = ChatColor.AQUA;
        if(random==8) color = ChatColor.GOLD;
        return color;
    }
}
