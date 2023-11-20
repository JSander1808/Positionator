package de.rembel.Menus;

import de.rembel.Config.NormalConfig;
import de.rembel.Language.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CompassCustomizerMenu {

    public CompassCustomizerMenu(Player player){
        LanguageManager language = new LanguageManager(player);
        NormalConfig config = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
        Inventory inv = Bukkit.createInventory(null, 1*9, language.transalte(150));
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

        ItemStack place = new ItemStack(Material.NAME_TAG);
        ItemMeta placeMeta = place.getItemMeta();
        placeMeta.setDisplayName(language.transalte(152));
        ArrayList placeLore = new ArrayList();
        placeLore.add(language.transalte(153)+config.get("compassPlaceholder"));
        placeLore.add(language.transalte(157));
        placeMeta.setLore(placeLore);
        place.setItemMeta(placeMeta);

        ItemStack color = null;
        if(config.get("compassBossbarColor").equals("white")) color = new ItemStack(Material.WHITE_WOOL);
        if(config.get("compassBossbarColor").equals("red")) color = new ItemStack(Material.RED_WOOL);
        if(config.get("compassBossbarColor").equals("green")) color = new ItemStack(Material.GREEN_WOOL);
        if(config.get("compassBossbarColor").equals("blue")) color = new ItemStack(Material.BLUE_WOOL);
        if(config.get("compassBossbarColor").equals("purple")) color = new ItemStack(Material.PURPLE_WOOL);
        if(config.get("compassBossbarColor").equals("yellow")) color = new ItemStack(Material.YELLOW_WOOL);
        if(config.get("compassBossbarColor").equals("pink")) color = new ItemStack(Material.PINK_WOOL);
        ItemMeta colorMeta = color.getItemMeta();
        colorMeta.setDisplayName(language.transalte(154));
        ArrayList colorLore = new ArrayList();
        if(config.get("compassBossbarColor").equals("white")) colorLore.add(language.transalte(155)+ ChatColor.WHITE+"■");
        if(config.get("compassBossbarColor").equals("red")) colorLore.add(language.transalte(155)+ ChatColor.RED+"■");
        if(config.get("compassBossbarColor").equals("green")) colorLore.add(language.transalte(155)+ ChatColor.GREEN+"■");
        if(config.get("compassBossbarColor").equals("blue")) colorLore.add(language.transalte(155)+ ChatColor.BLUE+"■");
        if(config.get("compassBossbarColor").equals("purple")) colorLore.add(language.transalte(155)+ ChatColor.DARK_PURPLE+"■");
        if(config.get("compassBossbarColor").equals("yellow")) colorLore.add(language.transalte(155)+ ChatColor.YELLOW+"■");
        if(config.get("compassBossbarColor").equals("pink")) colorLore.add(language.transalte(155)+ ChatColor.LIGHT_PURPLE+"■");
        colorLore.add(language.transalte(157));
        colorMeta.setLore(colorLore);
        color.setItemMeta(colorMeta);

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.setDisplayName(language.transalte(156));
        ArrayList compassLore = new ArrayList();
        if(config.getBoolean("compassAlwaysActive")) {
            compassLore.add(ChatColor.GREEN+"➜ "+language.transalte(66));
        }else{
            compassLore.add(ChatColor.GRAY+"   "+language.transalte(66));
        }
        if(!config.getBoolean("compassAlwaysActive")) {
            compassLore.add(ChatColor.GREEN+"➜ "+ChatColor.RED+language.transalte(73));
        }else{
            compassLore.add(ChatColor.GRAY+"   "+language.transalte(73));
        }
        compassMeta.setLore(compassLore);
        compass.setItemMeta(compassMeta);

        inv.setItem(1, place);
        inv.setItem(3, color);
        inv.setItem(5, compass);
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
