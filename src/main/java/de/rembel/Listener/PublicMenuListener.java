package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.Menus.PublicFilterMenu;
import de.rembel.Menus.PublicMenu;
import de.rembel.Menus.StartMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PublicMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            Config config = new Config("plugins//Positionator//Data//public.conf");
            if(event.getView().getTitle().split(" ").length==7){
                if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD+"Public Liste - Page "+event.getView().getTitle().split(" ")[4]+" / "+((config.list(General.PublicFilter.get(player.getUniqueId().toString())).length/(9*5))+1))){
                    if(event.getCurrentItem() == null){
                        event.setCancelled(true);
                        return;
                    }
                    int page = Integer.valueOf(event.getView().getTitle().split(" ")[4]);
                    int pagemax = Integer.valueOf(event.getView().getTitle().split(" ")[6]);
                    switch(event.getCurrentItem().getType()){
                        case PAPER:
                            if(event.getClick() == ClickType.RIGHT){
                                General.PublicFilter.remove(player.getUniqueId().toString());
                                new PublicMenu(player, 1);
                            }else if(event.getClick() == ClickType.LEFT){
                                new PublicFilterMenu(player);
                            }
                            break;
                        case SPRUCE_SIGN:
                            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD+"Previous Page")&&page>1){
                                new PublicMenu(player, Integer.valueOf(event.getView().getTitle().split(" ")[4])-1);
                            }else{
                                event.setCancelled(true);
                            }
                            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD+"Next Page")&&page<pagemax){
                                new PublicMenu(player, Integer.valueOf(event.getView().getTitle().split(" ")[4])+1);
                            }else{
                                event.setCancelled(true);
                            }
                            break;
                        case SPRUCE_DOOR:
                            new StartMenu(player);
                            break;
                        case BARRIER:
                            player.closeInventory();
                            break;
                        case CHEST:
                            Inventory inv = Bukkit.createInventory(null, 1*9,ChatColor.GOLD+"Public Settings - "+event.getCurrentItem().getItemMeta().getDisplayName());
                            for(int i = 0;i<1*9;i++){
                                inv.setItem(i,placeholder());
                            }
                            String positionName = event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"","").replace(ChatColor.RED+"","");;

                            if(event.getCurrentItem().getType() == Material.CHEST){
                                ItemStack setInOtherList = new ItemStack(Material.ENDER_CHEST);
                                ItemMeta setInOtherListMeta = setInOtherList.getItemMeta();
                                setInOtherListMeta.setDisplayName(ChatColor.GREEN+"Add "+positionName+" to private list");
                                ArrayList setInOtherListLore = new ArrayList();
                                setInOtherListLore.add(ChatColor.DARK_GRAY+"Left-Click: add");
                                setInOtherListLore.add(ChatColor.DARK_GRAY+"Rechts-Click: open public list.");
                                setInOtherListMeta.setLore(setInOtherListLore);
                                setInOtherList.setItemMeta(setInOtherListMeta);
                                inv.setItem(3,setInOtherList);
                            }

                            ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
                            ItemMeta backmeta = back.getItemMeta();
                            backmeta.setDisplayName(ChatColor.GOLD+"Back");
                            back.setItemMeta(backmeta);

                            ItemStack setOnBossbar = new ItemStack(Material.BEACON);
                            ItemMeta setOnBossbarmeta = setOnBossbar.getItemMeta();
                            setOnBossbarmeta.setDisplayName(ChatColor.GREEN+"Add "+positionName+" to Bossbar");
                            ArrayList setOnBossbarlore = new ArrayList();
                            setOnBossbarlore.add(ChatColor.GOLD+"The coordinates are displayed in the Bossbar ");
                            setOnBossbarlore.add(ChatColor.GOLD+"so you can always see them");
                            setOnBossbarlore.add(ChatColor.DARK_GRAY+"(Can be deleted again in the start menu)");
                            setOnBossbarmeta.setLore(setOnBossbarlore);
                            setOnBossbar.setItemMeta(setOnBossbarmeta);

                            ItemStack delete = new ItemStack(Material.RED_WOOL);
                            ItemMeta deletemeta = delete.getItemMeta();
                            deletemeta.setDisplayName(ChatColor.RED+"Delete "+positionName);
                            ArrayList deletelore = new ArrayList();
                            deletelore.add(ChatColor.RED+"Created: "+config.get(positionName)[2]);
                            deletemeta.setLore(deletelore);
                            delete.setItemMeta(deletemeta);

                            NormalConfig mainConfig = new NormalConfig("plugins//Positionator//config.yml");
                            if(mainConfig.getBoolean("allowPlayerToTeleport") || (player.isOp() && mainConfig.getBoolean("allowOpToTeleport"))){
                                ItemStack teleport = new ItemStack(Material.ENDER_PEARL);
                                ItemMeta teleportMeta = teleport.getItemMeta();
                                teleportMeta.setDisplayName(ChatColor.GREEN+"Teleport");
                                ArrayList teleportLore = new ArrayList();
                                teleportLore.add(ChatColor.GOLD+"You will be teleported to this point");
                                teleportMeta.setLore(teleportLore);
                                teleport.setItemMeta(teleportMeta);
                                inv.setItem(5, teleport);
                            }

                            ItemStack close = new ItemStack(Material.BARRIER);
                            ItemMeta closemeta = close.getItemMeta();
                            closemeta.setDisplayName(ChatColor.RED+"Close");
                            close.setItemMeta(closemeta);

                            inv.setItem(4,setOnBossbar);
                            inv.setItem(0,delete);
                            inv.setItem(7, back);
                            inv.setItem(8,close);

                            player.openInventory(inv);
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

    public static ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }
}
