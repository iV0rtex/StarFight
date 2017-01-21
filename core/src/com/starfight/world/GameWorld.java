package com.starfight.world;

import com.starfight.controlEnemy.ControlEnemy;
import com.starfight.gameObject.PlayerShip;

public class GameWorld {
    private PlayerShip player;
    private ControlEnemy enemies;
    private GameState currentState;
    public GameWorld(int midPointX,float gameWidth,float gameHeight){
        player = new PlayerShip(midPointX,gameWidth,gameHeight);
        enemies = new ControlEnemy(player,gameWidth,gameHeight);
        currentState = GameState.GAME;
    }
    PlayerShip getPlayer(){
        return this.player;
    }
    ControlEnemy getEnemies(){
        return this.enemies;
    }
    public void update(float delta){
        if(verificationGameStatus()){
            player.update(delta);
            enemies.update(delta);
        }

    }
    public void touch(float x,int upOrDown){
        player.touch(x,upOrDown);
    }
    public boolean verificationGameStatus(){
        if(!player.getObjectStatus()){
            currentState = GameState.LOSS;
        }
        return currentState == GameState.GAME;
    }
    public enum GameState{
        GAME,PAUSE,LOSS,WIN;
    }
}
