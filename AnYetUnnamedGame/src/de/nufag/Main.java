package de.nufag;

import java.util.ArrayList;

import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Quad;

import de.nufag.world.MazeCell;
import de.nufag.world.MazeGenerator;
import de.nufag.world.Player;

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
	private AnalogListener analogListener = new AnalogListener() {
		
		@Override
		public void onAnalog(String name, float value, float tpf) {
			final Spatial child = rootNode.getChild("Player");
			final float moveTo = value*speed;
			if (name.equals("Up")) {         // test?
				player.moveY(moveTo);

				// if player isnt null move player
				if (child != null && player.hasMoved()){
					// unset moved
					
					child.move(0f, moveTo, 0f);
					System.out.println(player.getX() + " " + player.getY());
				}


			} 
			if (name.equals("Down")) {         // test?
				player.moveY(-moveTo);

				// if player isnt null move player
				if (child != null && player.hasMoved()){
					// unset moved
					
					child.move(0f, -moveTo, 0f);
					System.out.println(player.getX() + " " + player.getY());
				}
			} 
			if (name.equals("Right")) {         // test?
				player.moveX(moveTo);

				// if player isnt null move player
				if (child != null && player.hasMoved()){

					
					child.move(moveTo, 0f, 0f);
				
				}
			} 
			if (name.equals("Left")) {         // test?
				player.moveX(-moveTo);

				// if player isnt null move player
				if (child != null && player.hasMoved()){
					player.setMoved(false);
					child.move(-moveTo, 0f, 0f);
					
				}
			}
			System.out.println(player.getX() + " " + player.getY());

		}
	};
	private ArrayList<MazeCell> maze;
	private Player player;

	@Override
	public void simpleInitApp() {

		// fixed camera angle
		cam.setLocation(new Vector3f(10f, 10f, 26f));
		cam.setParallelProjection(false);
		// remove moveable camera
		stateManager.detach( stateManager.getState(FlyCamAppState.class) ); 
		// delete default mapping
		//		inputManager.deleteMapping( SimpleApplication.INPUT_MAPPING_MEMORY );

		// add keys
		inputManager.addMapping("Left",  new KeyTrigger(KeyInput.KEY_A), 
				new KeyTrigger(KeyInput.KEY_LEFT)); // A and left arrow
		inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D), 
				new KeyTrigger(KeyInput.KEY_RIGHT)); // D and right arrow
		inputManager.addMapping("Up",  new KeyTrigger(KeyInput.KEY_W), 
				new KeyTrigger(KeyInput.KEY_UP)); // A and left arrow
		inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_S), 
				new KeyTrigger(KeyInput.KEY_DOWN)); // D and right arrow

		inputManager.addListener(analogListener, new String[]{"Left", "Right", "Up", "Down"});


		maze = MazeGenerator.getMaze(20, 20);


		// create mace
		for (MazeCell mc: maze )
		{
			//TODO fill this with live
			if(mc.isExit()) {
				player = new Player(mc.getPosX(),mc.getPosY());
				createPlayer(mc.getPosX(),mc.getPosY());
				createBox(mc, ColorRGBA.Gray);  
			} else {
				createBox(mc, ColorRGBA.White);        		
			}
		}

	}  

	private void createPlayer(int posX, int posY) {
		Quad q = new Quad(0.8f, 0.8f);

		//System.out.println(q.getHeight());
		Geometry geom = new Geometry("Player", q);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Green);
		geom.setMaterial(mat);
		geom.move(posX, posY, 1);
		rootNode.attachChild(geom);
		//player.setMoved(true);

	}

	@Override
	public void simpleUpdate(float tpf) {

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
