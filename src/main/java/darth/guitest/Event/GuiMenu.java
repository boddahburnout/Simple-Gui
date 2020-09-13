package darth.guitest.Event;

import darth.guitest.Enchant.EnchantEffect;
import darth.guitest.Guitest;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GuiMenu {
    private Guitest plugin = Guitest.getPlugin(Guitest.class);

    public Inventory getMenu(String configpath, Player p) {
        Inventory inventory;
        if (plugin.getConfig().getBoolean(configpath+".enablealias")) {
            inventory = Bukkit.createInventory(null, plugin.getConfig().getInt( configpath + ".size"), ChatColor.translateAlternateColorCodes('$', plugin.getConfig().getString(configpath+".alias").replaceAll("-", " ")));
        } else {
            inventory = Bukkit.createInventory(null, plugin.getConfig().getInt(configpath + ".size"), configpath.replaceAll("-", " "));
        }
        try {
            if (plugin.getConfig().getConfigurationSection(configpath + ".contents").getKeys(false) != null) {
                Set<String> test = plugin.getConfig().getConfigurationSection(configpath + ".contents").getKeys(false);
                for (String string : test) {
                    String item = plugin.getConfig().getString(configpath + ".contents." + string + ".material");
                    ItemStack itemStack = new ItemStack(Material.matchMaterial(item), 1);
                    ItemMeta meta = itemStack.getItemMeta();
                    if (plugin.getConfig().getBoolean(configpath+".contents."+string+".enchanteffect")) {
                        EnchantEffect enchantEffect = new EnchantEffect(NamespacedKey.minecraft("guienchant"));
                        meta.addEnchant(enchantEffect, 1, true);
                    }
                    if (plugin.getConfig().getString(configpath+".contents."+string+".lore") != null) {
                        Set<String> rawlore = plugin.getConfig().getConfigurationSection(configpath+".contents."+string+".lore").getKeys(false);
                        List<String> lore = new ArrayList<>();
                        for (int i =  0; i < 4; i = i + 1 ) {
                            lore.add("");
                        }
                        for (String line : rawlore) {
                            lore.set(Integer.parseInt(line)-1,ChatColor.translateAlternateColorCodes('$', plugin.getConfig().getString(configpath+".contents."+string+".lore."+line)));
                        }
                        meta.setLore(lore);
                    }
                    meta.setDisplayName(PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('$', plugin.getConfig().getString( configpath + ".contents." + string + ".itemname"))));
                    itemStack.setItemMeta(meta);
                    inventory.setItem(Integer.parseInt(string) - 1, itemStack);
                }
                return inventory;
            }
        } catch (NullPointerException e) {
            return inventory;
        }
        return inventory;
    }
}