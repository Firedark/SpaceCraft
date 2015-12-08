package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by fired on 08/12/2015.
 */
public class GenDisparoEnemigo extends Actor {

    public Rectangle rect;

    public void ChoqueDisparoVsNave(Nave nave, GenDisparoEnemigo disparo){
        if(nave.rect.overlaps(disparo.rect)){
            System.out.println("Choque de disparo enemigo vs nave");
            nave.logicalGame.vidas--;
            this.deleteDisparo();
        }
    }


    public void deleteDisparo(){

    }


    @Override
    public void draw(Batch batch, float parentAlpha){
        try {
            rect.set(getX(), getY(), getWidth(), getHeight());
        }catch (NullPointerException e){

        }
    }

}
