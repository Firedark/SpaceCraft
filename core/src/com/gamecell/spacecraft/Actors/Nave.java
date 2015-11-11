package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Clase Nave, actor principal que controla el usuario.
 * @author Sergio Jimenez Cortes *
 */

public class Nave extends Actor {

    private Texture imagenNave;
    private SpaceCraft game;
    private int velocidad = 10;
    public Rectangle rect;


    /**
     * Constructor de la clase.
     * @param game de la clase principal
     */



    public Nave(SpaceCraft game){
        this.game = game;
        //imagenNave = game.images.manager.get("Images/nave.png");
        imagenNave = game.images.manager.get("Images/nave.png",Texture.class);
        this.setBounds(236, 60, 128, 128);


    }

    /**
     * Mueve a la izquierda el actor.
     */

    public void moverIzquierda(){
        if(getX() > 0 ) {
            this.setX(this.getX() - velocidad);
        }
    }

    /**
     * Mueve a la derecha el actor.
     */

    public void moverDerecha(){
        if(getX() < 472) {
            this.setX(this.getX() + velocidad);
        }
    }

    /**
     * Metodo de dibujo del actor.
     */

    @Override
    public void draw(Batch batch, float parentAlpha){
        rect = new Rectangle(getX(),getY(),getWidth(),getHeight());
        batch.draw(imagenNave, getX(), getY());

    }

}
