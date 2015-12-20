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
import com.gamecell.spacecraft.Logics.LogicalPause;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Pantalla on el joc es troba en pausa
 * @author Josué Javier Campos Fernández
 */
public class PauseScreen implements Screen {
    private SpaceCraft game;
    private Stage stage;
    private LogicalPause logicalPause;
    private Viewport viewport;
    private Skin skin;
    ImageButton.ImageButtonStyle FXButtonStyle, musicButtonStyle, continueButtonStyle, quitButtonStyle;
    //Texto
    private Label.LabelStyle font;
    private Label pauseLbl;

    public PauseScreen(SpaceCraft game){
        this.game = game;
        this.stage = new Stage(new StretchViewport(game.w, game.h));
        this.font = new Label.LabelStyle(FontManager.font, null);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        logicalPause = new LogicalPause(game, this);
        stage.addActor(logicalPause);
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
        pauseLbl = new Label("Pause", font);
        pauseLbl.setBounds(236, game.h - 100, 0, 0);
        pauseLbl.setFontScale(0.9f, 0.9f);
        pauseLbl.setName("actorPause");
        logicalPause.addActor(pauseLbl);

        //FX
        final ImageButton buttonFX = new ImageButton(FXButtonStyle);
        if (!game.preferences.isFXEnabled()){
            buttonFX.setChecked(true);
        }
        buttonFX.setPosition((game.w / 2) - 50, game.h / 2 + 125);
        buttonFX.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (buttonFX.isChecked()){
                    game.audios.enableFX();
                    game.preferences.setFXEnabled();
                } else {
                    game.audios.muteFX();
                    game.preferences.setFXDisabled();
                }
                return false;
            }
        });
        logicalPause.addActor(buttonFX);

        //Music
        final ImageButton buttonMusic = new ImageButton(musicButtonStyle);
        if (!game.preferences.isMusicEnabled()){
            buttonMusic.setChecked(true);
        }
        buttonMusic.setPosition((game.w / 2) - 50, (game.h / 2));
        buttonMusic.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //opciones
                if (buttonMusic.isChecked()){
                    game.audios.enableMusic();
                    game.preferences.setMusicEnabled();
                } else {
                    game.audios.muteMusic();
                    game.preferences.setMusicDisabled();
                }
                return false;
            }
        });
        logicalPause.addActor(buttonMusic);


        //Continue
        ImageButton buttonContinue = new ImageButton(continueButtonStyle);
        buttonContinue.setPosition((game.w/2)-100,(game.h/2)-100);
        buttonContinue.setWidth(200);
        buttonContinue.setHeight(60);
        buttonContinue.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gameScreen.pause = false;
                return false;
            }
        });
        logicalPause.addActor(buttonContinue);

        //Quit
        ImageButton buttonQuit = new ImageButton(quitButtonStyle);
        buttonQuit.setPosition((game.w/2)-100,(game.h/2)-200);
        buttonQuit.setWidth(200);
        buttonQuit.setHeight(60);
        buttonQuit.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                logicalPause.remove();
                game.setScreen(game.startScreen);
                return false;
            }
        });
        logicalPause.addActor(buttonQuit);
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
        TextureAtlas atlasUiMenu = new TextureAtlas("icons.pack");

        if(skin==null){
            skin = new Skin(atlasUiMenu);

            FXButtonStyle = new ImageButton.ImageButtonStyle();
            FXButtonStyle.up = skin.getDrawable("FX");
            FXButtonStyle.down = skin.getDrawable("FX-bn");
            FXButtonStyle.imageChecked = skin.getDrawable("FX-off");
            FXButtonStyle.pressedOffsetX = 1;
            FXButtonStyle.pressedOffsetY = -1;

            musicButtonStyle = new ImageButton.ImageButtonStyle();
            musicButtonStyle = new ImageButton.ImageButtonStyle();
            musicButtonStyle.up = skin.getDrawable("Music");
            musicButtonStyle.down = skin.getDrawable("Music-bn");
            musicButtonStyle.imageChecked = skin.getDrawable("Music-OFF");
            musicButtonStyle.pressedOffsetX = 1;
            musicButtonStyle.pressedOffsetY = -1;

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
