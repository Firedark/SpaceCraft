package com.gamecell.spacecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.gamecell.spacecraft.Actors.mobs.EnemyShipA;
import com.gamecell.spacecraft.Actors.mobs.EnemyShipB;
import com.gamecell.spacecraft.Actors.mobs.EnemyShipC;
import com.gamecell.spacecraft.Actors.mobs.EnemyShipD;
import com.gamecell.spacecraft.Actors.mobs.Meteor;
import com.gamecell.spacecraft.Actors.Nave;
import com.gamecell.spacecraft.Actors.PowerUps;
import com.gamecell.spacecraft.Logics.LogicalGame;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Tratamiento de los diferentes niveles del juego
 * @author Josué Javier
 */
public class LevelManager {
    private SpaceCraft game;
    private LogicalGame logical;
    private int second;
    private int maxSecond;
    private Nave nave;
    private Array<XmlReader.Element> items;
    public int level;
    /**
     * Constructor de la clase.
     * @param game de la clase principal
     * @param logical Clase con la logica del juego.
     * @param second Segundo en el que nos encontramos.
     * @param nave Nave jugador.
     * @param level Nivel actual.
     */
    public LevelManager(SpaceCraft game, LogicalGame logical, int second, Nave nave, int level) {
        this.game = game;
        this.logical = logical;
        this.second = second;
        this.nave = nave;
        this.level = level;
    }

    /**
     * Carga los niveles mediante archivos xml
     */
    public void loadLevel(){
        try {
            String fileLevel = "Levels/" + this.level + ".xml";
            XmlReader reader = new XmlReader();

            if (Gdx.files.internal(fileLevel).exists()) {
                XmlReader.Element root = reader.parse(Gdx.files.internal(fileLevel));
                maxSecond = Integer.parseInt(root.getAttribute("max_sec"));
                items = root.getChildrenByName("time");
            } else {
                //Fin partida
                game.preferences.removeScore();
                logical.remove();
                game.setScreen(game.congratulationScreen);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Cada segundo que pasa en el juego, va mostrando los diferentes elementos del xml predefinidos
     * @param second
     */
    public void updateSecond(int second){
        try {
            if (second > maxSecond) {
                this.level++;
                this.loadLevel();
                logical.segundos = 0;

                //Cambiamos etiqueta nivel
                Label newLevelLbl = logical.findActor("actorLevel");
                newLevelLbl.setText("Level " + this.level);
            } else {
                for (XmlReader.Element item : items) {
                    if (Integer.parseInt(item.getAttribute("sec")) == second) {
                        for (XmlReader.Element mob : item.getChildrenByName("mob")) {
                            SpawnEnemy(mob.getText());
                        }
                    }
                }
            }
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Añade los elementos al escenario
     * @param mob
     */
    private void SpawnEnemy(String mob) {
        if(mob.equals("Meteor")){
            Meteor meteor = new Meteor(game, MathUtils.random(1, 2),10,logical);
            //Lo añadimos al juego
            logical.addActor(meteor);
            logical.colShootables.add(meteor);
            logical.colCollisionables.add(meteor);
        }

        if(mob.equals("EnemyA")){
            EnemyShipA enemy = new EnemyShipA(game,logical,1,15,(Texture) game.images.manager.get("Images/enemyship.png"),(Texture) game.images.manager.get("Images/disparoEA.png"));
            //Lo añadimos al juego
            logical.addActor(enemy);
            logical.colShootables.add(enemy);
            logical.colCollisionables.add(enemy);
        }

        if(mob.equals("Life")){
            PowerUps   power = new PowerUps(game,(Texture) game.images.manager.get("Images/poweruplife.png"),nave,logical,0);
            logical.colPowerUps.add(power);
            logical.addActor(power);
        }

        if(mob.equals("Power")){
            PowerUps   power = new PowerUps(game,(Texture) game.images.manager.get("Images/poweruppower.png"),nave,logical,1);
            logical.colPowerUps.add(power);
            logical.addActor(power);
        }

        if(mob.equals("Shield")){
            PowerUps   power = new PowerUps(game,(Texture) game.images.manager.get("Images/powerupshield.png"),nave,logical,2);
            logical.colPowerUps.add(power);
            logical.addActor(power);
        }

        if(mob.equals("EnemyB")){
            EnemyShipB enemy = new EnemyShipB(game,logical,2,20,(Texture) game.images.manager.get("Images/enemyB.png"),(Texture) game.images.manager.get("Images/disparoCE.png"));
            logical.addActor(enemy);
            logical.colShootables.add(enemy);
            logical.colCollisionables.add(enemy);
        }

        if(mob.equals("EnemyC")){
            EnemyShipC enemy = new EnemyShipC(game,logical,2,20,(Texture) game.images.manager.get("Images/shipYellow_manned.png"),
                    (Texture) game.images.manager.get("Images/disparoB.png"));
            logical.addActor(enemy);
            logical.colShootables.add(enemy);
            logical.colCollisionables.add(enemy);
        }

        if(mob.equals("EnemyD")){
            EnemyShipD enemy = new EnemyShipD(game,logical,1,20,(Texture) game.images.manager.get("Images/enemyUFO.png"),
                    (Texture) game.images.manager.get("Images/disparoEA.png"));
            logical.addActor(enemy);
            logical.colShootables.add(enemy);
            logical.colCollisionables.add(enemy);
        }
    }
}