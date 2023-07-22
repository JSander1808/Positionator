package de.rembel.Listener;

import de.rembel.CBossbar.CBossbar;
import de.rembel.CBossbar.CSmoothProfile;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.General.Position;
import de.rembel.Language.LanguageManager;
import de.rembel.Main.PositionatorMain;
import de.rembel.Menus.CompassCustomizerMenu;
import de.rembel.Menus.CompassManagerMenu;
import de.rembel.Menus.StartMenu;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

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
                    case COMPASS:
                        new CompassCustomizerMenu(player);
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
                            if(compass!=null) if((compass.getPositions().size()==4 && config.getBoolean("compassDirectionWiser")) || (compass.getPositions().size()==0 && !config.getBoolean("compassDirectionWiser"))) compass.remove();
                        }else{
                            config.set("compassAlwaysActive","true");
                            CBossbar compass = CBossbar.getByPlayer(player);
                            if(compass==null){
                                compass = new CBossbar(PositionatorMain.getPlugin());
                                compass.createBossbar(player);
                                compass.setSmoothProfile(CSmoothProfile.MIDDLE);
                                General.loadCompassData(CBossbar.getByPlayer(player));
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
            }
            event.setCancelled(true);
            NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
            if(normalConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, Float.valueOf(normalConfig.get("clickSoundPitch")));
        }
    }
}
