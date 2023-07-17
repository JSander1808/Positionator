package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.Language.LanguageManager;
import de.rembel.Menus.PublicFilterMenu;
import de.rembel.Menus.PublicMenu;
import de.rembel.Menus.StartMenu;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PublicMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        LanguageManager language = new LanguageManager((Player) event.getWhoClicked());
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            Config config = new Config("plugins//Positionator//Data//public.conf");
            if(event.getView().getTitle().split(" ").length==7){
                if(event.getView().getTitle().equalsIgnoreCase(language.transalte(9)+event.getView().getTitle().split(" ")[4]+" / "+((config.list(General.PublicFilter.get(player.getUniqueId().toString())).length/(9*5))+1))){
                    if(event.getCurrentItem() == null){
                        event.setCancelled(true);
                        return;
                    }
                    int page = Integer.valueOf(event.getView().getTitle().split(" ")[4]);
                    int pagemax = Integer.valueOf(event.getView().getTitle().split(" ")[6]);
                    switch(event.getCurrentItem().getType()){
                        case PAPER:
                            if(event.getClick() == ClickType.RIGHT){
                                General.PublicFilter.remove(player.getUniqueId().toString());
                                new PublicMenu(player, 1);
                            }else if(event.getClick() == ClickType.LEFT){
                                new PublicFilterMenu(player);
                            }
                            break;
                        case SPRUCE_SIGN:
                            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(language.transalte(11))&&page>1){
                                new PublicMenu(player, Integer.valueOf(event.getView().getTitle().split(" ")[4])-1);
                            }else{
                                event.setCancelled(true);
                            }
                            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(language.transalte(13))&&page<pagemax){
                                new PublicMenu(player, Integer.valueOf(event.getView().getTitle().split(" ")[4])+1);
                            }else{
                                event.setCancelled(true);
                            }
                            break;
                        case SPRUCE_DOOR:
                            new StartMenu(player);
                            break;
                        case BARRIER:
                            player.closeInventory();
                            break;
                        case CHEST:
                            if(event.getClick() == ClickType.LEFT){
                                Inventory inv = Bukkit.createInventory(null, 1*9,language.transalte(47)+event.getCurrentItem().getItemMeta().getDisplayName());
                                for(int i = 0;i<1*9;i++){
                                    inv.setItem(i,placeholder());
                                }
                                String positionName = event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"","").replace(ChatColor.RED+"","");;

                                if(event.getCurrentItem().getType() == Material.CHEST){
                                    ItemStack setInOtherList = new ItemStack(Material.ENDER_CHEST);
                                    ItemMeta setInOtherListMeta = setInOtherList.getItemMeta();
                                    setInOtherListMeta.setDisplayName(language.transalte(31)+positionName+language.transalte(48));
                                    ArrayList setInOtherListLore = new ArrayList();
                                    setInOtherListLore.add(language.transalte(33));
                                    setInOtherListLore.add(language.transalte(34));
                                    setInOtherListMeta.setLore(setInOtherListLore);
                                    setInOtherList.setItemMeta(setInOtherListMeta);
                                    inv.setItem(3,setInOtherList);
                                }

                                ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
                                ItemMeta backmeta = back.getItemMeta();
                                backmeta.setDisplayName(language.transalte(12));
                                back.setItemMeta(backmeta);

                                ItemStack setOnBossbar = new ItemStack(Material.BEACON);
                                ItemMeta setOnBossbarmeta = setOnBossbar.getItemMeta();
                                setOnBossbarmeta.setDisplayName(language.transalte(31)+positionName+language.transalte(35));
                                ArrayList setOnBossbarlore = new ArrayList();
                                setOnBossbarlore.add(language.transalte(36));
                                setOnBossbarlore.add(language.transalte(37));
                                setOnBossbarlore.add(language.transalte(38));
                                setOnBossbarmeta.setLore(setOnBossbarlore);
                                setOnBossbar.setItemMeta(setOnBossbarmeta);

                                ItemStack delete = new ItemStack(Material.RED_WOOL);
                                ItemMeta deletemeta = delete.getItemMeta();
                                deletemeta.setDisplayName(language.transalte(39)+positionName);
                                ArrayList deletelore = new ArrayList();
                                deletelore.add(language.transalte(40)+config.get(positionName)[2]);
                                deletemeta.setLore(deletelore);
                                delete.setItemMeta(deletemeta);

                                ItemStack rename = new ItemStack(Material.NAME_TAG);
                                ItemMeta renameMeta = rename.getItemMeta();
                                renameMeta.setDisplayName(language.transalte(41));
                                rename.setItemMeta(renameMeta);

                                NormalConfig mainConfig = new NormalConfig("plugins//Positionator//config.yml");
                                if(mainConfig.getBoolean("allowPlayerToTeleport") || (player.isOp() && mainConfig.getBoolean("allowOpToTeleport"))){
                                    ItemStack teleport = new ItemStack(Material.ENDER_PEARL);
                                    ItemMeta teleportMeta = teleport.getItemMeta();
                                    teleportMeta.setDisplayName(language.transalte(42));
                                    ArrayList teleportLore = new ArrayList();
                                    teleportLore.add(language.transalte(43));
                                    teleportMeta.setLore(teleportLore);
                                    teleport.setItemMeta(teleportMeta);
                                    inv.setItem(5, teleport);
                                }

                                ItemStack close = new ItemStack(Material.BARRIER);
                                ItemMeta closemeta = close.getItemMeta();
                                closemeta.setDisplayName(language.transalte(10));
                                close.setItemMeta(closemeta);

                                inv.setItem(4,setOnBossbar);
                                inv.setItem(2, rename);
                                inv.setItem(0,delete);
                                inv.setItem(7, back);
                                inv.setItem(8,close);

                                player.openInventory(inv);
                            }else if(event.getClick() == ClickType.SHIFT_LEFT){
                                event.setCancelled(true);
                                if(Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString()))!=null) {
                                    Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString())).removeAll();
                                    Bukkit.removeBossBar(NamespacedKey.fromString(player.getUniqueId().toString()));
                                }

                                General.BossBarPosition.remove(player.getUniqueId().toString());

                                if(!config.get(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", ""))[3].equalsIgnoreCase(player.getLocation().getWorld().getEnvironment().name())){
                                    BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), language.transalte(44)+ChatColor.GREEN+config.get(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", ""))[3], BarColor.GREEN, BarStyle.SOLID);
                                    bar.setProgress(1.0);
                                    bar.addPlayer(player);
                                }else{
                                    String[] position = config.get(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", ""))[1].split(" ");
                                    Location target = new Location(player.getWorld(), Integer.valueOf(position[0]), Integer.valueOf(position[1]), Integer.valueOf(position[2]));
                                    BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), language.transalte(45)+ChatColor.GREEN+config.get(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", ""))[1]+language.transalte(46)+ChatColor.GREEN+Math.round(player.getLocation().distance(target)),BarColor.GREEN,BarStyle.SOLID);
                                    bar.setProgress(1.0);
                                    bar.addPlayer(player);
                                }

                                General.BossBarPosition.put(player.getUniqueId().toString(), config.get(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", ""))[3]+"->"+config.get(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", ""))[1]);
                            }
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
    }

    public static ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }
}
