package at.coderdojo.teleporter;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Dictionary;
import java.util.Hashtable;

public final class Teleporter extends JavaPlugin {
    Dictionary<String, Location> teleportZiele = new Hashtable<>();

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //sender.sendMessage("Hallo " + sender.getName());
        switch (command.getName()) {
            case "sayhello":
                sender.sendMessage("Hallo " + sender.getName());
                break;
            case "teleportziel":
                teleportZielMerken(sender);
                break;
            case "teleportzu":
                teleportZuZiel(sender);
                break;
            default:
                sender.sendMessage("Leider kann ich das Kommando " + command.getName() + " noch nicht.");
        }

        return true;
    }

    private void teleportZielMerken(CommandSender sender) {
        Player player = (Player) sender;
        teleportZiele.put(player.getName(), player.getLocation());
        player.sendMessage("Ok, " + player.getName() + ", ich habe mir das gemerkt");
    }

    private void teleportZuZiel(CommandSender sender) {
        Player player = (Player) sender;
        if (teleportZiele.get(player.getName()) == null) {
            player.sendMessage("Hey, " + player.getName() + ". Du musst dir zuerst einen Punkt merken");
            return;
        }

        player.teleport(teleportZiele.get(player.getName()));
        player.sendMessage("Ok, " + player.getName() + ", hab dich dorthin geschickt");
    }
}
