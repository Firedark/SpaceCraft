package com.gamecell.spacecraft.Actors.mobs.mobsAtack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gamecell.spacecraft.Actors.GenDisparoEnemigo;
import com.gamecell.spacecraft.Actors.GenEnemigo;
import com.gamecell.spacecraft.Logics.LogicalGame;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Created by fired on 08/12/2015.
 */
public class DisparoEnemigoB extends GenDisparoEnemigo {

    private SpaceCraft game;
    private Table table;
    private Texture texture;
    private MoveByAction move;
    private LogicalGame logical;
    public DisparoEnemigoB(SpaceCraft game, GenEnemigo enemigo, LogicalGame logical, Texture texture, float tiempo,int offsetX,int amountX){

        this.game = game;
        this.logical = logical;
        this.texture = texture;
        setBounds(enemigo.getX()+(enemigo.getWidth()/2) + offsetX,enemigo.getY() -32,texture.getWidth(),texture.getHeight());
        move = new MoveByAction();
        move.setAmountY(-800);
        move.setAmountX(amountX);
        move.setDuration(tiempo);
        this.addAction(move);
        super.rect = new Rectangle(getX(),getY(),getWidth(),getHeight());

    }

    @Override
    public void deleteDisparo(){
        super.deleteDisparo();
        rect = null;
        logical.removeActor(this);
        logical.colDisparosEnemigos.remove(this);
    }


    @Override
    public void draw(Batch batch,float parentAlpha){
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth()*2, getHeight()*2);
    }

}
