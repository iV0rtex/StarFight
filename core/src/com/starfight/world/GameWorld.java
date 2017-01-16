package com.starfight.world;

import com.starfight.controlEnemy.ControlEnemy;
import com.starfight.gameObject.PlayerShip;

public class GameWorld {
    private PlayerShip player;
    private ControlEnemy enemies;
    public GameWorld(int midPointX){
        player = new PlayerShip(midPointX);
        enemies = new ControlEnemy(player);
    }
    PlayerShip getPlayer(){
        return this.player;
    }
    ControlEnemy getEnemies(){
        return this.enemies;
    }
    public void update(float delta){
        player.update(delta);
        enemies.update(delta);
    }
    public void touch(int x,int upOrDown){
        player.touch(x,upOrDown);
    }
}
