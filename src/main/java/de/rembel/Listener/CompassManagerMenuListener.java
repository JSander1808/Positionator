package de.rembel.Listener;

import de.rembel.CBossbar.CBossbar;
import de.rembel.CBossbar.CPosition;
import de.rembel.CBossbar.CSmoothProfile;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.General.Position;
import de.rembel.General.PositionFilter;
import de.rembel.Language.LanguageManager;
import de.rembel.Main.PositionatorMain;
import de.rembel.Menus.*;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

public class CompassManagerMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        LanguageManager language = new LanguageManager(player);
        NormalConfig config = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
        if(event.getView().getTitle().equalsIgnoreCase(language.transalte(142))){
            if(event.getCurrentItem()!=null){
                switch(event.getCurrentItem().getType()){
                    case PLAYER_HEAD:
                    case CHEST:
                        CBossbar compass = CBossbar.getByPlayer(player);
                        CPosition position = compass.getPositionByDescription(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", ""));
                        System.out.println(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", ""));
                        if(event.getClick() == ClickType.RIGHT){
                            if(position.getSymbol().equals("⌖")){
                                position.setSymbol("\uD83D\uDC80");
                                compass.updatePosition(position);
                                new CompassManagerMenu(player);
                            }else if(position.getSymbol().equals("\uD83D\uDC80")){
                                position.setSymbol("\uD83C\uDFE0");
                                compass.updatePosition(position);
                                new CompassManagerMenu(player);
                            }else if(position.getSymbol().equals("\uD83C\uDFE0")){
                                position.setSymbol("⚠");
                                compass.updatePosition(position);
                                new CompassManagerMenu(player);
                            }else if(position.getSymbol().equals("⚠")){
                                position.setSymbol("\uD83D\uDCC5");
                                compass.updatePosition(position);
                                new CompassManagerMenu(player);
                            }else if(position.getSymbol().equals("\uD83D\uDCC5")){
                                position.setSymbol("\uD83C\uDFAE");
                                compass.updatePosition(position);
                                new CompassManagerMenu(player);
                            }else if(position.getSymbol().equals("\uD83C\uDFAE")){
                                position.setSymbol("⌖");
                                compass.updatePosition(position);
                                new CompassManagerMenu(player);
                            }
                        }else if(event.getClick() == ClickType.LEFT){
                            if(position.getColor().equals(ChatColor.RED)){
                                position.setColor(ChatColor.BLUE);
                                compass.updatePosition(position);
                                new CompassManagerMenu(player);
                            }else if(position.getColor().equals(ChatColor.BLUE)){
                                position.setColor(ChatColor.YELLOW);
                                compass.updatePosition(position);
                                new CompassManagerMenu(player);
                            }else if(position.getColor().equals(ChatColor.YELLOW)){
                                position.setColor(ChatColor.DARK_PURPLE);
                                compass.updatePosition(position);
                                new CompassManagerMenu(player);
                            }else if(position.getColor().equals(ChatColor.DARK_PURPLE)){
                                position.setColor(ChatColor.LIGHT_PURPLE);
                                compass.updatePosition(position);
                                new CompassManagerMenu(player);
                            }else if(position.getColor().equals(ChatColor.LIGHT_PURPLE)){
                                position.setColor(ChatColor.AQUA);
                                compass.updatePosition(position);
                                new CompassManagerMenu(player);
                            }else if(position.getColor().equals(ChatColor.AQUA)){
                                position.setColor(ChatColor.GOLD);
                                compass.updatePosition(position);
                                new CompassManagerMenu(player);
                            }else if(position.getColor().equals(ChatColor.GOLD)){
                                position.setColor(ChatColor.RED);
                                compass.updatePosition(position);
                                new CompassManagerMenu(player);
                            }
                        }else if(event.getClick() == ClickType.SHIFT_LEFT){
                            compass.removePosition(position.getUuid());
                            General.loadCompassData(compass);
                            config.set("compassSave","");
                            if(compass!=null) if(compass.getPositions().size()==0) if(!config.getBoolean("compassAlwaysActive")) compass.remove();
                            new CompassManagerMenu(player);
                        }
                        General.loadCompassData(compass);
                        break;
                    case COMPASS:
                        if(config.getBoolean("compassAlwaysActive")) {
                            config.set("compassAlwaysActive","false");
                            CBossbar tempCompass = CBossbar.getByPlayer(player);
                            config.set("compassSave","");
                            if(tempCompass!=null) if(tempCompass.getPositions().size()==0) tempCompass.remove();
                        }else{
                            config.set("compassAlwaysActive","true");
                            CBossbar tempCompass = CBossbar.getByPlayer(player);
                            if(tempCompass==null){
                                tempCompass = new CBossbar(PositionatorMain.getPlugin());
                                tempCompass.createBossbar(player);
                                //compass.setSmoothProfile(CSmoothProfile.MIDDLE);
                                General.loadCompassData(tempCompass);
                            }
                        }
                        new CompassManagerMenu(player);
                        break;
                    case WHITE_WOOL:
                        config.set("compassBossbarColor","red");
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassManagerMenu(player);
                        break;
                    case RED_WOOL:
                        config.set("compassBossbarColor","green");
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassManagerMenu(player);
                        break;
                    case GREEN_WOOL:
                        config.set("compassBossbarColor","blue");
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassManagerMenu(player);
                        break;
                    case BLUE_WOOL:
                        config.set("compassBossbarColor","purple");
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassManagerMenu(player);
                        break;
                    case PURPLE_WOOL:
                        config.set("compassBossbarColor","yellow");
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassManagerMenu(player);
                        break;
                    case YELLOW_WOOL:
                        config.set("compassBossbarColor","pink");
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassManagerMenu(player);
                        break;
                    case PINK_WOOL:
                        config.set("compassBossbarColor","white");
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassManagerMenu(player);
                        break;
                    case NAME_TAG:
                        if(config.get("compassPlaceholder").equals("*")){
                            config.set("compassPlaceholder","|");
                        }else if(config.get("compassPlaceholder").equals("|")){
                            config.set("compassPlaceholder","-");
                        }else if(config.get("compassPlaceholder").equals("-")){
                            config.set("compassPlaceholder","•");
                        }else if(config.get("compassPlaceholder").equals("•")){
                            config.set("compassPlaceholder","*");
                        }
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassManagerMenu(player);
                        break;
                    case BARRIER:
                        player.closeInventory();
                        break;
                    case SPRUCE_DOOR:
                        new StartMenu(player);
                        break;
                    default:
                        break;
                }
            }
            event.setCancelled(true);
            NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
            if(normalConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, Float.valueOf(normalConfig.get("clickSoundPitch")));
        }else if(event.getView().getTitle().equalsIgnoreCase(language.transalte(150))){
            if(event.getCurrentItem()!=null){
                switch(event.getCurrentItem().getType()){
                    case NAME_TAG:
                        if(config.get("compassPlaceholder").equals("|")){
                            config.set("compassPlaceholder","*");
                        }else if(config.get("compassPlaceholder").equals("*")){
                            config.set("compassPlaceholder","•");
                        }else if(config.get("compassPlaceholder").equals("•")){
                            config.set("compassPlaceholder","-");
                        }else if(config.get("compassPlaceholder").equals("-")){
                            config.set("compassPlaceholder","|");
                        }
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassCustomizerMenu(player);
                        break;
                    case WHITE_WOOL:
                        config.set("compassBossbarColor","red");
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassCustomizerMenu(player);
                        break;
                    case RED_WOOL:
                        config.set("compassBossbarColor","green");
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassCustomizerMenu(player);
                        break;
                    case GREEN_WOOL:
                        config.set("compassBossbarColor","blue");
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassCustomizerMenu(player);
                        break;
                    case BLUE_WOOL:
                        config.set("compassBossbarColor","purple");
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassCustomizerMenu(player);
                        break;
                    case PURPLE_WOOL:
                        config.set("compassBossbarColor","yellow");
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassCustomizerMenu(player);
                        break;
                    case YELLOW_WOOL:
                        config.set("compassBossbarColor","pink");
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassCustomizerMenu(player);
                        break;
                    case PINK_WOOL:
                        config.set("compassBossbarColor","white");
                        if(CBossbar.getByPlayer(player)!=null) General.loadCompassData(CBossbar.getByPlayer(player));
                        new CompassCustomizerMenu(player);
                        break;
                    case COMPASS:
                        if(config.getBoolean("compassAlwaysActive")) {
                            config.set("compassAlwaysActive","false");
                            CBossbar compass = CBossbar.getByPlayer(player);
                            config.set("compassSave","");
                            if(compass!=null) if(compass.getPositions().size()==0) compass.remove();
                        }else{
                            config.set("compassAlwaysActive","true");
                            CBossbar compass = CBossbar.getByPlayer(player);
                            if(compass==null){
                                compass = new CBossbar(PositionatorMain.getPlugin());
                                compass.createBossbar(player);
                                //compass.setSmoothProfile(CSmoothProfile.MIDDLE);
                                General.loadCompassData(compass);
                            }
                        }
                        new CompassCustomizerMenu(player);
                        break;
                    case BARRIER:
                        player.closeInventory();
                        break;
                    case SPRUCE_DOOR:
                        new CompassManagerMenu(player);
                        break;
                    default:
                        break;
                }
                event.setCancelled(true);
                NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
                if(normalConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, Float.valueOf(normalConfig.get("clickSoundPitch")));
            }
        }else if(event.getView().getTitle().split(" ").length==7 && event.getView().getTitle().equalsIgnoreCase(language.transalte(158)+event.getView().getTitle().split(" ")[4]+" / "+event.getView().getTitle().split(" ")[6])){
                if(event.getCurrentItem()!=null){
                    int page = Integer.valueOf(event.getView().getTitle().split(" ")[4]);
                    int pagemax = Integer.valueOf(event.getView().getTitle().split(" ")[6]);
                    switch(event.getCurrentItem().getType()){
                        case SPRUCE_SIGN:
                            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(language.transalte(11))&&page>1){
                                new CompassPositionManagerMenu(player, Integer.valueOf(event.getView().getTitle().split(" ")[4])-1);
                            }else{
                                event.setCancelled(true);
                            }
                            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(language.transalte(13))&&page<pagemax){
                                new CompassPositionManagerMenu(player, Integer.valueOf(event.getView().getTitle().split(" ")[4])+1);
                            }else{
                                event.setCancelled(true);
                            }
                            break;
                        case SPRUCE_DOOR:
                            new CompassManagerMenu(player);
                            break;
                        case CHEST:
                            CBossbar compass = CBossbar.getByPlayer(player);
                            CPosition position = compass.getPositionByDescription(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", ""));
                            System.out.println(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", ""));
                            if(event.getClick() == ClickType.LEFT){
                                if(position.getSymbol().equals("⌖")){
                                    position.setSymbol("\uD83D\uDC80");
                                    compass.updatePosition(position);
                                    new CompassPositionManagerMenu(player, page);
                                }else if(position.getSymbol().equals("\uD83D\uDC80")){
                                    position.setSymbol("\uD83C\uDFE0");
                                    compass.updatePosition(position);
                                    new CompassPositionManagerMenu(player, page);
                                }else if(position.getSymbol().equals("\uD83C\uDFE0")){
                                    position.setSymbol("⚠");
                                    compass.updatePosition(position);
                                    new CompassPositionManagerMenu(player, page);
                                }else if(position.getSymbol().equals("⚠")){
                                    position.setSymbol("\uD83D\uDCC5");
                                    compass.updatePosition(position);
                                    new CompassPositionManagerMenu(player, page);
                                }else if(position.getSymbol().equals("\uD83D\uDCC5")){
                                    position.setSymbol("⌖");
                                    compass.updatePosition(position);
                                    new CompassPositionManagerMenu(player, page);
                                }
                            }else if(event.getClick() == ClickType.RIGHT){
                                if(position.getColor().equals(ChatColor.RED)){
                                    position.setColor(ChatColor.BLUE);
                                    compass.updatePosition(position);
                                    new CompassPositionManagerMenu(player, page);
                                }else if(position.getColor().equals(ChatColor.BLUE)){
                                    position.setColor(ChatColor.YELLOW);
                                    compass.updatePosition(position);
                                    new CompassPositionManagerMenu(player, page);
                                }else if(position.getColor().equals(ChatColor.YELLOW)){
                                    position.setColor(ChatColor.DARK_PURPLE);
                                    compass.updatePosition(position);
                                    new CompassPositionManagerMenu(player, page);
                                }else if(position.getColor().equals(ChatColor.DARK_PURPLE)){
                                    position.setColor(ChatColor.LIGHT_PURPLE);
                                    compass.updatePosition(position);
                                    new CompassPositionManagerMenu(player, page);
                                }else if(position.getColor().equals(ChatColor.LIGHT_PURPLE)){
                                    position.setColor(ChatColor.RED);
                                    compass.updatePosition(position);
                                    new CompassPositionManagerMenu(player, page);
                                }else if(position.getColor().equals(ChatColor.RED)){
                                    position.setColor(ChatColor.AQUA);
                                    compass.updatePosition(position);
                                    new CompassPositionManagerMenu(player, page);
                                }else if(position.getColor().equals(ChatColor.AQUA)){
                                    position.setColor(ChatColor.GOLD);
                                    compass.updatePosition(position);
                                    new CompassPositionManagerMenu(player, page);
                                }else if(position.getColor().equals(ChatColor.GOLD)){
                                    position.setColor(ChatColor.RED);
                                    compass.updatePosition(position);
                                    new CompassPositionManagerMenu(player, page);
                                }
                            }else if(event.getClick() == ClickType.SHIFT_LEFT){
                                compass.removePosition(position.getUuid());
                                General.loadCompassData(compass);
                                config.set("compassSave","");
                                if(compass!=null) if(compass.getPositions().size()==0) if(!config.getBoolean("compassAlwaysActive")) compass.remove();
                                new CompassPositionManagerMenu(player, page);
                            }
                            General.loadCompassData(compass);
                            break;
                        case BARRIER:
                            player.closeInventory();
                            break;
                        default:
                            break;
                    }
                    event.setCancelled(true);
                    NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
                    if(normalConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, Float.valueOf(normalConfig.get("clickSoundPitch")));
                }
        }else if(event.getView().getTitle().split(" ").length==6 && event.getView().getTitle().equalsIgnoreCase(language.transalte(164)+event.getView().getTitle().split(" ")[3]+" / "+event.getView().getTitle().split(" ")[5])){
                if(event.getCurrentItem()!=null){
                    int page = Integer.valueOf(event.getView().getTitle().split(" ")[3]);
                    int maxPage = Integer.valueOf(event.getView().getTitle().split(" ")[5]);
                    switch(event.getCurrentItem().getType()){
                        case BARRIER:
                            player.closeInventory();
                            break;
                        case SPRUCE_DOOR:
                            new CompassManagerMenu(player);
                            break;
                        case SPRUCE_SIGN:
                            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(language.transalte(11))){
                                if(page>=2){
                                    new CompassSelectPlayerMenu(player, (page-1));
                                }
                            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(language.transalte(13))){
                                if(page+1<=maxPage){
                                    new CompassSelectPlayerMenu(player, (page+1));
                                }
                            }
                            break;
                        case PLAYER_HEAD:
                            CBossbar compass = CBossbar.getByPlayer(player);
                            if(compass != null){
                                Entity entity = Bukkit.getPlayer(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
                                compass.addPosition(new CPosition("\uD83C\uDFAE", getRandomColor(), entity, event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", "")));
                                System.out.println(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", ""));
                            }
                            new CompassSelectPlayerMenu(player, page);
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

    public ChatColor getRandomColor(){
        ChatColor color = null;
        int random = 1 + (int)(Math.random() * ((7 - 1) + 1));
        if(random==1) color = ChatColor.RED;
        if(random==2) color = ChatColor.BLUE;
        if(random==3) color = ChatColor.YELLOW;
        if(random==4) color = ChatColor.DARK_PURPLE;
        if(random==5) color = ChatColor.LIGHT_PURPLE;
        if(random==6) color = ChatColor.AQUA;
        if(random==7) color = ChatColor.GOLD;
        return color;
    }
}
