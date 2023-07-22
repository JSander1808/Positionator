package de.rembel.Listener;

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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        LanguageManager language = new LanguageManager(event.getEntity());
        NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+event.getEntity().getUniqueId().toString()+"//config.yml");
        Config config = new Config("plugins//Positionator//Data//User//"+ event.getEntity().getUniqueId().toString()+"//data.conf");
        boolean finish = false;
        int temp = 1;
        Player player = event.getEntity();
        while(!finish){
            Position position = new Position("Death-"+temp, new String[]{String.valueOf(player.getLocation().getBlockX()), String.valueOf(player.getLocation().getBlockY()), String.valueOf(player.getLocation().getBlockZ())}, player.getName(), player.getWorld().getEnvironment().name(), PositionType.DEATHPOSITION);
            if(!config.existPosition(position)){

                config.set(position);

                if(normalConfig.getBoolean("setDeathPositionInBossbar")){
                    Config tempCompassConfig = new Config("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//data.conf");
                    CBossbar compass = CBossbar.getByPlayer(player);
                    ChatColor color = ChatColor.RED;
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
                        player.sendMessage(language.transalte(138)+color+position.getName()+language.transalte(139)+color+symbol+language.transalte(140));
                    }else{
                        player.sendMessage(language.transalte(141));
                    }
//                    if(Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString()))!=null) {
//                        Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString())).removeAll();
//                        Bukkit.removeBossBar(NamespacedKey.fromString(player.getUniqueId().toString()));
//                    }
//
//                    General.BossBarPosition.remove(player.getUniqueId().toString());
//
//                    if(!config.get(position.getName()).getDimension().equalsIgnoreCase(player.getLocation().getWorld().getEnvironment().name())){
//                        BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), ChatColor.GOLD+"Dimension: "+ChatColor.GREEN+config.get(position.getName()).getDimension(),BarColor.GREEN,BarStyle.SOLID);
//                        bar.setProgress(1.0);
//                        bar.addPlayer(player);
//                    }else{
//                        BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), ChatColor.GOLD+"Coordinates: "+ChatColor.GREEN+config.get(position.getName()).getPositionAsString()+ChatColor.GOLD+"     Distance: "+ChatColor.GREEN+Math.round(player.getLocation().distance(position.getLocation())),BarColor.RED,BarStyle.SOLID);
//                        bar.setProgress(1.0);
//                        bar.addPlayer(player);
//                    }
//
//                    General.BossBarPosition.put(player.getUniqueId().toString(), config.get(position.getName()).getDimension()+"->"+config.get(position.getName()).getPositionAsString());
                }

                finish=true;
            }
            temp++;
        }
    }
}
