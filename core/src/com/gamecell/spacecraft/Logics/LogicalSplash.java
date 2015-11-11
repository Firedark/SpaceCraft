package com.gamecell.spacecraft.Logics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gamecell.spacecraft.Actors.SplashBlack;
import com.gamecell.spacecraft.Screens.SplashScreen;
import com.gamecell.spacecraft.SpaceCraft;



/**
 * @author Sergio Jimenez Cortes
 */
public class LogicalSplash extends Table {
    SpaceCraft game;
    //Creamos el atributo para nuestro actor anterior Background
    Texture Tbackground;
    TextureRegion background;
    Sound start;
    SplashBlack sb;

    public LogicalSplash(SpaceCraft game, SplashScreen screen){

        //El metodo setbounds coloca un Rectangulo (X,Y,Width,Height)
        Tbackground = new Texture(Gdx.files.internal("Images/gamecel.jpg"));
        background = new TextureRegion(Tbackground,0,0,game.w,game.h);
        setBounds(0, 0, game.w,game.h);
        setClip(true);
        start = Gdx.audio.newSound((Gdx.files.internal("Sounds/start.mp3")));
        this.game = game;

        //Instanciar Actores


        sb = new SplashBlack(game);
        //Añadiendo Actores


        addActor(sb);
        start.play(game.audios.volumenSonidos);
    }

    /**
     * Metodo act se ejecuta al igual que el render, es donde insertaremos la logica.
     * @param delta
     */
    @Override
    public void act(float delta) {
        super.act(delta);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(Color.WHITE);
        //Aqui dibujamos nuestro fondo.
        batch.draw(background, 0, 0);
        super.draw(batch, parentAlpha);
    }

}

