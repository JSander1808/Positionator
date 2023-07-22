package de.rembel.CBossbar;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class CPosition {

    private String symbol = "";
    private ChatColor color = ChatColor.RESET;
    private int yaw = 0;
    private UUID uuid;
    private Location location = null;
    private Entity entity = null;

    public CPosition(){
    }

    public CPosition(String symbol, ChatColor color, int yaw){
        setSymbol(symbol);
        this.color=color;
        this.yaw=yaw;
        this.uuid=UUID.randomUUID();
    }

    public CPosition(String symbol, ChatColor color, Location location){
        setSymbol(symbol);
        this.color=color;
        this.location=location;
        this.uuid=UUID.randomUUID();
    }

    public CPosition(String symbol, ChatColor color, Entity entity){
        setSymbol(symbol);
        this.color=color;
        this.entity=entity;
        this.uuid=UUID.randomUUID();
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
}
