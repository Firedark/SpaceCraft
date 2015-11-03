package com.gamecell.spacecraft;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;


public class ImageManager  {
public AssetManager manager;

    public ImageManager(){
    manager = new AssetManager();
    }

//Método para cargar las imagenes en Memoria.
    public void loadAssets() {
        //Cargo una textura.
        manager.load("Images/nave.png",Texture.class);
        //Cargo un sonido
        //manager.load("data/sounds/boom.mp3",Sound.class);
        //Cargo una musica
        //manager.load("data/sounds/musicainicial.mp3",Music.class);
    }

}
