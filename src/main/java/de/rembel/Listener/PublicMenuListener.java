package de.rembel.Listener;

import de.rembel.Bossbar.BossbarService;
import de.rembel.CBossbar.CBossbar;
import de.rembel.CBossbar.CPosition;
import de.rembel.CBossbar.CSmoothProfile;
import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.General.Position;
import de.rembel.Language.LanguageManager;
import de.rembel.Main.PositionatorMain;
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
import java.util.Random;

public class PublicMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        LanguageManager language = new LanguageManager((Player) event.getWhoClicked());
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            Config config = new Config("plugins//Positionator//Data//public.conf");
            if(event.getView().getTitle().split(" ").length==7){
                if(event.getView().getTitle().equalsIgnoreCase(language.transalte(9)+event.getView().getTitle().split(" ")[4]+" / "+((config.list(General.PublicFilter.get(player.getUniqueId().toString()), player).length/(9*5))+1))){
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
                        case FURNACE:
                        case ENCHANTING_TABLE:
                        case CRAFTING_TABLE:
                        case SEA_LANTERN:
                        case CRYING_OBSIDIAN:
                        case CAMPFIRE:
                        case ANVIL:
                        case COMPOSTER:
                        case BEE_NEST:
                        case BOOKSHELF:
                        case EMERALD_BLOCK:
                        case IRON_BLOCK:
                        case SPAWNER:
                        case BELL:
                        case BEACON:
                        case BLAZE_POWDER:
                        case GOLD_BLOCK:
                        case SCULK_SHRIEKER:
                        case REDSTONE:
                        case OAK_BOAT:
                        case MINECART:
                        case FLOWERING_AZALEA:
                        case SPONGE:
                        case END_PORTAL_FRAME:
                            String positionName = event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"","").replace(ChatColor.RED+"","");;

                            if(event.getClick() == ClickType.LEFT){
                                Inventory inv = Bukkit.createInventory(null, 1*9,language.transalte(47)+event.getCurrentItem().getItemMeta().getDisplayName());
                                for(int i = 0;i<1*9;i++){
                                    inv.setItem(i,placeholder());
                                }

                                if(event.getCurrentItem().getType() != Material.TOTEM_OF_UNDYING){
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
                                deletelore.add(language.transalte(40)+config.get(positionName).getCreator());
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
                                Config tempCompassConfig = new Config("plugins//Positionator//Data//public.conf");
                                CBossbar compass = CBossbar.getByPlayer(player);
                                Position position = tempCompassConfig.get(positionName);
                                ChatColor color = getRandomColor();
                                CPosition cPosition = new CPosition("⌖", color, position.getLocation(), position.getName());

                                if(compass==null){
                                    compass = new CBossbar(PositionatorMain.getPlugin());
                                    compass.createBossbar(player);
                                    //compass.setSmoothProfile(CSmoothProfile.MIDDLE);
                                }
                                if(!compass.existPosition(cPosition)){
                                    compass.addPosition(cPosition);
                                    player.sendMessage(language.transalte(138)+color+positionName+language.transalte(139)+color+"⌖"+language.transalte(140));
                                }else{
                                    player.sendMessage(language.transalte(141));
                                }
                                General.loadCompassData(compass);
                                //new BossbarService(player, event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"","").replace(ChatColor.RED+"",""), new Config("plugins//Positionator//Data//public.conf"));
                            }else if(event.getClick() == ClickType.RIGHT){
                                Inventory inventory = Bukkit.createInventory(null, 9*3, language.transalte(136)+positionName);

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

                                ItemStack smithing = new ItemStack(Material.SEA_LANTERN);
                                ItemMeta smithingMeta = smithing.getItemMeta();
                                smithingMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.SEA_LANTERN){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    smithingMeta.setLore(lore);
                                }
                                smithing.setItemMeta(smithingMeta);

                                ItemStack blastfurnace = new ItemStack(Material.CRYING_OBSIDIAN);
                                ItemMeta blastfurnaceMeta = blastfurnace.getItemMeta();
                                blastfurnaceMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.CRYING_OBSIDIAN){
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

                                ItemStack emerald = new ItemStack(Material.EMERALD_BLOCK);
                                ItemMeta emeraldMeta = emerald.getItemMeta();
                                emeraldMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.EMERALD_BLOCK){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    emeraldMeta.setLore(lore);
                                }
                                emerald.setItemMeta(emeraldMeta);

                                ItemStack iron = new ItemStack(Material.IRON_BLOCK);
                                ItemMeta ironMeta = iron.getItemMeta();
                                ironMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.IRON_BLOCK){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    ironMeta.setLore(lore);
                                }
                                iron.setItemMeta(ironMeta);

                                ItemStack beacon = new ItemStack(Material.BEACON);
                                ItemMeta beaconMeta = beacon.getItemMeta();
                                beaconMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.BEACON){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    beaconMeta.setLore(lore);
                                }
                                beacon.setItemMeta(beaconMeta);

                                ItemStack spawner = new ItemStack(Material.SPAWNER);
                                ItemMeta spawnerMeta = spawner.getItemMeta();
                                spawnerMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.SPAWNER){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    spawnerMeta.setLore(lore);
                                }
                                spawner.setItemMeta(spawnerMeta);

                                ItemStack bell = new ItemStack(Material.BELL);
                                ItemMeta bellMeta = bell.getItemMeta();
                                bellMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.BELL){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    bellMeta.setLore(lore);
                                }
                                bell.setItemMeta(bellMeta);

                                ItemStack blaze = new ItemStack(Material.BLAZE_POWDER);
                                ItemMeta blazeMeta = blaze.getItemMeta();
                                blazeMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.BLAZE_POWDER){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    blazeMeta.setLore(lore);
                                }
                                blaze.setItemMeta(blazeMeta);

                                ItemStack gold = new ItemStack(Material.GOLD_BLOCK);
                                ItemMeta goldMeta = gold.getItemMeta();
                                goldMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.GOLD_BLOCK){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    goldMeta.setLore(lore);
                                }
                                gold.setItemMeta(goldMeta);

                                ItemStack skulk = new ItemStack(Material.SCULK_SHRIEKER);
                                ItemMeta skulkMeta = skulk.getItemMeta();
                                skulkMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.SCULK_SHRIEKER){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    skulkMeta.setLore(lore);
                                }
                                skulk.setItemMeta(skulkMeta);

                                ItemStack redstone = new ItemStack(Material.REDSTONE);
                                ItemMeta redstoneMeta = redstone.getItemMeta();
                                redstoneMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.REDSTONE){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    redstoneMeta.setLore(lore);
                                }
                                redstone.setItemMeta(redstoneMeta);

                                ItemStack boat = new ItemStack(Material.OAK_BOAT);
                                ItemMeta boatMeta = boat.getItemMeta();
                                boatMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.OAK_BOAT){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    boatMeta.setLore(lore);
                                }
                                boat.setItemMeta(boatMeta);

                                ItemStack minecart = new ItemStack(Material.MINECART);
                                ItemMeta minecartMeta = minecart.getItemMeta();
                                minecartMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.MINECART){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    minecartMeta.setLore(lore);
                                }
                                minecart.setItemMeta(minecartMeta);

                                ItemStack azalea = new ItemStack(Material.FLOWERING_AZALEA);
                                ItemMeta azaleaMeta = azalea.getItemMeta();
                                azaleaMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.FLOWERING_AZALEA){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    azaleaMeta.setLore(lore);
                                }
                                azalea.setItemMeta(azaleaMeta);

                                ItemStack sponge = new ItemStack(Material.SPONGE);
                                ItemMeta spongeMeta = sponge.getItemMeta();
                                spongeMeta.setDisplayName(language.transalte(135));
                                spongeMeta.setDisplayName(language.transalte(135));
                                if(event.getCurrentItem().getType()==Material.SPONGE){
                                    ArrayList lore = new ArrayList();
                                    lore.add(language.transalte(66));
                                    spongeMeta.setLore(lore);
                                }



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
                                inventory.setItem(11,end);
                                inventory.setItem(12,emerald);
                                inventory.setItem(13,iron);
                                inventory.setItem(14,beacon);
                                inventory.setItem(15,spawner);
                                inventory.setItem(16,bell);
                                inventory.setItem(17,blaze);
                                inventory.setItem(18,gold);
                                inventory.setItem(19,skulk);
                                inventory.setItem(20,redstone);
                                inventory.setItem(21,boat);
                                inventory.setItem(22,minecart);
                                inventory.setItem(23,azalea);
                                inventory.setItem(24,sponge);

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
        ChatColor color = null;
        int random = 1 + (int)(Math.random() * ((8 - 1) + 1));
        if(random==1) color = ChatColor.RED;
        if(random==2) color = ChatColor.BLUE;
        if(random==3) color = ChatColor.YELLOW;
        if(random==4) color = ChatColor.DARK_PURPLE;
        if(random==5) color = ChatColor.LIGHT_PURPLE;
        if(random==6) color = ChatColor.RED;
        if(random==7) color = ChatColor.AQUA;
        if(random==8) color = ChatColor.GOLD;
        return color;
    }
}
