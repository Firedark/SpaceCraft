package com.gamecell.spacecraft.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gamecell.spacecraft.FontManager;
import com.gamecell.spacecraft.Logics.LogicalGameOver;
import com.gamecell.spacecraft.MyPreferences;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Clase GameOverScreen, se carga cuando hemos terminado las vidas de la nave.
 * @author Josué Javier
 */
public class GameOverScreen implements Screen {
    private SpaceCraft game;
    private Stage stage;
    private LogicalGameOver logicalGameOver;
    private Viewport viewport;
    private Skin skin;
    ImageButton.ImageButtonStyle quitButtonStyle;
    //Texto
    private Label.LabelStyle font;
    private Label gameOverLbl, scoreLbl;

    public GameOverScreen(SpaceCraft game){
        this.game = game;
        this.stage = new Stage(new StretchViewport(game.w, game.h));
        this.font = new Label.LabelStyle(FontManager.font, null);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        logicalGameOver = new LogicalGameOver(game, this);
        stage.addActor(logicalGameOver);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

        //Botones
        getSkin();

        //Label game over
        gameOverLbl = new Label("Game Over!", font);
        gameOverLbl.setBounds(200, game.h - 100, 0, 0);
        gameOverLbl.setFontScale(0.9f, 0.9f);
        gameOverLbl.setName("actorGameOver");
        logicalGameOver.addActor(gameOverLbl);

        //Label score
        scoreLbl = new Label("Score " + Integer.toString(game.preferences.getScore()), font);
        scoreLbl.setBounds(240, game.h - 200, 0, 0);
        scoreLbl.setFontScale(0.6f, 0.6f);
        scoreLbl.setName("actorScore");
        logicalGameOver.addActor(scoreLbl);

        //Quit
        ImageButton buttonQuit = new ImageButton(quitButtonStyle);
        buttonQuit.setPosition((game.w/2)-100,(game.h/2)-200);
        buttonQuit.setWidth(200);
        buttonQuit.setHeight(60);
        buttonQuit.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                logicalGameOver.remove();
                game.setScreen(game.startScreen);
                return false;
            }
        });
        logicalGameOver.addActor(buttonQuit);
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

    protected Skin getSkin(){
        TextureAtlas atlasUiMenu = new TextureAtlas("menuButton.pack");

        if(skin==null){
            skin = new Skin(atlasUiMenu);

            quitButtonStyle = new ImageButton.ImageButtonStyle();
            quitButtonStyle = new ImageButton.ImageButtonStyle();
            quitButtonStyle.up = skin.getDrawable("Quit");
            quitButtonStyle.down = skin.getDrawable("Quit selected");
            quitButtonStyle.over = skin.getDrawable("Quit");
            quitButtonStyle.pressedOffsetX = 1;
            quitButtonStyle.pressedOffsetY = -1;
        }
        return skin;
    }
}
