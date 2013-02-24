package de.nufag.world;

public class Player {
	float posX;
	float posY;
	boolean moved;

	public Player(float posX, float posY) {
		this.posX=posX;
		this.posY=posY;
		moved=false;
	}

	public void moveX(float f) {
		posX+=f;
		moved=true;
	}

	public void moveY(float f) {
		posY+=f;
		moved=true;
	}

	public float getX() {
		// TODO Auto-generated method stub
		return posX;
	}

	public void setMoved(boolean moved) {
		this.moved =moved;
	}

	public float getY() {
		// TODO Auto-generated method stub
		return posY;
	}

	public boolean hasMoved() {
		return moved;		
	}


}
