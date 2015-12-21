package com.gamecell.spacecraft;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.gamecell.spacecraft.Actors.FallenActor;

/**
 * Created by Firedark on 02/12/2015.
 */
public class DinamicBackground {
    SpaceCraft game;
    Table table;

    private long TimeSpawnerFallen,TimeSpawnerFallenSol,TimeSpawnerFallenLuna,TimeSpawnerFallenCohete,
            TimeSpawnerFallenSatelite;

    public DinamicBackground(SpaceCraft game,Table logica){
        this.table = logica;
        this.game = game;
        TimeSpawnerFallen = TimeUtils.millis();
        TimeSpawnerFallenSol = TimeUtils.millis();
        TimeSpawnerFallenLuna = TimeUtils.millis();
        TimeSpawnerFallenCohete = TimeUtils.millis();
        TimeSpawnerFallenSatelite = TimeUtils.millis();
    }

    public void checkMillis(){

        //Estrellas
        if(TimeUtils.millis() - TimeSpawnerFallen > 300){
            spawnEstrellaActor();
            TimeSpawnerFallen = TimeUtils.millis();
        }

        //Soles

        if(TimeUtils.millis() - TimeSpawnerFallenSol > MathUtils.random(50000, 120000)) {
            spawnSolActor();
            TimeSpawnerFallenSol = TimeUtils.millis();
        }

        //Lunas
        if(TimeUtils.millis() - TimeSpawnerFallenLuna > MathUtils.random(30000, 90000)) {
            spawnLunaActor();
            TimeSpawnerFallenLuna = TimeUtils.millis();
        }

        if(TimeUtils.millis() - TimeSpawnerFallenCohete > MathUtils.random(80000, 150000)) {
            spawnCoheteActor();
            TimeSpawnerFallenCohete = TimeUtils.millis();
        }

        if(TimeUtils.millis() - TimeSpawnerFallenSatelite > MathUtils.random(60000, 130000)) {
            spawnSateliteActor();
            TimeSpawnerFallenSatelite = TimeUtils.millis();
        }
    }


    private void spawnEstrellaActor() {
        FallenActor fallenActor = new FallenActor(game,(Texture) game.images.manager.get("Images/estrella.png"),table);
        table.addActor(fallenActor);
    }

    private void spawnSolActor() {
        FallenActor fallenActor = new FallenActor(game,(Texture) game.images.manager.get("Images/sol.png"),table);
        table.addActor(fallenActor);
    }

    private void spawnLunaActor() {
        FallenActor fallenActor = new FallenActor(game,(Texture) game.images.manager.get("Images/luna.png"),table);
        table.addActor(fallenActor);
    }

    private void spawnSateliteActor() {
        FallenActor fallenActor = new FallenActor(game,(Texture) game.images.manager.get("Images/satelite.png"),table);
        table.addActor(fallenActor);
    }

    private void spawnCoheteActor() {
        FallenActor fallenActor = new FallenActor(game,(Texture) game.images.manager.get("Images/cohete.png"),table);
        table.addActor(fallenActor);
    }

}
