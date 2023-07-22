package de.rembel.Bossbar;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.General.Position;
import de.rembel.Language.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossbarService {

    public BossbarService(Player player, String positionName, Config config){
        LanguageManager language = new LanguageManager(player);

        Position position = config.get(positionName);
        if(Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString()))!=null) {
            Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString())).removeAll();
            Bukkit.removeBossBar(NamespacedKey.fromString(player.getUniqueId().toString()));
        }

        General.BossBarPosition.remove(player.getUniqueId().toString());

        if(!config.get(positionName).getDimension().equalsIgnoreCase(player.getLocation().getWorld().getEnvironment().name())){
            BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), language.transalte(44)+ ChatColor.GREEN+config.get(positionName).getDimension(), BarColor.GREEN, BarStyle.SOLID);
            bar.setProgress(1.0);
            bar.addPlayer(player);
        }else{
            BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), language.transalte(45)+ChatColor.GREEN+config.get(positionName).getPositionAsString()+ChatColor.GOLD+language.transalte(46)+ChatColor.GREEN+Math.round(player.getLocation().distance(position.getLocation())),BarColor.GREEN,BarStyle.SOLID);
            bar.setProgress(1.0);
            bar.addPlayer(player);
        }

        General.BossBarPosition.put(player.getUniqueId().toString(), config.get(positionName).getDimension()+"->"+config.get(positionName).getPositionAsString());
    }

    public static void updateBossbar(Player player){
        LanguageManager languageManager = new LanguageManager(player);
        NormalConfig config = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
        if(config.get("bossbarType").equalsIgnoreCase("classic")){
            if(Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString()))!=null) {
                BossBar bar = Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString()));
                if(General.BossBarPosition.get(player.getUniqueId().toString()) == null){
                    Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString())).removeAll();
                    Bukkit.removeBossBar(NamespacedKey.fromString(player.getUniqueId().toString()));
                }else{
                    if(!General.BossBarPosition.get(player.getUniqueId().toString()).split("->")[0].equalsIgnoreCase(player.getLocation().getWorld().getEnvironment().name())){
                        bar.setTitle(languageManager.transalte(44)+ChatColor.GREEN+General.BossBarPosition.get(player.getUniqueId().toString()).split("->")[0]);
                    }else{
                        String[] position = General.BossBarPosition.get(player.getUniqueId().toString()).split("->")[1].split(" ");
                        Location target = new Location(player.getWorld(), Integer.valueOf(position[0]), Integer.valueOf(position[1]), Integer.valueOf(position[2]));
                        bar.setTitle(languageManager.transalte(45)+ChatColor.GREEN+General.BossBarPosition.get(player.getUniqueId().toString()).split("->")[1]+languageManager.transalte(46)+ChatColor.GREEN+Math.round(player.getLocation().distance(target)));
                    }
                }
            }
        }else if(config.get("bossbarType").equalsIgnoreCase("compass")){

        }
    }
}
