package com.starfight.gameObject.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class DropSpares {
    private int randomSum;
    private boolean status = false;
    private Circle body;
    private Vector2 velocity;
    public DropSpares(int randomSum){
        body = new Circle();
        this.randomSum = randomSum;
        velocity = new Vector2();
        velocity.set(0,-(int)(Gdx.graphics.getHeight()*0.1f));
    }
    public void setPosition(float x,float y,float radius){
        body.set(x,y,radius);
        status = true;
    }
    public Circle getBody(){
        return body;
    }
    public void update(float delta,float gameSpeed){
        Vector2 positNext = new Vector2();
        positNext.set(this.velocity.x * gameSpeed,this.velocity.y * gameSpeed);
        Vector2 newPosit = positNext.cpy().scl(delta);
        this.body.x += newPosit.x;
        this.body.y += newPosit.y;
    }
    public int getSum(){
        return randomSum;
    }
}
