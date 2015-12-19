package com.gamecell.spacecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.gamecell.spacecraft.Actors.mobs.EnemyShipA;
import com.gamecell.spacecraft.Actors.mobs.EnemyShipB;
import com.gamecell.spacecraft.Actors.mobs.Meteor;
import com.gamecell.spacecraft.Actors.Nave;
import com.gamecell.spacecraft.Actors.PowerUps;
import com.gamecell.spacecraft.Logics.LogicalGame;

/**
 * Created by Firedark on 02/12/2015.
 */
public class LevelManager {
    private SpaceCraft game;
    private LogicalGame logical;
    private int second;
    private int maxSecond;
    private Nave nave;
    private Array<XmlReader.Element> items;
    public int level;

    public LevelManager(SpaceCraft game, LogicalGame logical, int second, Nave nave, int level) {
        this.game = game;
        this.logical = logical;
        this.second = second;
        this.nave = nave;
        this.level = level;
    }

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
                logical.remove();
                game.setScreen(game.startScreen);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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

    private void SpawnEnemy(String mob) {
        if(mob.equals("Meteor")){
            Meteor meteor = new Meteor(game,1,5,logical);
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
            EnemyShipB enemy = new EnemyShipB(game,logical,2,30,(Texture) game.images.manager.get("Images/enemyB.png"),(Texture) game.images.manager.get("Images/disparoCE.png"));
            logical.addActor(enemy);
            logical.colShootables.add(enemy);
            logical.colCollisionables.add(enemy);
        }
    }
}