package com.gamecell.spacecraft;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Firedark on 22/10/2015.
 */
public class AudioManager extends AssetManager {
    public AssetManager soundmanager;

    public AudioManager(){
        soundmanager = new AssetManager();
    }

    //Método para cargar las imagenes en Memoria.
    public void loadAssets() {
        //Cargo un sonido
        //manager.load("data/sounds/boom.mp3",Sound.class);
        //Cargo una musica
        //manager.load("data/sounds/musicainicial.mp3",Music.class);
    }
}