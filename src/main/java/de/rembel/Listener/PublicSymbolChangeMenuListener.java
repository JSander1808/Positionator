package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.Position;
import de.rembel.General.PositionType;
import de.rembel.Language.LanguageManager;
import de.rembel.Menus.PrivateMenu;
import de.rembel.Menus.PublicMenu;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PublicSymbolChangeMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        LanguageManager language = new LanguageManager(player);
        if(event.getView().getTitle().split(" ").length==4){
            if(event.getView().getTitle().equalsIgnoreCase(language.transalte(136) + event.getView().getTitle().split(" ")[3])){
                String positionName = event.getView().getTitle().split(" ")[3];
                Config config = new Config("plugins//Positionator//Data//public.conf");
                if(event.getCurrentItem() != null){
                    switch(event.getCurrentItem().getType()){
                        case CHEST:
                            Position position1 = config.get(positionName);
                            position1.setType(PositionType.CHESTPOSITION);
                            config.set(position1);
                            new PublicMenu(player, 1);
                            break;
                        case FURNACE:
                            Position position2 = config.get(positionName);
                            position2.setType(PositionType.FURNACEPOSITION);
                            config.set(position2);
                            new PublicMenu(player, 1);
                            break;
                        case ENCHANTING_TABLE:
                            Position position3 = config.get(positionName);
                            position3.setType(PositionType.ENCHANTPOSITION);
                            config.set(position3);
                            new PublicMenu(player, 1);
                            break;
                        case CRAFTING_TABLE:
                            Position position4 = config.get(positionName);
                            position4.setType(PositionType.CRAFTINGPOSITION);
                            config.set(position4);
                            new PublicMenu(player, 1);
                            break;
                        case SMITHING_TABLE:
                            Position position5 = config.get(positionName);
                            position5.setType(PositionType.SMITHINGPOSITION);
                            config.set(position5);
                            new PublicMenu(player, 1);
                            break;
                        case BLAST_FURNACE:
                            Position position6 = config.get(positionName);
                            position6.setType(PositionType.BLASTFURNACEPOSITION);
                            config.set(position6);
                            new PublicMenu(player, 1);
                            break;
                        case CAMPFIRE:
                            Position position7 = config.get(positionName);
                            position7.setType(PositionType.CAMPFIREPOSITION);
                            config.set(position7);
                            new PublicMenu(player, 1);
                            break;
                        case ANVIL:
                            Position position8 = config.get(positionName);
                            position8.setType(PositionType.ANVILPOSITION);
                            config.set(position8);
                            new PublicMenu(player, 1);
                            break;
                        case COMPOSTER:
                            Position position9 = config.get(positionName);
                            position9.setType(PositionType.COMPOSTERPOSITION);
                            config.set(position9);
                            new PublicMenu(player, 1);
                            break;
                        case BEE_NEST:
                            Position position10 = config.get(positionName);
                            position10.setType(PositionType.BEEPOSITION);
                            config.set(position10);
                            new PublicMenu(player, 1);
                            break;
                        case BOOKSHELF:
                            Position position11 = config.get(positionName);
                            position11.setType(PositionType.BOOKSHELFPOSITION);
                            config.set(position11);
                            new PublicMenu(player, 1);
                            break;
                        case END_PORTAL_FRAME:
                            Position position12 = config.get(positionName);
                            position12.setType(PositionType.ENDPORTALPOSITION);
                            config.set(position12);
                            new PublicMenu(player, 1);
                            break;
                        case BARRIER:
                            player.closeInventory();
                            break;
                        case SPRUCE_DOOR:
                            new PublicMenu(player, 1);
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
