package de.rembel.Menus;

import de.rembel.CBossbar.CBossbar;
import de.rembel.CBossbar.CPosition;
import de.rembel.General.General;
import de.rembel.General.Position;
import de.rembel.Language.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CompassPositionManagerMenu {

    public CompassPositionManagerMenu(Player player, int page){
        CBossbar compass = CBossbar.getByPlayer(player);
        LanguageManager language = new LanguageManager(player);
        Inventory inv = Bukkit.createInventory(null, 3*9, language.transalte(158) + page + " / " + (((compass != null ? compass.getPositions().size() : 1)/(2*9))+1));
        player.openInventory(inv);

        for(int i = 0;i<9*3;i++){
            inv.setItem(i,placeholder());
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(language.transalte(10));
        close.setItemMeta(closemeta);

        ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(language.transalte(12));
        back.setItemMeta(backmeta);

        ItemStack previouspage = new ItemStack(Material.SPRUCE_SIGN);
        ItemMeta previousmeta = previouspage.getItemMeta();
        previousmeta.setDisplayName(language.transalte(11));
        previouspage.setItemMeta(previousmeta);

        ItemStack nextpage = new ItemStack(Material.SPRUCE_SIGN);
        ItemMeta nextmeta = nextpage.getItemMeta();
        nextmeta.setDisplayName(language.transalte(13));
        nextpage.setItemMeta(nextmeta);

        if(compass != null){
            List<CPosition> positions = compass.getPositions();
            for(int i = 0;i<18;i++){
                if(positions.size()>i+((page-1)*18)){
                    ItemStack item = new ItemStack(Material.CHEST);
                    ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.setDisplayName(ChatColor.GOLD+positions.get(i+((page-1)*18)).getDescription());
                    ArrayList itemLore = new ArrayList();
                    //"⌖", "💀", "🏠", "⚠️", "📅"
                    itemLore.add(language.transalte(159)+ChatColor.GRAY+positions.get(i+((page-1)*18)).getSymbol());
                    itemLore.add(language.transalte(160)+positions.get(i+((page-1)*18)).getColor()+"■");
                    itemLore.add(" ");
                    itemLore.add(language.transalte(161));
                    itemLore.add(language.transalte(162));
                    itemLore.add(language.transalte(163));
                    itemMeta.setLore(itemLore);
                    item.setItemMeta(itemMeta);
                    inv.setItem(i, item);
                }
            }
        }

        inv.setItem(18, previouspage);
        inv.setItem(24, nextpage);
        inv.setItem(25, back);
        inv.setItem(26, close);
    }

    public static ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }
}
