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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gamecell.spacecraft.Logics.LogicalStart;
import com.gamecell.spacecraft.SpaceCraft;


public class StartScreen implements Screen {

    private SpaceCraft game;
    private Stage stage;
    private LogicalStart logicalStart;
    private Viewport viewport;
    private Skin skin;
    ImageButton.ImageButtonStyle playButtonStyle, optionButtonStyle, quitButtonStyle;
    public StartScreen(SpaceCraft game){
        this.game = game;
        this.stage = new Stage();
    }

    /**
     * Clase StartScreen, Screen que contiene el stage y la classe LogicalStart.
     * @author Sergio, Jaume*
     */





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
    }

    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width,height,true);


        //Tabla del menu
        Table table = new Table();
        table.setPosition(width /2, height /2);
        table.setFillParent(true);
        table.setHeight(height);
        stage.addActor(table);


        //Botones
        getSkin();
        //Play
        ImageButton buttonPlay = new ImageButton(playButtonStyle);
        buttonPlay.setPosition(buttonPlay.getOriginX(), buttonPlay.getOriginY() - 120);
        buttonPlay.setWidth(100);
        buttonPlay.setHeight(30);
        buttonPlay.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //empezar juego
                game.setScreen(game.gameScreen);
                return false;
            }
        });
        table.addActor(buttonPlay);
        ImageButton buttonOptions = new ImageButton(optionButtonStyle);
        //Options
        buttonOptions.setPosition(buttonOptions.getOriginX(), buttonOptions.getOriginY() - 170);
        buttonOptions.setWidth(100);
        buttonOptions.setHeight(30);
        buttonOptions.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //opciones
                return false;
            }
        });
        table.addActor(buttonOptions);

        //Quit
        ImageButton buttonQuit = new ImageButton(quitButtonStyle);
        buttonQuit.setPosition(buttonQuit.getOriginX(), buttonQuit.getOriginY() - 220);
        buttonQuit.setWidth(100);
        buttonQuit.setHeight(30);
        buttonQuit.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //salir
                System.exit(0);
                return false;
            }
        });
        table.addActor(buttonQuit);
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
