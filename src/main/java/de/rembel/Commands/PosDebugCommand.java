package de.rembel.Commands;

import de.rembel.CBossbar.CBossbar;
import de.rembel.CBossbar.CPosition;
import de.rembel.Language.LanguageManager;
import de.rembel.Language.Languages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class PosDebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            try{
                if(player.isOp()){
                    if(args[0].equalsIgnoreCase("compass")){
                        if(args[1].equalsIgnoreCase("modify")){
                            if(args[2].equalsIgnoreCase("viewfield")){
                                CBossbar compass = CBossbar.getByPlayer(player);
                                compass.setViewField(Integer.valueOf(args[3]));
                                compass.renderBossbar();
                            }
                            if(args[2].equalsIgnoreCase("spacebetween")){
                                CBossbar compass = CBossbar.getByPlayer(player);
                                compass.setSpaceBetween(Integer.valueOf(args[3]));
                                compass.renderBossbar();
                            }
                            if(args[2].equalsIgnoreCase("smooth")){
                                if(args[3].equalsIgnoreCase("FAST")){
                                    CBossbar compass = CBossbar.getByPlayer(player);
                                    compass.setSmoothProfile(1);
                                    compass.renderBossbar();
                                }else if(args[3].equalsIgnoreCase("MIDDLE")){
                                    CBossbar compass = CBossbar.getByPlayer(player);
                                    compass.setSmoothProfile(2);
                                    compass.renderBossbar();
                                }else if(args[3].equalsIgnoreCase("SLOW")){
                                    CBossbar compass = CBossbar.getByPlayer(player);
                                    compass.setSmoothProfile(3);
                                    compass.renderBossbar();
                                }else{
                                    throwError(player, label, args);
                                }
                            }
                            if(args[2].equalsIgnoreCase("directionWiser")){
                                CBossbar compass = CBossbar.getByPlayer(player);
                                compass.setEnableDirectionWiser(Boolean.valueOf(args[3]));
                                compass.renderBossbar();
                            }
                            if(args[2].equalsIgnoreCase("distanceTo")){
                                CBossbar compass = CBossbar.getByPlayer(player);
                                compass.setRenderDistanceToPosition(Boolean.valueOf(args[3]));
                                compass.renderBossbar();
                            }
                            if(args[2].equalsIgnoreCase("progress")){
                                CBossbar compass = CBossbar.getByPlayer(player);
                                compass.setProgress(Double.valueOf(args[3]));
                                compass.renderBossbar();
                            }
                        }
                        if(args[1].equalsIgnoreCase("add")){
                            if(args[2].equalsIgnoreCase("entity")){
                                CPosition position = new CPosition("\uD83C\uDFAE",getRandomColor(), Bukkit.getEntity(UUID.fromString(args[3])), Bukkit.getEntity(UUID.fromString(args[3])).getName());
                                CBossbar compass = CBossbar.getByPlayer(player);
                                compass.addPosition(position);
                            }
                        }
                    }
                }
            }catch(Exception e){
                throwError(player, label, args);
            }
        }
        return false;
    }

    private void throwError(Player player, String label, String[] args){
        LanguageManager language = new LanguageManager(player);

        StringBuilder error = new StringBuilder("/"+label);
        for(String arg : args){
            error.append(" "+arg);
        }
        player.sendMessage(language.transalte(189));
        player.sendMessage(error.toString()+""+ChatColor.RED+"<--");
    }

    public ChatColor getRandomColor(){
        ChatColor color = null;
        int random = 1 + (int)(Math.random() * ((7 - 1) + 1));
        if(random==1) color = ChatColor.RED;
        if(random==2) color = ChatColor.BLUE;
        if(random==3) color = ChatColor.YELLOW;
        if(random==4) color = ChatColor.DARK_PURPLE;
        if(random==5) color = ChatColor.LIGHT_PURPLE;
        if(random==6) color = ChatColor.AQUA;
        if(random==7) color = ChatColor.GOLD;
        return color;
    }
}
