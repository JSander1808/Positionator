package de.rembel.Menus;

import de.rembel.Config.NormalConfig;
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
        NormalConfig config = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
        Inventory inv = Bukkit.createInventory(null, 9*3, ChatColor.GOLD+"Settings");

        for(int i = 0;i<9*3;i++){
            inv.setItem(i,placeholder());
        }

        ItemStack showDeathInList = new ItemStack(Material.OBSERVER);
        ItemMeta showDeathInListMeta = showDeathInList.getItemMeta();
        showDeathInListMeta.setDisplayName(ChatColor.GOLD+"Show Death's");
        ArrayList showDeathInListLore = new ArrayList();
        if(config.getBoolean("showDeathPositionInList")){
            showDeathInListLore.add(ChatColor.GREEN+"Active");
        }else{
            showDeathInListLore.add(ChatColor.RED+"Inactive");
        }
        showDeathInListLore.add(ChatColor.GRAY+"When active, the deathpoints are");
        showDeathInListLore.add(ChatColor.GRAY+"displayed in the private list.");
        showDeathInListMeta.setLore(showDeathInListLore);
        showDeathInList.setItemMeta(showDeathInListMeta);

        ItemStack showDeathPositionInBossbar = new ItemStack(Material.NETHER_STAR);
        ItemMeta showDeathPositionInBossbarMeta = showDeathPositionInBossbar.getItemMeta();
        showDeathPositionInBossbarMeta.setDisplayName(ChatColor.GOLD+"Death in Bossbar");
        ArrayList showDeathPositionInBossbarLore = new ArrayList();
        if(config.getBoolean("setDeathPositionInBossbar")){
            showDeathPositionInBossbarLore.add(ChatColor.GREEN+"Active");
        }else{
            showDeathPositionInBossbarLore.add(ChatColor.RED+"Inactive");
        }
        showDeathPositionInBossbarLore.add(ChatColor.GRAY+"When active, the death point will automatically");
        showDeathPositionInBossbarLore.add(ChatColor.GRAY+"be displayed in the boss bar when they die.");
        showDeathPositionInBossbarMeta.setLore(showDeathPositionInBossbarLore);
        showDeathPositionInBossbar.setItemMeta(showDeathPositionInBossbarMeta);

        ItemStack enableFilter = new ItemStack(Material.PAPER);
        ItemMeta enableFilterMeta = enableFilter.getItemMeta();
        enableFilterMeta.setDisplayName(ChatColor.GOLD+"Filter");
        ArrayList enableFilterLore = new ArrayList();
        if(config.getBoolean("enableFilter")){
            enableFilterLore.add(ChatColor.GREEN+"Active");
        }else{
            enableFilterLore.add(ChatColor.RED+"Inactive");
        }
        enableFilterLore.add(ChatColor.GRAY+"When active you can access the filters.");
        enableFilterMeta.setLore(enableFilterLore);
        enableFilter.setItemMeta(enableFilterMeta);

        ItemStack menuClickSound = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta menuClickSoundMeta = menuClickSound.getItemMeta();
        menuClickSoundMeta.setDisplayName(ChatColor.GOLD+"Menu Click Sound");
        ArrayList menuClickSoundLore = new ArrayList();
        if(config.getBoolean("enableMenuClickSound")){
            menuClickSoundLore.add(ChatColor.GREEN+"Active");
        }else{
            menuClickSoundLore.add(ChatColor.RED+"Inactive");
        }
        menuClickSoundLore.add(ChatColor.GRAY+"When active, a sound is played with every click.");
        menuClickSoundMeta.setLore(menuClickSoundLore);
        menuClickSound.setItemMeta(menuClickSoundMeta);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(ChatColor.RED+"Close");
        close.setItemMeta(closemeta);

        ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(ChatColor.GOLD+"Back");
        back.setItemMeta(backmeta);

        inv.setItem(0,showDeathInList);
        inv.setItem(1,showDeathPositionInBossbar);
        inv.setItem(2,enableFilter);
        inv.setItem(3, menuClickSound);
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
