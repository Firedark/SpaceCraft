package com.gamecell.spacecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Firedark on 22/10/2015.
 */
public class MyPreferences {

    private Preferences preferences;
    public SpaceCraft game;

    public MyPreferences(SpaceCraft game){
        this.game = game;
        }

    public void GetPreferences(){
      preferences  = Gdx.app.getPreferences("spacecraft");
    }

    // Aqui habrá que hacer métodos para guardar y traerse los datos.


}


