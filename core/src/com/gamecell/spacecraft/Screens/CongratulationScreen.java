package com.gamecell.spacecraft.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.gamecell.spacecraft.Logics.LogicalCongratulation;
import com.gamecell.spacecraft.Logics.LogicalPause;
import com.gamecell.spacecraft.Logics.LogicalStart;
import com.gamecell.spacecraft.SpaceCraft;


/**
 * Clase StartScreen, Screen que contiene el stage y la classe LogicalStart.
 * @author Sergio, Jaume*
 */
public class CongratulationScreen implements Screen {
    private SpaceCraft game;
    private Stage stage;
    private LogicalCongratulation logicalCongratulation;
    private Viewport viewport;
    private Skin skin;
    ImageButton.ImageButtonStyle quitButtonStyle;
    //Texto
    private Label.LabelStyle font;
    private Label congratsLbl, scoreLbl;

    public CongratulationScreen(SpaceCraft game){
        this.game = game;
        this.stage = new Stage(new StretchViewport(game.w, game.h));
        this.font = new Label.LabelStyle(FontManager.font, null);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        logicalCongratulation = new LogicalCongratulation(game, this);
        stage.addActor(logicalCongratulation);
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

        //Label pause
        congratsLbl = new Label("¡Congratulation!", font);
        congratsLbl.setBounds(150, game.h - 100, 0, 0);
        congratsLbl.setFontScale(0.9f, 0.9f);
        congratsLbl.setName("actorCongrats");
        logicalCongratulation.addActor(congratsLbl);

        //Quit
        ImageButton buttonQuit = new ImageButton(quitButtonStyle);
        buttonQuit.setPosition((game.w/2)-100,(game.h/2)-200);
        buttonQuit.setWidth(200);
        buttonQuit.setHeight(60);
        buttonQuit.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                logicalCongratulation.remove();
                game.setScreen(game.startScreen);
                return false;
            }
        });
        logicalCongratulation.addActor(buttonQuit);
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
