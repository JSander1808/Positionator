package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.Position;
import de.rembel.General.PositionType;
import de.rembel.Language.LanguageManager;
import de.rembel.Menus.PrivateMenu;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PrivateSymbolChangeMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        LanguageManager language = new LanguageManager(player);
        if(event.getView().getTitle().split(" ").length==4){
            if(event.getView().getTitle().equalsIgnoreCase(language.transalte(134) + event.getView().getTitle().split(" ")[3])){
                String positionName = event.getView().getTitle().split(" ")[3];
                Config config = new Config("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//data.conf");
                if(event.getCurrentItem() != null){
                    switch(event.getCurrentItem().getType()){
                        case CHEST:
                            Position position1 = config.get(positionName);
                            position1.setType(PositionType.CHESTPOSITION);
                            config.set(position1);
                            new PrivateMenu(player, 1);
                            break;
                        case FURNACE:
                            Position position2 = config.get(positionName);
                            position2.setType(PositionType.FURNACEPOSITION);
                            config.set(position2);
                            new PrivateMenu(player, 1);
                            break;
                        case ENCHANTING_TABLE:
                            Position position3 = config.get(positionName);
                            position3.setType(PositionType.ENCHANTPOSITION);
                            config.set(position3);
                            new PrivateMenu(player, 1);
                            break;
                        case CRAFTING_TABLE:
                            Position position4 = config.get(positionName);
                            position4.setType(PositionType.CRAFTINGPOSITION);
                            config.set(position4);
                            new PrivateMenu(player, 1);
                            break;
                        case SEA_LANTERN:
                            Position position5 = config.get(positionName);
                            position5.setType(PositionType.SEALANTERNPOSITION);
                            config.set(position5);
                            new PrivateMenu(player, 1);
                            break;
                        case CRYING_OBSIDIAN:
                            Position position6 = config.get(positionName);
                            position6.setType(PositionType.CRYINGOBSIDIANPOSITION);
                            config.set(position6);
                            new PrivateMenu(player, 1);
                            break;
                        case CAMPFIRE:
                            Position position7 = config.get(positionName);
                            position7.setType(PositionType.CAMPFIREPOSITION);
                            config.set(position7);
                            new PrivateMenu(player, 1);
                            break;
                        case ANVIL:
                            Position position8 = config.get(positionName);
                            position8.setType(PositionType.ANVILPOSITION);
                            config.set(position8);
                            new PrivateMenu(player, 1);
                            break;
                        case COMPOSTER:
                            Position position9 = config.get(positionName);
                            position9.setType(PositionType.COMPOSTERPOSITION);
                            config.set(position9);
                            new PrivateMenu(player, 1);
                            break;
                        case BEE_NEST:
                            Position position10 = config.get(positionName);
                            position10.setType(PositionType.BEEPOSITION);
                            config.set(position10);
                            new PrivateMenu(player, 1);
                            break;
                        case BOOKSHELF:
                            Position position11 = config.get(positionName);
                            position11.setType(PositionType.BOOKSHELFPOSITION);
                            config.set(position11);
                            new PrivateMenu(player, 1);
                            break;
                        case END_PORTAL_FRAME:
                            Position position12 = config.get(positionName);
                            position12.setType(PositionType.ENDPORTALPOSITION);
                            config.set(position12);
                            new PrivateMenu(player, 1);
                            break;
                        case EMERALD_BLOCK:
                            Position position13 = config.get(positionName);
                            position13.setType(PositionType.EMERALDPOSITION);
                            config.set(position13);
                            new PrivateMenu(player, 1);
                            break;
                        case IRON_BLOCK:
                            Position position14 = config.get(positionName);
                            position14.setType(PositionType.IRONPOSITION);
                            config.set(position14);
                            new PrivateMenu(player, 1);
                            break;
                        case BEACON:
                            Position position15 = config.get(positionName);
                            position15.setType(PositionType.BEACONPOSITION);
                            config.set(position15);
                            new PrivateMenu(player, 1);
                            break;
                        case SPAWNER:
                            Position position16 = config.get(positionName);
                            position16.setType(PositionType.SPAWNERPOSITION);
                            config.set(position16);
                            new PrivateMenu(player, 1);
                            break;
                        case BLAZE_POWDER:
                            Position position17 = config.get(positionName);
                            position17.setType(PositionType.BLAZEPOWDERPOSITION);
                            config.set(position17);
                            new PrivateMenu(player, 1);
                            break;
                        case BELL:
                            Position position18 = config.get(positionName);
                            position18.setType(PositionType.BELLPOSITION);
                            config.set(position18);
                            new PrivateMenu(player, 1);
                            break;
                        case GOLD_BLOCK:
                            Position position19 = config.get(positionName);
                            position19.setType(PositionType.GOLDPOSITION);
                            config.set(position19);
                            new PrivateMenu(player, 1);
                            break;
                        case SCULK_SHRIEKER:
                            Position position20 = config.get(positionName);
                            position20.setType(PositionType.SKULKSKRIEKERPOSITION);
                            config.set(position20);
                            new PrivateMenu(player, 1);
                            break;
                        case REDSTONE:
                            Position position21 = config.get(positionName);
                            position21.setType(PositionType.REDSTONEPOSITION);
                            config.set(position21);
                            new PrivateMenu(player, 1);
                            break;
                        case OAK_BOAT:
                            Position position22 = config.get(positionName);
                            position22.setType(PositionType.BOATPOSITION);
                            config.set(position22);
                            new PrivateMenu(player, 1);
                            break;
                        case MINECART:
                            Position position23 = config.get(positionName);
                            position23.setType(PositionType.MINECARTPOSITION);
                            config.set(position23);
                            new PrivateMenu(player, 1);
                            break;
                        case FLOWERING_AZALEA:
                            Position position24 = config.get(positionName);
                            position24.setType(PositionType.AZALEAPOSITION);
                            config.set(position24);
                            new PrivateMenu(player, 1);
                            break;
                        case SPONGE:
                            Position position25 = config.get(positionName);
                            position25.setType(PositionType.SPONGEPOSITION);
                            config.set(position25);
                            new PrivateMenu(player, 1);
                            break;
                        case BARRIER:
                            player.closeInventory();
                            break;
                        case SPRUCE_DOOR:
                            new PrivateMenu(player, 1);
                            break;
                        default:
                            break;
                    }
                }
                event.setCancelled(true);
                NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
                if(normalConfig.getBoolean("enableMenuClickSound")) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, Float.valueOf(normalConfig.get("clickSoundPitch")));
            }
        }
    }
}
