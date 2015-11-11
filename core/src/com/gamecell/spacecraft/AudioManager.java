package com.gamecell.spacecraft;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;


/**
 * Clase AudioManager, control de datos de audios.
 * @author Sergio, Jaume*
 */

public class AudioManager extends AssetManager {
    public AssetManager soundmanager;
    public float volumenSonidos;
    public AudioManager(){
        soundmanager = new AssetManager();
        volumenSonidos = 1f;

    }

    //Método para cargar las imagenes en Memoria.
    public void loadAssets() {
        //Cargo un sonido
        soundmanager.load("Sounds/boom.mp3",Sound.class);
        soundmanager.load("Sounds/disparo.mp3",Sound.class);
        soundmanager.load("Sounds/start.mp3",Sound.class);
        //Cargo una musica
        //manager.load("data/sounds/musicainicial.mp3",Music.class);
    }


    public void playSound(Sound sonido){
        sonido.play(volumenSonidos);
    }
}