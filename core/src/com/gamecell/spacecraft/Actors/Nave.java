package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Created by Firedark on 03/11/2015.
 */
public class Nave extends Actor {

    private Texture imagenNave;
    private SpaceCraft game;
    private int velocidad = 10;

    public Nave(SpaceCraft game){
        this.game = game;
        //imagenNave = game.images.manager.get("Images/nave.png");
        imagenNave = new Texture(Gdx.files.internal("Images/nave.png"));
        this.setBounds(236, 60, 128, 128);


    }

    public void moverIzquierda(){
        if(getX() > 0 ) {
            this.setX(this.getX() - velocidad);
        }
    }

    public void moverDerecha(){
        if(getX() < 472) {
            this.setX(this.getX() + velocidad);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(imagenNave, getX(), getY());

            }

}
