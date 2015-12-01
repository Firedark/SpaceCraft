package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.gamecell.spacecraft.Logics.LogicalGame;
import com.gamecell.spacecraft.SpaceCraft;
import com.badlogic.gdx.math.Rectangle;

/**
 * Clase FallenActor, actor que va de arriba hacia abajo de la pantalla.
 * @author Sergio Jimenez Cortes *
 */
public class PowerUps extends Actor {
    private SpaceCraft game;
    private Texture textura;
    private MoveByAction accion;
    public Rectangle rect;
    private LogicalGame logical;
    private Nave nave;
    private int type;
    /**
     * Constructor de la clase.
     * @param game de la clase principal
     * @param textura textura para dibujar el actor.
     */
    public PowerUps(SpaceCraft game, Texture textura,Nave nave,LogicalGame logical,int type){
        this.game = game;
        this.textura = textura;
        this.nave = nave;
        this.logical = logical;
        this.type = type;
        this.setBounds( MathUtils.random(-50, game.w + 50),MathUtils.random(game.h + 100,game.h + 500), textura.getWidth(),textura.getHeight());
        rect = new Rectangle();
        accion = new MoveByAction();
        accion.setDuration(20f);
        accion.setAmountY(-2000);
        this.addAction(accion);

    }


    public void checkColision(){

        try {
            if (rect.overlaps(nave.rect)) {
                switch (type) {
                    case 0:
                        if (logical.vidas < logical.lifes.maxLifes) logical.vidas++;
                        deleteRectangle();
                        logical.colPowerUps.remove(this);
                        logical.removeActor(this);
                        break;
                }
            }

            if(this.getActions().size == 0){
                deleteRectangle();
                logical.removeActor(this);
            }

        }catch (NullPointerException e){

        }
    }



    public void deleteRectangle(){
        rect = null;
    }

    /**
     * Metodo de dibujo del actor.
     */
    @Override
    public void draw(Batch batch,float parentAlpha){
        try {
            rect.set(getX(),getY(),getWidth(),getHeight());
        }catch (NullPointerException e){

        }
        batch.draw(textura,getX(),getY(),getWidth(),getHeight());
    }

}
