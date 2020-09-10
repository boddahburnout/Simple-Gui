package darth.guitest.Event;

import darth.guitest.Guitest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Set;

public class GuiMenu {
    private Guitest plugin = Guitest.getPlugin(Guitest.class);

    public Inventory getMenu(String configpath) {
        Inventory inventory = Bukkit.createInventory(null, plugin.getConfig().getInt(configpath + ".size"), configpath.replaceAll("-", " "));
        try {
            if (plugin.getConfig().getConfigurationSection(configpath + ".contents").getKeys(false) != null) {
                Set<String> test = plugin.getConfig().getConfigurationSection(configpath + ".contents").getKeys(false);
                for (String string : test) {
                    String item = plugin.getConfig().getString(configpath + ".contents." + string + ".material");
                    ItemStack itemStack = new ItemStack(Material.matchMaterial(item), 1);
                    ItemMeta meta = itemStack.getItemMeta();
                    meta.setDisplayName(plugin.getConfig().getString(ChatColor.translateAlternateColorCodes('$', configpath + ".contents." + string + ".itemname")));
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