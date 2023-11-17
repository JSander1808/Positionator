package de.rembel.Menus;

import de.rembel.Config.NormalConfig;
import de.rembel.Language.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PrivateSettingsMenu {

    public PrivateSettingsMenu(Player player){
        LanguageManager language = new LanguageManager(player);
        NormalConfig config = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
        Inventory inv = Bukkit.createInventory(null, 9*3, language.transalte(71));

        for(int i = 0;i<9*3;i++){
            inv.setItem(i,placeholder());
        }

        ItemStack showDeathInList = new ItemStack(Material.OBSERVER);
        ItemMeta showDeathInListMeta = showDeathInList.getItemMeta();
        showDeathInListMeta.setDisplayName(language.transalte(72));
        ArrayList showDeathInListLore = new ArrayList();
        if(config.getBoolean("showDeathPositionInList")){
            showDeathInListLore.add(language.transalte(66));
        }else{
            showDeathInListLore.add(language.transalte(73));
        }
        showDeathInListLore.add(language.transalte(74));
        showDeathInListLore.add(language.transalte(75));
        showDeathInListMeta.setLore(showDeathInListLore);
        showDeathInList.setItemMeta(showDeathInListMeta);

        ItemStack showDeathPositionInBossbar = new ItemStack(Material.NETHER_STAR);
        ItemMeta showDeathPositionInBossbarMeta = showDeathPositionInBossbar.getItemMeta();
        showDeathPositionInBossbarMeta.setDisplayName(language.transalte(76));
        ArrayList showDeathPositionInBossbarLore = new ArrayList();
        if(config.getBoolean("setDeathPositionInBossbar")){
            showDeathPositionInBossbarLore.add(language.transalte(66));
        }else{
            showDeathPositionInBossbarLore.add(language.transalte(73));
        }
        showDeathPositionInBossbarLore.add(language.transalte(77));
        showDeathPositionInBossbarLore.add(language.transalte(78));
        showDeathPositionInBossbarMeta.setLore(showDeathPositionInBossbarLore);
        showDeathPositionInBossbar.setItemMeta(showDeathPositionInBossbarMeta);

        ItemStack enableFilter = new ItemStack(Material.PAPER);
        ItemMeta enableFilterMeta = enableFilter.getItemMeta();
        enableFilterMeta.setDisplayName(language.transalte(79));
        ArrayList enableFilterLore = new ArrayList();
        if(config.getBoolean("enableFilter")){
            enableFilterLore.add(language.transalte(66));
        }else{
            enableFilterLore.add(language.transalte(73));
        }
        enableFilterLore.add(language.transalte(80));
        enableFilterMeta.setLore(enableFilterLore);
        enableFilter.setItemMeta(enableFilterMeta);

        ItemStack menuClickSound = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta menuClickSoundMeta = menuClickSound.getItemMeta();
        menuClickSoundMeta.setDisplayName(language.transalte(81));
        ArrayList menuClickSoundLore = new ArrayList();
        if(config.getBoolean("enableMenuClickSound")){
            menuClickSoundLore.add(language.transalte(66));
        }else{
            menuClickSoundLore.add(language.transalte(73));
        }
        if(Double.valueOf(config.get("clickSoundPitch")) == 0){
            menuClickSoundLore.add(language.transalte(133)+config.get("clickSoundPitch"));
        }else if(Double.valueOf(config.get("clickSoundPitch")) == 1){
            menuClickSoundLore.add(language.transalte(133)+config.get("clickSoundPitch"));
        }else if(Double.valueOf(config.get("clickSoundPitch")) == 2){
            menuClickSoundLore.add(language.transalte(133)+config.get("clickSoundPitch"));
        }
        menuClickSoundLore.add(language.transalte(82));
        menuClickSoundMeta.setLore(menuClickSoundLore);
        menuClickSound.setItemMeta(menuClickSoundMeta);

        ItemStack broadcaseWhenPositionAdded = new ItemStack(Material.BELL);
        ItemMeta broadcaseWhenPositionAddedMeta = enableFilter.getItemMeta();
        broadcaseWhenPositionAddedMeta.setDisplayName(language.transalte(167));
        ArrayList broadcaseWhenPositionAddedLore = new ArrayList();
        if(config.getBoolean("broadcaseWhenPositionAdded")){
            broadcaseWhenPositionAddedLore.add(language.transalte(66));
        }else{
            broadcaseWhenPositionAddedLore.add(language.transalte(73));
        }
        broadcaseWhenPositionAddedLore.add(language.transalte(168));
        broadcaseWhenPositionAddedMeta.setLore(broadcaseWhenPositionAddedLore);
        broadcaseWhenPositionAdded.setItemMeta(broadcaseWhenPositionAddedMeta);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(language.transalte(10));
        close.setItemMeta(closemeta);

        ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(language.transalte(12));
        back.setItemMeta(backmeta);

        inv.setItem(0,showDeathInList);
        inv.setItem(1,showDeathPositionInBossbar);
        inv.setItem(2,enableFilter);
        inv.setItem(3, menuClickSound);
        inv.setItem(4, broadcaseWhenPositionAdded);
        inv.setItem(25, back);
        inv.setItem(26, close);

        player.openInventory(inv);
    }

    public ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }
}
