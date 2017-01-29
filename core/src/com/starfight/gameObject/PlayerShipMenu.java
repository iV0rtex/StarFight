package com.starfight.gameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.starfight.assets.AssetsLoader;

import java.util.Hashtable;
import java.util.Map;

public class PlayerShipMenu{
    private Texture texture;
    private Vector2 position;
    private Map<String,Integer> option;
    public PlayerShipMenu(Texture textureShip,int midPointX,int midPointY,float gameWidth,float gameHeight){
        texture = textureShip;
        position = new Vector2();
        position.set(midPointX,midPointY);
        option = new Hashtable<String, Integer>();
    }
}
