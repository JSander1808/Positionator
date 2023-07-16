package de.rembel.TextInput;

import de.rembel.General.Command;
import de.rembel.Main.PositionatorMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TextInputService {

    public static HashMap<UUID, TextInputModel> textInputSessionConntainer = new HashMap<UUID, TextInputModel>();
    public static ArrayList<UUID> textInputActiveSession = new ArrayList<UUID>();
    public static int ServiceTaskId;
    public static int sessionTimeSeconds = 30;

    public TextInputService(Player player, String reason, Command command){
        if(textInputActiveSession.contains(player.getUniqueId())){
            textInputSessionConntainer.remove(player.getUniqueId());
        }else{
            textInputActiveSession.add(player.getUniqueId());
        }
        textInputSessionConntainer.put(player.getUniqueId(), new TextInputModel(null, reason, player.getUniqueId(), command, sessionTimeSeconds));
        player.sendMessage(ChatColor.GREEN+"You now have "+ChatColor.GOLD+sessionTimeSeconds+"s"+ChatColor.GREEN+" to type in der Chat");
        if(reason != null && reason != "") player.sendMessage(reason+ChatColor.GRAY+":");
    }

    public static void TextInputService(){
        System.out.println("TextInputService started");
        ServiceTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(PositionatorMain.getPlugin(), () -> {
            for(int i = 0;i<textInputActiveSession.size();i++){
                UUID uuid = textInputActiveSession.get(i);
                if(textInputSessionConntainer.get(uuid).getTime()<=0){
                    textInputSessionConntainer.remove(uuid);
                    textInputActiveSession.remove(uuid);
                    for(Player player : Bukkit.getOnlinePlayers()){
                        if(player.getUniqueId() == uuid){
                            Bukkit.getPlayer(uuid).sendMessage(ChatColor.DARK_GRAY+"Time to enter has expired");
                        }
                    }
                }else{
                    TextInputModel model = textInputSessionConntainer.get(uuid);
                    textInputSessionConntainer.replace(uuid, new TextInputModel(model.getMessage(), model.getReason(), model.getUuid(), model.getCommand(), model.getTime()-1));
                    System.out.println(uuid.toString()+" - "+model.getTime());
                }
            }
        },0,30);
    }
}
