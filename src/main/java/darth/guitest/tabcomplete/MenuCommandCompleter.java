package darth.guitest.tabcomplete;

import darth.guitest.Guitest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MenuCommandCompleter implements TabCompleter {
    List<String> tab = new ArrayList<>();
    private Guitest plugin = Guitest.getPlugin(Guitest.class);

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> match = new ArrayList<>();
        if (command.getName().equalsIgnoreCase("openmenu")) {
            if (args.length == 1) {
                Set<String> menus = plugin.getConfig().getKeys(false);
                for (String menu : menus) {
                    match.add(menu);
                }
            }
            if (sender instanceof Player) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    sb.append(args[i]).append(" ");
                }
                for (String list : tab) {
                    if (list.toLowerCase().startsWith(sb.toString().trim())) {
                        match.add(list);
                    }
                }
                return match;
            }
        }
        return null;
    }
}