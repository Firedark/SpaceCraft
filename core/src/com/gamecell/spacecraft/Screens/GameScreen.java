package com.gamecell.spacecraft.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gamecell.spacecraft.Logics.LogicalGame;
import com.gamecell.spacecraft.Logics.LogicalPause;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Clase GameScreen, Screen que contiene el stage y la classe LogicalGame.
 * @author Sergio Jimenez Cortes / Jaume Gimeno Serrano
 */
public class GameScreen implements Screen{
    private SpaceCraft game;
    private Stage stage;
    private Stage stageP;
    private LogicalGame logicalGame;
    private LogicalPause logicalPause;
    private Viewport viewport;
    private Music music;
    private InputMultiplexer multi;
    public boolean pause;

    public GameScreen(SpaceCraft game){
        this.game = game;
        this.stage = new Stage(new StretchViewport(game.w, game.h));
        this.stageP = new Stage(new StretchViewport(game.w, game.h));
    }

    /**
     * Metodo show ejecutado cuando se carga la pantalla.
     */
    @Override
    public void show() {
        logicalGame = new LogicalGame(game,this);  // Genera la lógica del juego
        logicalPause = new LogicalPause(game, new PauseScreen(game));
        multi = new InputMultiplexer(); //Crea un multiplexor de entradas, sirve para hacer actuar varios procesadores de entradas a la vez.
        multi.addProcessor(logicalGame);  //Le añadimos el procesador de entradas de la lógica (general)
        multi.addProcessor(stage);   //Le añadimos el procesador de entradas del stage, para los listeners de los actores.
        Gdx.input.setInputProcessor(multi); //Set del multiplexor.
        stage.addActor(logicalGame);
        stageP.addActor(logicalPause);
        game.audios.playGameMusic(); //cargamos música
    }

    /**
     * Metodo ciclico de dibujo
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(!pause) {
            stage.act(delta);
            stage.draw();
        } else{
            game.setScreen(game.pauseScreen);
            stageP.act(delta);
            stageP.draw();
        }
    }

    /**
     * Metodo ejecutado al expandir contraer la ventana en modo escritorio.
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * Metodo ejecutado al recibir un evento el telefono, una llamada, etc...
     */
    @Override
    public void pause() {
        pause = true;
        game.audios.stopMusic();
        Gdx.input.setInputProcessor(stageP);
    }

    /**
     * Metodo llamado al salir de pausa.
     */
    @Override
    public void resume() {
        pause = false;
        game.audios.playGameMusic();
        Gdx.input.setInputProcessor(multi);
    }

    /**
     * Metodo al cerrar / cambiar de ventana
     */
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        game.audios.stopMusic();

    }

    /**
     * Metodo para liberar memoria.
     */
    @Override
    public void dispose() {
    }
}
