package de.rembel.Menus;

import de.rembel.CBossbar.CBossbar;
import de.rembel.Language.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CompassManagerMenu {

    public CompassManagerMenu(Player player){
        LanguageManager language = new LanguageManager(player);
        Inventory inv = Bukkit.createInventory(null, 1*9, language.transalte(142));
        player.openInventory(inv);

        for(int i = 0;i<9*1;i++){
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

        ItemStack compassPositionManager = new ItemStack(Material.CHEST);
        ItemMeta compassPositionManagerMeta = compassPositionManager.getItemMeta();
        compassPositionManagerMeta.setDisplayName(language.transalte(143));
        ArrayList compassPositionManagerLore = new ArrayList();
        compassPositionManagerLore.add(language.transalte(144));
        compassPositionManagerMeta.setLore(compassPositionManagerLore);
        compassPositionManager.setItemMeta(compassPositionManagerMeta);

        ItemStack bossbarSettings = new ItemStack(Material.COMPASS);
        ItemMeta bossbarSettingsMeta = bossbarSettings.getItemMeta();
        bossbarSettingsMeta.setDisplayName(language.transalte(150));
        ArrayList bossbarSettingsLore = new ArrayList();
        bossbarSettingsLore.add(language.transalte(151));
        bossbarSettingsMeta.setLore(bossbarSettingsLore);
        bossbarSettings.setItemMeta(bossbarSettingsMeta);



        inv.setItem(1, compassPositionManager);
        inv.setItem(4, bossbarSettings);
        inv.setItem(7, back);
        inv.setItem(8, close);
    }

    public ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }
}
