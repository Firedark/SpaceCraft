package com.gamecell.spacecraft;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Clase Image, control de datos de imagenes.
 * @author Sergio Jimenez Cortes*
 */
public class ImageManager  {
    public AssetManager manager;

    public ImageManager(){
    manager = new AssetManager();
    }

    /**
     * Metodo para cargar las imagenes en Memoria.
     */
    public void loadAssets() {
        //Cargo una textura.
        manager.load("Images/nave.png",Texture.class);
        manager.load("Images/nave2.png",Texture.class);
        manager.load("Images/nave3.png",Texture.class);
        manager.load("Images/life1.png",Texture.class);
        manager.load("Images/life2.png",Texture.class);
        manager.load("Images/life3.png",Texture.class);
        manager.load("Images/poweruplife.png",Texture.class);
        manager.load("Images/luna.png",Texture.class);
        manager.load("Images/estrella.png",Texture.class);
        manager.load("Images/sol.png",Texture.class);
        manager.load("Images/satelite.png",Texture.class);
        manager.load("Images/asteroide.png",Texture.class);
        manager.load("Images/cohete.png",Texture.class);
        manager.load("Images/disparo.png",Texture.class);
        manager.load("Images/disparoB.png",Texture.class);
        manager.load("Images/disparoC.png",Texture.class);
        manager.load("Images/disparoEA.png",Texture.class);
        manager.load("Images/enemyship.png",Texture.class);
        manager.load("Images/disparoCE.png",Texture.class);
        manager.load("Images/enemyB.png",Texture.class);
        manager.load("Images/poweruppower.png",Texture.class);
        manager.load("Images/powerupshield.png",Texture.class);
        manager.load("Images/shield.png",Texture.class);
        manager.finishLoading();
        //Cargo un sonido
        //manager.load("data/sounds/boom.mp3",Sound.class);
        //Cargo una musica
        //manager.load("data/sounds/musicainicial.mp3",Music.class);
    }

}
