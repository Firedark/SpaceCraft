package com.gamecell.spacecraft.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.gamecell.spacecraft.Logics.LogicalOptions;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * @author Sergio Jimenez Cortes / Jaume Gimeno Serrano
 */
public class OptionsScreen implements Screen {

    private SpaceCraft game;
    private Stage stage;
    private LogicalOptions logicalOptions;
    private Skin skin;
    ImageButton.ImageButtonStyle FXButtonStyle, musicButtonStyle, infoButtonStyle, exitButtonStyle;


    public OptionsScreen(SpaceCraft game){
        this.game = game;
        this.stage = new Stage(new StretchViewport(game.w, game.h));
    }



    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        logicalOptions = new LogicalOptions(game,this);
        stage.addActor(logicalOptions);
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
        //Botones
        getSkin();
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
        logicalOptions.addActor(buttonFX);

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
        logicalOptions.addActor(buttonMusic);

        //Info
        ImageButton buttonInfo = new ImageButton(infoButtonStyle);
        buttonInfo.setPosition((game.w / 2) - 50, (game.h / 2) - 125);
        buttonInfo.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.infoScreen);

                return false;
            }
        });
        logicalOptions.addActor(buttonInfo);



        //Exit
        ImageButton buttonExit = new ImageButton(exitButtonStyle);
        buttonExit.setPosition((game.w / 2) - 50, (game.h / 2) - 250);
        buttonExit.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                logicalOptions.remove();
                game.setScreen(game.startScreen);
                return false;
            }
        });
        logicalOptions.addActor(buttonExit);
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

            infoButtonStyle = new ImageButton.ImageButtonStyle();
            infoButtonStyle = new ImageButton.ImageButtonStyle();
            infoButtonStyle.up = skin.getDrawable("Info");
            infoButtonStyle.down = skin.getDrawable("Info-bn");
            infoButtonStyle.pressedOffsetX = 1;
            infoButtonStyle.pressedOffsetY = -1;

            exitButtonStyle = new ImageButton.ImageButtonStyle();
            exitButtonStyle = new ImageButton.ImageButtonStyle();
            exitButtonStyle.up = skin.getDrawable("Exit");
            exitButtonStyle.down = skin.getDrawable("Exit-bn");
            exitButtonStyle.pressedOffsetX = 1;
            exitButtonStyle.pressedOffsetY = -1;
        }
        return skin;
    }

}
