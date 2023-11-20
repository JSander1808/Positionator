package de.rembel.Commands;

import de.rembel.CBossbar.CBossbar;
import de.rembel.CBossbar.CPosition;
import de.rembel.CBossbar.CSmoothProfile;
import de.rembel.General.BackUpManager;
import de.rembel.General.General;
import de.rembel.Main.PositionatorMain;
import de.rembel.Menus.BackUpMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class BackUpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp()){
            if(args.length==0){
                if(sender instanceof Player){
                    Player player = ((Player) sender).getPlayer();
                    new BackUpMenu(player, 1);
                }
            }else{
                if(args[0].equalsIgnoreCase("create")) {
                    BackUpManager backUpManager = new BackUpManager();
                    if (backUpManager.createBackUp(sender.getName(), "Triggered by user"))
                        sender.sendMessage(ChatColor.GRAY + "BackUp created");
                }
                if(args.length >= 2){
                    if(args[0].equalsIgnoreCase("load") && (args[1] != null || args[1] != "")){
                        BackUpManager backUpManager = new BackUpManager();
                        ArrayList<String> backUps = new ArrayList<String>();
                        for(File file : new File("plugins//Positionator_BackUp//").listFiles()){
                            backUps.add(file.getName());
                        }
                        if(backUps.contains(args[1])){
                            backUpManager.loadBackUp(args[1]);
                        }
                    }
                    if(args[0].equalsIgnoreCase("delete") && (args[1] != null || args[1] != "")){
                        BackUpManager backUpManager = new BackUpManager();
                        ArrayList<String> backUps = new ArrayList<String>();
                        for(File file : new File("plugins//Positionator_BackUp//").listFiles()){
                            backUps.add(file.getName());
                        }
                        if(backUps.contains(args[1])) {
                            if(backUpManager.deleteBackUp(args[1])) sender.sendMessage(ChatColor.GRAY+"BackUp deleted");
                        }
                    }
                    if(args[0].equalsIgnoreCase("entity")){
                        Player player = (Player) sender;
                        CBossbar.getByPlayer(player).addPosition(new CPosition("⌖", ChatColor.GOLD, Bukkit.getEntity(UUID.fromString(args[1])), Bukkit.getEntity(UUID.fromString(args[1])).getName()));
                    }
                }
            }
        }else{
            sender.sendMessage(ChatColor.RED+"You must be a Operator to perform this Command");
        }

        return true;
    }
}
