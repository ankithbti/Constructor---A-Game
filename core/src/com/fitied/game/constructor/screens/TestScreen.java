package com.fitied.game.constructor.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.fitied.game.constructor.ConstructorMain;
import com.fitied.game.constructor.common.Constants;
import com.fitied.game.constructor.entities.GameWorld;

public class TestScreen implements Screen {

	private Body _ball;
	private Body _platform;
	private Body _box;
	private ConstructorMain _app;

	public TestScreen(final ConstructorMain app) {
		_app = app;
	}

	@Override
	public void show() {
		// Let's create a Platform and a ball in Box2d
		_ball = GameWorld.getInstance().createCircularBody(150f, 300f, 0.4f, BodyType.DynamicBody, 1.0f, 0.3f, 0.25f, true, false,
				Constants.NO_FILTER_BIT, Constants.NO_FILTER_BIT, Constants.NO_FILTER_BIT, null, null);
		_box = GameWorld.getInstance().createRectBody(200f, 300f, 32f, 32f, new Vector2(0,0), 0.0f, 
				BodyType.DynamicBody, 1.0f, 0.1f, 1.0f, true, false, 
				Constants.NO_FILTER_BIT, Constants.NO_FILTER_BIT, Constants.NO_FILTER_BIT, null, null);
		_platform = GameWorld.getInstance().createRectBody(150f, 100f, 200f, 20f, new Vector2(0,0), 0.0f, 
				BodyType.StaticBody, 1.0f, 0.1f, 0.5f, true, false, 
				Constants.NO_FILTER_BIT, Constants.NO_FILTER_BIT, Constants.NO_FILTER_BIT, null, null);
	}

	@Override
	public void render(float delta) {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
