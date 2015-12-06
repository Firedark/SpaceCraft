package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.gamecell.spacecraft.Logics.LogicalGame;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Clase Disparo, crea un actor con una acción de movimiento ascendente.
 * @author Sergio Jimenez Cortes *
 */
public class DisparoC extends GenDisparo {
    private SpaceCraft game;
    private Texture textura;
    private MoveByAction accion;
    private LogicalGame logical;
    private Nave nave;
    private int potencia;
    private float offsetX;
    /**
     * Constructor de la clase.
     * @param game de la clase principal
     * @param potencia valor de potencia del disparo, cambia el ancho alto de este.
     * @param nave le pasamos la nave para ubicar el disparo.
     */
    public DisparoC(SpaceCraft game, int potencia, Nave nave, LogicalGame logical,float offsetX){
        this.game = game;
        this.logical = logical;
        this.nave = nave;
        if(offsetX != 0) {
            this.offsetX = offsetX;
        }
        super.potencia = 1;
        this.textura = game.images.manager.get("Images/disparoC.png");
        accion = new MoveByAction();
        accion.setDuration(8f);
        accion.setAmountY(700);
        this.addAction(accion);
        switch (potencia) {
            case 1:
                break;
            case 2:
                DobleDisparo();
                break;
            case 3:
                TripleDisparo();
                break;
            case 4:
                CuadrupleDisparo();
                break;
            case 5:
                QuintupleDisparo();
                break;
        }
        this.setBounds(nave.getX() + 56 + this.offsetX, nave.getY() + 60, textura.getWidth(), textura.getHeight());
        super.rect = new Rectangle(getX(),getY(),getWidth(),getHeight());
    }


    public void DobleDisparo(){
        accion.setAmountX(-50);
        offsetX = -10;
        DisparoC disparoC = new DisparoC(game,1,nave,logical,10);
        disparoC.accion.setAmountX(50);
        logical.addActor(disparoC);
        logical.colDisparos.add(disparoC);

    }

    public void TripleDisparo(){
        DisparoC disparoC = new DisparoC(game,1,nave,logical,-10);
        disparoC.accion.setAmountX(-80);
        DisparoC disparoC2 = new DisparoC(game,1,nave,logical,10);
        disparoC2.accion.setAmountX(80);
        logical.addActor(disparoC);
        logical.colDisparos.add(disparoC);
        logical.addActor(disparoC2);
        logical.colDisparos.add(disparoC2);

    }

    public void CuadrupleDisparo(){
        accion.setAmountX(-40);
        offsetX = -10;
        DisparoC disparoC = new DisparoC(game,1,nave,logical,10);
        disparoC.accion.setAmountX(40);
        logical.addActor(disparoC);
        logical.colDisparos.add(disparoC);


        DisparoC disparoC2 = new DisparoC(game,1,nave,logical,-20);
        disparoC2.accion.setAmountX(-90);
        DisparoC disparoC3 = new DisparoC(game,1,nave,logical,20);
        disparoC3.accion.setAmountX(90);
        logical.addActor(disparoC2);
        logical.colDisparos.add(disparoC2);
        logical.addActor(disparoC3);
        logical.colDisparos.add(disparoC3);

    }

    public void QuintupleDisparo(){
        DisparoC disparoC = new DisparoC(game,1,nave,logical,-30);
        disparoC.accion.setAmountX(-60);
        DisparoC disparoC2 = new DisparoC(game,1,nave,logical,30);
        disparoC2.accion.setAmountX(60);
        logical.addActor(disparoC);
        logical.colDisparos.add(disparoC);
        logical.addActor(disparoC2);
        logical.colDisparos.add(disparoC2);

        DisparoC disparoC3 = new DisparoC(game,1,nave,logical,-50);
        disparoC3.accion.setAmountX(-130);
        DisparoC disparoC4 = new DisparoC(game,1,nave,logical,50);
        disparoC4.accion.setAmountX(130);
        logical.addActor(disparoC3);
        logical.colDisparos.add(disparoC3);
        logical.addActor(disparoC4);
        logical.colDisparos.add(disparoC4);

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
        batch.draw(textura, getX(), getY(), getWidth(), getHeight());
    }



}
