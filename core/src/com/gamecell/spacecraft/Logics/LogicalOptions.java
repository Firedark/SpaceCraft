package com.gamecell.spacecraft.Logics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gamecell.spacecraft.Screens.OptionsScreen;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Created by Firedark on 29/10/2015.
 */
public class LogicalOptions extends Table {
        //Atributos de la clase

        SpaceCraft game;

        public LogicalOptions(SpaceCraft game, OptionsScreen screen){

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
