package de.rembel.Menus;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.General.PositionFilter;
import de.rembel.Language.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
        LanguageManager language = new LanguageManager(player);
        Config config = new Config("plugins//Positionator//Data//public.conf");
        NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
        inv = Bukkit.createInventory(null,9*6, language.transalte(9)+page+" / "+((config.list(General.PublicFilter.get(player.getUniqueId().toString())).length/(9*5))+1));
        player.openInventory(inv);

        for(int i = 0;i<9*6;i++){
            inv.setItem(i,placeholder());
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(language.transalte(10));
        close.setItemMeta(closemeta);

        ItemStack previouspage = new ItemStack(Material.SPRUCE_SIGN);
        ItemMeta previousmeta = previouspage.getItemMeta();
        previousmeta.setDisplayName(language.transalte(11));
        previouspage.setItemMeta(previousmeta);

        ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(language.transalte(12));
        back.setItemMeta(backmeta);

        ItemStack nextpage = new ItemStack(Material.SPRUCE_SIGN);
        ItemMeta nextmeta = nextpage.getItemMeta();
        nextmeta.setDisplayName(language.transalte(13));
        nextpage.setItemMeta(nextmeta);

        ItemStack filter = new ItemStack(Material.PAPER);
        ItemMeta filterMeta = filter.getItemMeta();
        filterMeta.setDisplayName(language.transalte(14));
        ArrayList filterLore = new ArrayList();
        filterLore.add(language.transalte(15));
        if(General.PublicFilter.containsKey(player.getUniqueId().toString())){
            filter.addUnsafeEnchantment(Enchantment.LURE, 1);
            filterMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            PositionFilter filterData = General.PublicFilter.get(player.getUniqueId().toString());
            if(filterData.hasPlayername()){
                filterLore.add(language.transalte(16)+"("+filterData.getPlayername()+")");
            }
            if(filterData.hasDimension()){
                filterLore.add(language.transalte(17)+"("+filterData.getDimension()+")");
            }
        }else{
            filterLore.add(language.transalte(18));
        }
        filterLore.add(" ");
        filterLore.add(language.transalte(19));
        filterLore.add(language.transalte(20));
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
                    itemlore.add(language.transalte(21)+ChatColor.BLUE+data[i+(multiplierer*(page-1))][2]);
                    String[] cords = data[i+(multiplierer*(page-1))][1].split(" ");
                    if(player.getWorld().getEnvironment().name().equalsIgnoreCase(data[i+(multiplierer*(page-1))][3])){
                        Location targetPoint = new Location(player.getWorld(), Double.valueOf(cords[0]), Double.valueOf(cords[1]), Double.valueOf(cords[2]));
                        itemlore.add(language.transalte(22)+ChatColor.BLUE+data[i+(multiplierer*(page-1))][1]+" ("+(int) player.getLocation().distance(targetPoint)+")");
                    }else{
                        itemlore.add(language.transalte(22)+ChatColor.BLUE+data[i+(multiplierer*(page-1))][1]);
                    }
                    itemlore.add(language.transalte(23)+ChatColor.BLUE+data[i+(multiplierer*(page-1))][3]);
                    itemlore.add(" ");
                    itemlore.add(language.transalte(24));
                    itemlore.add(language.transalte(25));
                    itemmeta.setLore(itemlore);
                    item.setItemMeta(itemmeta);
                    inv.setItem(i,item);
                }else{
                    ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(ChatColor.RED+data[i+(multiplierer*(page-1))][0]);
                    ArrayList<String> itemlore = new ArrayList<String>();
                    itemlore.add(language.transalte(21)+ChatColor.BLUE+data[i+(multiplierer*(page-1))][2]);
                    String[] cords = data[i+(multiplierer*(page-1))][1].split(" ");
                    if(player.getWorld().getEnvironment().name().equalsIgnoreCase(data[i+(multiplierer*(page-1))][3])){
                        Location targetPoint = new Location(player.getWorld(), Double.valueOf(cords[0]), Double.valueOf(cords[1]), Double.valueOf(cords[2]));
                        itemlore.add(language.transalte(22)+ChatColor.BLUE+data[i+(multiplierer*(page-1))][1]+" ("+(int) player.getLocation().distance(targetPoint)+")");
                    }else{
                        itemlore.add(language.transalte(22)+ChatColor.BLUE+data[i+(multiplierer*(page-1))][1]);
                    }
                    itemlore.add(language.transalte(23)+ChatColor.BLUE+data[i+(multiplierer*(page-1))][3]);
                    itemlore.add(" ");
                    itemlore.add(language.transalte(24));
                    itemlore.add(language.transalte(25));
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
