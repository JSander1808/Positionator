package de.rembel.Listener;

import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.General.PositionFilter;
import de.rembel.Menus.PublicFilterMenu;
import de.rembel.Menus.PublicMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PublicFilterMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD+"Public Filter Menu")){
            switch(event.getCurrentItem().getType()){
                case BARRIER:
                    player.closeInventory();
                    break;
                case SPRUCE_DOOR:
                    new PublicMenu(player, 1);
                    break;
                case PLAYER_HEAD:
                    if(event.getClick() == ClickType.LEFT){
                        PublicFilterMenu.PublicPlayernameFilterMenu(player, 1);
                    }else if(event.getClick() == ClickType.RIGHT){
                        if(General.PublicFilter.containsKey(player.getUniqueId().toString())){
                            if(General.PublicFilter.get(player.getUniqueId().toString()).hasPlayername()){
                                General.PublicFilter.get(player.getUniqueId().toString()).removePlayername();
                            }
                            if(!General.PublicFilter.get(player.getUniqueId().toString()).hasDimension()){
                                General.PublicFilter.remove(player.getUniqueId().toString());
                            }
                        }
                        new PublicFilterMenu(player);
                    }
                    break;
                case END_PORTAL_FRAME:
                    if(event.getClick() == ClickType.LEFT){
                        PublicFilterMenu.PublicDimensionFilterMenu(player);
                    }else if(event.getClick() == ClickType.RIGHT){
                        if(General.PublicFilter.containsKey(player.getUniqueId().toString())){
                            if(General.PublicFilter.get(player.getUniqueId().toString()).hasDimension()){
                                General.PublicFilter.get(player.getUniqueId().toString()).removeDimension();
                            }
                            if(!General.PublicFilter.get(player.getUniqueId().toString()).hasPlayername()){
                                General.PublicFilter.remove(player.getUniqueId().toString());
                            }
                        }
                        new PublicFilterMenu(player);
                    }
                    break;
                default:
                    break;
            }
            event.setCancelled(true);
            NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
            if(normalConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
        }else if(event.getView().getTitle().split(" ").length==6){
            if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD+"Public Playername Filter "+event.getView().getTitle().split(" ")[3]+" / "+((General.getRegisteredPlayers().size()/(9*3))+1))){
                int page = Integer.valueOf(event.getView().getTitle().split(" ")[3]);
                int maxPage = Integer.valueOf((General.getRegisteredPlayers().size()/(9*3))+1);
                switch(event.getCurrentItem().getType()){
                    case BARRIER:
                        player.closeInventory();
                        break;
                    case SPRUCE_DOOR:
                        new PublicFilterMenu(player);
                        break;
                    case SPRUCE_SIGN:
                        if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD+"Previous Page")){
                            if(page>=2){
                                PublicFilterMenu.PublicPlayernameFilterMenu(player, (page-1));
                            }
                        }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD+"Next Page")){
                            if(page+1<=maxPage){
                                PublicFilterMenu.PublicPlayernameFilterMenu(player, (page+1));
                            }
                        }
                        break;
                    case PLAYER_HEAD:
                        if(General.PublicFilter.containsKey(player.getUniqueId().toString())) {
                            General.PublicFilter.get(player.getUniqueId().toString()).setPlayername(event.getCurrentItem().getItemMeta().getDisplayName().replace(String.valueOf(ChatColor.GOLD), ""));
                        }else{
                            PositionFilter filter = new PositionFilter();
                            filter.setPlayername(event.getCurrentItem().getItemMeta().getDisplayName().replace(String.valueOf(ChatColor.GOLD), ""));
                            General.PublicFilter.put(player.getUniqueId().toString(), filter);
                        }
                        PublicFilterMenu.PublicPlayernameFilterMenu(player, page);
                        break;
                    default:
                        break;
                }
                event.setCancelled(true);
                NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
                if(normalConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
            }
        }else if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD+"Public Dimension Filter")){
            switch(event.getCurrentItem().getType()){
                case GRASS_BLOCK:
                    if(General.PublicFilter.containsKey(player.getUniqueId().toString())){
                        General.PublicFilter.get(player.getUniqueId().toString()).setDimension("NORMAL");
                    }else{
                        PositionFilter filter = new PositionFilter();
                        filter.setDimension("NORMAL");
                        General.PublicFilter.put(player.getUniqueId().toString(),filter);
                    }
                    PublicFilterMenu.PublicDimensionFilterMenu(player);
                    break;
                case NETHERRACK:
                    if(General.PublicFilter.containsKey(player.getUniqueId().toString())){
                        General.PublicFilter.get(player.getUniqueId().toString()).setDimension("NETHER");
                    }else{
                        PositionFilter filter = new PositionFilter();
                        filter.setDimension("NETHER");
                        General.PublicFilter.put(player.getUniqueId().toString(),filter);
                    }
                    PublicFilterMenu.PublicDimensionFilterMenu(player);
                    break;
                case END_STONE:
                    if(General.PublicFilter.containsKey(player.getUniqueId().toString())){
                        General.PublicFilter.get(player.getUniqueId().toString()).setDimension("THE_END");
                    }else{
                        PositionFilter filter = new PositionFilter();
                        filter.setDimension("THE_END");
                        General.PublicFilter.put(player.getUniqueId().toString(),filter);
                    }
                    PublicFilterMenu.PublicDimensionFilterMenu(player);
                    break;
                case SPRUCE_DOOR:
                    new PublicFilterMenu(player);
                    break;
                case BARRIER:
                    player.closeInventory();
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
