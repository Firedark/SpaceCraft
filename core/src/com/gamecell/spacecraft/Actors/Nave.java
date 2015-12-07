package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.gamecell.spacecraft.Logics.LogicalGame;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Clase Nave, actor principal que controla el usuario.
 * @author Sergio Jimenez Cortes *
 */

public class Nave extends Actor {


    private SpaceCraft game;
    private int velocidad = 10;
    public Texture text;
    public int type;
    public Rectangle rect;


    /**
     * Constructor de la clase.
     * @param game de la clase principal
     */
    public Nave(SpaceCraft game, final LogicalGame table){
        this.game = game;
        type = 0;
        this.setBounds(236, 60, 128, 64);



        addListener(new InputListener() {


            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                switch (type){
                    case 0:
                        table.mov = false;
                        type =1;
                        break;
                    case 1:
                        table.mov = false;
                        type =2;
                        break;
                    case 2:
                        table.mov = false;
                        type =0;
                        break;
                }
                return true;
            }


        });







    }

    /**
     * Mueve a la izquierda el actor.
     */
    public void moverIzquierda(){
        if(getX() > -15 ) {
            this.setX(this.getX() - velocidad);
        }
    }

    /**
     * Mueve a la derecha el actor.
     */
    public void moverDerecha(){
        if(getX() < 487) {
            this.setX(this.getX() + velocidad);
        }
    }

    /**
     * Metodo de dibujo del actor.
     */
    @Override
    public void draw(Batch batch, float parentAlpha){
        rect = new Rectangle(getX()+22,getY()+2,getWidth()-44,getHeight()-9);

        switch (type){
            case 0:
                text = game.images.manager.get("Images/nave.png", Texture.class);
                batch.draw(text, getX(), getY());
                break;
            case 1:
                text = game.images.manager.get("Images/nave2.png", Texture.class);
                batch.draw(text, getX(), getY());
                break;
            case 2:
                text =game.images.manager.get("Images/nave3.png", Texture.class);
                batch.draw(text, getX(), getY());
                break;
        }


    }







}
