package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by fired on 08/12/2015.
 */
public class GenDisparoEnemigo extends Actor {

    public Rectangle rect;

    public boolean ChoqueDisparoVsNave(Nave nave, GenDisparoEnemigo disparo){
        boolean choque = false;
        if(nave.rect.overlaps(disparo.rect)){
            System.out.println("Choque de disparo enemigo vs nave");
            nave.logicalGame.vidas--;
            this.deleteDisparo();
            choque = true;
        }
        return choque;
    }


    public void deleteDisparo(){

    }


    @Override
    public void draw(Batch batch, float parentAlpha){
        try {
            rect.set(getX(), getY(), getWidth(), getHeight());
            if(this.getActions().size == 0) {
                deleteDisparo();
            }
        }catch (NullPointerException e){

        }
    }

}
