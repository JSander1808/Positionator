package de.rembel.Menus;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.*;
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

public class PrivateMenu {
    public static Inventory inv;

    public PrivateMenu(Player player, int page){
        LanguageManager language = new LanguageManager(player);
        Config config = new Config("plugins//Positionator//Data//User//"+ player.getUniqueId().toString()+"//data.conf");
        inv = Bukkit.createInventory(null,9*6, language.transalte(26)+page+" / "+((config.list(General.PrivateFilter.get(player.getUniqueId().toString()), player).length/(9*5))+1));
        player.openInventory(inv);

        for(int i = 0;i<9*6;i++){
            inv.setItem(i,placeholder());
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(language.transalte(10));
        close.setItemMeta(closemeta);

        ItemStack previouspage = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzYjJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ==");
//        ItemStack previouspage = new ItemStack(Material.SPRUCE_SIGN);
        SkullMeta previousmeta = (SkullMeta) previouspage.getItemMeta();
        previousmeta.setDisplayName(language.transalte(11));
        previouspage.setItemMeta(previousmeta);

        ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(language.transalte(12));
        back.setItemMeta(backmeta);

        ItemStack search = new ItemStack(Material.SPYGLASS);
        ItemMeta searchMeta = search.getItemMeta();
        searchMeta.setDisplayName(language.transalte(191));
        ArrayList<String> searchLore = new ArrayList<String>();
        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
            if(General.PrivateFilter.get(player.getUniqueId().toString()).hasName()){
                searchLore.add(language.transalte(193)+General.PrivateFilter.get(player.getUniqueId().toString()).getName());
            }
        }
        searchLore.add(ChatColor.DARK_GRAY+language.transalte(194));
        searchLore.add(ChatColor.DARK_GRAY+language.transalte(195));
        searchMeta.setLore(searchLore);
        search.setItemMeta(searchMeta);

        ItemStack nextpage = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTliZjMyOTJlMTI2YTEwNWI1NGViYTcxM2FhMWIxNTJkNTQxYTFkODkzODgyOWM1NjM2NGQxNzhlZDIyYmYifX19");
//        ItemStack nextpage = new ItemStack(Material.SPRUCE_SIGN);
        SkullMeta nextmeta = (SkullMeta) nextpage.getItemMeta();
        nextmeta.setDisplayName(language.transalte(13));
        nextpage.setItemMeta(nextmeta);

        ItemStack filter = new ItemStack(Material.PAPER);

        ItemMeta filterMeta = filter.getItemMeta();
        filterMeta.setDisplayName(language.transalte(14));
        ArrayList filterLore = new ArrayList();
        filterLore.add(language.transalte(15));
        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
            filter.addUnsafeEnchantment(Enchantment.LURE, 1);
            filterMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            PositionFilter filterData = General.PrivateFilter.get(player.getUniqueId().toString());
            if(filterData.hasPlayername()){
                filterLore.add(language.transalte(16)+"("+filterData.getPlayername()+")");
            }
            if(filterData.hasDimension()){
                filterLore.add(language.transalte(17)+"("+filterData.getDimension()+")");
            }
            if(filterData.hasDistance()){
                filterLore.add(language.transalte(188)+"( < "+filterData.getDistance()+" )");
            }
            if(filterData.hasName()){
                filterLore.add(language.transalte(193)+filterData.getName());
            }
        }else{
            filterLore.add(language.transalte(18));
        }
        filterLore.add(" ");
        filterLore.add(language.transalte(19));
        filterLore.add(language.transalte(20));
        filterMeta.setLore(filterLore);
        filter.setItemMeta(filterMeta);

        NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
        Position[] data = config.list(General.PrivateFilter.get(player.getUniqueId().toString()), player);

        int multiplierer = 44;

        for(int i = 0;i<=44;i++){
            if((i+(multiplierer*(page-1))) < data.length){
                if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.DEATHPOSITION){
                        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
                        ItemMeta itemmeta = item.getItemMeta();
                        itemmeta.setDisplayName(ChatColor.RED+data[i+(multiplierer*(page-1))].getName());
                        ArrayList<String> itemlore = new ArrayList<String>();
                        itemlore.add(language.transalte(27)+ChatColor.BLUE+data[i+(multiplierer*(page-1))].getCreator());
                        if(player.getWorld().getEnvironment().name().equalsIgnoreCase(data[i+(multiplierer*(page-1))].getDimension())){
                            itemlore.add(language.transalte(28)+ChatColor.BLUE+data[i+(multiplierer*(page-1))].getPositionAsString()+" ("+(int) player.getLocation().distance(data[i+(multiplierer*(page-1))].getLocation())+")");
                        }else{
                            itemlore.add(language.transalte(28)+ChatColor.BLUE+data[i+(multiplierer*(page-1))].getPositionAsString());
                        }
                        itemlore.add(language.transalte(29)+ChatColor.BLUE+data[i+(multiplierer*(page-1))].getDimension());
                        itemlore.add(" ");
                        itemlore.add(language.transalte(24));
                        itemlore.add(language.transalte(25));
                        itemmeta.setLore(itemlore);
                        item.setItemMeta(itemmeta);
                        inv.setItem(i,item);
                }else{
                    ItemStack item = null;
                    if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.CHESTPOSITION){
                        item = new ItemStack(Material.CHEST);
                    }else  if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.FURNACEPOSITION){
                        item = new ItemStack(Material.FURNACE);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.ENCHANTPOSITION){
                        item = new ItemStack(Material.ENCHANTING_TABLE);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.CRAFTINGPOSITION){
                        item = new ItemStack(Material.CRAFTING_TABLE);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.SEALANTERNPOSITION){
                        item = new ItemStack(Material.SEA_LANTERN);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.CRYINGOBSIDIANPOSITION){
                        item = new ItemStack(Material.CRYING_OBSIDIAN);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.CAMPFIREPOSITION){
                        item = new ItemStack(Material.CAMPFIRE);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.ANVILPOSITION){
                        item = new ItemStack(Material.ANVIL);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.COMPOSTERPOSITION){
                        item = new ItemStack(Material.COMPOSTER);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.BEEPOSITION){
                        item = new ItemStack(Material.BEE_NEST);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.BOOKSHELFPOSITION){
                        item = new ItemStack(Material.BOOKSHELF);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.ENDPORTALPOSITION){
                        item = new ItemStack(Material.END_PORTAL_FRAME);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.EMERALDPOSITION){
                        item = new ItemStack(Material.EMERALD_BLOCK);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.IRONPOSITION){
                        item = new ItemStack(Material.IRON_BLOCK);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.BEACONPOSITION){
                        item = new ItemStack(Material.BEACON);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.SPAWNERPOSITION){
                        item = new ItemStack(Material.SPAWNER);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.BLAZEPOWDERPOSITION){
                        item = new ItemStack(Material.BLAZE_POWDER);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.GOLDPOSITION){
                        item = new ItemStack(Material.GOLD_BLOCK);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.SKULKSKRIEKERPOSITION){
                        item = new ItemStack(Material.SCULK_SHRIEKER);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.REDSTONEPOSITION){
                        item = new ItemStack(Material.REDSTONE);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.BOATPOSITION){
                        item = new ItemStack(Material.OAK_BOAT);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.MINECARTPOSITION){
                        item = new ItemStack(Material.MINECART);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.AZALEAPOSITION){
                        item = new ItemStack(Material.FLOWERING_AZALEA);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.SPONGEPOSITION){
                        item = new ItemStack(Material.SPONGE);
                    }else if(Integer.valueOf(data[i+(multiplierer*(page-1))].getType()) == PositionType.BELLPOSITION){
                        item = new ItemStack(Material.BELL);
                    }
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(ChatColor.GOLD+data[i+(multiplierer*(page-1))].getName());
                    itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    itemmeta.addItemFlags(ItemFlag.HIDE_DYE);
                    itemmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                    itemmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    itemmeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
                    itemmeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    ArrayList<String> itemlore = new ArrayList<String>();
                    itemlore.add(language.transalte(21)+ChatColor.BLUE+data[i+(multiplierer*(page-1))].getCreator());
                    if(player.getWorld().getEnvironment().name().equalsIgnoreCase(data[i+(multiplierer*(page-1))].getDimension())){
                        itemlore.add(language.transalte(22)+ChatColor.BLUE+data[i+(multiplierer*(page-1))].getPositionAsString()+" ("+(int) player.getLocation().distance(data[i+(multiplierer*(page-1))].getLocation())+")");
                    }else{
                        itemlore.add(language.transalte(22)+ChatColor.BLUE+data[i+(multiplierer*(page-1))].getPositionAsString());
                    }
                    itemlore.add(language.transalte(23)+ChatColor.BLUE+data[i+(multiplierer*(page-1))].getDimension());
                    itemlore.add(" ");
                    itemlore.add(language.transalte(24));
                    itemlore.add(language.transalte(25));
                    itemlore.add(language.transalte(137));
                    itemmeta.setLore(itemlore);
                    item.setItemMeta(itemmeta);
                    inv.setItem(i,item);
                }
            }
        }
        inv.setItem(45,previouspage);
        if(normalConfig.getBoolean("enableFilter")) inv.setItem(47, filter);
        inv.setItem(49, search);
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
