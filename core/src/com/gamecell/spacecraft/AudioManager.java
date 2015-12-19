package com.gamecell.spacecraft;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;


/**
 * Clase AudioManager, control de datos de audios.
 * @author Sergio Jimenez Cortes / Jaume Gimeno Serrano
 */
public class AudioManager extends AssetManager {
    public AssetManager soundmanager;
    public float volumenSonidos;
    public float volumenMusica;
    private Music currentMusic;
    public AudioManager(){
        soundmanager = new AssetManager();
        volumenSonidos = 1f;
        volumenMusica = 1f;

    }

    /**
     * Metodo para cargar la música en Memoria.
     */
    public void loadAssets() {
        //Cargo un sonido
        soundmanager.load("Sounds/boom.mp3",Sound.class);
        soundmanager.load("Sounds/disparo.mp3",Sound.class);
        soundmanager.load("Sounds/start.mp3",Sound.class);
        soundmanager.load("Sounds/sfx_lose.ogg",Sound.class);
        soundmanager.load("Sounds/sfx_laser2.ogg",Sound.class);
        soundmanager.load("Sounds/sfx_laser1.ogg",Sound.class);
        soundmanager.load("Sounds/sfx_shieldDown.ogg",Sound.class);
        soundmanager.load("Sounds/sfx_shieldUp.ogg",Sound.class);
        soundmanager.load("Music/GameMusic.ogg", Music.class);
        soundmanager.load("Music/MenuMusic.mp3", Music.class);
        soundmanager.finishLoading();
        //Cargo una musica
        //manager.load("data/sounds/musicainicial.mp3",Music.class);
    }


    public void playSound(Sound sonido){
        sonido.play(volumenSonidos);
    }

    private void playMusic (Music music) {
        music.setVolume(volumenMusica);
        music.setLooping(true);
        music.play();
    }

    public void stopMusic () {
        currentMusic.stop();
        currentMusic.dispose();
    }

    public void muteMusic () {
        volumenMusica = 0;
        currentMusic.setVolume(volumenMusica);
    }

    public void enableMusic(){
        volumenMusica = 1.0f;
        currentMusic.setVolume(volumenMusica);
    }

    public void enableFX (){
        volumenSonidos = 1.0f;
    }
    public void muteFX (){
        volumenSonidos = 0;
    }

    public void playStartMusic(){
        currentMusic = soundmanager.get("Music/MenuMusic.mp3");
        playMusic(currentMusic);
    }

    public void playGameMusic(){
        currentMusic = soundmanager.get("Music/GameMusic.ogg");
        playMusic(currentMusic);
    }


}