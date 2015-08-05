package com.fitied.game.constructor.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.fitied.game.constructor.ConstructorMain;
import com.fitied.game.constructor.common.Constants;
import com.fitied.game.constructor.entities.Button;
import com.fitied.game.constructor.entities.FontResource;

public class MenuScreen implements Screen, InputProcessor {

	private ConstructorMain _app;

	private Texture _backgroundImage;
	private Texture _logoImage;

	private Texture _exitButtonImage;
	private Texture _startButtonImage;

	private Button[] _buttons;

	public MenuScreen(final ConstructorMain app) {
		super();
		_app = app;
	}

	@Override
	public void show() {
		_backgroundImage = new Texture(Gdx.files.internal("images/bg1.jpg"));
		_logoImage = new Texture(Gdx.files.internal("images/logo.png"));
		
		// All buttons should be of same height
		_exitButtonImage = new Texture(Gdx.files.internal("images/exitButton.png"));
		_startButtonImage = new Texture(Gdx.files.internal("images/startButton.png"));

		float bottomMargin = 20.0f;
		_buttons = new Button[2];
		_buttons[0] = new Button(new TextureRegion(_startButtonImage));
		_buttons[0].setPos(100, _startButtonImage.getHeight() * 1 + bottomMargin);
		_buttons[1] = new Button(new TextureRegion(_exitButtonImage));
		_buttons[1].setPos(100, _exitButtonImage.getHeight() * 2 + bottomMargin);

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {

		// clears the screen with white
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		_app._sb.begin();
		//_app._sb.draw(_backgroundImage, 0, 0);
		_app._sb.draw(_logoImage, Constants.SCREEN_WIDTH - _logoImage.getWidth(),
				Constants.SCREEN_HEIGHT - _logoImage.getHeight());
		FontResource.getInstance().getFont(30, Color.RED).draw(_app._sb, "Constructor - A Game", 150,Constants.SCREEN_HEIGHT - 30);
		for (int i = 0; i < _buttons.length; i++) {
			_buttons[i].draw(_app._sb);
		}
		_app._sb.end();
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
		if (_backgroundImage != null)
			_backgroundImage.dispose();
		if (_logoImage != null)
			_logoImage.dispose();
		if(_exitButtonImage != null){
			_exitButtonImage.dispose();
		}
		if(_startButtonImage != null){
			_startButtonImage.dispose();
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 touchPos = new Vector3();
		touchPos.set(screenX, screenY, 0);
		_app._camera.unproject(touchPos);
		for (int i = 0; i < _buttons.length; i++) {
			if (_buttons[i].isPressed(touchPos)) {
				if (i == 0) {
					_app.setScreen(_app._helperScreen);
				}else if(i == 1){
					Gdx.app.exit();
				}
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
