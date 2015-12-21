package com.gamecell.spacecraft.Logics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gamecell.spacecraft.Screens.CongratulationScreen;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Lógica de la pantalla de congratulation
 * @author Josué Javier
 */
public class LogicalCongratulation extends Table {
    //Atributos de la clase
    private SpaceCraft game;
    private CongratulationScreen screen;

    public LogicalCongratulation(SpaceCraft game, CongratulationScreen screen){
        this.game = game;
        this.screen = screen;
    }

    /**
     * Metodo act se ejecuta al igual que el render, es donde insertaremos la lógica.
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
