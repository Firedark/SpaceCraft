package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Created by Firedark on 09/11/2015.
 */
public class Disparo extends Actor {
    SpaceCraft game;
    Texture textura;
    MoveByAction accion;
    public Rectangle rect;

    public Disparo(SpaceCraft game, int potencia, Nave nave){
        this.game = game;
        this.textura = game.images.manager.get("Images/disparo.png");
        this.setBounds(nave.getX()+58,nave.getY()+110,6*potencia,6*potencia);
        accion = new MoveByAction();
        accion.setDuration(20f);
        accion.setAmountY(2000);
        this.addAction(accion);
        rect = new Rectangle(getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void draw(Batch batch,float parentAlpha){
        rect = new Rectangle(getX(),getY(),getWidth(),getHeight());
        batch.draw(textura, getX(), getY(), getWidth(), getHeight());
    }



}
