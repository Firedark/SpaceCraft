package com.gamecell.spacecraft.Logics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gamecell.spacecraft.Screens.NextLevelScreen;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Lógica de la pantalla de pausa del joc
 * @author Josué Javier Campos Fernández
 */
public class LogicalNextLevel extends Table {
    //Atributos de la clase

    SpaceCraft game;

    public LogicalNextLevel(SpaceCraft game, NextLevelScreen screen){

        this.game = game;

        //Zona de instancia de Actores varios.

    }

    /**
     * Metodo act se ejecuta al igual que el render, es donde insertaremos la logica.
     * @param delta
     */
    @Override
    public void act(float delta) {
        super.act(delta);
    }

    /**
     * Metodo Draw contiene el SpriteBatch para dibujar.
     * @param batch
     * @param parentAlpha
     */
    public void draw(SpriteBatch batch, float parentAlpha) {
        batch.setColor(Color.BLACK);
        super.draw(batch, parentAlpha);
    }



}
