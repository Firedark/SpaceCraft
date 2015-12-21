package com.gamecell.spacecraft.Logics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gamecell.spacecraft.FontManager;
import com.gamecell.spacecraft.Screens.GameScreen;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Lógica de la pantalla de pausa del joc
 * @author Josué Javier Campos Fernández
 */
public class LogicalPause extends Table {
    private SpaceCraft game;
    private GameScreen screen;
    private Skin skin;
    ImageButton.ImageButtonStyle FXButtonStyle, musicButtonStyle, continueButtonStyle, quitButtonStyle;
    //Texto
    private Label.LabelStyle font;
    private Label pauseLbl;


    public LogicalPause (final SpaceCraft game, final GameScreen screen){
        this.game = game;
        this.screen = screen;
        this.font = new Label.LabelStyle(FontManager.font, null);


        //Botones
        getSkin();

        //Label pause
        pauseLbl = new Label("Pause", font);
        pauseLbl.setBounds(236, game.h - 100, 0, 0);
        pauseLbl.setFontScale(0.9f, 0.9f);
        pauseLbl.setName("actorPause");
        this.addActor(pauseLbl);

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
        this.addActor(buttonFX);

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
        this.addActor(buttonMusic);


        //Continue
        ImageButton buttonContinue = new ImageButton(continueButtonStyle);
        buttonContinue.setPosition((game.w/2)-100,(game.h/2)-100);
        buttonContinue.setWidth(200);
        buttonContinue.setHeight(60);
        buttonContinue.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                screen.resume();
                return false;
            }
        });
        this.addActor(buttonContinue);






    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    /**
    * Metodo Draw contiene el SpriteBatch para dibujar.
    * @param batch
    * @param parentAlpha
    */
    public void draw(SpriteBatch batch, float parentAlpha) {
        batch.setColor(Color.BLACK);
        super.draw(batch, parentAlpha);
    }


    public Skin getSkin(){
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

        }
        return skin;
    }
}
