package com.dcorona.mario;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.dcorona.mario.Screens.FirstLevel;

public class Mario extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100;
	public static final short GROUND_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short ENEMY_HEAD_BIT = 128;
	public static final short ITEM_BIT = 256;

	private SpriteBatch batch;
	private AssetManager manager;

	public Mario() {}
	
	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.setScreen(new FirstLevel(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () { //se ejecuta al final
		this.batch.dispose();
		super.dispose(); //cierra al padre
	}

	public void beginDraw(){
		this.batch.begin();
	}

	public void endDraw(){
		this.batch.end();
	}

	public SpriteBatch getBatch(){
		return batch;
	}

	public AssetManager getManager(){
		return manager;
	}

	public void setProjectionMatrix(Matrix4 camera){
		this.batch.setProjectionMatrix(camera);
	}
}
