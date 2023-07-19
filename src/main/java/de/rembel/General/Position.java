package de.rembel.General;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Position {

    private String name;
    private String creator;
    private Location location;
    private String dimension;
    private int type;

    public Position(String name, String[] cords, String creator, String dimension, int type){
        World world = null;
        if(dimension.equalsIgnoreCase("NORMAL")) world = Bukkit.getWorld("world");
        if(dimension.equalsIgnoreCase("NETHER")) world = Bukkit.getWorld("world_nether");
        if(dimension.equalsIgnoreCase("THE_END")) world = Bukkit.getWorld("world_the_end");
        this.name=name;
        this.creator=creator;
        this.location=new Location(world, Integer.valueOf(cords[0]), Integer.valueOf(cords[1]), Integer.valueOf(cords[2]));
        this.dimension=world.getEnvironment().name();
        this.type=type;
    }
    public Position(String name){
        this.name=name;
    }

    public String getDimension() {
        return dimension;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }

    public String getCreator() {
        return creator;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPositionAsString(){
        return ((int) location.getX())+" "+ ((int) location.getY()+" "+((int) location.getZ()));
    }
}
