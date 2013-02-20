/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.nufag.world;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Janosch
 */
public class MazeGenerator {

    static ArrayList<MazeCell> maze;
    public static ArrayList<MazeCell> getMaze(int sizeX, int sizeY) {
        maze = new ArrayList<MazeCell>();
        
        generateField(sizeX, sizeY);
        int randCell = (int)(Math.random()*sizeX*sizeY);
        MazeCell exit = maze.get(randCell);
        exit.setExit(true);
        generateMaze(exit, new ArrayList<MazeCell>());
        
        
        return maze;
    }
    
    private static void generateMaze(MazeCell curCell, List<MazeCell> visited)
    {
        ArrayList<MazeCell> neighbors = new ArrayList<MazeCell>();
        int neighborSize = neighbors.size();
        for(int i=neighborSize-1;i>=0;i--)
        {
            MazeCell curNeighbor = neighbors.get((int)(i*Math.random()));
            if (curNeighbor != null && !visited.contains(curNeighbor)) 
            {
                //detect where the neighbor is
                if ( curCell.getNorth().equals(curNeighbor))
                {
                    curCell.setWallNorth(false);
                    curNeighbor.setWallSouth(false);
                }
                else if ( curCell.getSouth().equals(curNeighbor))
                {
                    curCell.setWallSouth(false);
                    curNeighbor.setWallNorth(false);
                }
                else if ( curCell.getEast().equals(curNeighbor))
                {
                    curCell.setWallEast(false);
                    curNeighbor.setWallWest(false);
                }
                else if ( curCell.getWest().equals(curNeighbor))
                {
                    curCell.setWallWest(false);
                    curNeighbor.setWallEast(false);
                }
                visited.add(curNeighbor);
                generateMaze(curNeighbor,visited);
                
            }
        }
    }

    private static void generateField(int sizeX, int sizeY) {
        MazeCell oldYCell = null;
        MazeCell oldXCell = null;
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                // generate new cell with current position
                MazeCell newCell = new MazeCell();
                newCell.setPosX(i);
                newCell.setPosY(j);
                
                // if old cell is not null add old cell as south
                if (oldYCell != null) 
                {
                    newCell.setSouth(oldYCell);
                    // old y cell has a left neighbor and this neighbor has 
                    // a north node add this node as the left node of the 
                    // new cell
                    if (oldYCell.getWest() != null &&
                            oldYCell.getWest().getNorth() != null) {
                        newCell.setWest(oldYCell.getWest().getNorth());
                    }
                }
                // if the old cell is a null the new cell is the newest cell 
                // on x axxis range
                else
                {
                    // check for an oldXCell
                    if (oldXCell != null)
                    {
                        // if so add the oldXcell left of the newcell
                        newCell.setWest(oldXCell);
                    }
                    // set the oldXCell to the new xCell
                    oldXCell = newCell;
                }
                // add new cell to the maze
                maze.add(newCell);
                oldYCell= newCell;
            }
        }
    }
}
