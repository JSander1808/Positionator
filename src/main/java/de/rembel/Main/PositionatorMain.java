package de.rembel.Main;

import de.rembel.Commands.PositionCommand;
import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.Config.OldNormalConfig;
import de.rembel.Listener.*;
import de.rembel.bStats.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class PositionatorMain extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        //convert old config from V.1.0.3-SNAPSHOT to config from V.1.0.4
        OldNormalConfig oldConfig = new OldNormalConfig("plugins//Positionator//config.yml");
        oldConfig.init();
        if(oldConfig.existdata("firstUse")) oldConfig.clearFile();

        Metrics metrics = new Metrics(this,  	18738);
        Config publicConfig = new Config("plugins//Positionator//public.conf");
        publicConfig.init();

        NormalConfig config = new NormalConfig("plugins//Positionator//config.yml");
        config.init();
        if(!config.existdata("showDeathPositionInList")) config.set("showDeathPositionInList","true");
        if(!config.existdata("setDeathPositionInBossbar")) config.set("setDeathPositionInBossbar","true");

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new InventoryListener(),this);
        pluginManager.registerEvents(new PublicMenuListener(),this);
        pluginManager.registerEvents(new PublicSettingsMenuListener(),this);
        pluginManager.registerEvents(new PrivateMenuListener(),this);
        pluginManager.registerEvents(new PrivateSettingsMenuListener(),this);
        pluginManager.registerEvents(new addMenuListener(),this);
        pluginManager.registerEvents(new PlayerDeathListener(),this);
        pluginManager.registerEvents(new PlayerMoveListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);

        getCommand("pos").setExecutor(new PositionCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
