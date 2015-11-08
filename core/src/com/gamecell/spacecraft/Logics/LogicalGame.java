package com.gamecell.spacecraft.Logics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.gamecell.spacecraft.Actors.FallenActor;
import com.gamecell.spacecraft.Actors.Nave;
import com.gamecell.spacecraft.Screens.GameScreen;
import com.gamecell.spacecraft.SpaceCraft;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Created by Firedark on 29/10/2015.
 */
public class LogicalGame extends Table implements InputProcessor {
        //Atributos de la clase

        SpaceCraft game;
        Nave nave;
        ArrayList<FallenActor> colFallen;
        Texture star,sol;
        private boolean mov, direction;
        private int teclas;
        private long TimeSpawnerFallen,TimeSpawnerFallenSol;


        public LogicalGame(SpaceCraft game, GameScreen screen)  {
            teclas = 0;
            Gdx.input.setInputProcessor(this);
            this.game = game;
            star = new Texture(Gdx.files.internal("Images/estrella.png"));
            sol = new Texture(Gdx.files.internal("Images/sol.png"));
            TimeSpawnerFallen = TimeUtils.millis();
            TimeSpawnerFallenSol = TimeUtils.millis();

            //zona de instancia de Colecciones
            colFallen = new ArrayList<FallenActor>();


            //Zona de instancia de Actores varios.
            nave = new Nave(game);


            //Añadir Actores
            this.addActor(nave);



        }

        //Metodo act se ejecuta al igual que el render, es donde insertaremos la lógica.
        @Override
        public void act(float delta) {
            super.act(delta);
            //Sirve para colocar la Nave sobre las estrellas, etc

            nave.setZIndex(50000);


            //Spawners de Objetos Fallen.
            //Estrellas
            if(TimeUtils.millis() - TimeSpawnerFallen > 300){
                spawnEstrellaActor();
                TimeSpawnerFallen = TimeUtils.millis();

            }

            if(TimeUtils.millis() - TimeSpawnerFallenSol > MathUtils.random(50000, 120000)) {
                spawnSolActor();
                TimeSpawnerFallenSol = TimeUtils.millis();

            }



            try {

            for(FallenActor f:colFallen) {
                boolean delete = false;
                if(!colFallen.isEmpty()) {

                    if (f.getActions().size == 0) {
                        this.removeActor(f);
                        delete = true;
                    }

                }

                    if (delete) {
                        colFallen.remove(f);
                    }

                System.out.println(colFallen.size());
            }

            } catch (ConcurrentModificationException e){
                System.out.println("Se queja por vicio");
            }

            //Control del Movimiento.
            if(mov) {
                if (direction) {
                    nave.moverIzquierda();
                } else {
                    nave.moverDerecha();
                }
            }

        }

    private void spawnEstrellaActor() {
        FallenActor fallenActor = new FallenActor(game,star);
        colFallen.add(fallenActor);
        this.addActor(fallenActor);
    }

    private void spawnSolActor() {
        FallenActor fallenActor = new FallenActor(game,sol);
        colFallen.add(fallenActor);
        this.addActor(fallenActor);
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
            if(teclas== 0) teclas = 1;

        }

        if (keycode == 22) {
            direction = false;
            if(teclas== 1) teclas = 2;
            if(teclas== 0) teclas = 1;

        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(teclas== 1) teclas = 0;
        if(teclas== 2) teclas = 1;

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