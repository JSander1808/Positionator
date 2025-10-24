package de.rembel.Main;

import de.rembel.CBossbar.CBossbar;
import de.rembel.Commands.BackUpCommand;
import de.rembel.Commands.PosDebugCommand;
import de.rembel.Commands.PositionCommand;
import de.rembel.Config.NormalConfig;
import de.rembel.General.*;
import de.rembel.Language.LanguageManager;
import de.rembel.Listener.*;
import de.rembel.TabComplet.BackUpTabCompletor;
import de.rembel.TabComplet.PosDebugTabCompletor;
import de.rembel.TextInput.ChatListener;
import de.rembel.bStats.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Callable;

public final class PositionatorMain extends JavaPlugin {

    public static Plugin plugin;
    public static JavaPlugin javaPlugin;
    public static int CompassUpdaterTaskID;

    @Override
    public void onEnable() {
        plugin = this;
        javaPlugin = this;

        new DataFixer();

        LanguageManager.LanguageInit();
        for(Player player : Bukkit.getOnlinePlayers()){
            PlayerJoinListener.initPlayerData(player);
        }

        Metrics metrics = new Metrics(this,  	18738);

        metrics.addCustomChart(new Metrics.SingleLineChart("saved_positions", new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return General.getAllPositions().size();
            }
        }));

        BackUpManager backUpManager = new BackUpManager();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new InventoryListener(),this);
        pluginManager.registerEvents(new PublicMenuListener(),this);
        pluginManager.registerEvents(new PublicSettingsMenuListener(),this);
        pluginManager.registerEvents(new PrivateMenuListener(),this);
        pluginManager.registerEvents(new PrivateSettingsMenuListener(),this);
        pluginManager.registerEvents(new addMenuListener(),this);
        pluginManager.registerEvents(new PlayerDeathListener(),this);
        //pluginManager.registerEvents(new PlayerMoveListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PublicFilterMenuListener(), this);
        pluginManager.registerEvents(new PrivateFilterMenuListener(), this);
        pluginManager.registerEvents(new ConfirmationListener(), this);
        pluginManager.registerEvents(new SettingsMenuListener(), this);
        pluginManager.registerEvents(new AdminSettingsMenuListener(), this);
        pluginManager.registerEvents(new BackUpMenuListener(), this);
        pluginManager.registerEvents(new ChatListener(), this);
        pluginManager.registerEvents(new LanguageMenuListener(), this);
        pluginManager.registerEvents(new PrivateSymbolChangeMenuListener(), this);
        pluginManager.registerEvents(new PublicSymbolChangeMenuListener(), this);
        pluginManager.registerEvents(new CompassManagerMenuListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);

        getCommand("pos").setExecutor(new PositionCommand());
        getCommand("pbackup").setExecutor(new BackUpCommand());
        getCommand("pdevelopersettings").setExecutor(new PosDebugCommand());
        getCommand("pbackup").setTabCompleter(new BackUpTabCompletor());
        getCommand("pdevelopersettings").setTabCompleter(new PosDebugTabCompletor());

        if(CBossbar.globalUpdateTime){
            CompassUpdaterTaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                @Override
                public void run() {
                    for(CBossbar compass : General.bossbarContainer.values()){
                        compass.renderBossbar();
                    }
                }
            },0,0);
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTask(CompassUpdaterTaskID);
        for(Player player : Bukkit.getOnlinePlayers()){
            if(player != null){
                NormalConfig playerConfig = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
                if(CBossbar.getByPlayer(player) != null) playerConfig.set("compassSave", CBossbar.getByPlayer(player).toString());
            }
        }
        NormalConfig config = new NormalConfig("plugins//Positionator//config.yml");
        config.init();
        if(config.getBoolean("createBackUpByServerRestart")){
            BackUpManager backUpManager = new BackUpManager();
            backUpManager.createBackUp("System", "Server Shutdown");
        }
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }
}
