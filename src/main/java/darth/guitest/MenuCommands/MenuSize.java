package darth.guitest.MenuCommands;

import darth.guitest.Guitest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MenuSize implements CommandExecutor {
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
        if (command.getName().equalsIgnoreCase("setsize")) {
            if (args.length < 2) {
                sender.sendMessage("/setsize <Menu-Name> <Size>");
                return true;
            } else {
                Integer size = Integer.parseInt(args[1]);
                boolean isdivisableby9 = size % 9 == 0;
                if (isdivisableby9 && size < 55) {
                    plugin.getConfig().set(args[0] + ".size", Integer.parseInt(args[1]));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    sender.sendMessage("Inventory size has been set to " + args[1]);
                } else {
                    sender.sendMessage("Size must be divisible by 9 and equal to or less then 54");
                    return true;
                }
            }
            return true;
        }
        return true;
    }
}