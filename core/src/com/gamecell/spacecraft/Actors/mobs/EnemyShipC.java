package com.gamecell.spacecraft.Actors.mobs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.gamecell.spacecraft.Actors.GenEnemigo;
import com.gamecell.spacecraft.Actors.mobs.mobsAtack.DisparoEnemigoB;
import com.gamecell.spacecraft.Logics.LogicalGame;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * @Author Maria Vivo Yubero & Sergio Jimenez
 */
public class EnemyShipC extends GenEnemigo {


    private Texture texture,Tdisparo;
    private RepeatAction repeat;
    private MoveByAction move,moveR;
    private LogicalGame table;

    public EnemyShipC(SpaceCraft game, LogicalGame table,int salud,int reward,Texture texturaEnemigo, Texture texturaDisparo) {
        super.reward = 20;
        super.game = game;
        this.table = table;
        super.salud = salud;
        this.texture = texturaEnemigo;
        this.Tdisparo = texturaDisparo;
        this.setPosition(-100, MathUtils.random(100, 500));
        this.setSize(90, 90);
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

        if(this.getActions().size == 0 && estado == 1) {
            DelayAction delay = new DelayAction();
            delay.setDuration(MathUtils.random(0.f,4f));
            Disparar();
            estado = 2;
        }

        if(this.getActions().size == 0 && estado == 2) {

            moveR.setDuration(12f);
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
        int estile = MathUtils.random(0,2);
        switch (estile){
            case 0:
                DisparoEnemigoB disparo = new DisparoEnemigoB(game,this,table,Tdisparo,6f,0,0);
                table.addActor(disparo);
                table.colDisparosEnemigos.add(disparo);
                break;
            case 1:
                int AmountX = MathUtils.random(30,200);
                DisparoEnemigoB disparo2 = new DisparoEnemigoB(game,this,table,Tdisparo,8f,10,AmountX);
                table.addActor(disparo2);
                DisparoEnemigoB disparo3 = new DisparoEnemigoB(game,this,table,Tdisparo,8f,-10,-AmountX);
                table.addActor(disparo3);
                table.colDisparosEnemigos.add(disparo2);
                table.colDisparosEnemigos.add(disparo3);
                break;
            case 2:
                DisparoEnemigoB disparo4 = new DisparoEnemigoB(game,this,table,Tdisparo,10f,0,0);
                table.addActor(disparo4);
                table.colDisparosEnemigos.add(disparo4);
                int AmountX2 = MathUtils.random(30,200);
                DisparoEnemigoB disparo5 = new DisparoEnemigoB(game,this,table,Tdisparo,8f,10,AmountX2);
                table.addActor(disparo5);
                DisparoEnemigoB disparo6 = new DisparoEnemigoB(game,this,table,Tdisparo,8f,-10,-AmountX2);
                table.addActor(disparo6);
                table.colDisparosEnemigos.add(disparo5);
                table.colDisparosEnemigos.add(disparo6);
                break;
        }

    }
}
