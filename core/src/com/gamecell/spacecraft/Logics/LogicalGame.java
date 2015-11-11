package com.gamecell.spacecraft.Logics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.gamecell.spacecraft.Actors.Disparo;
import com.gamecell.spacecraft.Actors.Enemigo;
import com.gamecell.spacecraft.Actors.FallenActor;
import com.gamecell.spacecraft.Actors.Nave;
import com.gamecell.spacecraft.FontManager;
import com.gamecell.spacecraft.Screens.GameScreen;
import com.gamecell.spacecraft.SpaceCraft;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Clase LogicalGame, contiene la logica del juego, colisiones, tiempos, etc...
 * @author Sergio, Josue, Maria*
 */

public class LogicalGame extends Table implements InputProcessor {
        //Atributos de la clase
        SpaceCraft game;
        Nave nave;
        ArrayList<FallenActor> colFallen;
        ArrayList<Disparo> colDisparos;
        ArrayList<Enemigo> colEnemigo;
        private boolean mov, direction;
        private int teclas;
        private long TimeSpawnerFallen,TimeSpawnerFallenSol,TimeSpawnerFallenLuna,TimeSpawnerFallenCohete,
                TimeSpawnerFallenSatelite, TimeSpawnerEnemigo,TimeSpawnerDisparo;
        //Texto
        private Label.LabelStyle font;
        private Label scoreLbl;

        //Puntuacion
        private int score;

    /**
     * Constructor de la clase.
     * @param game de la clase principal
     * @param screen Screen que contiene la logica.
     */





    public LogicalGame(SpaceCraft game, GameScreen screen)  {
            this.game = game;

            teclas = 0;
            Gdx.input.setInputProcessor(this);

            TimeSpawnerFallen = TimeUtils.millis();
            TimeSpawnerFallenSol = TimeUtils.millis();
            TimeSpawnerFallenLuna = TimeUtils.millis();
            TimeSpawnerFallenCohete = TimeUtils.millis();
            TimeSpawnerFallenSatelite = TimeUtils.millis();
            TimeSpawnerDisparo = TimeUtils.millis();
            TimeSpawnerEnemigo = TimeUtils.millis();//María: Tiempo de aparición enemigo

            //Zona de instancia de Colecciones
            colFallen = new ArrayList<FallenActor>();
            colDisparos = new ArrayList<Disparo>();
            colEnemigo = new ArrayList<Enemigo>();

            //Zona de instancia de Actores varios.
            nave = new Nave(game);

            //Fuente de texto
            font = new Label.LabelStyle(FontManager.font, null);

            //Score
            score = 0;
            scoreLbl = new Label(Integer.toString(score), font);
            scoreLbl.setBounds(30, 30, 0, 0);
            scoreLbl.setFontScale(0.9f, 0.9f);
            scoreLbl.setName("actorScore");

            //Añadir score
            this.addActor(scoreLbl);

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
                game.audios.playSound((Sound) game.audios.soundmanager.get("Sounds/disparo.mp3"));
                TimeSpawnerDisparo = TimeUtils.millis();
            }

            //María:El enemigo aparecerá cada 5 segundos.
            if(TimeUtils.millis() - TimeSpawnerEnemigo > 5000){
                //Instanciamos el enemigo
                Enemigo enemigo = new Enemigo(game);
                //Lo añadimos al juego
                this.addActor(enemigo);
                //Lo añadimos a la Array de enemigos
                colEnemigo.add(enemigo);
                //Refrescamos el tiempo de apareción
                TimeSpawnerEnemigo = TimeUtils.millis();
            }

            try {

                for(FallenActor item:colFallen) {

                        if (item.getActions().size == 0) {
                            this.removeActor(item);
                            colFallen.remove(item);
                        }

                }

                //////////////////////////////////////////////////////
               // María: Colisión del enemigo con actor principal  //
              // o con el disparo del actor principal             //
             //////////////////////////////////////////////////////

                //Para cada enemigo de la colección
                for(Enemigo enemigo :colEnemigo) {

                    if(enemigo.getActions().size == 0){
                        this.removeActor(enemigo);
                        colEnemigo.remove(enemigo);
                        System.out.println("Actor Enemigo Eliminado");
                        break;
                    }

                    //Si el enemigo está activo
                    if(enemigo.estado == 1) {

                        //Colisiona con la nave
                        if (enemigo.collisionEnemigo(nave.rect)) {
                            enemigo.setZIndex(3000);
                            //Si colisionan entonces la imagen del enemigo cambia a un BOOMB!
                            enemigo.setImagenEnemigo(game.images.manager.get("Images/sol.png", Texture.class));
                            //Eliminamos el enemigo destruido de la escena
                            enemigo.DeleteEnemigo();

                            System.out.println("Choque contra Nave");
                            break;
                        }

                        try {
                            for (Disparo disparo : colDisparos) {
                                //Colisiona con el disparo
                                if (enemigo.collisionEnemigo(disparo.rect)) {
                                    //Sumamos puntuacion
                                    score += 10;
                                    Label newScoreLbl = this.findActor("actorScore");
                                    newScoreLbl.setText(Integer.toString(score));

                                    enemigo.setImagenEnemigo(game.images.manager.get("Images/sol.png", Texture.class));
                                    enemigo.DeleteEnemigo();
                                    this.removeActor(disparo);
                                    colDisparos.remove(disparo);
                                    game.audios.playSound((Sound) game.audios.soundmanager.get("Sounds/boom.mp3"));
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
