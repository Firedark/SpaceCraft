package com.gamecell.spacecraft;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamecell.spacecraft.Screens.GameScreen;
import com.gamecell.spacecraft.Screens.OptionsScreen;
import com.gamecell.spacecraft.Screens.StartScreen;

// Modificación 22/10/1
public class SpaceCraft extends Game {
	StartScreen startScreen;
	GameScreen gameScreen;
	OptionsScreen optionsScreen;

	@Override
	public void create () {
	gameScreen = new GameScreen(this);



	this.setScreen(gameScreen);
	}

	@Override
	public void dispose() {

	}

}
