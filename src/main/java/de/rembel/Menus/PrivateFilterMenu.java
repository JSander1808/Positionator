package de.rembel.Menus;

import de.rembel.General.General;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class PrivateFilterMenu {
    public static Inventory inv;

    public PrivateFilterMenu(Player player){
        inv = Bukkit.createInventory(null, 3*9, ChatColor.GOLD+"Private Filter Menu");

        for(int i = 0;i<9*3;i++){
            inv.setItem(i,placeholder());
        }

        ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(ChatColor.GOLD+"Back");
        back.setItemMeta(backmeta);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(ChatColor.RED+"Close");
        close.setItemMeta(closemeta);

        ItemStack FilterPlayername = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta FilterPlayernameMeta = (SkullMeta) FilterPlayername.getItemMeta();
        FilterPlayernameMeta.setOwner(player.getName());
        FilterPlayernameMeta.setDisplayName(ChatColor.GOLD+"Playername");
        ArrayList FilterPlayernameLore = new ArrayList();
        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
            if(General.PrivateFilter.get(player.getUniqueId().toString()).hasPlayername()){
                FilterPlayernameLore.add(ChatColor.GREEN+General.PrivateFilter.get(player.getUniqueId().toString()).getPlayername());
            }
        }
        FilterPlayernameMeta.setLore(FilterPlayernameLore);
        FilterPlayername.setItemMeta(FilterPlayernameMeta);

        ItemStack FilterDimension = new ItemStack(Material.END_PORTAL_FRAME);
        ItemMeta FilterDimensionMeta = FilterDimension.getItemMeta();
        FilterDimensionMeta.setDisplayName(ChatColor.GOLD+"Dimension");
        ArrayList FilterDimensionLore = new ArrayList();
        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
            if(General.PrivateFilter.get(player.getUniqueId().toString()).hasDimension()){
                FilterDimensionLore.add(ChatColor.GREEN+General.PrivateFilter.get(player.getUniqueId().toString()).getDimension());
            }
        }
        FilterDimensionMeta.setLore(FilterDimensionLore);
        FilterDimension.setItemMeta(FilterDimensionMeta);

        inv.setItem(25, back);
        inv.setItem(26, close);
        inv.setItem(12, FilterPlayername);
        inv.setItem(14, FilterDimension);

        player.openInventory(inv);

    }

    public static void PrivatePlayernameFilterMenu(Player player, int page){
        Inventory inventory = Bukkit.createInventory(null, 3*9, ChatColor.GOLD+"Private Playername Filter "+page+" / "+((General.getRegisteredPlayers().size()/(9*3))+1));

        for(int i = 0;i<9*3;i++){
            inventory.setItem(i,placeholder());
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

        ArrayList<String> Players = General.getRegisteredPlayers();
        int multiplier = 18;

        for(int i = 0;i<=17;i++){
            if(Players.size()>i+(multiplier*(page-1))){
                ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta playerSkullMeta = (SkullMeta) playerSkull.getItemMeta();
                playerSkullMeta.setOwner(Players.get(i+(multiplier*(page-1))));
                playerSkullMeta.setDisplayName(ChatColor.GOLD+Players.get(i+(multiplier*(page-1))));
                ArrayList playerSkullLore = new ArrayList();
                if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
                    if(General.PrivateFilter.get(player.getUniqueId().toString()).hasPlayername()){
                        if(General.PrivateFilter.get(player.getUniqueId().toString()).getPlayername().equalsIgnoreCase(Players.get(i+(multiplier*(page-1))))){
                            playerSkullLore.add(ChatColor.GREEN+"Active");
                            playerSkull.addUnsafeEnchantment(Enchantment.LURE,1);
                        }
                    }
                }
                playerSkullMeta.setLore(playerSkullLore);
                playerSkull.setItemMeta(playerSkullMeta);
                inventory.setItem(i, playerSkull);
            }
        }


        inventory.setItem(18, previouspage);
        inventory.setItem(24, nextpage);
        inventory.setItem(25, back);
        inventory.setItem(26, close);

        player.openInventory(inventory);
    }

    public static void PrivateDimensionFilterMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null, 3*9, ChatColor.GOLD+"Private Dimension Filter");

        for(int i = 0;i<9*3;i++){
            inventory.setItem(i,placeholder());
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(ChatColor.RED+"Close");
        close.setItemMeta(closemeta);

        ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(ChatColor.GOLD+"Back");
        back.setItemMeta(backmeta);

        ItemStack overworld = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta overworldMeta = overworld.getItemMeta();
        overworldMeta.setDisplayName(ChatColor.GOLD+"NORMAL");
        ArrayList overworldLore = new ArrayList();
        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
            if(General.PrivateFilter.get(player.getUniqueId().toString()).hasDimension()){
                if(General.PrivateFilter.get(player.getUniqueId().toString()).getDimension().equalsIgnoreCase("NORMAL")){
                    overworldLore.add(ChatColor.GREEN+"Active");
                }
            }
        }
        overworldMeta.setLore(overworldLore);
        overworld.setItemMeta(overworldMeta);

        ItemStack nether = new ItemStack(Material.NETHERRACK);
        ItemMeta netherMeta = nether.getItemMeta();
        netherMeta.setDisplayName(ChatColor.GOLD+"NETHER");
        ArrayList netherLore = new ArrayList();
        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
            if(General.PrivateFilter.get(player.getUniqueId().toString()).hasDimension()){
                if(General.PrivateFilter.get(player.getUniqueId().toString()).getDimension().equalsIgnoreCase("NETHER")){
                    netherLore.add(ChatColor.GREEN+"Active");
                }
            }
        }
        netherMeta.setLore(netherLore);
        nether.setItemMeta(netherMeta);

        ItemStack end = new ItemStack(Material.END_STONE);
        ItemMeta endMeta = end.getItemMeta();
        endMeta.setDisplayName(ChatColor.GOLD+"THE_END");
        ArrayList endLore = new ArrayList();
        if(General.PrivateFilter.containsKey(player.getUniqueId().toString())){
            if(General.PrivateFilter.get(player.getUniqueId().toString()).hasDimension()){
                if(General.PrivateFilter.get(player.getUniqueId().toString()).getDimension().equalsIgnoreCase("THE_END")){
                    endLore.add(ChatColor.GREEN+"Active");
                }
            }
        }
        endMeta.setLore(endLore);
        end.setItemMeta(endMeta);



        inventory.setItem(0,overworld);
        inventory.setItem(1,nether);
        inventory.setItem(2,end);
        inventory.setItem(25, back);
        inventory.setItem(26, close);

        player.openInventory(inventory);
    }

    public static ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }
}
