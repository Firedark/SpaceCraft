package com.gamecell.spacecraft.Actors.mobs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gamecell.spacecraft.Actors.GenEnemigo;
import com.gamecell.spacecraft.Logics.LogicalGame;
import com.gamecell.spacecraft.SpaceCraft;

import com.badlogic.gdx.math.Rectangle;


/**
 * Clase Meteor, aqui esta toda la funcionalidad que tiene el enemigo. Extiende de Actor.
 *
 * @author Maria Vivo Yubero
 */
public class Meteor extends GenEnemigo {
    //Variables
    private TextureRegion asteroideTR;
    private Texture asteroideTextura;
    private MoveByAction accion;
    private RotateByAction rotateAction ;
    private ParallelAction paralel;
    private LogicalGame table;

    /**
     * Constructor de la clase Meteor
     * @param game de la clase principal
     */
    public Meteor(SpaceCraft game, int salud,int reward,LogicalGame table) {
        super.game = game;
        super.reward = reward;
        this.table = table;

        //Indicamos que imagen es la del meteorito(Grande o pequeña dependiendo de su salud)
        if(salud > 1) {
            asteroideTextura = game.images.manager.get("Images/meteorBig.png");
        }else{
            asteroideTextura = game.images.manager.get("Images/meteorSmall.png");
        }
        asteroideTR = new TextureRegion(asteroideTextura);
        //x,y,ancho y alto. Aparece fuera de la pantalla para que el usuario no lo vea
        this.setBounds(MathUtils.random(10, game.w + -10), MathUtils.random(game.h + 100, game.h + 500),
                asteroideTextura.getWidth(), asteroideTextura.getHeight());
        this.setOrigin(asteroideTextura.getWidth()/2, asteroideTextura.getHeight()/2);

        //El enemigo está en la misma Z que el actor principal
        this.setZIndex(50000);
        super.rect = new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
        //Creación de Acciones del enemigo.
        createActionsMeteor();
        //Estados del enemigo 0 = destruido, 1 = destruyendose, 2 = activo
        super.estado = 1;
        super.salud = salud;
    }

    /**
     * Actions del Meteor
     */
    private void createActionsMeteor() {

        //////////////////////////////////////
       // Caída del asteroide por el eje Y //
       // y rotación del mismo            //
      /////////////////////////////////////

        //Instanciamos la acción que realizará el enemigo
        accion = new MoveByAction();
        rotateAction = new RotateByAction();
        paralel = new ParallelAction();

        accion = new MoveByAction();
        accion.setDuration(18f);
        accion.setAmountY(-2000);
        rotateAction = new RotateByAction();
        rotateAction.setDuration(40f);
        rotateAction.setAmount(MathUtils.random(-360, 360));
        paralel = new ParallelAction();
        paralel.addAction(accion);
        paralel.addAction(rotateAction);

        this.addAction(paralel);

    }
    /**
     * Metodo para pintar el objeto en la escena
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(asteroideTR, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
                getHeight(), getScaleX(), getScaleY(), getRotation());

    }


    @Override
    public void DeadEnemy() {
        super.DeadEnemy();
        table.colCollisionables.remove(this);
        table.colShootables.remove(this);
        table.removeActor(this);

    }

}
