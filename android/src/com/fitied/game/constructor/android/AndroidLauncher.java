package com.fitied.game.constructor.android;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.List;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.fitied.game.constructor.ConstructorMain;
import com.fitied.game.constructor.handlers.GameEventListener;
import com.fitied.game.constructor.score.HighScoreManager.HighScore;

public class AndroidLauncher extends AndroidApplication implements GameEventListener {
	
	private ConstructorMain _gameMain;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		RelativeLayout layout = new RelativeLayout(this);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGLSurfaceView20API18 = true;
		cfg.useAccelerometer = true;
		cfg.useCompass = false;

		// Our Game Instance
		_gameMain = new ConstructorMain(this, "English");
		
		View gameView = initializeForView(_gameMain, cfg);
		layout.addView(gameView);
		setContentView(layout);
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
