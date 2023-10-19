package de.rembel.CBossbar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class CPosition {

    private String symbol = "";
    private ChatColor color = ChatColor.RESET;
    private int yaw = 0;
    private UUID uuid;
    private Location location = null;
    private Entity entity = null;
    private String description;
    public final String SPLITTER = "/ne93m47ngg/3jtgbnr/";
    //"⌖", "💀", "🏠", "⚠", "📅", "🎮"

    public String toString(){
        StringBuilder builder = new StringBuilder();
        if(symbol.equals("⌖")) builder.append("marker").append(SPLITTER);
        if(symbol.equals("\uD83D\uDC80")) builder.append("death").append(SPLITTER);
        if(symbol.equals("\uD83C\uDFE0")) builder.append("house").append(SPLITTER);
        if(symbol.equals("⚠")) builder.append("warning").append(SPLITTER);
        if(symbol.equals("\uD83D\uDCC5")) builder.append("kalender").append(SPLITTER);
        if(symbol.equals("\uD83C\uDFAE")) builder.append("controller").append(SPLITTER);
        if(symbol.equals("N") || symbol.equals("S") || symbol.equals("W") || symbol.equals("O")) return null;
        if(builder.length()==0) builder.append(symbol).append(SPLITTER);
        builder.append(color.name()).append(SPLITTER);
        if(yaw!=0) builder.append(yaw).append(SPLITTER); else builder.append("0").append(SPLITTER);
        builder.append(uuid.toString()).append(SPLITTER);
        if(location!=null) builder.append(getStringLocation(location)).append(SPLITTER); else builder.append("null").append(SPLITTER);
        if(entity!=null) builder.append(entity.getUniqueId().toString()).append(SPLITTER); else builder.append("null").append(SPLITTER);
        builder.append(description).append(SPLITTER);
        return builder.toString();
    }

    public boolean fromString(String value){
        String[] data = value.split(SPLITTER);
        if(data[0].equals("marker")) symbol = "⌖";
        if(data[0].equals("death")) symbol = "\uD83D\uDC80";
        if(data[0].equals("house")) symbol = "\uD83C\uDFE0";
        if(data[0].equals("warning")) symbol = "⚠";
        if(data[0].equals("kalender")) symbol = "\uD83D\uDCC5";
        if(data[0].equals("controller")) symbol = "\uD83C\uDFAE";
        if(symbol.equals("N") || symbol.equals("S") || symbol.equals("W") || symbol.equals("O")) return false;
        if(symbol.equals("")) symbol = data[0];
        color = getChatColor(data[1]);
        if(!data[2].equals(0) && data[4].equals("null") && data[5].equals("null")) yaw = Integer.valueOf(data[2]);
        uuid = UUID.fromString(data[3]);
        if(!data[4].equals("null")){location = getLocationString(data[4]); }
        if(!data[5].equals("null")){
            System.out.println(data[5]);
            if(Bukkit.getEntity(UUID.fromString(data[5]))==null) return false;
            entity = Bukkit.getEntity(UUID.fromString(data[5]));
        }
        description = data[6];
        return true;
    }

    public CPosition(){
        this.uuid=UUID.randomUUID();
    }

    public CPosition(String symbol, ChatColor color, int yaw, String description){
        setSymbol(symbol);
        this.color=color;
        this.yaw=yaw;
        this.uuid=UUID.randomUUID();
        this.description=description;
    }

    public CPosition(String symbol, ChatColor color, Location location, String description){
        setSymbol(symbol);
        this.color=color;
        this.location=location;
        this.uuid=UUID.randomUUID();
        this.description=description;
    }

    public CPosition(String symbol, ChatColor color, Entity entity, String description){
        setSymbol(symbol);
        this.color=color;
        this.entity=entity;
        this.uuid=UUID.randomUUID();
        this.description=description;
    }

    public UUID getUuid() {
        return uuid;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getYaw() {
        return yaw;
    }

    public Location getLocation() {
        return location;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    public void setSymbol(String symbol) {
        if(symbol.length()>=3){
            symbol = symbol.substring(0, 2);
        }
        this.symbol=symbol;
    }

    public void setYaw(int yaw) {
        this.yaw = yaw;
        this.location=null;
        this.entity=null;
    }

    public void setLocation(Location location) {
        this.location = location;
        this.yaw=0;
        this.entity=null;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        this.yaw=0;
        this.location=null;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    static public Location getLocationString(final String s) {
        if (s == null || s.trim() == "") {
            return null;
        }
        final String[] parts = s.split(":");
        if (parts.length == 4) {
            final World w = Bukkit.getServer().getWorld(parts[0]);
            final int x = Integer.parseInt(parts[1]);
            final int y = Integer.parseInt(parts[2]);
            final int z = Integer.parseInt(parts[3]);
            return new Location(w, x, y, z);
        }
        return null;
    }

    static public String getStringLocation(final Location l) {
        if (l == null) {
            return "";
        }
        return l.getWorld().getName() + ":" + l.getBlockX() + ":" + l.getBlockY() + ":" + l.getBlockZ();
    }

    public ChatColor getChatColor(String color) {
        for(ChatColor c : ChatColor.class.getEnumConstants()) {
            if(c.name().equalsIgnoreCase(color)) {
                return c;
            }
        }
        return null;
    }
}
