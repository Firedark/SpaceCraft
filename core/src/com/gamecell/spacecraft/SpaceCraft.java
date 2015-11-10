package com.gamecell.spacecraft;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamecell.spacecraft.Screens.GameScreen;
import com.gamecell.spacecraft.Screens.OptionsScreen;
import com.gamecell.spacecraft.Screens.SplashScreen;
import com.gamecell.spacecraft.Screens.StartScreen;

import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

// Modificación 22/10/1
public class SpaceCraft extends Game {

	//Atributos de Objetos Generales

	public AudioManager audios;
	public ImageManager images;
	public MyPreferences preferences;
	public StartScreen startScreen;
	public GameScreen gameScreen;
	public OptionsScreen optionsScreen;
	public SplashScreen splashScreen;

	public int w = 600;
	public int h = 800;

	@Override
	public void create () {
		//Instancia de objetos Generales.

		//Managers
		audios = new AudioManager();
		images = new ImageManager();
		preferences = new MyPreferences(this);

		images.loadAssets();
		audios.loadAssets();

		//Carga font manager
		FontManager.load();

		//Pantallas
		startScreen = new StartScreen(this);
		optionsScreen = new OptionsScreen(this);
		gameScreen = new GameScreen(this);
		splashScreen = new SplashScreen(this);

		//Sets
		//Carga las imagenes en memoria.


			this.setScreen(splashScreen);
			//Carga la pantalla GameScreen.
			//this.setScreen(splashScreen);


	}

	@Override
	public void dispose() {

	}

}
