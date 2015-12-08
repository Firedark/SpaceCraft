package com.gamecell.spacecraft.Actors.mobs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
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
    private Texture imagenEnemigo;
    MoveByAction accion;
    private final float ancho = 50;
    private final float alto = 50;
    private LogicalGame table;
    /**
     * Constructor de la clase Meteor
     * @param game de la clase principal
     */
    public Meteor(SpaceCraft game, int salud,int reward,LogicalGame table) {

        super.game = game;
        super.reward = reward;
        this.table = table;
        //Indicamos que imagen es la del enemigo
        imagenEnemigo = game.images.manager.get("Images/asteroide.png", Texture.class);
        //x,y,ancho y alto. Aparece fuera de la pantalla para que el usuario no lo vea
        this.setBounds(MathUtils.random(0, game.w - ancho), game.h + MathUtils.random(100,200), ancho, alto);
        //El enemigo está en la misma Z que el actor principal
        this.setZIndex(50000);
        super.rect = new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
        //Creación de Acciones del enemigo.
        createActionsEnemigo();
        //Estados del enemigo 0 = destruido, 1 = destruyendose, 2 = activo
        super.estado = 1;
        super.salud = salud;

    }

    /**
     * Actions del Meteor
     */
    private void createActionsEnemigo() {

        /////////////////////////////////////
       // Caída del enemigo por el eje Y  //
      /////////////////////////////////////

        //Instanciamos la acción que realizará el enemigo
        accion = new MoveByAction();
        //Lo que tarda en pasar por la pantalla sobre el eje Y
        accion.setDuration(20f);
        //El tamaño del eje Y
        accion.setAmountY(-2000);
        //Añadimos las acciones
        this.addAction(accion);

        ///////////////////////////
       // Disparo del enemigo   //
      ///////////////////////////

        /////////////////////////////
       // Movimiento del enemigo  //
      /////////////////////////////

    }
    /**
     * Metodo para pintar el objeto en la escena
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
        batch.draw(imagenEnemigo, getX(), getY(),ancho,alto);

    };


    @Override
    public void DeadEnemy() {
        super.DeadEnemy();
        table.colCollisionables.remove(this);
        table.colShootables.remove(this);
        table.removeActor(this);

    };
}
