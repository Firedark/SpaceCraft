package com.gamecell.spacecraft.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.gamecell.spacecraft.SpaceCraft;

/**
 *
 * @author
 *
 */
public class InfoScreen implements Screen, InputProcessor {

    private SpaceCraft game;
    private Stage stage;
    private SpriteBatch          spriteBatch;
    private BitmapFont           bitmapFont;
    private OrthographicCamera       cam2d;
    private PerspectiveCamera      cam3d;
    private int                  WIDTH,HEIGHT;

    private final float            scrollSpeed = 16.0f; //unit per second

    private String text = "SpaceCraft\n\n Episodio I\n\nUN TRABAJO FINAL\n\nBatallando contra enemigos y\nasteroides,\nnuestros 4 heroes\ntienen la misión de llegar al final....\n\n\nControla de derecha\na izquierda y disfruta\nel disparo automático\n\nPara cambiar de nave\npulsa sobre ella\n\nPower-ups disponibles\n\nInmunidad\nVida extra\nMejora de disparo\n\n\n\nLicencias:\nIMAGENES\nKenney Vleugels\nwww.kenney.nl\nCreative Commons CC0\n\nICONOS\nSpace game interface\nDollar Photo Club\nRoyalty free license\n\nMUSICA\n8bit thingy\nextenz\nwww.opengameart.org\n\nSpace dimensions\nmatthew.pablo\nCreative Commons CC-BY3.0\nwww.opengameart.org\n\nAUTORES\nJosué Javier Campos\nJaume Gimeno\nSergio Jiménez\nMaria Vivo\n\n\nGameCell\n\n\nIOC 2015";

    private Texture               square;
    private boolean hold;
    public InfoScreen(SpaceCraft game){
        this.game = game;
        this.stage = new Stage(new StretchViewport(game.w, game.h));

    }

    @Override
    public void show() {

        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();
        bitmapFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        bitmapFont.setUseIntegerPositions(false);
        bitmapFont.setColor(Color.YELLOW);
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(float delta) {
        float dt = Gdx.graphics.getDeltaTime();

        cam3d.translate(0.0f, -dt * scrollSpeed, 0.0f);
        cam3d.update(false);



        GL20 gl = Gdx.graphics.getGL20();
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(cam3d.combined);
        spriteBatch.begin();
        bitmapFont.draw(spriteBatch, text, -cam3d.viewportWidth / 2f, -cam3d.viewportHeight, cam3d.viewportWidth, Align.center, true);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

        WIDTH = width;
        HEIGHT = height;

        //camara ortogonal
        float camWidth = 120f;
        float camHeight = camWidth * (float)HEIGHT / (float)WIDTH;
        cam2d = new OrthographicCamera(camWidth, camHeight);
        cam2d.position.set(camWidth / 1.0f, camHeight / 1.0f, 0.0f);
        cam2d.update();

        //se define la camara perspectiva
        cam3d = new PerspectiveCamera(90.0f, camWidth, camHeight);
        cam3d.translate(0.0f, -50.0f, 70.0f);
        cam3d.lookAt(0.0f, -15.0f, 10.0f);
        cam3d.update(true);


    }


    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        hold = true;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(hold) game.setScreen(game.optionsScreen); hold = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

}