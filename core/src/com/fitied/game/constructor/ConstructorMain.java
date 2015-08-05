package com.fitied.game.constructor;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fitied.game.constructor.common.Constants;
import com.fitied.game.constructor.entities.FontResource;
import com.fitied.game.constructor.entities.GameWorld;
import com.fitied.game.constructor.handlers.GameEventListener;
import com.fitied.game.constructor.screens.HelperScreen;
import com.fitied.game.constructor.screens.MenuScreen;
import com.fitied.game.constructor.screens.TestScreen;

public class ConstructorMain extends Game {

	private float _deltaTime = 0.0f;
	
	private TestScreen _testScreen;
	public MenuScreen _menuScreen;
	public HelperScreen _helperScreen;
	
	public OrthographicCamera _camera;
	public SpriteBatch _sb;

	public ConstructorMain(GameEventListener gameEventListener, String locale) {
		
	}

	@Override
	public void create() {
		_camera = new OrthographicCamera();
		_camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		_sb  = new SpriteBatch();
		_testScreen = new TestScreen(this);
		_menuScreen = new MenuScreen(this);
		_helperScreen = new HelperScreen(this);
		
		this.setScreen(_menuScreen);
		GameWorld.getInstance();
		FontResource.getInstance().setFont("fonts/AmaticSC-Regular.ttf");
	}
	

	private void update(float delta) {

		// Check for the WORLD Step time
		// Update only if it is equal or greater than WORLD_STEP time
		if (delta >= Constants.WORLD_STEP) {
			_deltaTime -= Constants.WORLD_STEP;
			GameWorld.getInstance().update();
		}
	}

	@Override
	public void render() {

		// clears the screen with white
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		_deltaTime += Gdx.graphics.getDeltaTime();
		update(_deltaTime);

		_camera.update();
		_sb.setProjectionMatrix(_camera.combined);
		
		// Render After this point
		
		this.getScreen().render(_deltaTime);
		
		GameWorld.getInstance().render();
	}

	@Override
	public void dispose() {
		_testScreen.dispose();
		_menuScreen.dispose();
		_helperScreen.dispose();
		_sb.dispose();
	}
}
