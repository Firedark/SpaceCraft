package com.gamecell.spacecraft.Logics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gamecell.spacecraft.Actors.SplashBlack;
import com.gamecell.spacecraft.Screens.SplashScreen;
import com.gamecell.spacecraft.SpaceCraft;



/**
 * Created by Firedark on 08/11/2015.
 */
public class LogicalSplash extends Table {
    SpaceCraft game;
    //Creamos el atributo para nuestro actor anterior Background
    Texture Tbackground;
    TextureRegion background;
    SplashBlack sb;

    public LogicalSplash(SpaceCraft game, SplashScreen screen){

        //El metodo setbounds coloca un Rectangulo (X,Y,Width,Height)
        Tbackground = new Texture(Gdx.files.internal("Images/firegames.jpg"));
        background = new TextureRegion(Tbackground,0,0,800,480);
        setBounds(0, 0, 800,480);
        setClip(true);
        this.game = game;

        //Instanciar Actores


        sb = new SplashBlack(game);
        //Añadiendo Actores


        addActor(sb);

    }


    //Metodo act se ejecuta al igual que el render, es donde insertaremos la lógica.
    @Override
    public void act(float delta) {
        super.act(delta);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(Color.WHITE);
//Aqui dibujamos nuestro fondo.
        batch.draw(background,0,0);
        super.draw(batch, parentAlpha);
    }

}

