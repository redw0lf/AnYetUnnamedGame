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
        List<MazeCell> neighbors = curCell.getNeighbors();
        int neighborSize = neighbors.size();

        for(int i=neighborSize-1;i>=0;i--)
        {
            MazeCell curNeighbor = neighbors.get((int)((i+1)*Math.random()));

            if (curNeighbor != null && !visited.contains(curNeighbor))
            {
                //detect where the neighbor is
                if ( curNeighbor.equals(curCell.getNorth()))
                {
                    curCell.setWallNorth(false);
                    curNeighbor.setWallSouth(false);
                }
                else if ( curNeighbor.equals(curCell.getSouth()))
                {
                    curCell.setWallSouth(false);
                    curNeighbor.setWallNorth(false);
                }
                else if ( curNeighbor.equals(curCell.getEast()))
                {
                    curCell.setWallEast(false);
                    curNeighbor.setWallWest(false);
                }
                else if ( curNeighbor.equals(curCell.getWest()))
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
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                // generate new cell with current position
                MazeCell newCell = new MazeCell();
                newCell.setPosX(i);
                newCell.setPosY(j);
                maze.add(newCell);
            }
        }
        
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
            	
            	MazeCell newCell = maze.get(i*sizeY + j);
            	
		        if (j != 0) {
		        	newCell.setNorth(maze.get(i*sizeY + (j-1)));
				}
				if (j != sizeY - 1) {
					newCell.setSouth(maze.get(i*sizeY + (j+1)));
				}
				if (i != 0) {
					newCell.setWest(maze.get((i-1)*sizeY +j));
				}
				if (i != sizeX - 1) {
					newCell.setEast(maze.get((i+1)*sizeY +j));
				}
            }
        }
    }
}
