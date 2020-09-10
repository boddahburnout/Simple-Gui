package darth.guitest.MenuCommands;

import darth.guitest.Event.GuiMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCommand implements CommandExecutor {
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
        if(command.getName().equalsIgnoreCase("openmenu")) {
            if (!sender.hasPermission("Guitest.openmenu")) {
                sender.sendMessage("You don't have permission to this command");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage("Please define a valid menu name!");
                return true;
            } else {
                if (args.length > 1) {
                    sender.sendMessage("Too many arguments!");
                    return true;
                }
                Player p = (Player) sender;
                try {
                    p.getPlayer().openInventory(new GuiMenu().getMenu(args[0]));
                } catch (IllegalArgumentException e) {
                    p.sendMessage("Menu not found!");
                }
            }
        }
        return true;
    }
}
