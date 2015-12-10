package com.gamecell.spacecraft.Logics;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.gamecell.spacecraft.Actors.Disparo;
import com.gamecell.spacecraft.Actors.DisparoB;
import com.gamecell.spacecraft.Actors.DisparoC;
import com.gamecell.spacecraft.Actors.GenDisparoEnemigo;
import com.gamecell.spacecraft.Actors.mobs.Meteor;
import com.gamecell.spacecraft.Actors.FallenActor;
import com.gamecell.spacecraft.Actors.GUI.Lifes;
import com.gamecell.spacecraft.Actors.GenDisparo;
import com.gamecell.spacecraft.Actors.GenEnemigo;
import com.gamecell.spacecraft.Actors.Nave;
import com.gamecell.spacecraft.Actors.PowerUps;
import com.gamecell.spacecraft.DinamicBackground;
import com.gamecell.spacecraft.FontManager;
import com.gamecell.spacecraft.LevelManager;
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
        private SpaceCraft game;
        private Nave nave;
        private LevelManager levelManager;

        //Valores de Juego
        public int vidas;
        public int velocidad;
        public int potenciaA,potenciaB,potenciaC;

        public boolean shield,hold,hold2;
        private int segundos;
        private DinamicBackground dinBack;
        private ArrayList<FallenActor> colFallen;
        public ArrayList<GenDisparo> colDisparos;
        public ArrayList<PowerUps> colPowerUps;
        public ArrayList<GenEnemigo> colShootables;
        public ArrayList<GenEnemigo> colCollisionables;
        public ArrayList<GenDisparoEnemigo> colDisparosEnemigos;
        public boolean mov, direction;
        private int teclas;
        private long TimeSpawnerDisparo,TimeSpawner,timeEntreChoques;
        //Texto
        private Label.LabelStyle font;
        private Label scoreLbl;

        //Interfaz
            //Vidas
            public Lifes lifes;

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


            //Defaults
            vidas = 2;
            segundos = 0;
            TimeSpawnerDisparo = TimeUtils.millis();
            TimeSpawner = TimeUtils.millis();
            timeEntreChoques = TimeUtils.millis();
            potenciaA = 1;
            potenciaB = 1;
            potenciaC = 1;

        //Zona de instancia de Colecciones
            colDisparos = new ArrayList<GenDisparo>();
            colPowerUps = new ArrayList<PowerUps>();
            colShootables = new ArrayList<GenEnemigo>();
            colCollisionables = new ArrayList<GenEnemigo>();
            colDisparosEnemigos = new ArrayList<GenDisparoEnemigo>();

            //Zona de instancia de Actores varios.
            dinBack = new DinamicBackground(game,this);
            nave = new Nave(game,this);
            lifes = new Lifes(game,vidas,nave);
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
            this.addActor(lifes);

            //Niveles
            levelManager = new LevelManager(game,this,0,nave);
            levelManager.loadLevel("Levels/1.xml");


        }

    /**
     * Metodo act se ejecuta al igual que el render, es donde insertaremos la lógica.
     * @param delta
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        dinBack.checkMillis();
        lifes.updateLifes(vidas);

        //Condiciones de derrota.

        if(vidas < 0){
            this.remove();
            game.setScreen(game.startScreen);
        }

        //Sirve para colocar la Nave sobre las estrellas, etc
        nave.setZIndex(50000);

        //Spawners de Objetos Fallen.
        //Estrellas

        if(TimeUtils.millis() - TimeSpawner > 1000){
            levelManager.updateSecond(segundos);
            segundos++;
            System.out.println(segundos);
            TimeSpawner = TimeUtils.millis();
        }

        if(TimeUtils.millis() - TimeSpawnerDisparo > 1500) {
            GenDisparo disparo = null;
            switch (nave.type){
                case 0:              disparo = new Disparo(game,potenciaA,nave,this);
                    game.audios.playSound((Sound) game.audios.soundmanager.get("Sounds/disparo.mp3"));
                    break;
                case 1:              disparo = new DisparoB(game,potenciaB,nave,this);
                    game.audios.playSound((Sound) game.audios.soundmanager.get("Sounds/sfx_laser2.ogg"));
                    break;
                case 2:              disparo = new DisparoC(game,potenciaC,nave,this,0);
                    game.audios.playSound((Sound) game.audios.soundmanager.get("Sounds/sfx_laser1.ogg"));
                    break;
            }

            colDisparos.add(disparo);
            this.addActor(disparo);

            TimeSpawnerDisparo = TimeUtils.millis();
        }




            //////////////////////////////////////////////////////
           // María: Colisión del enemigo con actor principal  //
          // o con el disparo del actor principal             //
         //////////////////////////////////////////////////////
        try {
            //Para cada enemigo de la colección
            for(GenEnemigo colisionable : colCollisionables) {

                colisionable.setZIndex(3000);
                if(TimeUtils.millis() - timeEntreChoques > nave.timeUncollision) {
                    boolean choque = false;
                    if (choque = colisionable.choqueVsNave(nave)) {

                            nave.uncollisionable = true;
                            timeEntreChoques = TimeUtils.millis();
                            hold = true;
                    }

                    if(!hold){
                        nave.uncollisionable = false;
                    }
                }
            }

            hold = false;

            for(GenEnemigo shootable : colShootables ){
                for (GenDisparo disparo : colDisparos) {
                    disparo.setZIndex(2000);

                    //Colisiona con el disparo
                    shootable.choqueVsDisparo(disparo);

                    //Sumamos puntuacion
                    Label newScoreLbl = this.findActor("actorScore");
                    newScoreLbl.setText(Integer.toString(game.preferences.score));

                }
            }

            for (GenDisparoEnemigo disparoEnemigo : colDisparosEnemigos){
                if(TimeUtils.millis() - timeEntreChoques > nave.timeUncollision) {
                    boolean choque = false;


                    if (choque = disparoEnemigo.ChoqueDisparoVsNave(nave, disparoEnemigo)) {

                        nave.uncollisionable = true;
                        timeEntreChoques = TimeUtils.millis();
                        hold2 = true;
                    }

                    if(!hold2){
                        nave.uncollisionable = false;
                    }
                }
            }

                    } catch (Exception f){
        }


        try{


                for(PowerUps p : colPowerUps){
                    p.checkColision();
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
