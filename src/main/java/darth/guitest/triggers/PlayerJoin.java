package darth.guitest.triggers;

import darth.guitest.event.GuiMenu;
import darth.guitest.Guitest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Set;

public class PlayerJoin implements Listener {
    private Guitest plugin = Guitest.getPlugin(Guitest.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Set<String> keys = plugin.getConfig().getConfigurationSection("Trigger.playerjoin").getKeys(false);
        for (String menu : keys) {
            if (plugin.getConfig().getBoolean("Trigger.playerjoin." + menu)) {
                e.getPlayer().openInventory(new GuiMenu().getMenu(menu, e.getPlayer()));
            }
        }
    }
}