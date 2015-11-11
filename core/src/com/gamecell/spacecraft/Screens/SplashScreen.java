package com.gamecell.spacecraft.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gamecell.spacecraft.Logics.LogicalGame;
import com.gamecell.spacecraft.Logics.LogicalSplash;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Clase SplashScreen, Screen que contiene el stage y la classe LogicalSplash.
 * @author Sergio Jimenez Cortes *
 */

public class SplashScreen implements Screen{
    private SpaceCraft game;
    private Stage stage;
    private LogicalSplash logicalSplash;
    private Viewport viewport;
    public SplashScreen(SpaceCraft game){
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        logicalSplash = new LogicalSplash(game,this);
        stage.addActor(logicalSplash);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    }
}
