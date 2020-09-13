package darth.guitest.TabComplete;

import darth.guitest.Guitest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SetSlotLoreCompleter implements TabCompleter {
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
        List<String> match = new ArrayList<>();
        List<String> tab = new ArrayList<>();
        if (command.getName().equalsIgnoreCase("setitemlore")) {
            if (args.length == 1) {
                Set<String> menus = plugin.getConfig().getKeys(false);
                for (String menu : menus) {
                    match.add(menu);
                }
            }
            if (args.length == 2) {
                Set<String> slots = plugin.getConfig().getConfigurationSection(args[0] + ".contents").getKeys(false);
                for (String slot : slots) {
                    match.add(slot);
                }
            }
            if (args.length == 3) {
                for (int i = 1; i < 5; i = i + 1) {
                    match.add(String.valueOf(i));
                }
            }
        }
        return match;
    }
}