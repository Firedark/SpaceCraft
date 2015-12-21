package com.gamecell.spacecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
* Clase MyPreferences, para datos guardados de manera permanente.
* @author Sergio, Jaume*
*/
public class MyPreferences {

    private Preferences preferences;
    public SpaceCraft game;
    public int score;

    private static final String PREF_VOLUME = "volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_FX_ENABLED = "fx.enabled";
    private static final String PREFS_NAME = "spacecraft";
    private static final String PREF_SCORE = "score";

    public MyPreferences(SpaceCraft game){
        this.game = game;
    }

    public Preferences getPreferences(){
        return Gdx.app.getPreferences(PREFS_NAME);
    }

    // Aqui habrá que hacer métodos para guardar y traerse los datos.

    // Music
    public void setMusicEnabled(){
        getPreferences().putBoolean(PREF_MUSIC_ENABLED, true);
        getPreferences().flush();
    }

    public void setMusicDisabled(){
        getPreferences().putBoolean(PREF_MUSIC_ENABLED, false);
        getPreferences().flush();
    }

    public boolean isMusicEnabled(){
        return getPreferences().getBoolean(PREF_MUSIC_ENABLED, true);
    }

    // Sound effects
    public void setFXEnabled(){
        getPreferences().putBoolean(PREF_FX_ENABLED, true);
        getPreferences().flush();
    }

    public void setFXDisabled(){
        getPreferences().putBoolean(PREF_FX_ENABLED, false);
        getPreferences().flush();
    }
    public boolean isFXEnabled(){
        return getPreferences().getBoolean(PREF_FX_ENABLED, true);
    }

    // Score
    public void setScore() {
        getPreferences().putInteger(PREF_SCORE, score);
        getPreferences().flush();
    }
    public int getScore(){
        return getPreferences().getInteger(PREF_SCORE);
    }

    public void removeScore() {
        score = 0;
        getPreferences().remove(PREF_SCORE);
    }
}


