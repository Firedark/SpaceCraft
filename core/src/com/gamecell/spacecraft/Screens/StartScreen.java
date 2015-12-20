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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gamecell.spacecraft.Logics.LogicalStart;
import com.gamecell.spacecraft.SpaceCraft;




/**
 * Clase StartScreen, Screen que contiene el stage y la classe LogicalStart.
 * @author Sergio, Jaume*
 */
public class StartScreen implements Screen {

    private SpaceCraft game;
    private Stage stage;
    private LogicalStart logicalStart;
    private Viewport viewport;
    private Skin skin;
    private SpriteBatch batch;
    private Music music;

    ImageButton.ImageButtonStyle playButtonStyle, optionButtonStyle, continueButtonStyle, quitButtonStyle;
    public StartScreen(SpaceCraft game){
        this.game = game;
        this.stage = new Stage(new StretchViewport(game.w, game.h));
        batch = new SpriteBatch();
        music = game.audios.soundmanager.get("Music/MenuMusic.mp3");

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        logicalStart = new LogicalStart(game,this);
        stage.addActor(logicalStart);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        game.audios.playStartMusic();
    }

    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width, height, true);


        //Botones
        getSkin();
        //Play
        ImageButton buttonPlay = new ImageButton(playButtonStyle);
        buttonPlay.setPosition((game.w/2)-100,game.h/2);
        buttonPlay.setWidth(200);
        buttonPlay.setHeight(60);
        buttonPlay.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //empezar juego
                game.audios.stopMusic();
                logicalStart.remove();
                game.setScreen(game.gameScreen);
                return false;
            }
        });
        logicalStart.addActor(buttonPlay);
        ImageButton buttonOptions = new ImageButton(optionButtonStyle);

        //Options
        buttonOptions.setPosition((game.w/2)-100,(game.h/2)-100);
        buttonOptions.setWidth(200);
        buttonOptions.setHeight(60);
        buttonOptions.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //opciones
                logicalStart.remove();
                game.setScreen(game.optionsScreen);
                return false;
            }
        });
        logicalStart.addActor(buttonOptions);

        //Quit
        ImageButton buttonQuit = new ImageButton(quitButtonStyle);
        buttonQuit.setPosition((game.w/2)-100,(game.h/2)-100);
        buttonQuit.setWidth(200);
        buttonQuit.setHeight(60);
        buttonQuit.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //salir
                System.exit(0);
                return false;
            }
        });
        logicalStart.addActor(buttonQuit);
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
        if (game.audios.volumenMusica == 0){
            game.audios.muteMusic();
        }

    }

    @Override
    public void dispose() {

    }

    protected Skin getSkin(){
        TextureAtlas atlasUiMenu = new TextureAtlas("menuButton.pack");
        if(skin==null){
            skin = new Skin(atlasUiMenu);
            playButtonStyle = new ImageButton.ImageButtonStyle();
            playButtonStyle.up = skin.getDrawable("New Game");
            playButtonStyle.down = skin.getDrawable("New Game selected");
            playButtonStyle.over = skin.getDrawable("New Game");
            playButtonStyle.pressedOffsetX = 1;
            playButtonStyle.pressedOffsetY = -1;

            optionButtonStyle = new ImageButton.ImageButtonStyle();
            optionButtonStyle = new ImageButton.ImageButtonStyle();
            optionButtonStyle.up = skin.getDrawable("Options");
            optionButtonStyle.down = skin.getDrawable("Options selected");
            optionButtonStyle.over = skin.getDrawable("Options");
            optionButtonStyle.pressedOffsetX = 1;
            optionButtonStyle.pressedOffsetY = -1;

            continueButtonStyle = new ImageButton.ImageButtonStyle();
            continueButtonStyle = new ImageButton.ImageButtonStyle();
            continueButtonStyle.up = skin.getDrawable("Continue");
            continueButtonStyle.down = skin.getDrawable("Continue selected");
            continueButtonStyle.over = skin.getDrawable("Continue");
            continueButtonStyle.pressedOffsetX = 1;
            continueButtonStyle.pressedOffsetY = -1;

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
