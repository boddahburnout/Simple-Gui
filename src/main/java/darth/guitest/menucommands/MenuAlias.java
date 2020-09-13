package darth.guitest.menucommands;

import darth.guitest.Guitest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MenuAlias implements CommandExecutor {
    private Guitest plugin = Guitest.getPlugin(Guitest.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("menualias")) {
            if (args.length < 2) {
                sender.sendMessage("/menualias <Menu-Name> <enabled/set> <true/false/test>");
                return true;
            } else {
                if (args[1].equalsIgnoreCase("set")) {
                    int i = 0;
                    StringBuilder sb = new StringBuilder();
                    for (String arg : args) {
                        i = i + 1;
                        if (i > 3) {
                            sb.append(arg).append(" ");
                        }
                    }
                    plugin.getConfig().set(args[0] + ".alias", args[2]);
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    sender.sendMessage("Alias has been set to " + args[2]);
                    return true;
                }
                if (args[1].equalsIgnoreCase("enabled")) {
                    if (!args[2].equalsIgnoreCase("true") || !args[2].equalsIgnoreCase("false")) {
                        sender.sendMessage("Value must be set to true or false");
                        return true;
                    }
                    plugin.getConfig().set(args[0]+".enablealias", Boolean.parseBoolean(args[2]));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    sender.sendMessage("Alias has been set to " + args[2]);
                }
            }
        }
        return true;
    }
}

