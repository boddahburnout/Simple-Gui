package darth.guitest.MenuCommands;

import darth.guitest.Guitest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemSet implements CommandExecutor {
    private Guitest plugin = Guitest.getPlugin(Guitest.class);
    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("itemset")) {
            if (!sender.hasPermission("guitest."+command.getName())) {
                return true;
            }
            if (args.length < 1) {
                sender.sendMessage("/itemset <Menu-Name> <Slot>");
            } else {
                Player p = (Player) sender;
                ItemStack item = p.getInventory().getItemInMainHand();
                plugin.getConfig().set(args[0] + ".contents."+args[1]+".material", item.getType().name());
                plugin.getConfig().set(args[0] + ".contents."+args[1]+".itemname", item.getItemMeta().getDisplayName());
                plugin.saveConfig();
                plugin.reloadConfig();
                sender.sendMessage("Item added to slot "+args[1]+" of menu "+args[0]);
            }
            return true;
        }
        return true;
    }
}