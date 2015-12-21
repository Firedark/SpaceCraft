package com.gamecell.spacecraft.Actors.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gamecell.spacecraft.Actors.Nave;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Clase Lifes, encargada de la gestión de las vidas.
 * @author Josué Javier
 */
public class Lifes extends Actor {
    private int lifes;
    private SpaceCraft game;
    public int maxLifes = 9;
    private Nave nave;
    private int i;

    public Lifes(SpaceCraft game,int defaultLifes,Nave nave){
        this.nave = nave;
        lifes = defaultLifes;
        this.game = game;
        this.setZIndex(50001);
        this.setBounds(20,750,32,32);
    }

    /**
     * Actualiza las vidas
     * @param life
     */
    public void updateLifes(int life){
        lifes = life;
    }

    /**
     * Dibujamos las vidas
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch,float parentAlpha){
        i = 0;
        switch(nave.type){
            case 0:
                while(i<lifes) {
                    batch.draw(game.images.manager.get("Images/life1.png", Texture.class), getX()+ (i*35), getY());
                    i++;
                }
                break;
            case 1:
                while(i<lifes) {
                    batch.draw(game.images.manager.get("Images/life3.png", Texture.class), getX()+ (i*35), getY());
                    i++;
                }
                break;
            case 2:
                while(i<lifes) {
                    batch.draw(game.images.manager.get("Images/life2.png", Texture.class), getX()+ (i*35), getY());
                    i++;
                }
                break;
        }
    }
}
