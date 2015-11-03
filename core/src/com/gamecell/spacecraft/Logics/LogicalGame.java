package com.gamecell.spacecraft.Logics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gamecell.spacecraft.Actors.Nave;
import com.gamecell.spacecraft.Screens.GameScreen;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Created by Firedark on 29/10/2015.
 */
public class LogicalGame extends Table implements InputProcessor {
        //Atributos de la clase

        SpaceCraft game;
        Nave nave;
        private boolean mov, direction;
        private int teclas;


        public LogicalGame(SpaceCraft game, GameScreen screen)  {
            teclas = 0;
            Gdx.input.setInputProcessor(this);
            this.game = game;

            //Zona de instancia de Actores varios.
            nave = new Nave(game);


            //Añadir Actores
            this.addActor(nave);
        }

        //Metodo act se ejecuta al igual que el render, es donde insertaremos la lógica.
        @Override
        public void act(float delta) {
            super.act(delta);
            if(mov) {
                if (direction) {
                    nave.moverIzquierda();
                } else {
                    nave.moverDerecha();
                }
            }

        }


        //Método Draw contiene el SpriteBatch para dibujar.
        public void draw(SpriteBatch batch, float parentAlpha) {
            batch.setColor(Color.BLACK);
            super.draw(batch, parentAlpha);
        }




    //InputProcessor

    @Override
    public boolean keyDown(int keycode) {
        mov = true;
        if (keycode == 21) {
            direction = true;
            if(teclas== 1) teclas = 2;
            if(teclas== 0) teclas = 1; ;

        }

        if (keycode == 22) {
            direction = false;
            if(teclas== 1) teclas = 2;
            if(teclas== 0) teclas = 1; ;

        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(teclas== 1) teclas = 0;
        if(teclas== 2) teclas = 1; ;

        if(teclas == 0){
            mov = false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mov = true;
        if(screenX < 300 ){
            direction = true;
        }else{
            direction = false;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        mov = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
