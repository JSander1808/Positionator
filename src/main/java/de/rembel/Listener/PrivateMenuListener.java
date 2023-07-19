package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.Language.LanguageManager;
import de.rembel.Menus.PrivateFilterMenu;
import de.rembel.Menus.PrivateMenu;
import de.rembel.Menus.PublicMenu;
import de.rembel.Menus.StartMenu;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PrivateMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        LanguageManager language = new LanguageManager((Player) event.getWhoClicked());
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            Config config = new Config("plugins//Positionator//Data//User//"+ player.getUniqueId().toString()+"//data.conf");
            if(event.getView().getTitle().split(" ").length==7){
                if(event.getView().getTitle().equalsIgnoreCase(language.transalte(26)+event.getView().getTitle().split(" ")[4]+" / "+((config.list(General.PrivateFilter.get(player.getUniqueId().toString())).length/(9*5))+1))){
                    if(event.getCurrentItem() == null){
                        event.setCancelled(true);
                        return;
                    }
                    int page = Integer.valueOf(event.getView().getTitle().split(" ")[4]);
                    int pagemax = Integer.valueOf(event.getView().getTitle().split(" ")[6]);
                    switch(event.getCurrentItem().getType()){
                        case PAPER:
                            if(event.getClick() == ClickType.RIGHT){
                                General.PrivateFilter.remove(player.getUniqueId().toString());
                                new PrivateMenu(player, page);
                            }else if(event.getClick() == ClickType.LEFT){
                                new PrivateFilterMenu(player);
                            }
                            break;
                        case SPRUCE_SIGN:
                            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(language.transalte(11))&&page>1){
                                new PrivateMenu(player, Integer.valueOf(event.getView().getTitle().split(" ")[4])-1);
                            }else{
                                event.setCancelled(true);
                            }
                            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(language.transalte(13))&&page<pagemax){
                                new PrivateMenu(player, Integer.valueOf(event.getView().getTitle().split(" ")[4])+1);
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
                        case TOTEM_OF_UNDYING:
                            if(event.getClick() == ClickType.LEFT){
                                Inventory inv1 = Bukkit.createInventory(null, 1*9,language.transalte(30)+event.getCurrentItem().getItemMeta().getDisplayName());
                                for(int i = 0;i<1*9;i++){
                                    inv1.setItem(i,placeholder());
                                }
                                String positionName1 = event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"","").replace(ChatColor.RED+"","");

                                if(event.getCurrentItem().getType() == Material.CHEST){
                                    ItemStack setInOtherList = new ItemStack(Material.CHEST);
                                    ItemMeta setInOtherListMeta = setInOtherList.getItemMeta();
                                    setInOtherListMeta.setDisplayName(language.transalte(31)+positionName1+language.transalte(32));
                                    ArrayList setInOtherListLore = new ArrayList();
                                    setInOtherListLore.add(language.transalte(33));
                                    setInOtherListLore.add(language.transalte(34));
                                    setInOtherListMeta.setLore(setInOtherListLore);
                                    setInOtherList.setItemMeta(setInOtherListMeta);
                                    inv1.setItem(3,setInOtherList);
                                }

                                ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
                                ItemMeta backmeta = back.getItemMeta();
                                backmeta.setDisplayName(language.transalte(12));
                                back.setItemMeta(backmeta);

                                ItemStack setOnBossbar = new ItemStack(Material.BEACON);
                                ItemMeta setOnBossbarmeta = setOnBossbar.getItemMeta();
                                setOnBossbarmeta.setDisplayName(language.transalte(31)+positionName1+language.transalte(35));
                                ArrayList setOnBossbarlore = new ArrayList();
                                setOnBossbarlore.add(language.transalte(36));
                                setOnBossbarlore.add(language.transalte(37));
                                setOnBossbarlore.add(language.transalte(38));
                                setOnBossbarmeta.setLore(setOnBossbarlore);
                                setOnBossbar.setItemMeta(setOnBossbarmeta);

                                ItemStack delete = new ItemStack(Material.RED_WOOL);
                                ItemMeta deletemeta = delete.getItemMeta();
                                deletemeta.setDisplayName(language.transalte(39)+positionName1);
                                ArrayList deletelore = new ArrayList();
                                deletelore.add(language.transalte(40)+config.get(positionName1).getCreator());
                                deletemeta.setLore(deletelore);
                                delete.setItemMeta(deletemeta);

                                ItemStack close = new ItemStack(Material.BARRIER);
                                ItemMeta closemeta = close.getItemMeta();
                                closemeta.setDisplayName(language.transalte(10));
                                close.setItemMeta(closemeta);

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
                                    inv1.setItem(5, teleport);
                                }

                                inv1.setItem(4,setOnBossbar);
                                if(event.getCurrentItem().getType() == Material.CHEST) {inv1.setItem(2, rename);}else{inv1.setItem(3, rename);}
                                inv1.setItem(0,delete);
                                inv1.setItem(8,close);
                                inv1.setItem(7, back);

                                player.openInventory(inv1);
                            }else if(event.getClick() == ClickType.SHIFT_LEFT){
                                event.setCancelled(true);
                                if(Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString()))!=null) {
                                    Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString())).removeAll();
                                    Bukkit.removeBossBar(NamespacedKey.fromString(player.getUniqueId().toString()));
                                }

                                General.BossBarPosition.remove(player.getUniqueId().toString());

                                if(!config.get(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", "").replace(ChatColor.RED+"", "")).getDimension().equalsIgnoreCase(player.getLocation().getWorld().getEnvironment().name())){
                                    BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), language.transalte(44)+ChatColor.GREEN+config.get(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", "").replace(ChatColor.RED+"", "")).getDimension(), BarColor.GREEN, BarStyle.SOLID);
                                    bar.setProgress(1.0);
                                    bar.addPlayer(player);
                                }else{
                                    BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), language.transalte(45)+ChatColor.GREEN+config.get(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", "").replace(ChatColor.RED+"", "")).getPositionAsString()+language.transalte(46)+ChatColor.GREEN+Math.round(player.getLocation().distance(config.get(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", "").replace(ChatColor.RED+"", "")).getLocation())),BarColor.GREEN,BarStyle.SOLID);
                                    bar.setProgress(1.0);
                                    bar.addPlayer(player);
                                }

                                General.BossBarPosition.put(player.getUniqueId().toString(), config.get(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", "").replace(ChatColor.RED+"", "")).getDimension()+"->"+config.get(event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", "").replace(ChatColor.RED+"", "")).getPositionAsString());
                            }
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
    }

    public static ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }
}
