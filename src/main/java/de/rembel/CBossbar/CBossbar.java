package de.rembel.CBossbar;

import de.rembel.General.Position;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.*;

public class CBossbar implements Listener {

    public static HashMap<UUID, CBossbar> bossbarContainer = new HashMap<UUID, CBossbar>();

    private Plugin plugin = null;
    private BossBar bossBar;
    private BarStyle barStyle = BarStyle.SOLID;
    private BarColor barColor = BarColor.WHITE;
    private String title = "";
    private double progress = 1;
    private UUID uuid;
    private Player player;
    private String initText = "Compass loading...";
    private boolean visible = true;
    private int viewField = 90;
    private String placeholder = "|";
    private int spaceBetween = 5;
    private int lastYaw = 181;
    private int smoothProfile = 0;
    private int yaw;
    private int entityThread;
    private boolean renderInQueue;
    private boolean waitForComplettRender = true;
    private boolean enableDirectionWiser = true;
    private ArrayList<CPosition> positionContainer = new ArrayList<CPosition>();
    //private boolean enableWaitFormComplettRender = true;


    private final CPosition north = new CPosition("N", ChatColor.RESET, 180);
    private final CPosition east = new CPosition("O", ChatColor.RESET, -90);
    private final CPosition south = new CPosition("S", ChatColor.RESET, 0);
    private final CPosition west = new CPosition("W", ChatColor.RESET, 90);

    public CBossbar(Plugin plugin){
        this.plugin=plugin;
        uuid = UUID.randomUUID();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public UUID createBossbar(Player player){
        if(player != null && bossBar != null) return null;
        if(getByPlayer(player)!=null) getByPlayer(player).remove();
        this.player=player;
        deleteExistBossbar();
        bossBar = Bukkit.createBossBar(NamespacedKey.fromString(this.player.getUniqueId().toString()), initText, barColor, barStyle);
        bossBar.addPlayer(player);
        bossbarContainer.put(this.uuid, this);
        return uuid;
    }

    public boolean remove(){
        bossBar.removeAll();
        Bukkit.removeBossBar(NamespacedKey.fromString(this.player.getUniqueId().toString()));
        HandlerList.unregisterAll(this);
        bossbarContainer.remove(uuid);
        if(Bukkit.getScheduler().isCurrentlyRunning(entityThread)) Bukkit.getScheduler().cancelTask(entityThread);
        return true;
    }

    public boolean renderBossbar(){
        if(enableDirectionWiser){
            if(!existPosition(north)) addPosition(north);
            if(!existPosition(east)) addPosition(east);
            if(!existPosition(south)) addPosition(south);
            if(!existPosition(west)) addPosition(west);
        }else{
            if(existPosition(north)) removePosition(north.getUuid());
            if(existPosition(east)) removePosition(east.getUuid());
            if(existPosition(south)) removePosition(south.getUuid());
            if(existPosition(west)) removePosition(west.getUuid());
        }
        bossBar = Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString()));
        bossBar.setStyle(barStyle);
        bossBar.setColor(barColor);
        bossBar.setProgress(progress);
        bossBar.setVisible(visible);
        yaw = (int) player.getLocation().getYaw();

        if((!renderInQueue && smoothProfile != CSmoothProfile.RESET) || !waitForComplettRender){
            if(smoothProfile==CSmoothProfile.FAST && (!(lastYaw>181) || !(lastYaw<-181))){
                int distance = getDistanceBetweenYaw(lastYaw, yaw);
                if(distance>=2){
                    renderInQueue = true;
                    if(lastYaw>yaw){
                        for(int i = 0;i<distance;i++){
                            renderBossbarTitle(yaw-i);
                        }
                    }else if(lastYaw<yaw){
                        for(int i = 0;i<distance;i++){
                            renderBossbarTitle(yaw+i);
                        }
                    }
                }
            }else if(smoothProfile==CSmoothProfile.MIDDLE && (!(lastYaw>181) || !(lastYaw<-181))){
                int distance = getDistanceBetweenYaw(lastYaw, yaw);
                if(distance>=2){
                    renderInQueue = true;
                    double modifier = 0.5;
                    if(((lastYaw > 0 && yaw < 0) && (lastYaw >= 150 && yaw <= -150)) || ((lastYaw < 0 && yaw > 0) && (lastYaw <= -150 && yaw >= 150))){
                        if(lastYaw<yaw && ((lastYaw < 0 && yaw > 0) || (lastYaw <= -150 && yaw >= 150))){
                            int currentYaw = distance;
                            for(int i = 1;currentYaw>0;i++){
                                if(currentYaw>=20){
                                    modifier = 0.3;
                                }else if(currentYaw>=15){
                                    modifier = 0.55;
                                }else if(currentYaw>=10){
                                    modifier = 0.75;
                                }else if(currentYaw>=5){
                                    modifier = 0.995;
                                }
                                currentYaw = (int) (currentYaw*(Math.pow(modifier, i)));
                                renderBossbarTitle(lastYaw-(distance-currentYaw) <= -180 ? lastYaw-(distance-currentYaw)+360 : lastYaw-(distance-currentYaw));
                                sleep(5);
                            }
                        }else if(lastYaw>yaw && ((lastYaw > 0 && yaw < 0) || (lastYaw >= 150 && yaw <= -150))){
                            int currentYaw = distance;
                            for(int i = 1;currentYaw>0;i++){
                                if(currentYaw>=20){
                                    modifier = 0.3;
                                }else if(currentYaw>=15){
                                    modifier = 0.55;
                                }else if(currentYaw>=10){
                                    modifier = 0.75;
                                }else if(currentYaw>=5){
                                    modifier = 0.995;
                                }
                                currentYaw = (int) (currentYaw*(Math.pow(modifier, i)));
                                renderBossbarTitle(lastYaw+(distance-currentYaw) >= 180 ? lastYaw+(distance-currentYaw)-360 : lastYaw+(distance-currentYaw));
                                sleep(5);
                            }
                        }
                    }else{
                        if(lastYaw>yaw){
                            int currentYaw = distance;
                            for(int i = 1;currentYaw>0;i++){
                                if(currentYaw>=20){
                                    modifier = 0.3;
                                }else if(currentYaw>=15){
                                    modifier = 0.55;
                                }else if(currentYaw>=10){
                                    modifier = 0.75;
                                }else if(currentYaw>=5){
                                    modifier = 0.995;
                                }
                                currentYaw = (int) (currentYaw*(Math.pow(modifier, i)));
                                renderBossbarTitle(lastYaw-(distance-currentYaw) <= -180 ? lastYaw-(distance-currentYaw)+360 : lastYaw-(distance-currentYaw));
                                sleep(5);
                            }
                        }else if(lastYaw<yaw){
                            int currentYaw = distance;
                            for(int i = 1;currentYaw>0;i++){
                                if(currentYaw>=20){
                                    modifier = 0.3;
                                }else if(currentYaw>=15){
                                    modifier = 0.55;
                                }else if(currentYaw>=10){
                                    modifier = 0.75;
                                }else if(currentYaw>=5){
                                    modifier = 0.995;
                                }
                                currentYaw = (int) (currentYaw*(Math.pow(modifier, i)));
                                renderBossbarTitle(lastYaw+(distance-currentYaw) >= 180 ? lastYaw+(distance-currentYaw)-360 : lastYaw+(distance-currentYaw));
                                sleep(5);
                            }
                        }
                    }
                }
            }
        }

        if(!renderInQueue) renderBossbarTitle(yaw);

        lastYaw=yaw;
        renderInQueue = false;
        return true;
    }

    private boolean renderBossbarTitle(int yaw){
        int currentStartYaw = 0;
        if(yaw < -(180-viewField/2)){
            currentStartYaw = 180-(viewField/2-(yaw-(-180)));
        }else{
            currentStartYaw = yaw-viewField/2;
        }

        StringBuilder title = new StringBuilder();

        for(int i = 0;i<viewField;i++){
            int currentYaw = currentStartYaw+i;
            if(currentYaw>180){
                currentYaw = (currentStartYaw+i)-360;
            }

            boolean isUsed = false;
            for(CPosition position : positionContainer){
                if(position.getYaw()!=0 && !isUsed || (position.getLocation() == null && position.getEntity() == null)){
                    if(position.getYaw()==currentYaw){
                        title.append(position.getColor()+position.getSymbol());
                        if(position.getSymbol().length()==2) i++;
                        isUsed = true;
                    }
                }else if(position.getLocation()!=null && !isUsed){
                    if(position.getLocation().getWorld()==player.getWorld()){
                        Location origin = this.player.getLocation();
                        Vector target = position.getLocation().toVector();
                        origin.setDirection(target.subtract(origin.toVector()));
                        int newYaw = (int) origin.getYaw();
                        if(newYaw>=180) newYaw -= 360;
                        if(newYaw==currentYaw){
                            title.append(position.getColor()+position.getSymbol());
                            if(position.getSymbol().length()==2) i++;
                            isUsed = true;
                        }
                    }
                }else if(position.getEntity()!=null && !isUsed){
                    Entity entity = Bukkit.getEntity(position.getEntity().getUniqueId());
                    if(entity!=null){
                        if(position.getEntity().getLocation().getWorld()==player.getWorld()){
                            Location origin = this.player.getLocation();
                            Vector target = entity.getLocation().toVector();
                            origin.setDirection(target.subtract(origin.toVector()));
                            int newYaw = (int) origin.getYaw();
                            if(newYaw>=180) newYaw -= 360;
                            if(newYaw==currentYaw){
                                title.append(position.getColor()+position.getSymbol());
                                if(position.getSymbol().length()==2) i++;
                                isUsed = true;
                            }
                        }
                    }else{
                        removePosition(position.getUuid());
                    }
                }
            }

            if(currentYaw%spaceBetween==0 && !isUsed){
                title.append(ChatColor.GRAY+placeholder);
                isUsed = true;
            }else{
                title.append(" ");
                isUsed = true;
            }
        }

        bossBar.setTitle(title.toString());
        return true;
    }

    private int getDistanceBetweenYaw(int lastYaw, int currentYaw){
        int distance = 0;
        if(lastYaw>currentYaw) distance = lastYaw-currentYaw;
        if(lastYaw<currentYaw) distance = currentYaw-lastYaw;
        if(distance<0) distance = -distance;
        if(distance>180) distance = 360-distance;
        return distance;
    }

    public static CBossbar get(UUID bossbarUuid){
        if(!checkExistBossbarContainer()) return null;
        if(!bossbarContainer.containsKey(bossbarUuid)) return null;
        return bossbarContainer.get(bossbarUuid);
    }

    public static CBossbar getByPlayer(Player player){
        if(player==null && !checkExistBossbarContainer()) return null;
        for(int i = 0;i<bossbarContainer.size();i++){
            Map.Entry<UUID, CBossbar> entry = bossbarContainer.entrySet().iterator().next();
            CBossbar value = entry.getValue();
            if(value.getPlayer().getUniqueId().toString().equals(player.getUniqueId().toString())){
                return entry.getValue();
            }
        }
        return null;
    }

    private boolean checkIsPlayerOnline(Player player){
        if(Bukkit.getOnlinePlayers().contains(player)) return true;
        return false;
    }

    private static boolean checkExistBossbarContainer(){
        if(bossbarContainer==null) return false;
        return true;
    }

    private boolean deleteExistBossbar(){
        if(Bukkit.getBossBar(NamespacedKey.fromString(this.player.getUniqueId().toString()))!=null){
            BossBar bar = Bukkit.getBossBar(NamespacedKey.fromString(this.player.getUniqueId().toString()));
            bar.removeAll();
            Bukkit.removeBossBar(NamespacedKey.fromString(this.player.getUniqueId().toString()));
        }
        return true;
    }

    public boolean addPosition(CPosition position){
        if(position==null || positionContainer.contains(position)) return false;

        positionContainer.add(position);
        if(position.getEntity()!=null){
            if(!Bukkit.getScheduler().isCurrentlyRunning(entityThread)){
                Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {renderBossbar(); }, 0, 100);
            }
        }
        return true;
    }

    public boolean removePosition(UUID positionUuid){
        if(positionUuid==null) return false;
        for(CPosition position : positionContainer){
            if(position.getUuid()==positionUuid){
                positionContainer.remove(position);
                return true;
            }
        }
        checkExistEntitysInList();
        return false;
    }
    
    public List<CPosition> getPositions(){
        List<CPosition> positions = new ArrayList<CPosition>();
        for(CPosition position : positionContainer){
            positions.add(position);
        }
        return positions;
    }
    
    public boolean existPosition(CPosition checkPosition){
        for(CPosition position : positionContainer){
            if(position.getUuid()==checkPosition.getUuid()){
                return true;
            }
        }
        return false;
    }

    private boolean sleep(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private boolean checkExistEntitysInList(){
        boolean existEntityInList = false;
        for(CPosition position : positionContainer){
            if(position.getEntity()!=null) existEntityInList = true;
        }
        if(!existEntityInList) if(Bukkit.getScheduler().isCurrentlyRunning(entityThread)){
            Bukkit.getScheduler().cancelTask(entityThread);
            return false;
        }
        return true;
    }

    private boolean checkEntityExist(Entity entity){
        if(entity==null){
            for(CPosition position : positionContainer){
                if(position.getEntity()!=null && position.getEntity().getUniqueId().toString().equals(entity.getUniqueId().toString())){
                    removePosition(position.getUuid());
                    checkExistEntitysInList();
                    return false;
                }
            }
        }
        return true;
    }

    public boolean removeAllPoints(){
        positionContainer.clear();
        return true;
    }











    @EventHandler
    private void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(checkIsPlayerOnline(player)){
            renderBossbar();
        }
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(this.player.getUniqueId().toString().equals(player.getUniqueId().toString())){
            this.player=player;
        }
    }











    public BarColor getBarColor() {
        return barColor;
    }

    public BarStyle getBarStyle() {
        return barStyle;
    }

    public String getTitle() {
        return title;
    }

    public double getProgress() {
        return progress;
    }

    public Player getPlayer() {
        return player;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public String getInitText() {
        return initText;
    }
    public boolean getVisible(){
        return visible;
    }

    public int getViewField() {
        return viewField;
    }

    public int getSpaceBetween() {
        return spaceBetween;
    }

    public int getSmoothProfile() {
        return smoothProfile;
    }
    public boolean getWaitForComplettRender(){
        return waitForComplettRender;
    }
    public boolean getEnableDirectionWiser(){
        return enableDirectionWiser;
    }

    public void setBarStyle(BarStyle barStyle) {
        this.barStyle = barStyle;
    }

    public void setBarColor(BarColor barColor) {
        this.barColor = barColor;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public void setInitText(String initText) {
        this.initText = initText;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setViewField(int viewField) {
        this.viewField = viewField;
    }

    public void setSpaceBetween(int spaceBetween) {
        this.spaceBetween = spaceBetween;
    }

    public void setSmoothProfile(int smoothProfile) {
        this.smoothProfile = smoothProfile;
    }

    public void setWaitForComplettRender(boolean waitForComplettRender) {
        this.waitForComplettRender = waitForComplettRender;
    }
    public void setEnableDirectionWiser(boolean enableDirectionWiser){
        this.enableDirectionWiser=enableDirectionWiser;
    }
}
