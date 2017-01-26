package com.starfight.controlEnemy;

import com.badlogic.gdx.math.Intersector;
import com.starfight.gameObject.PlayerShip;
import com.starfight.gameObject.StaticAttack;
import com.starfight.gameObject.enemies.DropSpares;
import com.starfight.gameObject.enemies.EnemyV1;
import com.starfight.object.FightObject;

import java.util.ArrayList;

public class ControlEnemy {
    private ArrayList<EnemyV1> listEnemies;
    private ArrayList<DropSpares> listSpares;
    private PlayerShip player;
    private int gameWidth;
    private int gameHeight;

    public ControlEnemy(PlayerShip player,float gameWidth,float gameHeight){
        listSpares = new ArrayList<DropSpares>();
        int count = 3;
        listEnemies = new ArrayList<EnemyV1>();
        int nextPositX = (int)(gameWidth*0.10);
        for(int i = 0; i <count; i++){
            listEnemies.add(i, new EnemyV1(nextPositX,(int)gameHeight,(int)gameWidth,(int)gameHeight));
            EnemyV1 lastAdded = listEnemies.get(i);
            nextPositX = (int)(lastAdded.position.x + lastAdded.getOption("width") + (gameWidth*0.35));
        }
        this.gameWidth = (int)gameWidth;
        this.gameHeight = (int)gameHeight;
        this.player = player;
    }
    private void temporalityFunction(){//TODO: Delete this function after testing;
        int count = 3;
        int nextPositX = (int)(gameWidth*0.10);
        for(int i = 0; i <count; i++){
            listEnemies.add(i, new EnemyV1(nextPositX,this.gameHeight,this.gameWidth,this.gameHeight));
            EnemyV1 lastAdded = listEnemies.get(i);
            nextPositX = (int)(lastAdded.position.x + lastAdded.getOption("width") + (gameWidth*0.35));
        }
    }
    public ArrayList getListEnemies(){
        return this.listEnemies;
    }
    public ArrayList getListSpares(){
        return listSpares;
    }
    public void update(float delta){
        int size = listEnemies.size();
        int sizeSpar = listSpares.size();
        if(size == 0){//TODO: Delete this exception after testing
            temporalityFunction();
        }
        for (int i = 0;i < sizeSpar;i++){
            boolean removeSpar = false;
            listSpares.get(i).update(delta);
            DropSpares spar = listSpares.get(i);
            if(ControlCollision.collision(spar,player)){
                player.setScore(spar.getSum());
                removeSpar = true;
            }else if(spar.getBody().y + spar.getBody().radius < 0){
                removeSpar = true;
            }

            if(removeSpar){
                listSpares.remove(i);
                i--;
                sizeSpar--;
            }
        }
        for(int i = 0;i < size;i++){
            EnemyV1 enemy = listEnemies.get(i);
            boolean removeEnemy = false;
            enemy.update(delta);

            ArrayList<StaticAttack> listAttack = player.getListAttack();
            int sizeAttack = listAttack.size();
            for(int j = 0; j < sizeAttack;j++){
                if(ControlCollision.collision(enemy,listAttack.get(j))){
                    enemy.setDamage(listAttack.get(j).getToDamage());
                    if(enemy.getObjectStatus() == false){
                        ArrayList<DropSpares> dieEnemySpares = enemy.getListSpares();
                        for (DropSpares enemySpar:dieEnemySpares){
                            listSpares.add(enemySpar);
                        }
                        removeEnemy = true;
                    }
                    listAttack.remove(j);
                    break;
                }

            }
            if((enemy.position.y+enemy.getOption("height")) < 0){
                removeEnemy = true;
            }
            if(ControlCollision.collision(enemy,player)){
                player.setDamage(enemy.getToDamage());
                enemy.die();
                removeEnemy = true;

            }

            if(removeEnemy){
                listEnemies.remove(i);
                i--;
                size--;
            }
        }
    }
    static private class ControlCollision{

        static private boolean collision (FightObject obj1, FightObject obj2){
            if(obj1.position.y < obj2.position.y + obj2.getOption("height")&& obj1.position.y+obj1.getOption("height") > obj2.position.y){
                return Intersector.overlaps(obj1.getBody(),obj2.getBody());
            }else{
                return false;
            }

        }
        static private boolean collision (DropSpares obj1, FightObject obj2){
            if(obj1.getBody().y-obj1.getBody().radius < obj2.position.y+ obj2.getOption("height") && obj1.getBody().y+obj1.getBody().radius > obj2.position.y){
                return Intersector.overlaps(obj1.getBody(),obj2.getBody());
            }else{
                return false;
            }

        }

    }
}
