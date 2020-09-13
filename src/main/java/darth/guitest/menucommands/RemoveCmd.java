package darth.guitest.menucommands;

import darth.guitest.Guitest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RemoveCmd implements CommandExecutor {
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
        if (command.getName().equalsIgnoreCase("removecmd")) {
            if (!sender.hasPermission("guitest."+command.getName())) {
                return true;
            }
            if (args.length < 2) {
                sender.sendMessage("/removecmd <Menu-Name> <Slot>");
                return true;
            } else {
                plugin.getConfig().set(args[0]+".contents."+args[1]+".actions.commands", null);
                plugin.saveConfig();
                plugin.reloadConfig();
                sender.sendMessage("Command action removed!");
                return true;
            }
        }
        return true;
    }
}
