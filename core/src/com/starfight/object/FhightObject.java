package com.starfight.object;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.starfight.object.objInterface.FhightInterface;

import java.util.Hashtable;
import java.util.Map;


public abstract class FhightObject implements FhightInterface{
    protected Map<String,Integer> option;
    public Vector2 position,velocity,staticVelocity;
    public Rectangle body;
    protected int speedAttack;
    protected float timeAttack = 0;
    protected FhightObject(){
        option = new Hashtable<String, Integer>();
        body = new Rectangle();
        position = new Vector2();
        velocity = new Vector2();
        staticVelocity = new Vector2();
    }

    public int getOption(String key){
        if(option.containsKey(key)) {
            return option.get(key);
        }else{
            return 0;
        }
    }
    public void update(float delta) {
        this.position.add(this.staticVelocity.cpy().scl(delta));
        body.set(position.x,position.y,(float) getOption("width"),(float) getOption("height"));
    }

}
