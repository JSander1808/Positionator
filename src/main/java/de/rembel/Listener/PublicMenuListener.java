package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.Menus.PublicMenu;
import de.rembel.Menus.StartMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
            Config config = new Config("data//position//public.conf");
            if(event.getView().getTitle().split(" ").length==7){
                if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD+"Public Liste - Page "+event.getView().getTitle().split(" ")[4]+" / "+((config.list().length/(9*5))+1))){
                    int page = Integer.valueOf(event.getView().getTitle().split(" ")[4]);
                    int pagemax = Integer.valueOf(event.getView().getTitle().split(" ")[6]);
                    switch(event.getCurrentItem().getType()){
                        case SPRUCE_SIGN:
                            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD+"Last Page")&&page>1){
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
                                setInOtherListLore.add(ChatColor.DARK_GRAY+"Left-Click add");
                                setInOtherListLore.add(ChatColor.DARK_GRAY+"Rechts-Click open public list.");
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
