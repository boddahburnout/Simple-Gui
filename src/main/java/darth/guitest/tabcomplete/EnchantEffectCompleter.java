package darth.guitest.tabcomplete;

import darth.guitest.Guitest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EnchantEffectCompleter implements TabCompleter {
    private Guitest plugin = Guitest.getPlugin(Guitest.class);
    /**
     * Requests a list of possible completions for a command argument.
     *
     * @param sender  Source of the command.  For players tab-completing a
     *                command inside of a command block, this will be the player, not
     *                the command block.
     * @param command Command which was executed
     * @param alias   The alias used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed and command label
     * @return A List of possible completions for the final argument, or null
     * to default to the command executor
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tab = new ArrayList<>();
        List<String> match = new ArrayList<>();
        if (command.getName().equalsIgnoreCase("setenchant")) {
            if (args.length == 1) {
                Set<String> menus = plugin.getConfig().getKeys(false);
                for (String menu : menus) {
                    match.add(menu);
                }
            }
            if (args.length == 2) {
                Set<String> slots = plugin.getConfig().getConfigurationSection(args[0]+".contents").getKeys(false);
                for (String slot : slots) {
                    match.add(slot);
                }
            }
            if (args.length == 3) {
                match.add("true");
                match.add("false");
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
