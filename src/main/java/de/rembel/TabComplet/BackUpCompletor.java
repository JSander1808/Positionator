package de.rembel.TabComplet;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BackUpCompletor implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if(sender.isOp()){
            String[] commands = new String[]{"create", "load", "delete"};
            String[] load = new String[new File("plugins//Positionator_BackUp//").list().length];

            for(int i = 0;i<new File("plugins//Positionator_BackUp//").list().length;i++){
                load[i] = new File("plugins//Positionator_BackUp//").listFiles()[i].getName();
            }

            List<String> collection = new ArrayList<String>();

            if(args.length<=1){
                StringUtil.copyPartialMatches(args[0], Arrays.asList(commands), collection);
            }else if((args[0].equalsIgnoreCase("load") || args[0].equalsIgnoreCase("delete")) && args.length == 2){
                StringUtil.copyPartialMatches(args[1], Arrays.asList(load), collection);
            }

            Collections.sort(collection);
            return collection;
        }
        return null;
    }
}
