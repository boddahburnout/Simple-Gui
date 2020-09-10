package darth.guitest.MenuCommands;

import darth.guitest.Guitest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MenuAction implements CommandExecutor {
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
        if (command.getName().equalsIgnoreCase("setaction")) {
            if (args.length < 4) {
                sender.sendMessage("/setaction <Menu-Name> <Slot> <Action-Type> <Action>");
            } else {
                int i = 0;
                StringBuilder sb = new StringBuilder();
                for (String arg : args) {
                    i = i + 1;
                    if (i > 3) {
                        sb.append(arg).append(" ");
                    }
                }
                plugin.getConfig().set(args[0] + ".contents." + args[1] + ".actions." + args[2], sb.toString().trim());
                sender.sendMessage("Action set!");
                plugin.saveConfig();
                plugin.reloadConfig();
            }
        }
        return true;
    }
}
