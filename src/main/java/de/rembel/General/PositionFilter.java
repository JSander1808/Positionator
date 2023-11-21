package de.rembel.General;

import org.bukkit.entity.Player;

public class PositionFilter {

    private String Playername;
    private String Dimension;
    private int Distance = -1;
    private Player Player;

    public PositionFilter(){

    }

    public String getDimension() {
        return Dimension;
    }

    public String getPlayername() {
        return Playername;
    }
    public int getDistance() {
        return Distance;
    }
    public Player getPlayer(){return Player;}

    public void setDimension(String dimension) {
        Dimension = dimension;
    }

    public void setPlayername(String playername) {
        Playername = playername;
    }
    public void setDistance(int distance){
        this.Distance=distance;
    }
    public void setPlayer(Player Player){this.Player=Player;}

    public boolean hasPlayername(){
        if(Playername != null) return true;
        return false;
    }

    public boolean hasDimension(){
        if(Dimension != null) return true;
        return false;
    }
    public boolean hasDistance(){
        if(Distance <= -1){
            return false;
        }
        return true;
    }
    public boolean hasPlayer(){
        if(Player != null){
            return true;
        }
        return false;
    }
    public void removePlayername(){
        Playername = null;
    }
    public void removeDimension(){
        Dimension = null;
    }
    public void removeDistance() {Distance = -1;}
    public void removePlayer(){Player = null;}
}
