package com.starfight.gameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.starfight.assets.AssetsLoader;

import java.util.Hashtable;
import java.util.Map;

public class PlayerShipMenu{
    private Texture texture;
    public Vector2 position;
    private Map<String,Integer> option;
    public PlayerShipMenu(Texture textureShip,int midPointX,int midPointY,float gameWidth,float gameHeight){
        texture = textureShip;

        option = new Hashtable<String, Integer>();
        option.put("width",(int)(gameWidth*0.11f));
        option.put("height",(int)((gameWidth*0.11f)*1.4f));
        position = new Vector2();
        position.set(midPointX - (option.get("width")/2),midPointY- (option.get("height")/2));
    }
    public int getOption(String key){
        if(option.containsKey(key)) {
            return option.get(key);
        }else{
            return 0;
        }
    }
}
