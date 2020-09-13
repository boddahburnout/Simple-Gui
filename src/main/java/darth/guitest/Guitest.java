package darth.guitest;

import darth.guitest.Enchant.EnchantEffect;
import darth.guitest.event.MenuClick;
import darth.guitest.menucommands.*;
import darth.guitest.tabcomplete.*;
import darth.guitest.triggers.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandMap;
import org.bukkit.enchantments.Enchantment;
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
        this.getCommand("createmenu").setTabCompleter(new CreateMenuCompleter());
        this.getCommand("itemset").setExecutor(new ItemSet());
        this.getCommand("itemset").setTabCompleter(new ItemSetCompleter());
        this.getCommand("setaction").setExecutor(new MenuAction());
        this.getCommand("setaction").setTabCompleter(new MenuActionCompleter());
        this.getCommand("setclose").setExecutor(new CloseAfterAction());
        this.getCommand("setclose").setTabCompleter(new CloseAfterActionCompleter());
        this.getCommand("guireload").setExecutor(new ReloadCmd());
        this.getCommand("setsize").setExecutor(new MenuSize());
        this.getCommand("setsize").setTabCompleter(new MenuSizeCompleter());
        this.getCommand("removeitem").setExecutor(new RemoveItem());
        this.getCommand("removeitem").setTabCompleter(new RemoveItemCompleter());
        this.getCommand("removecmd").setExecutor(new RemoveCmd());
        this.getCommand("removecmd").setTabCompleter(new RemoveCmdCompleter());
        this.getCommand("setplayerjoin").setExecutor(new SetPlayerJoin());
        this.getCommand("setplayerjoin").setTabCompleter(new SetPlayerJoinCompleter());
        this.getCommand("openmenu").setExecutor(new MenuCommand());
        this.getCommand("openmenu").setTabCompleter(new MenuCommandCompleter());
        this.getCommand("setenchant").setExecutor(new EnchantEffectCmd());
        this.getCommand("setenchant").setTabCompleter(new EnchantEffectCompleter());
        this.getCommand("setitemlore").setExecutor(new SetSlotLore());
        this.getCommand("setitemlore").setTabCompleter(new SetSlotLoreCompleter());
        this.getCommand("menualias").setExecutor(new MenuAlias());
        this.getCommand("menualias").setTabCompleter(new MenuAliasCompleter());
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
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder() + File.separator + "config.yml");
        if (!file.exists()) {
            getConfig().addDefault("Test-Menu.size", 9);
            getConfig().addDefault("Test-Menu.contents.2.material", "diamond");
            getConfig().addDefault("Test-Menu.contents.2.itemname", "Test Button");
            getConfig().addDefault("Test-Menu.contents.2.actions.commands", "say hello");
            getConfig().addDefault("Test-Menu.alias", "Test-Menu");
            getConfig().addDefault("Trigger.playerjoin.Test-Menu", true);
            getConfig().options().copyDefaults(true);
            saveConfig();
        } else {
            saveConfig();
            reloadConfig();
        }
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            Bukkit.broadcastMessage("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        registerEnchantEffect();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerEnchantEffect() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Enchantment.registerEnchantment(new EnchantEffect(NamespacedKey.minecraft("guienchant")));
        } catch (IllegalArgumentException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}