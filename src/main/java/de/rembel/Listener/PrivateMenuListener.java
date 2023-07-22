package de.rembel.Listener;

import de.rembel.Bossbar.BossbarService;
import de.rembel.CBossbar.CBossbar;
import de.rembel.CBossbar.CPosition;
import de.rembel.CBossbar.CSmoothProfile;
import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.General.Position;
import de.rembel.General.PositionType;
import de.rembel.Language.LanguageManager;
import de.rembel.Main.PositionatorMain;
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
import java.util.Random;

public class PrivateMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        LanguageManager language = new LanguageManager((Player) event.getWhoClicked());
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            Config config = new Config("plugins//Positionator//Data//User//"+ player.getUniqueId().toString()+"//data.conf");
            if(event.getView().getTitle().split(" ").length==7){
                if(event.getView().getTitle().equalsIgnoreCase(language.transalte(26)+event.getView().getTitle().split(" ")[4]+" / "+((config.list(General.PrivateFilter.get(player.getUniqueId().toString()), player).length/(9*5))+1))){
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
                        case FURNACE:
                        case ENCHANTING_TABLE:
                        case CRAFTING_TABLE:
                        case SMITHING_TABLE:
                        case BLAST_FURNACE:
                        case CAMPFIRE:
                        case ANVIL:
                        case COMPOSTER:
                        case BEE_NEST:
                        case BOOKSHELF:
                        case END_PORTAL_FRAME:
                            String positionName1 = event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"","").replace(ChatColor.RED+"","");
                            if(event.getClick() == ClickType.LEFT){
                                Inventory inv1 = Bukkit.createInventory(null, 1*9,language.transalte(30)+event.getCurrentItem().getItemMeta().getDisplayName());
                                for(int i = 0;i<1*9;i++){
                                    inv1.setItem(i,placeholder());
                                }


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
                                Config tempCompassConfig = new Config("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//data.conf");
                                CBossbar compass = CBossbar.getByPlayer(player);
                                Position position = tempCompassConfig.get(positionName1);
                                ChatColor color = getRandomColor();
                                String symbol = "⌖";
                                if(position.getType()== PositionType.DEATHPOSITION) symbol = "\uD83D\uDC80";
                                CPosition cPosition = new CPosition(symbol, color, position.getLocation());

                                if(compass==null){
                                    compass = new CBossbar(PositionatorMain.getPlugin());
                                    compass.createBossbar(player);
                                    compass.setSmoothProfile(CSmoothProfile.MIDDLE);
                                }
                                General.loadCompassData(compass);
                                if(!compass.existPosition(cPosition)){
                                    compass.addPosition(cPosition);
                                    player.sendMessage(language.transalte(138)+color+positionName1+language.transalte(139)+color+symbol+language.transalte(140));
                                }else{
                                    player.sendMessage(language.transalte(141));
                                }
                                //new BossbarService(player, event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"","").replace(ChatColor.RED+"",""),new Config("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//data.conf"));
                            }else if(event.getClick() == ClickType.RIGHT && event.getCurrentItem().getType() != Material.TOTEM_OF_UNDYING){
                                Inventory inventory = Bukkit.createInventory(null, 9*3, language.transalte(134)+positionName1);

                                for(int i = 0;i<1*9;i++){
                                    inventory.setItem(i,placeholder());
                                }

                                ItemStack close = new ItemStack(Material.BARRIER);
                                ItemMeta closemeta = close.getItemMeta();
                                closemeta.setDisplayName(language.transalte(10));
                                close.setItemMeta(closemeta);

                                ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
                                ItemMeta backmeta = back.getItemMeta();
                                backmeta.setDisplayName(language.transalte(12));
                                back.setItemMeta(backmeta);

                                ItemStack chest = new ItemStack(Material.CHEST);
                                ItemMeta chestMeta = chest.getItemMeta();
                                chestMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.CHEST){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    chestMeta.setLore(lore);
                                }
                                chest.setItemMeta(chestMeta);

                                ItemStack furnace = new ItemStack(Material.FURNACE);
                                ItemMeta furnaceMeta = furnace.getItemMeta();
                                furnaceMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.FURNACE){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    furnaceMeta.setLore(lore);
                                }
                                furnace.setItemMeta(furnaceMeta);

                                ItemStack enchant = new ItemStack(Material.ENCHANTING_TABLE);
                                ItemMeta enchantMeta = enchant.getItemMeta();
                                enchantMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.ENCHANTING_TABLE){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    enchantMeta.setLore(lore);
                                }
                                enchant.setItemMeta(enchantMeta);

                                ItemStack crafting = new ItemStack(Material.CRAFTING_TABLE);
                                ItemMeta craftingMeta = crafting.getItemMeta();
                                craftingMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.CRAFTING_TABLE){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    craftingMeta.setLore(lore);
                                }
                                crafting.setItemMeta(craftingMeta);

                                ItemStack smithing = new ItemStack(Material.SMITHING_TABLE);
                                ItemMeta smithingMeta = smithing.getItemMeta();
                                smithingMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.SMITHING_TABLE){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    smithingMeta.setLore(lore);
                                }
                                smithing.setItemMeta(smithingMeta);

                                ItemStack blastfurnace = new ItemStack(Material.BLAST_FURNACE);
                                ItemMeta blastfurnaceMeta = blastfurnace.getItemMeta();
                                blastfurnaceMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.BLAST_FURNACE){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    blastfurnaceMeta.setLore(lore);
                                }
                                blastfurnace.setItemMeta(blastfurnaceMeta);

                                ItemStack campfire = new ItemStack(Material.CAMPFIRE);
                                ItemMeta campfireMeta = campfire.getItemMeta();
                                campfireMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.CAMPFIRE){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    campfireMeta.setLore(lore);
                                }
                                campfire.setItemMeta(campfireMeta);

                                ItemStack anvil = new ItemStack(Material.ANVIL);
                                ItemMeta anvilMeta = anvil.getItemMeta();
                                anvilMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.ANVIL){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    anvilMeta.setLore(lore);
                                }
                                anvil.setItemMeta(anvilMeta);

                                ItemStack composter = new ItemStack(Material.COMPOSTER);
                                ItemMeta composterMeta = composter.getItemMeta();
                                composterMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.COMPOSTER){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    composterMeta.setLore(lore);
                                }
                                composter.setItemMeta(composterMeta);

                                ItemStack bee = new ItemStack(Material.BEE_NEST);
                                ItemMeta beeMeta = bee.getItemMeta();
                                beeMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.BEE_NEST){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    beeMeta.setLore(lore);
                                }
                                bee.setItemMeta(beeMeta);

                                ItemStack bookshelf = new ItemStack(Material.BOOKSHELF);
                                ItemMeta bookshelfMeta = bookshelf.getItemMeta();
                                bookshelfMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.BOOKSHELF){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    bookshelfMeta.setLore(lore);
                                }
                                bookshelf.setItemMeta(bookshelfMeta);

                                ItemStack end = new ItemStack(Material.END_PORTAL_FRAME);
                                ItemMeta endMeta = end.getItemMeta();
                                endMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.END_PORTAL_FRAME){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    endMeta.setLore(lore);
                                }
                                end.setItemMeta(endMeta);

                                inventory.setItem(0,chest);
                                inventory.setItem(1,furnace);
                                inventory.setItem(2,enchant);
                                inventory.setItem(3,crafting);
                                inventory.setItem(4,smithing);
                                inventory.setItem(5,blastfurnace);
                                inventory.setItem(6,campfire);
                                inventory.setItem(7,anvil);
                                inventory.setItem(8,composter);
                                inventory.setItem(9,bee);
                                inventory.setItem(10,bookshelf);
                                inventory.setItem(10,end);

                                inventory.setItem(25, back);
                                inventory.setItem(26, close);

                                player.openInventory(inventory);

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

    public ChatColor getRandomColor(){
        boolean finish = false;
        ChatColor color = null;
        while(!finish){
            finish = true;
            color = ChatColor.getByChar(Integer.toHexString(new Random().nextInt(16)));
            if(color==ChatColor.GRAY || color==ChatColor.DARK_GRAY) finish = false;
            if(color==ChatColor.GREEN || color==ChatColor.DARK_GREEN) finish = false;
            if(color==ChatColor.BLACK) finish = false;
            if(color==ChatColor.WHITE) finish = false;
        }
        return color;
    }
}
