package de.nufag;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

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
       app.start();
    }

    @Override
    public void simpleInitApp() {
        final ArrayList<MazeCell> maze = MazeGenerator.getMaze(20, 20);
        
        for (MazeCell mc: maze )
        {
        	//TODO fill this with live   
         createBox( ColorRGBA.Blue);
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

    private void createBox(ColorRGBA color) {
        Box b = new Box((float)0.5, (float)0.5,(float) 0.5);
        Geometry geom = new Geometry("Box", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);
        geom.setMaterial(mat);
        rootNode.attachChild(geom);
        
    }


}
