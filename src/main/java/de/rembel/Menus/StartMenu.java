package de.rembel.Menus;

import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.General.SkullCreator;
import de.rembel.Language.LanguageManager;
import de.rembel.Language.Languages;
import org.bukkit.*;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class StartMenu {

    public StartMenu(Player player){
        LanguageManager language = new LanguageManager(player);
        Inventory inv = Bukkit.createInventory(null, 9*1, language.transalte(8));
        for(int i = 0;i<9*1;i++){
            inv.setItem(i,placeholder());
        }

        ItemStack publicList = new ItemStack(Material.CHEST);
        ItemMeta publicListMeta = publicList.getItemMeta();
        publicListMeta.setDisplayName(language.transalte(1));
        ArrayList<String> publicListlore = new ArrayList<String>();
        publicListlore.add(language.transalte(2));
        publicListMeta.setLore(publicListlore);
        publicList.setItemMeta(publicListMeta);

        ItemStack privateList = new ItemStack(Material.ENDER_CHEST);
        ItemMeta privateListMeta = publicList.getItemMeta();
        privateListMeta.setDisplayName(language.transalte(3));
        ArrayList<String> privateListlore = new ArrayList<String>();
        privateListlore.add(language.transalte(4));
        privateListMeta.setLore(privateListlore);
        privateList.setItemMeta(privateListMeta);

        ItemStack clearBossBar = new ItemStack(Material.COMPASS);
        ItemMeta clearBossBarMeta = clearBossBar.getItemMeta();
        clearBossBarMeta.setDisplayName(language.transalte(5));
        clearBossBar.setItemMeta(clearBossBarMeta);

        ItemStack privateSettings = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta privateSettingsMeta = privateSettings.getItemMeta();
        privateSettingsMeta.setDisplayName(language.transalte(6));
        privateSettings.setItemMeta(privateSettingsMeta);

        ItemStack publicSettings = new ItemStack(Material.ANVIL);
        ItemMeta publicSettingsMeta = publicSettings.getItemMeta();
        publicSettingsMeta.setDisplayName(language.transalte(7));
        publicSettings.setItemMeta(publicSettingsMeta);

        ItemStack languageItem = null;
        if(language.getLanguage().equals(Languages.GERMAN)){
            languageItem = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWU3ODk5YjQ4MDY4NTg2OTdlMjgzZjA4NGQ5MTczZmU0ODc4ODY0NTM3NzQ2MjZiMjRiZDhjZmVjYzc3YjNmIn19fQ==");
        }else if(language.getLanguage().equals(Languages.ENGLISH)){
            languageItem = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODgzMWM3M2Y1NDY4ZTg4OGMzMDE5ZTI4NDdlNDQyZGZhYTg4ODk4ZDUwY2NmMDFmZDJmOTE0YWY1NDRkNTM2OCJ9fX0=");
        }else if(language.getLanguage().equals(Languages.FRENCH)){
            languageItem = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTEyNjlhMDY3ZWUzN2U2MzYzNWNhMWU3MjNiNjc2ZjEzOWRjMmRiZGRmZjk2YmJmZWY5OWQ4YjM1Yzk5NmJjIn19fQ==");
        }else if(language.getLanguage().equals(Languages.NORWEGIAN)){
            languageItem = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTA1OTZlMTY1ZWMzZjM4OWI1OWNmZGRhOTNkZDZlMzYzZTk3ZDljNjQ1NmU3YzJlMTIzOTczZmE2YzVmZGEifX19");
        }else if(language.getLanguage().equals(Languages.FINNISH)){
            languageItem = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTlmMjM0OTcyOWE3ZWM4ZDRiMTQ3OGFkZmU1Y2E4YWY5NjQ3OWU5ODNmYmFkMjM4Y2NiZDgxNDA5YjRlZCJ9fX0=");
        }
        SkullMeta languageItemMeta = (SkullMeta) languageItem.getItemMeta();
        languageItemMeta.setDisplayName(language.transalte(121));
        languageItem.setItemMeta(languageItemMeta);

        if(!player.isOp()){
            inv.setItem(1,publicList);
            inv.setItem(3,privateList);
            inv.setItem(5,clearBossBar);
            inv.setItem(7,privateSettings);
            inv.setItem(8, languageItem);
        }else{
            inv.setItem(1, publicList);
            inv.setItem(2, privateList);
            inv.setItem(4, clearBossBar);
            inv.setItem(6, privateSettings);
            inv.setItem(7, publicSettings);
            inv.setItem(8, languageItem);
        }
        player.openInventory(inv);
    }

    public static void startMenuListener(InventoryClickEvent event){
        LanguageManager language = new LanguageManager((Player) event.getWhoClicked());
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            if(event.getView().getTitle().equalsIgnoreCase(language.transalte(8))){
                if(event.getCurrentItem() == null){
                    event.setCancelled(true);
                    return;
                }
                switch(event.getCurrentItem().getType()){
                    case CHEST:
                        player.closeInventory();
                        new PublicMenu(player,1);
                        break;
                    case ENDER_CHEST:
                        new PrivateMenu(player,1);
                        break;
                    case COMPASS:
                        new CompassManagerMenu(player);
                        break;
                    case CRAFTING_TABLE:
                        new PrivateSettingsMenu(player);
                        break;
                    case ANVIL:
                        new AdminSettingsMenu(player);
                        break;
                    case PLAYER_HEAD:
                        new LanguageMenu(player);
                        break;
                    default:
                        break;
                }
                event.setCancelled(true);
                NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
                if(normalConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, Float.valueOf(normalConfig.get("clickSoundPitch")));
            }
        }
    }

    public ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }


}
