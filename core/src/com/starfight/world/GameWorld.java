package com.starfight.world;

import com.starfight.controlEnemy.ControlEnemy;
import com.starfight.gameObject.PlayerShip;

public class GameWorld {
    private PlayerShip player;
    private ControlEnemy enemies;
    private GameState currentState;
    private float ScalarGameSpeed = 1;
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
            player.update(delta,ScalarGameSpeed);
            enemies.update(delta,ScalarGameSpeed);
        }

    }
    public void touch(float x,int upOrDown){
        player.touch(x,upOrDown);
    }
    public boolean verificationGameStatus(){
        if(!player.getObjectStatus()){
            currentState = GameState.LOSS;
        }
        return currentState == GameState.GAME || currentState == GameState.SLOW;
    }
    public enum GameState{
        GAME,PAUSE,LOSS,WIN,SLOW;
    }
    public boolean isSlow(){
        return currentState == GameState.SLOW;
    }
    public void setSlowGame(){
        ScalarGameSpeed = .1f;
        currentState = GameState.SLOW;
    }
    public void setNormalGame(){
        ScalarGameSpeed = 1;
        currentState = GameState.GAME;
    }
}
