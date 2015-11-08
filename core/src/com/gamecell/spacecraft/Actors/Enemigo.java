package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.gamecell.spacecraft.SpaceCraft;

import com.badlogic.gdx.math.Rectangle;


/**
 * Created by María Vivo Yubero on 08/11/2015.
 */
public class Enemigo extends Actor {

    //Variables
    private Texture imagenEnemigo;
    private SpaceCraft game;
    MoveByAction accion;
    private final float ancho = 50;
    private final float alto = 50;

    //Constructor del enemigo.
    public Enemigo(SpaceCraft game) {
        this.game = game;
        setImagenEnemigo(game.images.manager.get("Images/asteroide.png",Texture.class));
        //x,y,ancho y alto. Aparece fuera de la pantalla para que el usuario no lo vea
        this.setBounds(MathUtils.random(0, game.w - ancho), game.h + 100, ancho, alto);
        //El enemigo está en la misma Z que el actor principal
        this.setZIndex(50000);

        //Creación de Acciones del enemigo.
        createActionsEnemigo();
    }

    private void createActionsEnemigo() {
        /////////////////////////////////////
        // Caída del enemigo por el eje Y //
        ///////////////////////////////////

        //Instanciamos la acción que realizará el enemigo
        accion = new MoveByAction();
        //Lo que tarda en pasar por la pantalla sobre el eje Y
        accion.setDuration(100f);
        //El tamaño del eje Y
        accion.setAmountY(-10000);
        //Añadimos las acciones
        this.addAction(accion);

        ///////////////////////////
        // Disparo del enemigo //
        /////////////////////////

        /////////////////////////////
        // Movimiento del enemigo //
        ///////////////////////////
    }

    /**
     * Método para la colsión del enemigo con el actor principal
     * @param nave
     * @return
     */
    public boolean collisionEnemigo(Nave nave){
        Rectangle rectNave = new Rectangle(nave.getX(),nave.getY(),nave.getWidth(),nave.getHeight());
        Rectangle rectEnemigo = new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());

        return rectNave.overlaps(rectEnemigo);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(getImagenEnemigo(), getX(), getY(),ancho,alto);
    }

    public Texture getImagenEnemigo() {
        return imagenEnemigo;
    }

    public void setImagenEnemigo(Texture imagenEnemigo) {
        this.imagenEnemigo = imagenEnemigo;
    }
}
