package de.rembel.Listener;

import de.rembel.Config.NormalConfig;
import de.rembel.Language.LanguageManager;
import de.rembel.Language.Languages;
import de.rembel.Menus.LanguageMenu;
import de.rembel.Menus.StartMenu;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class LanguageMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        LanguageManager language = new LanguageManager(player);
        NormalConfig config = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
        if(event.getView().getTitle().equalsIgnoreCase(language.transalte(121))){
            if(event.getCurrentItem()!= null){
                switch(event.getCurrentItem().getType()){
                    case PLAYER_HEAD:
                        String languageName = event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.GOLD+"", "");
                        if(languageName.equalsIgnoreCase("English")){
                            config.set("language", Languages.ENGLISH);
                        }else if(languageName.equalsIgnoreCase("Deutsch")){
                            config.set("language", Languages.GERMAN);
                            sendLanguageBetaProjectMessage(player);
                        }else if(languageName.equalsIgnoreCase("Français")){
                            config.set("language", Languages.FRENCH);
                            sendLanguageBetaProjectMessage(player);
                        }else if(languageName.equalsIgnoreCase("Norsk")){
                            config.set("language", Languages.NORWEGIAN);
                            sendLanguageBetaProjectMessage(player);
                        }//else if(languageName.equalsIgnoreCase("Suomen")){
                        //    config.set("language", Languages.FINNISH);
                        //}
                        new LanguageMenu(player);
                        break;
                    case SPRUCE_DOOR:
                        new StartMenu(player);
                        break;
                    case BARRIER:
                        player.closeInventory();
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

    private void sendLanguageBetaProjectMessage(Player player){
        LanguageManager language = new LanguageManager(player);
        player.sendMessage(" ");
        player.sendMessage(" ");
        player.sendMessage(" ");
        player.sendMessage(language.transalte(124));
        player.sendMessage(" ");
        player.sendMessage(language.transalte(125));
        player.sendMessage(language.transalte(126));
        player.sendMessage(language.transalte(127));
        TextComponent feedback = new TextComponent(language.transalte(128));
        feedback.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/JSander1808/Positionator/issues"));
        feedback.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(language.transalte(129))));
        player.sendMessage(language.transalte(130));
        player.spigot().sendMessage(feedback);
        player.sendMessage(" ");
        player.sendMessage(language.transalte(124));
    }
}
