package com.starfight.controlEnemy;

import com.badlogic.gdx.math.Intersector;
import com.starfight.gameObject.PlayerShip;
import com.starfight.gameObject.StaticAttack;
import com.starfight.gameObject.enemies.EnemyV1;
import com.starfight.object.FhightObject;

import java.util.ArrayList;

public class ControlEnemy {
    private ArrayList<EnemyV1> listEnemies;
    private PlayerShip player;
    public ControlEnemy(PlayerShip player){
        int count = 3;
        listEnemies = new ArrayList<EnemyV1>();
        int nextPositX = 10;
        for(int i = 0; i <count; i++){
            listEnemies.add(i, new EnemyV1(nextPositX,190));
            nextPositX += 30;
        }
        this.player = player;
    }
    private void temporalityFunction(){//TODO: Delete this function after testing;
        int count = 3;
        int nextPositX = 10;
        for(int i = 0; i <count; i++){
            listEnemies.add(i, new EnemyV1(nextPositX,190));
            nextPositX += 30;
        }
    }
    public ArrayList getListEnemies(){
        return this.listEnemies;
    }
    public void update(float delta){
        int size = listEnemies.size();
        if(size == 0){//TODO: Delete this exception after testing
            temporalityFunction();
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
                        removeEnemy = true;
                    }
                    listAttack.remove(j);
                    //j--;
                    //sizeAttack--;
                    break;
                }
            }
            if((enemy.position.y+enemy.getOption("height")) < 0){
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
        static private boolean collision (FhightObject obj1, FhightObject obj2){
            return Intersector.overlaps(obj1.body,obj2.body);
        }

    }
}
