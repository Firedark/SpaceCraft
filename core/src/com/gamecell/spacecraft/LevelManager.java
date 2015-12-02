package com.gamecell.spacecraft;

import com.badlogic.gdx.graphics.Texture;
import com.gamecell.spacecraft.Actors.Enemigo;
import com.gamecell.spacecraft.Actors.Nave;
import com.gamecell.spacecraft.Actors.PowerUps;
import com.gamecell.spacecraft.Logics.LogicalGame;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


/**
 * Created by Firedark on 02/12/2015.
 */
public class LevelManager {

    private SpaceCraft game;
    private LogicalGame logical;
    private int second;
    private Document doc;
    private Nave nave;

    public LevelManager(SpaceCraft game, LogicalGame logical, int second,Nave nave) {
        this.game = game;
        this.logical = logical;
        this.second = second;
        this.nave = nave;
    }

    public void loadLevel(String nivel){
        try {
            File fXmlFile = new File(nivel);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




    public void updateSecond(int second){

        NodeList nList = doc.getElementsByTagName("time");


        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node time = nList.item(temp);

            if (time.getNodeType() == Node.ELEMENT_NODE) {

                Element eTime = (Element) time;



                if(Integer.parseInt(eTime.getAttribute("sec")) == second){


                    NodeList mobs = ((Element) time).getElementsByTagName("mob");

                    for (int i = 0; i < mobs.getLength(); i++){

                        Node mob = mobs.item(i);

                        Element eMob = (Element) mob;
                        SpawnEnemy(eMob.getTextContent());
                    }

                    break;



                }


            }

        }




    }


    private void SpawnEnemy(String mob) {
        if(mob.equals("Meteor")){
            Enemigo enemigo = new Enemigo(game);
            //Lo añadimos al juego
            logical.addActor(enemigo);
            //Lo añadimos a la Array de enemigos
            logical.colEnemigo.add(enemigo);
        }

        if(mob.equals("Life")){
            PowerUps   power = new PowerUps(game,(Texture) game.images.manager.get("Images/poweruplife.png"),nave,logical,0);
            logical.colPowerUps.add(power);
            logical.addActor(power);
        }


    }





}