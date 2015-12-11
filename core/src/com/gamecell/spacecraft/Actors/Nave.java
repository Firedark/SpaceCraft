package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.gamecell.spacecraft.Logics.LogicalGame;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Clase Nave, actor principal que controla el usuario.
 * @author Sergio Jimenez Cortes *
 */

public class Nave extends Actor {


    private SpaceCraft game;
    private int velocidad = 6;
    public Texture text;
    public int type;
    public Rectangle rect;
    public LogicalGame logicalGame;
    public boolean uncollisionable;
    public boolean visible;
    public long timeVisible,timeUncollision;
    public  int maxPotenciaA, maxPotenciaB,maxPotenciaC;
    public boolean shield;
    public int shieldPower;

    /**
     * Constructor de la clase.
     * @param game de la clase principal
     */
    public Nave(SpaceCraft game, final LogicalGame logicalGame){
        this.game = game;
        this.logicalGame = logicalGame;
        type = 0;
        maxPotenciaA = 3;
        maxPotenciaB = 4;
        maxPotenciaC = 4;
        this.setBounds(236, 60, 128, 64);
        visible = true;
        timeVisible = TimeUtils.millis();
        timeUncollision = 3000;
        uncollisionable = false;
        addListener(new InputListener() {


            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                switch (type){
                    case 0:
                        logicalGame.mov = false;
                        type =1;
                        break;
                    case 1:
                        logicalGame.mov = false;
                        type =2;
                        break;
                    case 2:
                        logicalGame.mov = false;
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

        if(uncollisionable){

            if(TimeUtils.millis() - timeVisible  > 300){
                if(visible){
                    visible = false;
                }else{
                    visible = true;
                }
                timeVisible = TimeUtils.millis();
            }


        }else{
            visible = true;
        }



        if(visible) {

            switch (type) {
                case 0:
                    text = game.images.manager.get("Images/nave.png", Texture.class);
                    batch.draw(text, getX(), getY());
                    break;
                case 1:
                    text = game.images.manager.get("Images/nave2.png", Texture.class);
                    batch.draw(text, getX(), getY());
                    break;
                case 2:
                    text = game.images.manager.get("Images/nave3.png", Texture.class);
                    batch.draw(text, getX(), getY());
                    break;
            }

            if(shield){
                batch.draw((Texture) game.images.manager.get("Images/shield.png"), getX(), getY()-10);

            }
        }

    }


    public void setUpShield() {
        game.audios.playSound((Sound) game.audios.soundmanager.get("Sounds/sfx_shieldUp.ogg"));
        shieldPower = shieldPower + 2;
        shield = true;

    }


    public void lessShield(int disparo){
        shieldPower = shieldPower - disparo;
        if(shieldPower <= 0) {
            shieldPower = 0;
            turnOffShield();
        }
    }

    public void turnOffShield() {
        shield = false;
        shieldPower = 0;
        game.audios.playSound((Sound) game.audios.soundmanager.get("Sounds/sfx_shieldDown.ogg"));


    }
}
