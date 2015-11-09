package com.gamecell.spacecraft.Logics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.gamecell.spacecraft.Actors.Disparo;
import com.gamecell.spacecraft.Actors.Enemigo;
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
        Enemigo enemigo;// María: El enemigo
        ArrayList<FallenActor> colFallen;
        ArrayList<Disparo> colDisparos;
        ArrayList<Enemigo> colEnemigo;
        private boolean mov, direction;
        private int teclas;
        private long TimeSpawnerFallen,TimeSpawnerFallenSol,TimeSpawnerFallenLuna,TimeSpawnerFallenCohete,
                TimeSpawnerFallenSatelite, TimeSpawnerEnemigo,TimeSpawnerDisparo;


        public LogicalGame(SpaceCraft game, GameScreen screen)  {
            teclas = 0;
            Gdx.input.setInputProcessor(this);
            this.game = game;
            TimeSpawnerFallen = TimeUtils.millis();
            TimeSpawnerFallenSol = TimeUtils.millis();
            TimeSpawnerFallenLuna = TimeUtils.millis();
            TimeSpawnerFallenCohete = TimeUtils.millis();
            TimeSpawnerFallenSatelite = TimeUtils.millis();
            TimeSpawnerDisparo = TimeUtils.millis();
            TimeSpawnerEnemigo = TimeUtils.millis();//María: Tiempo de aparición enemigo

            //zona de instancia de Colecciones
            colFallen = new ArrayList<FallenActor>();
            colDisparos = new ArrayList<Disparo>();
            colEnemigo = new ArrayList<Enemigo>();
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

            //enemigo.setZIndex(50000);//María: El enemigo está a la misma altura que la nave.
            nave.setZIndex(50000);


            //Spawners de Objetos Fallen.
            //Estrellas
            if(TimeUtils.millis() - TimeSpawnerFallen > 300){
                spawnEstrellaActor();
                TimeSpawnerFallen = TimeUtils.millis();

            }
            //Soles
            if(TimeUtils.millis() - TimeSpawnerFallenSol > MathUtils.random(50000, 120000)) {
                spawnSolActor();
                TimeSpawnerFallenSol = TimeUtils.millis();

            }
            //Lunas
            if(TimeUtils.millis() - TimeSpawnerFallenLuna > MathUtils.random(30000, 90000)) {
                spawnLunaActor();
                TimeSpawnerFallenLuna = TimeUtils.millis();

            }

            if(TimeUtils.millis() - TimeSpawnerFallenCohete > MathUtils.random(80000, 150000)) {
                spawnCoheteActor();
                TimeSpawnerFallenCohete = TimeUtils.millis();

            }

            if(TimeUtils.millis() - TimeSpawnerFallenSatelite > MathUtils.random(60000, 130000)) {
                spawnSateliteActor();
                TimeSpawnerFallenSatelite = TimeUtils.millis();

            }

            if(TimeUtils.millis() - TimeSpawnerDisparo > 3000) {
                spawnDisparoActor();
                TimeSpawnerDisparo = TimeUtils.millis();

            }

            //María:El enemigo aparecerá cada 5 segundos.
            if(TimeUtils.millis() - TimeSpawnerEnemigo > 5000){
                Enemigo enemigo = new Enemigo(game);
                this.addActor(enemigo);
                colEnemigo.add(enemigo);
                TimeSpawnerEnemigo = TimeUtils.millis();
            }


            try {

                for(FallenActor f:colFallen) {



                        if (f.getActions().size == 0) {
                            this.removeActor(f);
                            colFallen.remove(f);
                        }



                }



            //////////////////////////////////////////////////////
            // María: Colisión del enemigo con actor principal //
            ////////////////////////////////////////////////////

            for(Enemigo e :colEnemigo) {

                if(e.getActions().size == 0){
                    this.removeActor(e);
                    colEnemigo.remove(e);
                    System.out.println("Actor Enemigo Eliminado");
                    break;
                }


                if(e.estado == 1) {

                    if (e.collisionEnemigo(nave)) {
                        e.setZIndex(3000);
                        //Si colisionan entonces la imagen del enemigo cambia a un BOOMB!
                        e.setImagenEnemigo(game.images.manager.get("Images/sol.png", Texture.class));
                        e.DestruirRectangulo();

                        System.out.println("Choque contra Nave");
                        break;
                    }

                    try {
                        for (Disparo d : colDisparos) {
                            if (e.rect.overlaps(d.rect)) {
                                e.setImagenEnemigo(game.images.manager.get("Images/sol.png", Texture.class));
                                e.DestruirRectangulo();
                                this.removeActor(d);
                                colDisparos.remove(d);
                                System.out.println("Enemigo contra disparo");
                            }
                        }
                    } catch (NullPointerException f){}
                }


            }


            for(Disparo d : colDisparos){

                if(d.getActions().size == 0){
                    this.removeActor(d);
                    colDisparos.remove(d);
                    System.out.println("Actor Enemigo Eliminado");
                }
            }
            } catch (ConcurrentModificationException e){
            }


            //Control del Movimiento.
            if(mov) {
                if (direction) {
                    nave.moverIzquierda();
                } else {
                    nave.moverDerecha();
                }
            }

            System.out.println("Enemigos Totales en memoria:" + colEnemigo.size());
            System.out.println("Disparos Totales en memoria:" + colDisparos.size());
            System.out.println("Fallen Totales en memoria:" + colFallen.size());
        }

    private void spawnEstrellaActor() {
        FallenActor fallenActor = new FallenActor(game,(Texture) game.images.manager.get("Images/estrella.png"));
        colFallen.add(fallenActor);
        this.addActor(fallenActor);
    }

    private void spawnSolActor() {
        FallenActor fallenActor = new FallenActor(game,(Texture) game.images.manager.get("Images/sol.png"));
        colFallen.add(fallenActor);
        this.addActor(fallenActor);
    }

    private void spawnLunaActor() {
        FallenActor fallenActor = new FallenActor(game,(Texture) game.images.manager.get("Images/luna.png"));
        colFallen.add(fallenActor);
        this.addActor(fallenActor);
    }

    private void spawnSateliteActor() {
        FallenActor fallenActor = new FallenActor(game,(Texture) game.images.manager.get("Images/satelite.png"));
        colFallen.add(fallenActor);
        this.addActor(fallenActor);
    }

    private void spawnCoheteActor() {
        FallenActor fallenActor = new FallenActor(game,(Texture) game.images.manager.get("Images/cohete.png"));
        colFallen.add(fallenActor);
        this.addActor(fallenActor);
    }

    private void spawnDisparoActor() {
        Disparo disparo = new Disparo(game,2,nave);
        colDisparos.add(disparo);
        this.addActor(disparo);
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
