package com.starfight.healthObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Hashtable;
import java.util.Map;

public class HealthObjects {
    private Rectangle body;
    private int maxHealth,minHealth;
    public HealthObjects(){
        body = new Rectangle();
        this.minHealth = 0;
    }

    public void setWidthHeight(float width,float height){
        body.height = height;
        body.width = width;
    }
    public void setPosit(float x,float y){
        body.x = x;
        body.y = y;
    }
    public void setHealth(int health){
        this.maxHealth = health;
    }
    public Rectangle getBody(){
        return body;
    }
}
