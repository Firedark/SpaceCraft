package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Created by Firedark on 03/12/2015.
 */
public class GenEnemigo extends Actor {
    public int estado;
    public Rectangle rect;
    public boolean targeted;
    public int salud;
    public SpaceCraft game;
    public int reward;
    public long TimeDisparo = TimeUtils.millis();



    public boolean choqueVsNave(Nave nave){
        boolean choque = false;
        if(estado > 0) {
            if (this.rect.overlaps(nave.rect)) {
                this.salud = 0;
                nave.logicalGame.vidas--;
                choque = true;
                game.audios.playSound((Sound) game.audios.soundmanager.get("Sounds/sfx_lose.ogg"));
            }
        }
        return choque;
    }


    public void choqueVsDisparo(GenDisparo disparo){
        if(estado > 0) {
            if (disparo.rect.overlaps(rect)) {
                disparo.potencia--;
                this.salud--;
                game.audios.playSound((Sound) game.audios.soundmanager.get("Sounds/boom.mp3"));
            }
        }
    }


    public void DeadEnemy() {
        rect = null;
        game.preferences.score += this.reward;
    };

    @Override
    public void draw(Batch batch, float parentAlpha){
        if(salud == 0) {
            estado = 0;
        }

        if(estado == 0) {
            DeadEnemy();
        }

        try{
            rect.set(this.getX(),this.getY(),this.getWidth(),this.getHeight());
        }catch (NullPointerException e){

        }
    }

    public void updateDisparo(int freq){
        if(TimeUtils.millis() - TimeDisparo > freq){
            Disparar();
            TimeDisparo = TimeUtils.millis();
        }
    }

    public void Disparar(){

    }
}
