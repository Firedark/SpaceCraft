package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.gamecell.spacecraft.SpaceCraft;

import com.badlogic.gdx.math.Rectangle;


/**
 * Clase Enemigo, aqui esta toda la funcionalidad que tiene el enemigo. Extiende de Actor.
 *
 * @author Maria Vivo Yubero
 */
public class Enemigo extends Actor {

    //Variables
    private Texture imagenEnemigo;
    private SpaceCraft game;
    MoveByAction accion;
    private final float ancho = 50;
    private final float alto = 50;
    public Rectangle rect;
    public int estado;

    /**
     * Constructor de la clase Enemigo
     * @param game de la clase principal
     */
    public Enemigo(SpaceCraft game) {

        this.game = game;
        //Indicamos que imagen es la del enemigo
        setImagenEnemigo(game.images.manager.get("Images/asteroide.png",Texture.class));
        //x,y,ancho y alto. Aparece fuera de la pantalla para que el usuario no lo vea
        this.setBounds(MathUtils.random(0, game.w - ancho), game.h + 100, ancho, alto);
        //El enemigo está en la misma Z que el actor principal
        this.setZIndex(50000);
        rect = new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
        //Creación de Acciones del enemigo.
        createActionsEnemigo();
        //Estados del enemigo 0 = destruido, 1 = destruyendose, 2 = activo
        estado = 1;

    }

    /**
     * Actions del Enemigo
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
     * Metodo para la colision del enemigo con alguna parte del actor principal
     * el mismo cuerpo o el disparo que lanza
     * @param parteNave
     * @return
     */
    public boolean collisionEnemigo(Rectangle parteNave){

        //Rectangle rectNave = new Rectangle(nave.getX(),nave.getY(),nave.getWidth(),nave.getHeight());
        return parteNave.overlaps(rect);

    }

    /**
     * Metodo para pintar el objeto en la escena
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha){

        rect = new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
        batch.draw(getImagenEnemigo(), getX(), getY(),ancho,alto);

    }

    /**
     * Devuelve la Imagen del Enemigo
     * @return imagenEnemigo
     */
    public Texture getImagenEnemigo() {

        return imagenEnemigo;

    }

    /**
     * Settea la imagen del enemigo
     * @param imagenEnemigo la imagen nueva
     */
    public void setImagenEnemigo(Texture imagenEnemigo) {

        this.imagenEnemigo = imagenEnemigo;

    }

    /**
     * Metodo para eliminar el enemigo destruido de la escena
     */
    public void DeleteEnemigo(){

        rect = null;
        //Destruido
        estado = 0;

    }
}
