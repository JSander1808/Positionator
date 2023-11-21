package de.rembel.Listener;

import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.General.PositionFilter;
import de.rembel.Language.LanguageManager;
import de.rembel.Menus.PrivateMenu;
import de.rembel.Menus.PrivateFilterMenu;
import de.rembel.Menus.PrivateMenu;
import de.rembel.Menus.PublicFilterMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class PrivateFilterMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        LanguageManager language = new LanguageManager((Player) event.getWhoClicked());
        Player player = (Player) event.getWhoClicked();
        if(event.getView().getTitle().equalsIgnoreCase(language.transalte(68))){
            if(event.getCurrentItem() == null){
                event.setCancelled(true);
                return;
            }
            switch(event.getCurrentItem().getType()){
                case BARRIER:
                    player.closeInventory();
                    break;
                case SPRUCE_DOOR:
                    new PrivateMenu(player, 1);
                    break;
                case PLAYER_HEAD:
                    if(event.getClick() == ClickType.LEFT){
                        PrivateFilterMenu.PrivatePlayernameFilterMenu(player, 1);
                    }else if(event.getClick() == ClickType.RIGHT){
                        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
                            if(General.PrivateFilter.get(player.getUniqueId().toString()).hasPlayername()){
                                General.PrivateFilter.get(player.getUniqueId().toString()).removePlayername();
                            }
                            if(!General.PrivateFilter.get(player.getUniqueId().toString()).hasDimension()){
                                General.PrivateFilter.remove(player.getUniqueId().toString());
                            }
                        }
                        new PrivateFilterMenu(player);
                    }
                    break;
                case END_PORTAL_FRAME:
//                    if(event.getClick() == ClickType.LEFT){
//                        PrivateFilterMenu.PublicDimensionFilterMenu(player);
//                    }else if(event.getClick() == ClickType.RIGHT){
//                        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
//                            if(General.PrivateFilter.get(player.getUniqueId().toString()).hasDimension()){
//                                General.PrivateFilter.get(player.getUniqueId().toString()).removeDimension();
//                            }
//                            if(!General.PrivateFilter.get(player.getUniqueId().toString()).hasPlayername()){
//                                General.PrivateFilter.remove(player.getUniqueId().toString());
//                            }
//                        }
//                        new PrivateFilterMenu(player);
//                    }

                    if(!General.PrivateFilter.containsKey(player.getUniqueId().toString()) || !General.PrivateFilter.get(player.getUniqueId().toString()).hasDimension()){
                        PositionFilter filter = null;
                        if(!General.PrivateFilter.containsKey(player.getUniqueId().toString())){
                            filter = new PositionFilter();
                            filter.setPlayer(player);
                            General.PrivateFilter.put(player.getUniqueId().toString(), filter);
                        }else{
                            filter = General.PrivateFilter.get(player.getUniqueId().toString());
                        }
                        filter.setDimension("NORMAL");
                    }else if(General.PrivateFilter.get(player.getUniqueId().toString()).getDimension().equals("NORMAL")){
                        General.PrivateFilter.get(player.getUniqueId().toString()).setDimension("NETHER");
                    }else if(General.PrivateFilter.get(player.getUniqueId().toString()).getDimension().equals("NETHER")){
                        General.PrivateFilter.get(player.getUniqueId().toString()).setDimension("THE_END");
                    }else if(General.PrivateFilter.get(player.getUniqueId().toString()).getDimension().equals("THE_END")){
                        General.PrivateFilter.get(player.getUniqueId().toString()).removeDimension();
                        if(!General.PrivateFilter.get(player.getUniqueId().toString()).hasDistance() && !General.PrivateFilter.get(player.getUniqueId().toString()).hasPlayername()){
                            General.PrivateFilter.remove(player.getUniqueId().toString());
                        }
                    }
                    new PrivateFilterMenu(player);
                    break;
                case ELYTRA:
                    if(!General.PrivateFilter.containsKey(player.getUniqueId().toString()) || !General.PrivateFilter.get(player.getUniqueId().toString()).hasDistance()){
                        PositionFilter filter = null;
                        if(!General.PrivateFilter.containsKey(player.getUniqueId().toString())) {
                            filter = new PositionFilter();
                            filter.setPlayer(player);
                            General.PrivateFilter.put(player.getUniqueId().toString(), filter);
                        }else{
                            filter = General.PrivateFilter.get(player.getUniqueId().toString());
                        }
                        filter.setDistance(50);
                    }else if(General.PrivateFilter.get(player.getUniqueId().toString()).getDistance() == 50){
                        General.PrivateFilter.get(player.getUniqueId().toString()).setDistance(100);
                    }else if(General.PrivateFilter.get(player.getUniqueId().toString()).getDistance() == 100){
                        General.PrivateFilter.get(player.getUniqueId().toString()).setDistance(250);
                    }else if(General.PrivateFilter.get(player.getUniqueId().toString()).getDistance() == 250){
                        General.PrivateFilter.get(player.getUniqueId().toString()).setDistance(1000);
                    }else if(General.PrivateFilter.get(player.getUniqueId().toString()).getDistance() == 1000){
                        General.PrivateFilter.get(player.getUniqueId().toString()).removeDistance();
                        if(!General.PrivateFilter.get(player.getUniqueId().toString()).hasDimension() && !General.PrivateFilter.get(player.getUniqueId().toString()).hasPlayername()){
                            General.PrivateFilter.remove(player.getUniqueId().toString());
                        }
                    }
                    new PrivateFilterMenu(player);
                    break;
                default:
                    break;
            }
            event.setCancelled(true);
            NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
            if(normalConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, Float.valueOf(normalConfig.get("clickSoundPitch")));
        }else if(event.getView().getTitle().split(" ").length==6){
            if(event.getView().getTitle().equalsIgnoreCase(language.transalte(69)+event.getView().getTitle().split(" ")[3]+" / "+((General.getRegisteredPlayers().size()/(9*3))+1))){
                if(event.getCurrentItem() == null){
                    event.setCancelled(true);
                    return;
                }
                int page = Integer.valueOf(event.getView().getTitle().split(" ")[3]);
                int maxPage = Integer.valueOf((General.getRegisteredPlayers().size()/(9*3))+1);
                switch(event.getCurrentItem().getType()){
                    case BARRIER:
                        player.closeInventory();
                        break;
                    case SPRUCE_DOOR:
                        new PrivateFilterMenu(player);
                        break;
                    case SPRUCE_SIGN:
                        if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(language.transalte(11))){
                            if(page>=2){
                                PrivateFilterMenu.PrivatePlayernameFilterMenu(player, (page-1));
                            }
                        }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(language.transalte(13))){
                            if(page+1<=maxPage){
                                PrivateFilterMenu.PrivatePlayernameFilterMenu(player, (page+1));
                            }
                        }
                        break;
                    case PLAYER_HEAD:
                        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())) {
                            General.PrivateFilter.get(player.getUniqueId().toString()).setPlayername(event.getCurrentItem().getItemMeta().getDisplayName().replace(String.valueOf(ChatColor.GOLD), ""));
                        }else{
                            PositionFilter filter = new PositionFilter();
                            filter.setPlayername(event.getCurrentItem().getItemMeta().getDisplayName().replace(String.valueOf(ChatColor.GOLD), ""));
                            filter.setPlayer(player);
                            General.PrivateFilter.put(player.getUniqueId().toString(), filter);
                        }
                        PrivateFilterMenu.PrivatePlayernameFilterMenu(player, page);
                        break;
                    default:
                        break;
                }
                event.setCancelled(true);
                NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
                if(normalConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, Float.valueOf(normalConfig.get("clickSoundPitch")));
            }
        }else if(event.getView().getTitle().equalsIgnoreCase(language.transalte(70))){
            if(event.getCurrentItem() == null){
                event.setCancelled(true);
                return;
            }
            switch(event.getCurrentItem().getType()){
                case GRASS_BLOCK:
                    if(event.getClick() == ClickType.LEFT){
                        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
                            General.PrivateFilter.get(player.getUniqueId().toString()).setDimension("NORMAL");
                        }else{
                            PositionFilter filter = new PositionFilter();
                            filter.setDimension("NORMAL");
                            General.PrivateFilter.put(player.getUniqueId().toString(),filter);
                        }
                    }else if(event.getClick() == ClickType.RIGHT){
                        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
                            if(General.PrivateFilter.get(player.getUniqueId().toString()).getDimension().equalsIgnoreCase("NORMAL")){
                                General.PrivateFilter.get(player.getUniqueId().toString()).removeDimension();
                                if(!General.PrivateFilter.get(player.getUniqueId().toString()).hasPlayername()){
                                    General.PrivateFilter.remove(player.getUniqueId().toString());
                                }
                            }
                        }
                    }
                    PrivateFilterMenu.PrivateDimensionFilterMenu(player);
                    break;
                case NETHERRACK:
                    if(event.getClick() == ClickType.LEFT){
                        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
                            General.PrivateFilter.get(player.getUniqueId().toString()).setDimension("NETHER");
                        }else{
                            PositionFilter filter = new PositionFilter();
                            filter.setDimension("NETHER");
                            filter.setPlayer(player);
                            General.PrivateFilter.put(player.getUniqueId().toString(),filter);
                        }
                    }else if(event.getClick() == ClickType.RIGHT){
                        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
                            if(General.PrivateFilter.get(player.getUniqueId().toString()).getDimension().equalsIgnoreCase("NETHER")){
                                General.PrivateFilter.get(player.getUniqueId().toString()).removeDimension();
                                if(!General.PrivateFilter.get(player.getUniqueId().toString()).hasPlayername()){
                                    General.PrivateFilter.remove(player.getUniqueId().toString());
                                }
                            }
                        }
                    }
                    PrivateFilterMenu.PrivateDimensionFilterMenu(player);
                    break;
                case END_STONE:
                    if(event.getClick() == ClickType.LEFT){
                        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
                            General.PrivateFilter.get(player.getUniqueId().toString()).setDimension("THE_END");
                        }else{
                            PositionFilter filter = new PositionFilter();
                            filter.setDimension("THE_END");
                            General.PrivateFilter.put(player.getUniqueId().toString(),filter);
                        }
                    }else if(event.getClick() == ClickType.RIGHT){
                        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
                            if(General.PrivateFilter.get(player.getUniqueId().toString()).getDimension().equalsIgnoreCase("THE_END")){
                                General.PrivateFilter.get(player.getUniqueId().toString()).removeDimension();
                                if(!General.PrivateFilter.get(player.getUniqueId().toString()).hasPlayername()){
                                    General.PrivateFilter.remove(player.getUniqueId().toString());
                                }
                            }
                        }
                    }
                    PrivateFilterMenu.PrivateDimensionFilterMenu(player);
                    break;
                case SPRUCE_DOOR:
                    new PrivateFilterMenu(player);
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
