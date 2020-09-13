package darth.guitest.Event;

import darth.guitest.Guitest;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuClick implements Listener {
    private Guitest plugin = Guitest.getPlugin(Guitest.class);

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (plugin.getConfig().get(e.getView().getTitle().replaceAll(" ", "-")) != null) {
            if (e.getClickedInventory() == null) return;
            if (e.getClickedInventory().getType() == null) return;
            if (e.getSlot() < plugin.getConfig().getInt(e.getView().getTitle().replaceAll(" ", "-") + ".size") && e.getClickedInventory().getType().name() != "PLAYER") {
                e.setCancelled(true);
                if (e.getClick().isLeftClick()) {
                    int slot = e.getSlot() + 1;
                    String command = plugin.getConfig().getString(e.getView().getTitle().replaceAll(" ", "-") + ".contents." + slot + ".actions.commands");
                    Player p = (Player) e.getWhoClicked();
                    if (plugin.getConfig().getString(e.getView().getTitle().replaceAll(" ", "-") + ".contents." + slot + ".actions.commands") != null) {
                        p.performCommand(command);
                    }
                    if (plugin.getConfig().getString(e.getView().getTitle().replaceAll(" ", "-") + ".contents." + slot + ".actions.menu") != null) {
                        String menu = plugin.getConfig().getString(e.getView().getTitle().replaceAll(" ", "-") + ".contents." + slot + ".actions.menu");
                        e.getWhoClicked().closeInventory();
                        e.getWhoClicked().openInventory(new GuiMenu().getMenu(menu, p));
                    }
                    if (plugin.getConfig().getBoolean(e.getView().getTitle().replaceAll(" ", "-") + ".contents." + slot + ".close-after-actions")) {
                        e.getWhoClicked().closeInventory();
                    }

                }
            }
        }
    }
}