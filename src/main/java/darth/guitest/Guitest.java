package darth.guitest;

import darth.guitest.Event.MenuClick;
import darth.guitest.MenuCommands.*;
import darth.guitest.Triggers.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Set;

public final class Guitest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(new MenuClick(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        this.getCommand("createmenu").setExecutor(new CreateMenu());
        this.getCommand("itemset").setExecutor(new ItemSet());
        this.getCommand("setaction").setExecutor(new MenuAction());
        this.getCommand("setclose").setExecutor(new CloseAfterAction());
        this.getCommand("guireload").setExecutor(new ReloadCmd());
        this.getCommand("setsize").setExecutor(new MenuSize());
        this.getCommand("removeitem").setExecutor(new RemoveItem());
        this.getCommand("removecmd").setExecutor(new RemoveCmd());
        this.getCommand("setplayerjoin").setExecutor(new SetPlayerJoin());
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            Set<String> menus = getConfig().getKeys(false);
            for (String menu : menus) {
                commandMap.register(menu, new Command(menu));
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        this.getCommand("openmenu").setExecutor(new MenuCommand());
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder() + File.separator + "config.yml");
        if (!file.exists()) {
            getConfig().addDefault("Test-Menu.size", 9);
            getConfig().addDefault("Test-Menu.contents.2.material", "diamond");
            getConfig().addDefault("Test-Menu.contents.2.itemname", "Test Button");
            getConfig().addDefault("Test-Menu.contents.2.actions.commands", "say hello");
            getConfig().addDefault("Trigger.playerjoin.Test-Menu", true);
            getConfig().options().copyDefaults(true);
            saveConfig();
        } else {
            saveConfig();
            reloadConfig();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
