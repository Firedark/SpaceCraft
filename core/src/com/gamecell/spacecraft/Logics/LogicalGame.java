package com.gamecell.spacecraft.Logics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gamecell.spacecraft.Screens.GameScreen;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Created by Firedark on 29/10/2015.
 */
public class LogicalGame extends Table {
        //Atributos de la clase.

        SpaceCraft game;

        public LogicalGame(SpaceCraft game, GameScreen screen){

            this.game = game;

            //Zona de instancia de Actores varios.

        }

        //Metodo act se ejecuta al igual que el render, es donde insertaremos la lógica.
        @Override
        public void act(float delta) {
            super.act(delta);
        }


        //Método Draw contiene el SpriteBatch para dibujar.
        public void draw(SpriteBatch batch, float parentAlpha) {
            batch.setColor(Color.BLACK);
            super.draw(batch, parentAlpha);
        }



}
