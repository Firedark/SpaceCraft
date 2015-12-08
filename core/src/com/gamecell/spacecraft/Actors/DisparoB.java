package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.gamecell.spacecraft.Logics.LogicalGame;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Clase Disparo, crea un actor con una acción de movimiento ascendente.
 * @author Sergio Jimenez Cortes *
 */
public class DisparoB extends GenDisparo {
    private SpaceCraft game;
    private Texture textura;
    private MoveByAction accion;
    private LogicalGame logical;
    private Nave nave;
    private int potencia;
    /**
     * Constructor de la clase.
     * @param game de la clase principal
     * @param potencia valor de potencia del disparo, cambia el ancho alto de este.
     * @param nave le pasamos la nave para ubicar el disparo.
     */
    public DisparoB(SpaceCraft game, int potencia, Nave nave, LogicalGame logical){
        this.game = game;
        this.logical = logical;
        this.nave = nave;
        super.potencia = potencia +1;
        this.textura = game.images.manager.get("Images/disparoB.png");
        this.setBounds(nave.getX()+58 - potencia ,nave.getY()+60,textura.getWidth(),textura.getHeight());
        accion = new MoveByAction();
        accion.setDuration(8f);
        accion.setAmountY(700);
        this.addAction(accion);
        super.rect = new Rectangle(getX(),getY(),getWidth(),getHeight());
    }

    /**
     * Metodo de dibujo del actor.
     */
    @Override
    public void draw(Batch batch,float parentAlpha){

        try {

            if(super.potencia == 0 || this.getActions().size == 0){
                logical.removeActor(this);
                logical.colDisparos.remove(this);
            }



        }catch (Exception e){

        }


        super.rect.set(getX(),getY(),getWidth(),getHeight());
        batch.draw(textura, getX(), getY(), getWidth()* (1 + super.potencia/2), getHeight()*(1 + super.potencia/2));
    }



}
