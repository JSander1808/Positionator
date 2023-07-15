package de.rembel.Menus;

import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BackUpMenu {

    public BackUpMenu(Player player, int page){
        Inventory inv = Bukkit.createInventory(null, 9*6, ChatColor.GOLD+"BackUps - Page "+page+" / "+((new File("plugins//Positionator_BackUp//").listFiles().length/(9*5))+1));

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

        ItemStack nextpage = new ItemStack(Material.SPRUCE_SIGN);
        ItemMeta nextmeta = nextpage.getItemMeta();
        nextmeta.setDisplayName(ChatColor.GOLD+"Next Page");
        nextpage.setItemMeta(nextmeta);

        ItemStack createBackUp = new ItemStack(Material.RESPAWN_ANCHOR);
        ItemMeta createBackUpMeta = createBackUp.getItemMeta();
        createBackUpMeta.setDisplayName(ChatColor.GOLD+"Create BackUp");
        createBackUp.setItemMeta(createBackUpMeta);

        ItemStack shortInfo = new ItemStack(Material.PAPER);
        ItemMeta shortInfoMeta = shortInfo.getItemMeta();
        shortInfoMeta.setDisplayName(ChatColor.GOLD+"Short Info");
        ArrayList shortInfoLore = new ArrayList();
        shortInfoLore.add(ChatColor.GREEN+"BackUps: "+ChatColor.BLUE+new File("plugins//Positionator_BackUp//").listFiles().length);
        shortInfoLore.add(ChatColor.GREEN+"Size of all BackUps: "+ChatColor.BLUE+(folderSize(new File("plugins//Positionator_BackUp//")) / 1024) + " KB");
        shortInfoMeta.setLore(shortInfoLore);
        shortInfo.setItemMeta(shortInfoMeta);


        for(int i = 0;i<45;i++){
            if(new File("plugins//Positionator_BackUp//").listFiles().length>i+((page-1)*45)){
                String backUpId = new File("plugins//Positionator_BackUp//").listFiles()[i+((page-1)*45)].getName();
                NormalConfig config = new NormalConfig("plugins//Positionator_BackUp//"+backUpId+"//config.yml");
                ItemStack backup = new ItemStack(Material.SMITHING_TABLE);
                ItemMeta backupMeta = backup.getItemMeta();
                backupMeta.setDisplayName(ChatColor.GOLD+backUpId);
                ArrayList backupLore = new ArrayList();
                String[] date = config.get("date").split("\\.");
                backupLore.add(ChatColor.GREEN+"Created: "+ChatColor.BLUE+date[0]+"."+date[1]+"."+date[2]+"  "+date[3]+":"+date[4]+":"+date[5]);
                backupLore.add(ChatColor.GREEN+"Plugin Version: "+ChatColor.BLUE+config.get("version"));
                backupLore.add(ChatColor.GREEN+"Creator: "+ChatColor.BLUE+config.get("creator"));
                backupLore.add(ChatColor.GREEN+"Reason: "+ChatColor.BLUE+config.get("reason"));
                backupLore.add(ChatColor.GREEN+"Size: "+ChatColor.BLUE+config.get("size"));
                backupLore.add(" ");
                backupLore.add(ChatColor.DARK_GRAY+"Left-Click: Load BackUp");
                backupLore.add(ChatColor.DARK_GRAY+"Right-Click: Delete BackUp");
                backupMeta.setLore(backupLore);
                backup.setItemMeta(backupMeta);
                inv.setItem(i, backup);
            }
        }

        inv.setItem(52, createBackUp);
        inv.setItem(48, shortInfo);
        inv.setItem(45,previouspage);
        inv.setItem(51,nextpage);
        inv.setItem(53,close);

        player.openInventory(inv);
    }

    public static ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }

    public static long folderSize(File directory) {
        long length = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile())
                length += file.length();
            else
                length += folderSize(file);
        }
        return length;
    }
}
