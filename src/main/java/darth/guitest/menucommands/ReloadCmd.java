package darth.guitest.menucommands;

import darth.guitest.Guitest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCmd implements CommandExecutor {
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
        if(command.getName().equalsIgnoreCase("guireload")) {
            if (!sender.hasPermission("guitest.guireload")) {
                sender.sendMessage("You are not permitted to use this command");
                return true;
            }
            plugin.saveConfig();
            plugin.reloadConfig();
            sender.sendMessage("Config has been reloaded!");
        }
        return true;
    }
}
