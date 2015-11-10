package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Created by Firedark on 08/11/2015.
 */

public class SplashBlack extends Actor {
    SpaceCraft game;
    //Atributos de las acciones alpha, Delay y la acción sequencia.
    AlphaAction alpha,alpha2;
    //La acción delay es una simple acción de espera, no hace nada.
    DelayAction delay;
    //Insertaremos nuestras acciónes en otra accion secuencia que nos ejecutará las cosas en orden.
    SequenceAction sequence;
    //Textura del Rectangulo, Region...
    Texture BlackRectangle;
    TextureRegion br;
    //Imagen, éste es el objeto que nos permite modificar su color alpha.
    Image ImgBR;
    //Nos servirá para saber cuando a terminado la acción.
    boolean fin;

    public SplashBlack(SpaceCraft game){

        this.game = game;
        //Instanciamos Secuencia
        sequence = new SequenceAction();
        //Creamos las acciones alpha 1 y 2, como veís solo le damos la cantidad de alpha que queremos
        //alcanzar en 2 segundos de duracion.
        alpha = new AlphaAction();
        //Alpha a 0, Opaco.
        alpha.setAlpha(0f);
        alpha.setDuration(2f);

        alpha2 = new AlphaAction();
        //Alpha a 1, Transparente.
        alpha2.setAlpha(1f);
        alpha2.setDuration(2f);
        //La accion de espera de 3 segundos.
        delay = new DelayAction();
        delay.setDuration(3f);
        //Añadimos a la sequencia las tres acciones.
        sequence.addAction(alpha);
        sequence.addAction(delay);
        sequence.addAction(alpha2);
        //Y finalmente añadimos al actor la sequencia.
        addAction(sequence);
        //Cargamos Textura y Region.
        BlackRectangle = new Texture(Gdx.files.internal("Images/black.jpg"));
        br = new TextureRegion(BlackRectangle,0,0,800,480);
        //Creamos el objeto Imagen y le pasamos como parametro la region de nuestra textura.
        ImgBR = new Image(br);
        //Colocamos a 1 el valor alpha de nuestra imagen, por lo tanto la hacemos transparente.
        ImgBR.getColor().a = 1;
    }

    @Override
    public void draw(Batch batch,float parentAlpha){
        //Continuamente le cargamos el valor de nuestro actor alpha, al valor de la imagen alpha.
        ImgBR.getColor().a = getColor().a;
        //Así se dibuja una imagen.
        ImgBR.draw(batch, parentAlpha);
        //Las acciones una vez finalizadas se eliminan no? Ésto nos ayuda a que cuando no queden acciones
        //En la secuencia habrá terminado.
        //Mientras tanto aprovechamos para cargar los assets en memoria.
        game.images.manager.update();
        if(this.getActions().size == 0){
            //Un pequeño ejemplo para ir de una pantalla a otra, al terminar nuestra acción nos cambiará
            //de pantalla.
            game.setScreen(game.startScreen);
            this.remove();
        }
    }

}
