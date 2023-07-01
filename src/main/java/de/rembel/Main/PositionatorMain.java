package de.rembel.Main;

import de.rembel.Commands.PositionCommand;
import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.Config.OldNormalConfig;
import de.rembel.General.DataFixer;
import de.rembel.General.General;
import de.rembel.General.PositionFilter;
import de.rembel.General.UpdateChecker;
import de.rembel.Listener.*;
import de.rembel.bStats.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

public final class PositionatorMain extends JavaPlugin {

    public static Plugin plugin;
    public static JavaPlugin javaPlugin;

    @Override
    public void onEnable() {
        plugin = this;
        javaPlugin = this;

        new DataFixer();

        Metrics metrics = new Metrics(this,  	18738);

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
        pluginManager.registerEvents(new PublicFilterMenuListener(), this);
        pluginManager.registerEvents(new PrivateFilterMenuListener(), this);
        pluginManager.registerEvents(new ConfirmationListener(), this);

        getCommand("pos").setExecutor(new PositionCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }
}
