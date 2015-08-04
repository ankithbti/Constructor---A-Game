package com.fitied.game.constructor.common;

import com.badlogic.gdx.math.Vector2;

public class Constants {

	
	public static final Vector2 GRAVITY = new Vector2(0, -9.81f);
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 400;
	
	public static final float WORLD_STEP = 1.0f/60.0f;
	public static final float M2P = 30.0f;
	public static final float P2M = 1/M2P;
	public static final int VELOCITY_ITERATION = 8;
	public static final int POSITION_ITERATION = 5;
	
	public static final boolean WORLD_DO_SLEEP = true;
	public static final boolean WORLD_DEBUG = true;
	
	public static final short NO_FILTER_BIT = 0;
	
	
	
}
