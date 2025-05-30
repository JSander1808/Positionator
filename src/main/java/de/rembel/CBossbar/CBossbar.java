package de.rembel.CBossbar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.General.Position;
import de.rembel.Language.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Panda;
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
    private boolean renderDistanceToPosition = true;
    public final String SPLITTER = "/me02ng478hgn/n2m904ngh/";
    public final String ARRAYSPLITTER = "/meng99n3htn/03gnh48thngneje/";
    private ArrayList<CPosition> positionContainer = new ArrayList<CPosition>();
    private int distanceYawTolerenz = 11;
    public static boolean globalUpdateTime = true;
    //private boolean enableWaitFormComplettRender = true;


    private final CPosition north = new CPosition("N", ChatColor.WHITE, 180, "North");
    private final CPosition east = new CPosition("O", ChatColor.WHITE, -90, "East");
    private final CPosition south = new CPosition("S", ChatColor.WHITE, 0, "South");
    private final CPosition west = new CPosition("W", ChatColor.WHITE, 90, "West");

    public boolean fromString(String value){
        String[] data = value.split(SPLITTER);
        barStyle = BarStyle.valueOf(data[0]);
        barColor = BarColor.valueOf(data[1]);
        title = data[2];
        progress = Double.valueOf(data[3]);
        uuid = UUID.fromString(data[4]);
        player = Bukkit.getPlayer(UUID.fromString(data[5]));
        initText = data[6];
        visible = Boolean.valueOf(data[7]);
        placeholder = data[8];
        spaceBetween = Integer.valueOf(data[9]);
        yaw = Integer.valueOf(data[10]);
        if(data.length==12){
            for(int i = 0;i<data[11].split(ARRAYSPLITTER).length;i++){
                CPosition position = new CPosition();
                if(position.fromString(data[11].split(ARRAYSPLITTER)[i])) positionContainer.add(position);
            }
        }

        deleteExistBossbar();
        bossBar = Bukkit.createBossBar(NamespacedKey.fromString(this.player.getUniqueId().toString()), initText, barColor, barStyle);
        bossBar.addPlayer(player);
        General.bossbarContainer.put(this.uuid, this);
        //smoothProfile = CSmoothProfile.MIDDLE;
        return true;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(barStyle.toString()).append(SPLITTER);
        builder.append(barColor.toString()).append(SPLITTER);
        builder.append(title).append(SPLITTER);
        builder.append(progress).append(SPLITTER);
        builder.append(uuid.toString()).append(SPLITTER);
        builder.append(player.getUniqueId().toString()).append(SPLITTER);
        builder.append(initText).append(SPLITTER);
        builder.append(visible).append(SPLITTER);
        builder.append(placeholder).append(SPLITTER);
        builder.append(spaceBetween).append(SPLITTER);
        builder.append(yaw).append(SPLITTER);
        for(CPosition position : positionContainer){
            if(position.toString() != null) builder.append(position.toString()).append(ARRAYSPLITTER);
        }
        return builder.toString();
    }

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
        General.bossbarContainer.put(this.uuid, this);
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
        return uuid;
    }

    public boolean remove(){
        if(bossBar != null) bossBar.removeAll();
        Bukkit.removeBossBar(NamespacedKey.fromString(this.player.getUniqueId().toString()));
        HandlerList.unregisterAll(this);
        if(General.bossbarContainer!=null) General.bossbarContainer.remove(uuid);
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
            removePosition(north.getUuid());
            removePosition(east.getUuid());
            removePosition(south.getUuid());
            removePosition(west.getUuid());
        }
        bossBar = Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString()));
        if(bossBar == null){
            remove();
            return false;
        }
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
            }else if(smoothProfile==CSmoothProfile.SLOW && (!(lastYaw>181) || !(lastYaw<-181))){
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
        CPosition selectedPosition = null;
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
            for(int j = positionContainer.size();j>0;j--){
                CPosition position = positionContainer.get(j-1);
                if(position.getYaw()!=0 && !isUsed || (position.getLocation() == null && position.getEntity() == null && !isUsed)){
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
                        if(newYaw>180) newYaw -= 360;
                        if(newYaw==currentYaw){
                            if(player.getLocation().getBlockY()<position.getLocation().getBlockY()) title.append(position.getColor()+position.getSymbol()+"↑");
                            if(player.getLocation().getBlockY()>position.getLocation().getBlockY()) title.append(position.getColor()+position.getSymbol()+"↓");
                            if(player.getLocation().getBlockY()==position.getLocation().getBlockY()) title.append(position.getColor()+position.getSymbol());
                            if(position.getSymbol().length()==2) i++;
                            isUsed = true;
                        }
                        if(newYaw > player.getLocation().getYaw()-distanceYawTolerenz && newYaw < player.getLocation().getYaw()+distanceYawTolerenz){
                            selectedPosition = position;
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
                                if(player.getLocation().getBlockY()<entity.getLocation().getBlockY()) title.append(position.getColor()+position.getSymbol()+"↑");
                                if(player.getLocation().getBlockY()>entity.getLocation().getBlockY()) title.append(position.getColor()+position.getSymbol()+"↓");
                                if(player.getLocation().getBlockY()==entity.getLocation().getBlockY()) title.append(position.getColor()+position.getSymbol());
                                if(position.getSymbol().length()==2) i++;
                                isUsed = true;
                            }
                            if(newYaw > player.getLocation().getYaw()-distanceYawTolerenz && newYaw < player.getLocation().getYaw()+distanceYawTolerenz){
                                selectedPosition = position;
                            }
                        }
                    }else{
                        removePosition(position.getUuid());
                        NormalConfig playerConfig = new NormalConfig("plugins//Positionator//Data//User//"+this.player.getUniqueId().toString()+"//config.yml");
                        if(this!=null) if(this.getPositions().size()==0) if(!playerConfig.getBoolean("compassAlwaysActive")) this.remove();
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

        if(selectedPosition != null && renderDistanceToPosition){
            String p;
            if(selectedPosition.getLocation() != null){
                p = "     "+selectedPosition.getColor()+(int) selectedPosition.getLocation().distance(player.getLocation());
            }else{
                p = "     "+selectedPosition.getColor()+(int) selectedPosition.getEntity().getLocation().distance(player.getLocation());
            }
            String space = new String(new char[p.length()]).replace('\0', ' ');
            bossBar.setTitle(space+title.toString()+p);
        }else{
            bossBar.setTitle(title.toString());
        }
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
        if(!General.bossbarContainer.containsKey(bossbarUuid)) return null;
        return General.bossbarContainer.get(bossbarUuid);
    }

    public static CBossbar getByPlayer(Player player){
        if(player==null && !checkExistBossbarContainer()) return null;
        for(int i = 0;i<General.bossbarContainer.size();i++){
            Object firstKey = General.bossbarContainer.keySet().toArray()[i];
            CBossbar value = General.bossbarContainer.get(firstKey);
            if(value.getPlayer().getUniqueId().toString().equals(player.getUniqueId().toString())){
                return value;
            }
        }
        return null;
    }

    private boolean checkIsPlayerOnline(Player player){
        if(Bukkit.getOnlinePlayers().contains(player)) return true;
        return false;
    }

    private static boolean checkExistBossbarContainer(){
        if(General.bossbarContainer==null) return false;
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
        if(getPositionsWithoutEntitys().size() <= 17 && getOnlyEntityPositions().size() <= 5){
            if(position==null || positionContainer.contains(position)) return false;

            positionContainer.add(position);
            if(position.getEntity()!=null){
                if(!Bukkit.getScheduler().isCurrentlyRunning(entityThread) && !globalUpdateTime){
                    Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {renderBossbar(); }, 0, 100);
                }
            }
            NormalConfig playerConfig = new NormalConfig("plugins//Positionator//Data//User//"+this.player.getUniqueId().toString()+"//config.yml");
            if(CBossbar.getByPlayer(player)!=null) playerConfig.set("compassSave", CBossbar.getByPlayer(this.player).toString()); else playerConfig.set("compassSave","");
        }else{
            LanguageManager language = new LanguageManager(player);
            player.sendMessage(language.transalte(190));
            return false;
        }
        return true;
    }

    public ArrayList<CPosition> getPositionsWithoutEntitys(){
        ArrayList list = new ArrayList();
            for(int i = 0;i<getPositions().size();i++){
                if(getPositions().get(i).getEntity()==null){
                    list.add(getPositions().get(i));
                }
            }
        return list;
    }

    public ArrayList<CPosition> getOnlyEntityPositions(){
        ArrayList list = new ArrayList();
            for(int i = 0;i<getPositions().size();i++){
                if(getPositions().get(i).getEntity()!=null){
                    list.add(getPositions().get(i));
                }
            }

        return list;
    }

    public boolean removePosition(UUID positionUuid){
        if(positionUuid==null) return false;
        for(CPosition position : positionContainer){
            if(position.getUuid()==positionUuid){
                positionContainer.remove(position);
                return true;
            }
        }
        NormalConfig playerConfig = new NormalConfig("plugins//Positionator//Data//User//"+this.player.getUniqueId().toString()+"//config.yml");
        if(CBossbar.getByPlayer(player)!=null) playerConfig.set("compassSave", CBossbar.getByPlayer(this.player).toString()); else playerConfig.set("compassSave","");
        checkExistEntitysInList();
        return false;
    }
    
    public List<CPosition> getPositions(){
        List<CPosition> positions = new ArrayList<CPosition>();
        for(CPosition position : positionContainer){
            if(!position.getUuid().toString().equals(north.getUuid().toString()) && !position.getUuid().toString().equals(south.getUuid().toString()) && !position.getUuid().toString().equals(west.getUuid().toString()) && !position.getUuid().toString().equals(east.getUuid().toString())){
                positions.add(position);
            }
        }
        return positions;
    }
    
    public CPosition getPositionByDescription(String description){
        for(CPosition position : positionContainer){
            if(position.getDescription().equals(description)){
                return position;
            }
        }
        return null;
    }

    public boolean updatePosition(CPosition position){
        for(int i = 0;i<positionContainer.size();i++){
            if(positionContainer.get(i).getUuid().toString().equals(position.getUuid().toString())){
                positionContainer.set(i, position);
                return true;
            }
        }
        NormalConfig playerConfig = new NormalConfig("plugins//Positionator//Data//User//"+this.player.getUniqueId().toString()+"//config.yml");
        if(CBossbar.getByPlayer(player)!=null) playerConfig.set("compassSave", CBossbar.getByPlayer(this.player).toString()); else playerConfig.set("compassSave","");
        return false;
    }
    
    public boolean existPosition(CPosition checkPosition){
        if(checkPosition==null) return false;
        for(CPosition position : positionContainer){
            if(position.getUuid().toString().equals(checkPosition.getUuid().toString())) return true;
            if(checkLocations(position.getLocation(), checkPosition.getLocation())) return true;
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

    private boolean checkLocations(Location location1, Location location2){
        if(location1 == null || location2 == null) return false;
        if(location1.getBlockX()!=location2.getBlockX()) return false;
        if(location1.getBlockY()!=location2.getBlockY()) return false;
        if(location1.getBlockZ()!=location2.getBlockZ()) return false;
        if(!location1.getWorld().getEnvironment().name().equals(location2.getWorld().getEnvironment().name())) return false;
        return true;
    }

    public void saveAsString(){

    }











    @EventHandler
    private void onMove(PlayerMoveEvent event){
        if(checkIsPlayerOnline(this.player)){
            if(!globalUpdateTime){
//                renderBossbar();
            }
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

    public boolean isGlobalUpdateTime() {
        return globalUpdateTime;
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

    public String getPlaceholder() {
        return placeholder;
    }

    public boolean isRenderDistanceToPosition() {
        return renderDistanceToPosition;
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

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }


    private void setPlayer(Player player) {
        this.player = player;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    private void setYaw(int yaw) {
        this.yaw = yaw;
    }

    public void setRenderDistanceToPosition(boolean renderDistanceToPosition) {
        this.renderDistanceToPosition = renderDistanceToPosition;
    }

    private void setPositionContainer(ArrayList<CPosition> positionContainer) {
        this.positionContainer = positionContainer;
    }
}
