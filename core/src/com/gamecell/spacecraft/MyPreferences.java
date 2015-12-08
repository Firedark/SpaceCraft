package com.gamecell.spacecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
* Clase MyPreferences, para datos guardados de manera permanente.
* @author Sergio Jimenez Cortes*
*/
public class MyPreferences {

    private Preferences preferences;
    public SpaceCraft game;
    public int score;

    public MyPreferences(SpaceCraft game){
        this.game = game;
        }

    public void GetPreferences(){
      preferences  = Gdx.app.getPreferences("spacecraft");
    }

    // Aqui habrá que hacer métodos para guardar y traerse los datos.


}


