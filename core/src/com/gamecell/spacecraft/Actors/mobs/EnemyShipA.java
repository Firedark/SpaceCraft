package com.gamecell.spacecraft.Actors.mobs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gamecell.spacecraft.Actors.GenEnemigo;
import com.gamecell.spacecraft.Actors.mobs.mobsAtack.DisparoEnemigoA;
import com.gamecell.spacecraft.Logics.LogicalGame;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Created by fired on 07/12/2015.
 */
public class EnemyShipA extends GenEnemigo {

    private Texture texture,Tdisparo;
    private RepeatAction repeat;
    private MoveByAction move,moveR;
    private LogicalGame table;


public EnemyShipA(SpaceCraft game, LogicalGame table,int salud,int reward,Texture texturaEnemigo, Texture texturaDisparo){
    super.reward = reward;
    super.game = game;
    this.table = table;
    super.salud = salud;
    this.texture = texturaEnemigo;
    this.Tdisparo = texturaDisparo;
    this.setPosition(-100,MathUtils.random(100,500));
    this.setSize(64, 32);
    super.estado = 1;
    super.salud = salud;
    super.rect = new Rectangle(getX(), getY(), getWidth(), getHeight());
    move = new MoveByAction();
    repeat = new RepeatAction();

    move.setDuration(5f);
    move.setAmountX(600);
    move.setAmountY(MathUtils.random(0, 400));
    this.addAction(move);

}

    @Override
    public void draw (Batch batch,float parentAlpha) {
        super.draw(batch, parentAlpha);
        moveR = new MoveByAction();
        if(this.getActions().size == 0) {
           moveR.setDuration(6f);
            if(this.getX() > 300){
                moveR.setAmountX(-500);
            }else{
                moveR.setAmountX(500);
            }

            if(this.getY() > 100 ) {
                moveR.setAmountY(MathUtils.random(-400, 0));
            }else{
                moveR.setAmountY(MathUtils.random(0,400));
            }
            this.addAction(moveR);
        }
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        updateDisparo(1500);
    }


    @Override
    public void DeadEnemy() {
        super.DeadEnemy();
        table.colCollisionables.remove(this);
        table.colShootables.remove(this);
        table.removeActor(this);


    };

    @Override
    public void Disparar(){
        DisparoEnemigoA disparo = new DisparoEnemigoA(game,this,table,Tdisparo,6f);
        table.addActor(disparo);
        table.colDisparosEnemigos.add(disparo);
    }

}
