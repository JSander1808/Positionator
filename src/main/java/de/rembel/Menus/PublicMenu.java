package de.rembel.Menus;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.General.PositionFilter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.IOException;
import java.util.ArrayList;

public class PublicMenu {
    public static Inventory inv;

    public PublicMenu(Player player, int page){
        Config config = new Config("plugins//Positionator//Data//public.conf");
        NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
        inv = Bukkit.createInventory(null,9*6, ChatColor.GOLD+"Public Liste - Page "+page+" / "+((config.list(General.PublicFilter.get(player.getUniqueId().toString())).length/(9*5))+1));
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
        previousmeta.setDisplayName(ChatColor.GOLD+"Previous Page");
        previouspage.setItemMeta(previousmeta);

        ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(ChatColor.GOLD+"Back");
        back.setItemMeta(backmeta);

        ItemStack nextpage = new ItemStack(Material.SPRUCE_SIGN);
        ItemMeta nextmeta = nextpage.getItemMeta();
        nextmeta.setDisplayName(ChatColor.GOLD+"Next Page");
        nextpage.setItemMeta(nextmeta);

        ItemStack filter = new ItemStack(Material.PAPER);
        ItemMeta filterMeta = filter.getItemMeta();
        filterMeta.setDisplayName(ChatColor.GOLD+"Filter");
        ArrayList filterLore = new ArrayList();
        filterLore.add(ChatColor.GRAY+"Active filters:");
        if(General.PublicFilter.containsKey(player.getUniqueId().toString())){
            filter.addUnsafeEnchantment(Enchantment.LURE, 1);
            filterMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            PositionFilter filterData = General.PublicFilter.get(player.getUniqueId().toString());
            if(filterData.hasPlayername()){
                filterLore.add(ChatColor.GREEN+"-Player ("+filterData.getPlayername()+")");
            }
            if(filterData.hasDimension()){
                filterLore.add(ChatColor.GREEN+"-Dimension ("+filterData.getDimension()+")");
            }
        }else{
            filterLore.add(ChatColor.GRAY+"No active filters!");
        }
        filterLore.add(" ");
        filterLore.add(ChatColor.DARK_GRAY+"Left-Click: Edit filters");
        filterLore.add(ChatColor.DARK_GRAY+"Right-Click: Reset all active filters");
        filterMeta.setLore(filterLore);
        filter.setItemMeta(filterMeta);

        String[][] data = config.list(General.PublicFilter.get(player.getUniqueId().toString()));

        int multiplierer = 44;

        for(int i = 0;i<=44;i++){
            if((i+(multiplierer*(page-1)))< data.length){
                if(Integer.valueOf(data[i+(multiplierer*(page-1))][4])==0){
                    ItemStack item = new ItemStack(Material.CHEST);
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(ChatColor.GOLD+data[i+(multiplierer*(page-1))][0]);
                    ArrayList<String> itemlore = new ArrayList<String>();
                    itemlore.add(ChatColor.GREEN+"Creator: "+ChatColor.BLUE+data[i+(multiplierer*(page-1))][2]);
                    itemlore.add(ChatColor.GREEN+"Coordinates: "+ChatColor.BLUE+data[i+(multiplierer*(page-1))][1]);
                    itemlore.add(ChatColor.GREEN+"Dimension: "+ChatColor.BLUE+data[i+(multiplierer*(page-1))][3]);
                    itemmeta.setLore(itemlore);
                    item.setItemMeta(itemmeta);
                    inv.setItem(i,item);
                }else{
                    ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(ChatColor.RED+data[i+(multiplierer*(page-1))][0]);
                    ArrayList<String> itemlore = new ArrayList<String>();
                    itemlore.add(ChatColor.RED+"Creator: "+ChatColor.BLUE+data[i+(multiplierer*(page-1))][2]);
                    itemlore.add(ChatColor.RED+"Coordinates: "+ChatColor.BLUE+data[i+(multiplierer*(page-1))][1]);
                    itemlore.add(ChatColor.RED+"Dimension: "+ChatColor.BLUE+data[i+(multiplierer*(page-1))][3]);
                    itemmeta.setLore(itemlore);
                    item.setItemMeta(itemmeta);
                    inv.setItem(i,item);
                }
            }
        }
        inv.setItem(45, previouspage);
        if(normalConfig.getBoolean("enableFilter")) inv.setItem(48, filter);
        inv.setItem(51, nextpage);
        inv.setItem(52, back);
        inv.setItem(53, close);
    }

    public static ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }
}
