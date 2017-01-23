package com.starfight.gameObject.enemies;

import com.badlogic.gdx.math.Circle;

public class DropSpares {
    private int randomSum;
    private boolean status = false;
    private Circle body;
    public DropSpares(int randomSum){
        body = new Circle();
        this.randomSum = randomSum;
    }
    public void setPosition(float x,float y,float radius){
        body.set(x,y,radius);
        status = true;
    }
    public Circle getBody(){
        return body;
    }
}
