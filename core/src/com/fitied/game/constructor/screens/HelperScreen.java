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
import com.fitied.game.constructor.entities.TextResources;

public class HelperScreen implements Screen, InputProcessor {
	private ConstructorMain _app;

	private Texture _backButtonImage;

	private Button[] _buttons;

	private TextResources _textRes;
	private int startLine;
	private int lastLineIndex;
	private int lineHeight = 30;
	private int touchStartY;

	public HelperScreen(final ConstructorMain app) {
		super();
		_app = app;
		_textRes = new TextResources("en");
	}

	@Override
	public void show() {
		_backButtonImage = new Texture(Gdx.files.internal("images/backButton.png"));

		_buttons = new Button[1];
		_buttons[0] = new Button(new TextureRegion(_backButtonImage));
		_buttons[0].setPos(Constants.SCREEN_WIDTH - _backButtonImage.getWidth(), _backButtonImage.getHeight());

		startLine = 0;
		lineHeight = 30;
		lastLineIndex = _textRes.getHelpLines().length - 1;

		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void render(float delta) {

		// clears the screen with white
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		_app._sb.begin();
		// _app._sb.draw(_backgroundImage, 0, 0);

		for (int i = 0; i < _buttons.length; i++) {
			_buttons[i].draw(_app._sb);
		}

		int lineY = Constants.SCREEN_HEIGHT - lineHeight - 45;
		for (int l = startLine; l <= lastLineIndex; l++) {
			String line = _textRes.getHelpLines()[l];
			FontResource.getInstance().getFont(30, Color.BLACK).draw(_app._sb, line, 150, lineY);
			lineY -= lineHeight;
			if (lineY < 0) {
				break;
			}
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
		if (_backButtonImage != null) {
			_backButtonImage.dispose();
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
		touchStartY = screenY;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 touchPos = new Vector3();
		touchPos.set(screenX, screenY, 0);
		_app._camera.unproject(touchPos);

		for (int i = 0; i < _buttons.length; i++) {
			if (_buttons[i].isPressed(touchPos)) {
				if (i == 0) {
					_app.setScreen(_app._menuScreen);
				}
				break;
			}
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		int diffY = touchStartY - screenY;
		int linesDiff = diffY / lineHeight;
		if (linesDiff != 0) {
			touchStartY = screenY;
			scrolled(linesDiff);
		}
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		startLine += amount;
		if (startLine < 0) {
			startLine = 0;
		}
		if (startLine > lastLineIndex) {
			startLine = lastLineIndex;
		}
		return true;
	}
}
