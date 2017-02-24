package com.starfight.gameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.starfight.assets.AssetsLoader;

import java.util.Hashtable;
import java.util.Map;

public class PlayerShipMenu{
    private Texture texture;
    public Vector2 position;
    private Map<String,Integer> option;
    private Vector2 staticVelocity;
    private boolean stopStatus = false;
    private int midPointX, midPointY;
    private Vector2 positNext;
    public PlayerShipMenu(Texture textureShip,int midPointX,int midPointY,float positY,float gameWidth,float gameHeight){
        texture = textureShip;
        this.midPointY = midPointY;
        this.midPointX = midPointX;
        option = new Hashtable<String, Integer>();
        option.put("width",(int)(gameWidth*0.11f));
        option.put("height",(int)((gameWidth*0.11f)*1.4f));
        position = new Vector2();
        position.set(midPointX - (option.get("width")/2),positY- option.get("height"));
        staticVelocity = new Vector2();
        staticVelocity.set(0,gameHeight * 0.3f);
        positNext = new Vector2();
    }
    public void setPosit(float delta){
        if(!stopStatus){

            positNext.set(staticVelocity.x,staticVelocity.y);
            positNext = positNext.cpy().scl(delta);
            if(positNext.y + position.y< this.midPointY){
                position.add(positNext);
            }else{
                float yPosit = this.midPointY - position.y;
                positNext.y = positNext.y + yPosit;
                position.add(positNext);
                this.stopStatus = true;
            }
        }
    }
    public int getOption(String key){
        if(option.containsKey(key)) {
            return option.get(key);
        }else{
            return 0;
        }
    }
}
