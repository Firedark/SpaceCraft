package com.gamecell.spacecraft.Logics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gamecell.spacecraft.Screens.GameScreen;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Created by fired on 18/12/2015.
 */
public class LogicalPause extends Table {

    private SpaceCraft game;
    private GameScreen screen;


    public LogicalPause (SpaceCraft game,GameScreen screen){
        this.game = game;
        this.screen = screen;
    }


    @Override
    public void act(float delta){

    }



}
