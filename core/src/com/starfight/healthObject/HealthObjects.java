package com.starfight.healthObject;

import com.badlogic.gdx.math.Rectangle;

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
    public int getHealth(){
        return this.maxHealth;
    }
    public Rectangle getBody(){
        return body;
    }
}
