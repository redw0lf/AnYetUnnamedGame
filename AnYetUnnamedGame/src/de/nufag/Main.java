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
        	if(mc.isExit()) {
        		createBox(mc, ColorRGBA.Red);
        	} else {
        		createBox(mc, ColorRGBA.White);
        	}
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
    	Quad q = new Quad(0.8f, 0.8f);
    	
    	//System.out.println(q.getHeight());
        Geometry geom = new Geometry("Quad", q);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);
        geom.setMaterial(mat);
        geom.move(mc.getPosX()+0.1f, 19-mc.getPosY()+0.1f, 0);
        rootNode.attachChild(geom);
        
    	Quad tmp = new Quad(0.1f,1f);
    	Geometry g = new Geometry("Quad", tmp);
        Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        m.setColor("Color", mc.isWallWest() ? ColorRGBA.DarkGray : ColorRGBA.White);
        g.setMaterial(m);
        g.move(mc.getPosX(), 19-mc.getPosY(), 0);
    	linesToAdd.add(g);
        
        
       	Quad tmp2 = new Quad(1f,0.1f);
        Geometry g2 = new Geometry("Quad", tmp2);
        Material m2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        m2.setColor("Color", mc.isWallNorth() ? ColorRGBA.DarkGray : ColorRGBA.White);
        g2.setMaterial(m2);
        g2.move(mc.getPosX(), 20-mc.getPosY()-0.1f, 0);
        linesToAdd.add(g2);
       
    	Quad tmp3 = new Quad(1f,0.1f);
    	Geometry g3 = new Geometry("Quad", tmp3);
        Material m3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        m3.setColor("Color", mc.isWallSouth() ? ColorRGBA.DarkGray : ColorRGBA.White);
        g3.setMaterial(m3);
        g3.move(mc.getPosX(), 20-mc.getPosY()-1f, 0);
    	linesToAdd.add(g3);
    
    	Quad tmp4 = new Quad(0.1f,1f);
    	Geometry g4 = new Geometry("Quad", tmp4);
        Material m4 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        m4.setColor("Color", mc.isWallEast() ? ColorRGBA.DarkGray : ColorRGBA.White);
        g4.setMaterial(m4);
        g4.move(mc.getPosX()+0.9f, 19-mc.getPosY(), 0);
    	linesToAdd.add(g4);
        
        
        for(Geometry l : linesToAdd) {
            rootNode.attachChild(l);
        }
    }


}
