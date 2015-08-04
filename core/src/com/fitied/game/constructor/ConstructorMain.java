package com.fitied.game.constructor;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.fitied.game.constructor.common.Constants;
import com.fitied.game.constructor.entities.GameWorld;
import com.fitied.game.constructor.handlers.GameEventListener;
import com.fitied.game.constructor.screens.TestScreen;

public class ConstructorMain extends Game {

	private float _deltaTime = 0.0f;
	
	private TestScreen _testScreen;

	public ConstructorMain(GameEventListener gameEventListener, String locale) {
		
	}

	@Override
	public void create() {
		_testScreen = new TestScreen(this);
		this.setScreen(_testScreen);
		GameWorld.getInstance();
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

		// Render After this point
		GameWorld.getInstance().render();
	}

	@Override
	public void dispose() {
		_testScreen.dispose();
	}
}
