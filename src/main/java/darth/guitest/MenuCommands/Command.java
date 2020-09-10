package darth.guitest.MenuCommands;

import darth.guitest.Event.GuiMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Command extends BukkitCommand {
    String path = "";
    public Command(String name) {
        super(name);
        this.description = "Opens "+name;
        this.usageMessage = "/"+name;
        this.setPermission("GuiTest."+name);
        this.setAliases(new ArrayList<String>());
        path = name;
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(ChatColor.RED + "You don't have permission.");
            return true;
        }
        Player p = (Player) sender;
        p.openInventory(new GuiMenu().getMenu(path));
        return true;
    }
}