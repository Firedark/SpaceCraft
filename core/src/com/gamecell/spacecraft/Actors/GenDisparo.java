package com.gamecell.spacecraft.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.gamecell.spacecraft.Actors.Nave;
import com.gamecell.spacecraft.SpaceCraft;

/**
 * Clase Disparo, crea un actor con una acción de movimiento ascendente.
 * @author Sergio Jimenez Cortes *
 */
public class GenDisparo extends Actor {
    public Rectangle rect;
    public int potencia;
    public GenEnemigo target;
}
