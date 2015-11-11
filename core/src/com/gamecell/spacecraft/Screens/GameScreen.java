package com.gamecell.spacecraft.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gamecell.spacecraft.Logics.LogicalGame;
import com.gamecell.spacecraft.SpaceCraft;



/**
 * Clase GameScreen, Screen que contiene el stage y la classe LogicalGame.
 * @author Sergio Jimenez Cortes *
 */

public class GameScreen implements Screen{
    private SpaceCraft game;
    private Stage stage;
    private LogicalGame logicalGame;
    private Viewport viewport;

    public GameScreen(SpaceCraft game){
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
    }



    /**
     * Metodo show ejecutado cuando se carga la pantalla.
     */


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        logicalGame = new LogicalGame(game,this);
        stage.addActor(logicalGame);
    }

    /**
     * Metodo ciclico de dibujo
     */



    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    /**
     * Metodo ejecutado al expandir contraer la ventana en modo escritorio.
     */


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
    }


    /**
     * Metodo ejecutado al recibir un evento el telefono, una llamada, etc...
     */


    @Override
    public void pause() {}

    /**
     * Metodo llamado al salir de pausa.
     */

    @Override
    public void resume() {}

    /**
     * Metodo al cerrar / cambiar de ventana
     */



    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    /**
     * Metodo para liberar memoria.
     */



    @Override
    public void dispose() {}
}
