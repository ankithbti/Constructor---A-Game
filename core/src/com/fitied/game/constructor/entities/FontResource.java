package com.fitied.game.constructor.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontResource {

	private BitmapFont _font;
	private static FontResource _instance;
	FreeTypeFontGenerator.FreeTypeFontParameter _params;
	FreeTypeFontGenerator _fontGenerator;

	private FontResource() {
		
	}
	
	public void setFont(String fontFile){
		_fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(fontFile));
		_params = new FreeTypeFontGenerator.FreeTypeFontParameter();
	}

	public static FontResource getInstance() {
		if (_instance == null) {
			_instance = new FontResource();
		}
		return _instance;
	}

	public BitmapFont getFont(int size, Color color) {
		if(_fontGenerator == null || _params == null){
			return null;
		}
		_params.size = size;
		_params.color = color;
		_font = _fontGenerator.generateFont(_params);
		return _font;
	}
}
