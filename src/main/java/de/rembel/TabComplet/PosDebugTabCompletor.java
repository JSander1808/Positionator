package de.rembel.TabComplet;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PosDebugTabCompletor implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> collection = new ArrayList<String>();

        try{
            if(sender instanceof Player){
                Player player = (Player) sender;
                if(player.isOp()){
                    if(args.length == 1){
                        String[] MajorDebug = new String[]{"compass"};
                        StringUtil.copyPartialMatches(args[0], Arrays.asList(MajorDebug), collection);
                    }

                    if(args[0].equalsIgnoreCase("compass")){
                        if(args.length == 2){
                            String[] options1 = new String[]{"modify","add","remove"};
                            StringUtil.copyPartialMatches(args[1], Arrays.asList(options1), collection);
                        }

                        if(args[1].equalsIgnoreCase("modify")){
                            if(args.length == 3){
                                String[] options2 = new String[]{"viewfield","spacebetween","smooth","directionWiser","progress","distanceTo"};
                                StringUtil.copyPartialMatches(args[2], Arrays.asList(options2), collection);
                            }

                            if(args[2].equalsIgnoreCase("smooth")){
                                if(args.length == 4){
                                    String[] options3 = new String[]{"FAST","MIDDLE","SLOW"};
                                    StringUtil.copyPartialMatches(args[3], Arrays.asList(options3), collection);
                                }
                            }
                            if(args[2].equalsIgnoreCase("directionWiser")){
                                if(args.length == 4){
                                    String[] options3 = new String[]{"true","false"};
                                    StringUtil.copyPartialMatches(args[3], Arrays.asList(options3), collection);
                                }
                            }
                            if(args[2].equalsIgnoreCase("distanceTo")){
                                if(args.length == 4){
                                    String[] options3 = new String[]{"true","false"};
                                    StringUtil.copyPartialMatches(args[3], Arrays.asList(options3), collection);
                                }
                            }
                        }
                        if(args[1].equalsIgnoreCase("add")){
                            if(args.length == 3){
                                String[] options2 = new String[]{"entity"};
                                StringUtil.copyPartialMatches(args[2], Arrays.asList(options2), collection);
                            }
                            if(args[2].equalsIgnoreCase("entity")){
                                if(args.length == 4){
                                    String[] options3 = new String[]{player.getWorld().rayTraceEntities(player.getLocation(), player.getLocation().getDirection(), 5, 0.5).getHitEntity().getUniqueId().toString()};
                                    StringUtil.copyPartialMatches(args[3], Arrays.asList(options3), collection);
                                }
                            }
                        }
                    }
                }
            }
            Collections.sort(collection);
            return collection;
        }catch(Exception e){
            return null;
        }
    }
}
