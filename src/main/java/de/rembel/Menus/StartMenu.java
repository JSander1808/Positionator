package de.rembel.Menus;

import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
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

import java.util.ArrayList;

public class StartMenu {

    public StartMenu(Player player){
        Inventory inv = Bukkit.createInventory(null, 9*1, ChatColor.GOLD+"Positionator");
        for(int i = 0;i<9*1;i++){
            inv.setItem(i,placeholder());
        }

        ItemStack publicList = new ItemStack(Material.CHEST);
        ItemMeta publicListMeta = publicList.getItemMeta();
        publicListMeta.setDisplayName(ChatColor.GOLD+"Public Position List");
        ArrayList<String> publicListlore = new ArrayList<String>();
        publicListlore.add(ChatColor.GREEN+"Anyone can see this list");
        publicListMeta.setLore(publicListlore);
        publicList.setItemMeta(publicListMeta);

        ItemStack privateList = new ItemStack(Material.ENDER_CHEST);
        ItemMeta privateListMeta = publicList.getItemMeta();
        privateListMeta.setDisplayName(ChatColor.GOLD+"Private Position List");
        ArrayList<String> privateListlore = new ArrayList<String>();
        privateListlore.add(ChatColor.GREEN+"Only you can see this list");
        privateListMeta.setLore(privateListlore);
        privateList.setItemMeta(privateListMeta);

        ItemStack clearBossBar = new ItemStack(Material.ARROW);
        ItemMeta clearBossBarMeta = clearBossBar.getItemMeta();
        clearBossBarMeta.setDisplayName(ChatColor.RED+"Remove Position from Bossbar");
        clearBossBar.setItemMeta(clearBossBarMeta);

        ItemStack privateSettings = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta privateSettingsMeta = privateSettings.getItemMeta();
        privateSettingsMeta.setDisplayName(ChatColor.GOLD+"Settings");
        privateSettings.setItemMeta(privateSettingsMeta);

        ItemStack publicSettings = new ItemStack(Material.ANVIL);
        ItemMeta publicSettingsMeta = publicSettings.getItemMeta();
        publicSettingsMeta.setDisplayName(ChatColor.RED+"Admin Settings");
        publicSettings.setItemMeta(publicSettingsMeta);

        if(!player.isOp()){
            inv.setItem(1,publicList);
            inv.setItem(3,privateList);
            inv.setItem(5,clearBossBar);
            inv.setItem(7,privateSettings);
        }else{
            inv.setItem(1, publicList);
            inv.setItem(2, privateList);
            inv.setItem(4, clearBossBar);
            inv.setItem(6, privateSettings);
            inv.setItem(7, publicSettings);
        }
        player.openInventory(inv);
    }

    public static void startMenuListener(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD+"Positionator")){
                switch(event.getCurrentItem().getType()){
                    case CHEST:
                        player.closeInventory();
                        new PublicMenu(player,1);
                        break;
                    case ENDER_CHEST:
                        new PrivateMenu(player,1);
                        break;
                    case ARROW:
                        BossBar bar = Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString()));
                        General.BossBarPosition.remove(player.getUniqueId());
                        try{
                            bar.removeAll();
                        }catch(Exception e){}
                        break;
                    case CRAFTING_TABLE:
                        new PrivateSettingsMenu(player);
                        break;
                    case ANVIL:
                        new AdminSettingsMenu(player);
                        break;
                    default:
                        break;
                }
                event.setCancelled(true);
                NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
                if(normalConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
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
