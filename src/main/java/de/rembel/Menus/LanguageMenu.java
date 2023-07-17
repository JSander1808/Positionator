package de.rembel.Menus;

import de.rembel.General.SkullCreator;
import de.rembel.Language.LanguageContainer.EnglishLanguageContainer;
import de.rembel.Language.LanguageContainer.FrenchLanguageContainer;
import de.rembel.Language.LanguageContainer.GermanLanguageContainer;
import de.rembel.Language.LanguageContainer.NorwegianLanguageContainer;
import de.rembel.Language.LanguageManager;
import de.rembel.Language.Languages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class LanguageMenu {
    
    public LanguageMenu(Player player){
        LanguageManager language = new LanguageManager(player);
        Inventory inv = Bukkit.createInventory(null, 3*9, language.transalte(121));
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
        
        ItemStack english = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODgzMWM3M2Y1NDY4ZTg4OGMzMDE5ZTI4NDdlNDQyZGZhYTg4ODk4ZDUwY2NmMDFmZDJmOTE0YWY1NDRkNTM2OCJ9fX0=");
        SkullMeta englishMeta = (SkullMeta) english.getItemMeta();
        englishMeta.setDisplayName(ChatColor.GOLD+"English");
        ArrayList englishLore = new ArrayList();
        if(language.getLanguage().equals(Languages.ENGLISH)){
            englishLore.add(language.transalte(66));
            englishLore.add(" ");
        }
        englishLore.add(ChatColor.GOLD+"100% "+language.transalte(123));
        englishMeta.setLore(englishLore);
        english.setItemMeta(englishMeta);

        ItemStack german = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWU3ODk5YjQ4MDY4NTg2OTdlMjgzZjA4NGQ5MTczZmU0ODc4ODY0NTM3NzQ2MjZiMjRiZDhjZmVjYzc3YjNmIn19fQ==");
        SkullMeta germanMeta = (SkullMeta) german.getItemMeta();
        germanMeta.setDisplayName(ChatColor.GOLD+"Deutsch");
        ArrayList germanLore = new ArrayList();
        if(language.getLanguage().equals(Languages.GERMAN)){
            germanLore.add(language.transalte(66));
            germanLore.add(" ");
        }
        germanLore.add(ChatColor.GOLD+""+(GermanLanguageContainer.translation.size()*100/EnglishLanguageContainer.translation.size()-2)+"% "+language.transalte(123));
        germanMeta.setLore(germanLore);
        german.setItemMeta(germanMeta);

        ItemStack french = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTEyNjlhMDY3ZWUzN2U2MzYzNWNhMWU3MjNiNjc2ZjEzOWRjMmRiZGRmZjk2YmJmZWY5OWQ4YjM1Yzk5NmJjIn19fQ==");
        SkullMeta frenchMeta = (SkullMeta) french.getItemMeta();
        frenchMeta.setDisplayName(ChatColor.GOLD+"Français");
        ArrayList frenchLore = new ArrayList();
        if(language.getLanguage().equals(Languages.FRENCH)){
            frenchLore.add(language.transalte(66));
            frenchLore.add(" ");
        }
        frenchLore.add(ChatColor.GOLD+""+(FrenchLanguageContainer.translation.size()*100/EnglishLanguageContainer.translation.size()-2)+"% "+language.transalte(123));
        frenchMeta.setLore(frenchLore);
        french.setItemMeta(frenchMeta);

        ItemStack norwegian = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTA1OTZlMTY1ZWMzZjM4OWI1OWNmZGRhOTNkZDZlMzYzZTk3ZDljNjQ1NmU3YzJlMTIzOTczZmE2YzVmZGEifX19");
        SkullMeta norwegianMeta = (SkullMeta) norwegian.getItemMeta();
        norwegianMeta.setDisplayName(ChatColor.GOLD+"Norsk");
        ArrayList norwegianLore = new ArrayList();
        if(language.getLanguage().equals(Languages.NORWEGIAN)){
            norwegianLore.add(language.transalte(66));
            norwegianLore.add(" ");
        }
        norwegianLore.add(ChatColor.GOLD+""+(NorwegianLanguageContainer.translation.size()*100/EnglishLanguageContainer.translation.size()-2)+"% "+language.transalte(123));
        norwegianMeta.setLore(norwegianLore);
        norwegian.setItemMeta(norwegianMeta);

        ItemStack finnish = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTlmMjM0OTcyOWE3ZWM4ZDRiMTQ3OGFkZmU1Y2E4YWY5NjQ3OWU5ODNmYmFkMjM4Y2NiZDgxNDA5YjRlZCJ9fX0=");
        SkullMeta finnishMeta = (SkullMeta) finnish.getItemMeta();
        finnishMeta.setDisplayName(ChatColor.GOLD+"Suomen");
        ArrayList finnishLore = new ArrayList();
        finnishLore.add(language.transalte(122));
        if(language.getLanguage().equals(Languages.FINNISH)){
            finnishLore.add(language.transalte(66));
            finnishLore.add(" ");
        }
        finnishLore.add(ChatColor.GOLD+"0% "+language.transalte(123));
        finnishMeta.setLore(finnishLore);
        finnish.setItemMeta(finnishMeta);
        
        
        
        inv.setItem(25, back);
        inv.setItem(26, close);
        inv.setItem(11, english);
        inv.setItem(12, german);
        inv.setItem(13, french);
        inv.setItem(14, norwegian);
        inv.setItem(15, finnish);

    }

    public static ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }
}
