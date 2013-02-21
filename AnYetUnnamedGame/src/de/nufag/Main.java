package de.nufag;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Line;
import com.jme3.scene.shape.Quad;

import de.nufag.world.MazeCell;
import de.nufag.world.MazeGenerator;

import java.util.ArrayList;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
       
       Main app = new Main();
       app.setShowSettings(false);
       app.start();
    }

    @Override
    public void simpleInitApp() {
        final ArrayList<MazeCell> maze = MazeGenerator.getMaze(20, 20);
//        Line q = new Line(new Vector3f(0, 0, 0), new Vector3f(0,1,0));
//        Geometry geom = new Geometry("Quad", q);
//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", ColorRGBA.White);
//        geom.setMaterial(mat);
//        geom.move(2, 4, 0);
//        rootNode.attachChild(geom);
        for (MazeCell mc: maze )
        {
        	//TODO fill this with live   
        	createBox(mc, ColorRGBA.Red);
        	System.out.println("x: " + mc.getPosX() + " y: " + mc.getPosY());
        	System.out.println("north: " + mc.isWallNorth());
        	System.out.println("east: " + mc.isWallEast());
        	System.out.println("south: " + mc.isWallSouth());
        	System.out.println("west: " + mc.isWallWest());
        }
       
    }  

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private void createBox(MazeCell mc, ColorRGBA color) {
    	
    	ArrayList<Line> linesToAdd = new ArrayList<Line>();
    	Quad q = new Quad(1, 1);
        Geometry geom = new Geometry("Quad", q);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);
        geom.setMaterial(mat);
        geom.move(mc.getPosX(), mc.getPosY(), 0);
        rootNode.attachChild(geom);
        if(mc.isWallEast()) {
        	Line l = new Line(new Vector3f(mc.getPosX()+1, mc.getPosY(), 0), new Vector3f(mc.getPosX()+1,mc.getPosY()+1,0));
        	linesToAdd.add(l);
        }
        
        if(mc.isWallNorth()) {
        	Line l = new Line(new Vector3f(mc.getPosX(),mc.getPosY(),0), new Vector3f(mc.getPosX()+1,mc.getPosY(),0));
        	linesToAdd.add(l);
        }
        
        if(mc.isWallSouth()) {
        	Line l = new Line(new Vector3f(mc.getPosX(),mc.getPosY()+1,0), new Vector3f(mc.getPosX()+1,mc.getPosY()+1,0));
        	linesToAdd.add(l);
        }
        
        if(mc.isWallWest()) {
        	Line l = new Line(new Vector3f(mc.getPosX(), mc.getPosY(), 0), new Vector3f(mc.getPosX(),mc.getPosY()+1,0));
        	System.out.println(l.getStart().toString());
        	System.out.println(l.getEnd().toString());
        	linesToAdd.add(l);
        }
        
        for(Line l : linesToAdd) {
        	Geometry g = new Geometry("Line", l);
        	Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        	m.setColor("Color", ColorRGBA.White);
            g.setMaterial(m);
            rootNode.attachChild(g);
        }
    }


}
