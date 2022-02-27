package net.relfect.task;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.milkbowl.vault.economy.Economy;
import net.relfect.task.commands.SpawnerCommands;
import net.relfect.task.mysql.MySql;
import net.relfect.task.spawner.Spawner;
import net.relfect.task.spawner.SpawnerManager;
import net.relfect.task.util.ConfigFile;
import net.relfect.task.util.SignManager;
import net.relfect.task.util.menu.MenuManager;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class RPG extends JavaPlugin {

    @Getter private static RPG instance;
    @Getter private static Economy econ;

    ConfigFile config, lang;
    MySql mySql;


    @Override
    public void onEnable() {
        instance = this;


        config = new ConfigFile("config.yml", this);
        lang = new ConfigFile("lang.yml", this);

        setupHooks();

        registerManagers();
        registerListeners();
        registerCommands();

    }

    @Override
    public void onDisable() {


        mySql.disconnect();
    }

    private void setupHooks() {
        econ = getServer().getServicesManager().getRegistration(Economy.class).getProvider();
        Arrays.asList(Spawner.class).forEach(ConfigurationSerialization::registerClass);
    }

    private void registerManagers() {
        mySql = new MySql();
        mySql.connect();
        new SpawnerManager(this);
        new SignManager(this);
        new MenuManager(this);
    }

    private void registerCommands() {
        new SpawnerCommands(this);
    }

    public void registerListeners() {
    }
}
