package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
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
    private ParallelAction paralel;
    private RotateByAction rotate;
    private TextureRegion textureR;
    private int type;
    /**
     * Constructor de la clase.
     * @param game de la clase principal
     * @param textura textura para dibujar el actor.
     */
    public PowerUps(SpaceCraft game, Texture textura,Nave nave,LogicalGame logical,int type){
        this.game = game;
        this.textureR = new TextureRegion(textura);
        this.nave = nave;
        this.logical = logical;
        this.type = type;
        this.setBounds( MathUtils.random(10, game.w + -10),MathUtils.random(game.h + 100,game.h + 500), textura.getWidth(),textura.getHeight());
        this.setOrigin(textura.getWidth()/2,textura.getHeight()/2);
        rect = new Rectangle();
        accion = new MoveByAction();
        accion.setDuration(20f);
        accion.setAmountY(-2000);
        rotate = new RotateByAction();
        rotate.setDuration(MathUtils.random(20f,30f));
        rotate.setAmount(MathUtils.random(100, 720));
        paralel = new ParallelAction();
        paralel.addAction(accion);
        paralel.addAction(rotate);

        this.addAction(paralel);

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
                    case 1:

                        switch(nave.type) {
                            case 0: if(logical.potenciaA <= nave.maxPotenciaA )  logical.potenciaA++; break;
                            case 1: if(logical.potenciaB <= nave.maxPotenciaB )  logical.potenciaB++; break;
                            case 2: if(logical.potenciaC <= nave.maxPotenciaC )  logical.potenciaC++; break;
                            }

                        deleteRectangle();
                        logical.colPowerUps.remove(this);
                        logical.removeActor(this);
                        break;
                    case 2:
                        nave.setUpShield();
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

        batch.draw(textureR,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());

    }

}
