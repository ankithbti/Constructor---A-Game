package com.fitied.game.constructor.handlers;

import java.util.List;

import com.fitied.game.constructor.score.HighScoreManager.HighScore;

public interface GameEventListener {

	void showAds(boolean show);

	void getHighScoreName();

	void showScores(List<HighScore> highScores);
}
