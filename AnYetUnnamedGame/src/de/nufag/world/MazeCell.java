/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.nufag.world;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Janosch
 */
public class MazeCell {
    public static final String PROP_POSX = "PROP_POSX";
    public static final String PROP_POSY = "PROP_POSY";
    public static final String PROP_NORTH = "PROP_NORTH";
    public static final String PROP_EAST = "PROP_EAST";
    public static final String PROP_SOUTH = "PROP_SOUTH";
    public static final String PROP_WEST = "PROP_WEST";
    public static final String PROP_WALLNORTH = "PROP_WALLNORTH";
    public static final String PROP_WALLEAST = "PROP_WALLEAST";
    public static final String PROP_WALLSOUTH = "PROP_WALLSOUTH";
    public static final String PROP_WALLWEST = "PROP_WALLWEST";
    private int posX;
    private int posY;
    
    private MazeCell north;
    private MazeCell east;
    private MazeCell south;
    private MazeCell west;
    
    private boolean exit;
    private boolean wallNorth;
    private boolean wallEast;
    private boolean wallSouth;
    private boolean wallWest;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    public MazeCell() {
        posX=0;
        posY=0;
        north = null;
        east = null;
        south = null;
        west = null;
        wallNorth = false;
        wallNorth = false;
        wallNorth = false;
        wallNorth = false;
    }
    public List<MazeCell> getNeighbors() {    
        ArrayList<MazeCell> neighbors = new ArrayList<MazeCell>();
        if(getNorth()!= null) {   
            neighbors.add(getNorth());
        }
        if(getSouth()!= null) {
            neighbors.add(getSouth());
        }
        if(getEast()!= null) {
            neighbors.add(getEast());
        }
        if(getWest()!= null) {
            neighbors.add(getWest());
        }
        return neighbors;
    }
    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(int posX) {
        int oldPosX = posX;
        this.posX = posX;
        propertyChangeSupport.firePropertyChange(PROP_POSX, oldPosX, posX);
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(int posY) {
        int oldPosY = posY;
        this.posY = posY;
        propertyChangeSupport.firePropertyChange(PROP_POSY, oldPosY, posY);
    }

    /**
     * @return the north
     */
    public MazeCell getNorth() {
        return north;
    }

    /**
     * @param north the north to set
     */
    public void setNorth(MazeCell north) {
       MazeCell oldNorth = north;
        this.north = north;
        propertyChangeSupport.firePropertyChange(PROP_NORTH, oldNorth, north);
    }

    /**
     * @return the east
     */
    public MazeCell getEast() {
        return east;
    }

    /**
     * @param east the east to set
     */
    public void setEast(MazeCell east) {
        MazeCell oldEast = east;
        this.east = east;
        propertyChangeSupport.firePropertyChange(PROP_EAST, oldEast, east);
    }

    /**
     * @return the south
     */
    public MazeCell getSouth() {
        return south;
    }

    /**
     * @param south the south to set
     */
    public void setSouth(MazeCell south) {
        MazeCell oldSouth = south;
        this.south = south;
        propertyChangeSupport.firePropertyChange(PROP_SOUTH, oldSouth, south);
    }

    /**
     * @return the west
     */
    public MazeCell getWest() {
        return west;
    }

    /**
     * @param west the west to set
     */
    public void setWest(MazeCell west) {
       MazeCell oldWest = west;
        this.west = west;
        propertyChangeSupport.firePropertyChange(PROP_WEST, oldWest, west);
    }

    /**
     * @return the wallNorth
     */
    public boolean isWallNorth() {
        return wallNorth;
    }

    /**
     * @param wallNorth the wallNorth to set
     */
    public void setWallNorth(boolean wallNorth) {
        boolean oldWallNorth = wallNorth;
        this.wallNorth = wallNorth;
        propertyChangeSupport.firePropertyChange(PROP_WALLNORTH, oldWallNorth, wallNorth);
    }

    /**
     * @return the wallEast
     */
    public boolean isWallEast() {
        return wallEast;
    }

    /**
     * @param wallEast the wallEast to set
     */
    public void setWallEast(boolean wallEast) {
        boolean oldWallEast = wallEast;
        this.wallEast = wallEast;
        propertyChangeSupport.firePropertyChange(PROP_WALLEAST, oldWallEast, wallEast);
    }

    /**
     * @return the wallSouth
     */
    public boolean isWallSouth() {
        return wallSouth;
    }

    /**
     * @param wallSouth the wallSouth to set
     */
    public void setWallSouth(boolean wallSouth) {
        boolean oldWallSouth = wallSouth;
        this.wallSouth = wallSouth;
        propertyChangeSupport.firePropertyChange(PROP_WALLSOUTH, oldWallSouth, wallSouth);
    }

    /**
     * @return the wallWest
     */
    public boolean isWallWest() {
        return wallWest;
    }

    /**
     * @param wallWest the wallWest to set
     */
    public void setWallWest(boolean wallWest) {
        boolean oldWallWest = wallWest;
        this.wallWest = wallWest;
        propertyChangeSupport.firePropertyChange(PROP_WALLWEST, oldWallWest, wallWest);
    }

    /**
     * @return the exit
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * @param exit the exit to set
     */
    public void setExit(boolean exit) {
        this.exit = exit;
    }
}
