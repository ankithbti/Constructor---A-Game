package com.fitied.game.constructor.desktop;

import java.util.List;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fitied.game.constructor.ConstructorMain;
import com.fitied.game.constructor.common.Constants;
import com.fitied.game.constructor.handlers.GameEventListener;
import com.fitied.game.constructor.score.HighScoreManager.HighScore;

public class DesktopLauncher implements GameEventListener{
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useGL30 = false;
		config.title = "Constructor";
		config.width = Constants.SCREEN_WIDTH;
		config.height = Constants.SCREEN_HEIGHT;
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;
		config.resizable = true;
		
		new LwjglApplication(new ConstructorMain(null, "English"), config);
	}

	@Override
	public void showAds(boolean show) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getHighScoreName() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showScores(List<HighScore> highScores) {
		// TODO Auto-generated method stub
		
	}
}
