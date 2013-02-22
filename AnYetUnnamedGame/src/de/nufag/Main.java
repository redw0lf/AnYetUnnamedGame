package de.nufag;

import java.util.ArrayList;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Quad;

import de.nufag.world.MazeCell;
import de.nufag.world.MazeGenerator;

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
    	cam.setLocation(new Vector3f(10f, 10f, 26f));
        final ArrayList<MazeCell> maze = MazeGenerator.getMaze(20, 20);

        for (MazeCell mc: maze )
        {
        	//TODO fill this with live
        	createBox(mc, ColorRGBA.White);
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
    	
    	ArrayList<Geometry> linesToAdd = new ArrayList<Geometry>();
    	Quad q = new Quad(1, 1);
    	
    	//System.out.println(q.getHeight());
        Geometry geom = new Geometry("Quad", q);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);
        geom.setMaterial(mat);
        geom.move(mc.getPosX() + 0.33f, mc.getPosY() + 0.33f, 0);
        rootNode.attachChild(geom);
        if(mc.isWallEast()) {
        	Quad tmp = new Quad(0.33f,1);
        	Geometry g = new Geometry("Quad", tmp);
            Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            m.setColor("Color", ColorRGBA.DarkGray);
            g.setMaterial(m);
            g.move(mc.getPosX()+1, mc.getPosY(), 0);
        	linesToAdd.add(g);
        }
        
        if(mc.isWallNorth()) {
        	Quad tmp = new Quad(1,0.33f);
        	Geometry g = new Geometry("Quad", tmp);
            Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            m.setColor("Color", ColorRGBA.DarkGray);
            g.setMaterial(m);
            g.move(mc.getPosX(), mc.getPosY(), 0);
        	linesToAdd.add(g);
        }
        
        if(mc.isWallSouth()) {
        	Quad tmp = new Quad(1,0.33f);
        	Geometry g = new Geometry("Quad", tmp);
            Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            m.setColor("Color", ColorRGBA.DarkGray);
            g.setMaterial(m);
            g.move(mc.getPosX(), mc.getPosY()+1, 0);
        	linesToAdd.add(g);
        }
        
        if(mc.isWallWest()) {
        	Quad tmp = new Quad(0.33f,1);
        	Geometry g = new Geometry("Quad", tmp);
            Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            m.setColor("Color", ColorRGBA.DarkGray);
            g.setMaterial(m);
            g.move(mc.getPosX(), mc.getPosY(), 0);
        	linesToAdd.add(g);
        }
        
        for(Geometry l : linesToAdd) {
            rootNode.attachChild(l);
        }
    }


}
