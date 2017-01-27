package com.starfight.world;

import com.starfight.assets.AssetsLoader;
import com.starfight.controlEnemy.ControlEnemy;
import com.starfight.gameObject.PlayerShip;
import com.starfight.ui.MenuPause;
import com.starfight.ui.menuSlowGame;

public class GameWorld {
    private PlayerShip player;
    private ControlEnemy enemies;
    private menuSlowGame menuSlow;
    private MenuPause menuPause;
    private GameState currentState;
    private float ScalarGameSpeed = 1;
    public GameWorld(int midPointX, float gameWidth, float gameHeight, AssetsLoader assets){
        player = new PlayerShip(midPointX,gameWidth,gameHeight);
        enemies = new ControlEnemy(player,gameWidth,gameHeight);
        currentState = GameState.GAME;
        menuSlow = new menuSlowGame(gameWidth,gameHeight,assets);
        menuPause = new MenuPause(gameWidth,gameHeight,assets);
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
    public menuSlowGame getMenuSlow(){
        return menuSlow;
    }
    public MenuPause getMenuPause(){
        return menuPause;
    }
    public void touch(float x,int upOrDown){
        player.touch(x,upOrDown);
    }
    public void touchSlow(float x,float y,int upOrDown){
        if(!menuSlow.onClick(x,y,upOrDown,this)){
            setNormalGame();
            touch(x,upOrDown);
        }
    }
    public void touchPause(float x,float y,int upOrDown){
        menuPause.onClick(x,y,upOrDown,this);
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
    public boolean isNormalGame(){
        return currentState == GameState.GAME;
    }
    public boolean isPause(){
        return currentState == GameState.PAUSE;
    }
    public void setPause(){
        currentState = GameState.PAUSE;
    }
}
