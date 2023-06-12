package de.rembel.Menus;

import de.rembel.Config.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.IOException;
import java.util.ArrayList;

public class PrivateMenu {
    public static Inventory inv;

    public PrivateMenu(Player player, int page){
        Config config = new Config("plugins//Positionator//"+player.getUniqueId()+".conf");
        inv = Bukkit.createInventory(null,9*6, ChatColor.GOLD+"Private Liste - Page "+page+" / "+((config.list().length/(9*5))+1));
        player.openInventory(inv);

        for(int i = 0;i<9*6;i++){
            inv.setItem(i,placeholder());
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(ChatColor.RED+"Close");
        close.setItemMeta(closemeta);

        ItemStack previouspage = new ItemStack(Material.SPRUCE_SIGN);
        ItemMeta previousmeta = previouspage.getItemMeta();
        previousmeta.setDisplayName(ChatColor.GOLD+"Last Page");
        previouspage.setItemMeta(previousmeta);

        ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(ChatColor.GOLD+"Back");
        back.setItemMeta(backmeta);

        ItemStack nextpage = new ItemStack(Material.SPRUCE_SIGN);
        ItemMeta nextmeta = nextpage.getItemMeta();
        nextmeta.setDisplayName(ChatColor.GOLD+"Next Page");
        nextpage.setItemMeta(nextmeta);

        String[][] data = config.listFormToEnd((page-1)*(9*5),page*(9*5)-1);
        for(int i = 0;i<data.length;i++){
            ItemStack item;
            if(Integer.valueOf(data[i][4])==0){
                item = new ItemStack(Material.CHEST);
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(ChatColor.GOLD+data[i][0]);
                ArrayList<String> itemlore = new ArrayList<String>();
                itemlore.add(ChatColor.GREEN+"Creator: "+ChatColor.BLUE+data[i][2]);
                itemlore.add(ChatColor.GREEN+"Coordinates: "+ChatColor.BLUE+data[i][1]);
                itemlore.add(ChatColor.GREEN+"Dimension: "+ChatColor.BLUE+data[i][3]);
                itemmeta.setLore(itemlore);
                item.setItemMeta(itemmeta);
                inv.setItem(i,item);
            }else{
                item = new ItemStack(Material.TOTEM_OF_UNDYING);
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(ChatColor.RED+data[i][0]);
                ArrayList<String> itemlore = new ArrayList<String>();
                itemlore.add(ChatColor.RED+"Creator: "+ChatColor.BLUE+data[i][2]);
                itemlore.add(ChatColor.RED+"Coordinates: "+ChatColor.BLUE+data[i][1]);
                itemlore.add(ChatColor.RED+"Dimension: "+ChatColor.BLUE+data[i][3]);
                itemmeta.setLore(itemlore);
                item.setItemMeta(itemmeta);
                inv.setItem(i,item);
            }
        }
        inv.setItem(45,previouspage);
        inv.setItem(51,nextpage);
        inv.setItem(52,back);
        inv.setItem(53,close);
    }

    public static ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }
}
