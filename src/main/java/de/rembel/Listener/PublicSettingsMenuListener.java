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
import de.rembel.Language.LanguageManager;
import de.rembel.Main.PositionatorMain;
import de.rembel.Menus.Confirmation;
import de.rembel.Menus.PrivateMenu;
import de.rembel.Menus.PublicMenu;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class PublicSettingsMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        LanguageManager language = new LanguageManager((Player) event.getWhoClicked());
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            Config config = new Config("plugins//Positionator//Data//public.conf");
            if(event.getView().getTitle().split(" ").length==4){
                if(event.getView().getTitle().equalsIgnoreCase(language.transalte(47)+event.getView().getTitle().split(" ")[3])){
                    if(event.getCurrentItem() == null){
                        event.setCancelled(true);
                        return;
                    }
                    String positionName = event.getView().getTitle().split(" ")[3].replace(ChatColor.GOLD+"", "").replace(ChatColor.RED+"","");
                    NormalConfig normalConfig = new NormalConfig("plugins//Positionator//config.yml");
                    switch(event.getCurrentItem().getType()){
                        case NAME_TAG:
                            if(!normalConfig.getBoolean("enableEditPositionsFromOtherPlayer")){
                                if(!player.getName().equalsIgnoreCase(config.get(positionName).getCreator())){
                                    player.sendMessage(language.transalte(49));
                                    break;
                                }
                            }
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
                                            if(config.existPosition(newName)) return Arrays.asList(AnvilGUI.ResponseAction.replaceInputText(language.transalte(50)));
                                            if(config.existPosition(oldName)) config.rename(oldName, newName);
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
                            if(!normalConfig.getBoolean("enableEditPositionsFromOtherPlayer")){
                                if(!player.getName().equalsIgnoreCase(event.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[1])){
                                    player.sendMessage(language.transalte(49));
                                    break;
                                }
                            }
                            Command confirm = ()-> {
                                config.remove(new Position(positionName));
                                player.closeInventory();
                                player.sendMessage(language.transalte(54)+positionName+language.transalte(55));
                                new PublicMenu(player, 1);
                            };
                            Command cancel = ()-> {
                                new PublicMenu(player, 1);
                            };
                            new Confirmation(player, confirm, cancel);
                            break;
                        case BEACON:
                            event.setCancelled(true);
                            Config tempCompassConfig = new Config("plugins//Positionator//Data//public.conf");
                            CBossbar compass = CBossbar.getByPlayer(player);
                            Position position = tempCompassConfig.get(positionName);
                            ChatColor color = getRandomColor();
                            CPosition cPosition = new CPosition("⌖", color, position.getLocation());

                            if(compass==null){
                                compass = new CBossbar(PositionatorMain.getPlugin());
                                compass.createBossbar(player);
                                compass.setSmoothProfile(CSmoothProfile.MIDDLE);
                            }
                            General.loadCompassData(compass);
                            if(!compass.existPosition(cPosition)){
                                compass.addPosition(cPosition);
                                player.sendMessage(language.transalte(138)+color+positionName+language.transalte(139)+color+"⌖"+language.transalte(140));
                            }else{
                                player.sendMessage(language.transalte(141));
                            }
                            //new BossbarService(player, positionName, new Config("plugins//Positionator//Data//public.conf"));
                            break;
                        case BARRIER:
                            player.closeInventory();
                            break;
                        case ENDER_CHEST:
                            if(event.getClick() == ClickType.LEFT){
                                Config publicconfig = new Config("plugins//Positionator//Data//User//"+ player.getUniqueId().toString()+"//data.conf");
                                if(publicconfig.existPosition(positionName)){
                                    player.sendMessage(language.transalte(56));
                                }else{
                                    Position privatePosition = config.get(positionName);
                                    publicconfig.set(privatePosition);
                                    player.sendMessage(ChatColor.GREEN+positionName+language.transalte(57));
                                }
                            }else if(event.getClick() == ClickType.RIGHT){
                                new PrivateMenu(player, 1);
                            }
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
                                }else if(config.get(positionName).getDimension().equalsIgnoreCase("THE_END")){
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
                    NormalConfig tempConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
                    if(tempConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, Float.valueOf(tempConfig.get("clickSoundPitch")));
                }
            }
        }
    }

    public ChatColor getRandomColor(){
        boolean finish = false;
        ChatColor color = null;
        while(!finish){
            finish = true;
            color = ChatColor.getByChar(Integer.toHexString(new Random().nextInt(16)));
            if(color==ChatColor.GRAY || color==ChatColor.DARK_GRAY) finish = false;
            if(color==ChatColor.GREEN || color==ChatColor.DARK_GREEN) finish = false;
            if(color==ChatColor.BLACK) finish = false;
            if(color==ChatColor.WHITE) finish = false;
        }
        return color;
    }
}
