package com.gamecell.spacecraft.Logics;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.gamecell.spacecraft.Actors.Disparo;
import com.gamecell.spacecraft.Actors.DisparoB;
import com.gamecell.spacecraft.Actors.DisparoC;
import com.gamecell.spacecraft.Actors.FallenActor;
import com.gamecell.spacecraft.Actors.GUI.Lifes;
import com.gamecell.spacecraft.Actors.GenDisparo;
import com.gamecell.spacecraft.Actors.GenDisparoEnemigo;
import com.gamecell.spacecraft.Actors.GenEnemigo;
import com.gamecell.spacecraft.Actors.Nave;
import com.gamecell.spacecraft.Actors.PowerUps;
import com.gamecell.spacecraft.DinamicBackground;
import com.gamecell.spacecraft.FontManager;
import com.gamecell.spacecraft.LevelManager;
import com.gamecell.spacecraft.Screens.GameScreen;
import com.gamecell.spacecraft.SpaceCraft;
import com.gamecell.spacecraft.MyPreferences;

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
    public LevelManager levelManager;

    //Valores de Juego
    public int vidas;
    public int velocidad;
    public int potenciaA,potenciaB,potenciaC;

    public boolean shield,hold,hold2;
    public int segundos;
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
    private Label levelLbl;
    private Label pauseLbl;

    //Vidas
    public Lifes lifes;

    /**
     * Constructor de la clase.
     * @param game de la clase principal
     * @param screen Screen que contiene la logica.
     */
    public LogicalGame(final SpaceCraft game, final GameScreen screen)  {
        this.game = game;
        teclas = 0;

        //Defaults
        vidas = 3;
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
        lifes.setZIndex(50001);
        //Fuente de texto
        font = new Label.LabelStyle(FontManager.font, null);

        //Score
        scoreLbl = new Label(Integer.toString(this.game.preferences.score), font);
        scoreLbl.setBounds(30, 30, 0, 0);
        scoreLbl.setFontScale(0.9f, 0.9f);
        scoreLbl.setName("actorScore");
        scoreLbl.setZIndex(50001);
        //Añadir score
        this.addActor(scoreLbl);

        //Añadir Actores
        this.addActor(nave);
        this.addActor(lifes);

        //Niveles
        levelManager = new LevelManager(game, this, 0, nave, 1);
        levelManager.loadLevel();
        levelLbl = new Label(("Level " + levelManager.level), font);
        levelLbl.setBounds(442, game.h - 35, 0, 0);
        levelLbl.setFontScale(0.9f, 0.9f);
        levelLbl.setName("actorLevel");
        levelLbl.setZIndex(99999);
        //Añadir label level
        this.addActor(levelLbl);

        //Pause
        pauseLbl = new Label("Pause", font);
        pauseLbl.setBounds(442, 30, 150, 30);
        pauseLbl.setFontScale(0.9f, 0.9f);
        pauseLbl.setName("actorPause");
        pauseLbl.setZIndex(50001);
        pauseLbl.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Ponemos en pausa el juego
                screen.pause();
                game.gameScreen.pause = true;
                return false;
            }
        });
        //Añadir label pause
        this.addActor(pauseLbl);
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
        if(vidas == 0){
            // Guardamos la puntuación
            game.preferences.setScore();

            this.remove();
            game.setScreen(game.gameOverScreen);
        }

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
        } catch (Exception f){}

        try{
            for(PowerUps p : colPowerUps){
                p.checkColision();
            }
        } catch (ConcurrentModificationException e){}

        //Control del Movimiento.
        if(mov) {
            if (direction) {
                nave.moverIzquierda();
            } else {
                nave.moverDerecha();
            }
        }

        //Sirve para colocar la Nave sobre las estrellas, etc
        lifes.setZIndex(50000);
        levelLbl.setZIndex(50001);
        pauseLbl.setZIndex(50002);
        scoreLbl.setZIndex(50003);
        nave.setZIndex(50000);
    }

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
