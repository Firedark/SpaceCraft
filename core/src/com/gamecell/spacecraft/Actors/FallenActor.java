package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Clase FallenActor, actor que va de arriba hacia abajo de la pantalla.
 * @author Sergio Jimenez Cortes *
 */
public class FallenActor extends Actor {
    SpaceCraft game;
    Texture textura;
    MoveByAction accion;

    /**
     * Constructor de la clase.
     * @param game de la clase principal
     * @param textura textura para dibujar el actor.
     */

    public FallenActor(SpaceCraft game, Texture textura){
        this.game = game;
        this.textura = textura;
        int width = MathUtils.random((textura.getWidth() - textura.getWidth()/2),textura.getWidth());
        this.setBounds( MathUtils.random(-50, game.w + 50),MathUtils.random(game.h + 100,game.h + 500),width,width);
        accion = new MoveByAction();
        accion.setDuration(20f);
        accion.setAmountY(-2000);
        this.addAction(accion);

    }

    /**
     * Metodo de dibujo del actor.
     */

    @Override
    public void draw(Batch batch,float parentAlpha){
        batch.draw(textura,getX(),getY(),getWidth(),getHeight());
    }

}
