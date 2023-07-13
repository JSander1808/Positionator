package de.rembel.General;

import org.bukkit.entity.Player;

public class PositionFilter {

    private String Playername;
    private String Dimension;

    public PositionFilter(){

    }

    public String getDimension() {
        return Dimension;
    }

    public String getPlayername() {
        return Playername;
    }

    public void setDimension(String dimension) {
        Dimension = dimension;
    }

    public void setPlayername(String playername) {
        Playername = playername;
    }
    public boolean hasPlayername(){
        if(Playername != null) return true;
        return false;
    }

    public boolean hasDimension(){
        if(Dimension != null) return true;
        return false;
    }
    public void removePlayername(){
        Playername = null;
    }
    public void removeDimension(){
        Dimension = null;
    }
}
