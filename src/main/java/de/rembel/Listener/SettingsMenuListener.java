package de.rembel.Listener;

import de.rembel.Config.NormalConfig;
import de.rembel.Language.LanguageManager;
import de.rembel.Menus.PrivateSettingsMenu;
import de.rembel.Menus.StartMenu;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SettingsMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        LanguageManager language = new LanguageManager((Player) event.getWhoClicked());
        if(event.getView().getTitle().equalsIgnoreCase(language.transalte(71))){
            if(event.getCurrentItem() == null){
                event.setCancelled(true);
                return;
            }
            Player player = (Player) event.getWhoClicked();
            NormalConfig config = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
            switch(event.getCurrentItem().getType()){
                case OBSERVER:
                    if(config.getBoolean("showDeathPositionInList")){
                        config.set("showDeathPositionInList","false");
                    }else{
                        config.set("showDeathPositionInList","true");
                    }
                    new PrivateSettingsMenu(player);
                    break;
                case NETHER_STAR:
                    if(config.getBoolean("setDeathPositionInBossbar")){
                        config.set("setDeathPositionInBossbar","false");
                    }else{
                        config.set("setDeathPositionInBossbar","true");
                    }
                    new PrivateSettingsMenu(player);
                    break;
                case PAPER:
                    if(config.getBoolean("enableFilter")){
                        config.set("enableFilter","false");
                    }else{
                        config.set("enableFilter","true");
                    }
                    new PrivateSettingsMenu(player);
                    break;
                case EXPERIENCE_BOTTLE:
                    if(event.getClick() == ClickType.LEFT){
                        if(config.getBoolean("enableMenuClickSound")){
                            config.set("enableMenuClickSound","false");
                        }else{
                            config.set("enableMenuClickSound","true");
                        }
                    }else if(event.getClick() == ClickType.RIGHT){
                        if(Double.valueOf(config.get("clickSoundPitch")) == 2){
                            config.set("clickSoundPitch", "0");
                        }else if(Double.valueOf(config.get("clickSoundPitch")) == 0){
                            config.set("clickSoundPitch", "1");
                        }else if(Double.valueOf(config.get("clickSoundPitch")) == 1){
                            config.set("clickSoundPitch", "2");
                        }
                    }
                    new PrivateSettingsMenu(player);
                    break;
                case BELL:
                    if(config.getBoolean("broadcaseWhenPositionAdded")){
                        config.setBoolean("broadcaseWhenPositionAdded", false);
                    }else{
                        config.setBoolean("broadcaseWhenPositionAdded", true);
                    }
                    new PrivateSettingsMenu(player);
                    break;
                case SPRUCE_DOOR:
                    new StartMenu(player);
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
    }

}
