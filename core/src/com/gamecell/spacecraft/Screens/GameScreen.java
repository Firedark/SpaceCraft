package com.gamecell.spacecraft.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gamecell.spacecraft.Logics.LogicalGame;
import com.gamecell.spacecraft.SpaceCraft;


public class GameScreen implements Screen{
    private SpaceCraft game;
    private Stage stage;
    private LogicalGame logicalGame;
    private Viewport viewport;
    public GameScreen(SpaceCraft game){
        this.game = game;
        this.stage = new Stage();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        logicalGame = new LogicalGame(game,this);
        stage.addActor(logicalGame);
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
