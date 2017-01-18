package com.starfight.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.starfight.healthObject.HealthObjects;
import com.starfight.object.objInterface.FhightInterface;

import java.util.Hashtable;
import java.util.Map;


public abstract class FhightObject implements FhightInterface{
    protected Map<String,Integer> option;
    public Vector2 position,velocity,staticVelocity;
    public Rectangle body;
    protected int speedAttack;
    protected float timeAttack = 0;
    protected int health;
    protected boolean status;
    protected int damage;
    protected HealthObjects healthBody;
    protected FhightObject(){
        option = new Hashtable<String, Integer>();
        body = new Rectangle();
        position = new Vector2();
        velocity = new Vector2();
        staticVelocity = new Vector2();
        health = 2;
        healthBody = new HealthObjects();
        status = true;

        damage = -1;
    }
    public int getOption(String key){
        if(option.containsKey(key)) {
            return option.get(key);
        }else{
            return 0;
        }
    }
    public HealthObjects getHealthBody(){
        return healthBody;
    }
    public void update(float delta) {
        this.position.add(this.staticVelocity.cpy().scl(delta));
        body.set(position.x,position.y,(float) getOption("width"),(float) getOption("height"));
    }
    public void setHealth(int val){
        this.health += val;
        if(health <= 0){
            this.die();
        }else if(!this.getObjectStatus() && this.health >0){
            this.reanimate();
        }
    }
    public int getHealth(){
        return health;
    }
    public void setDamage(int damage){
        this.setHealth(damage);
    }
    public void die(){
        status = false;
    }
    public boolean getObjectStatus(){
        return status;
    }
    public int getToDamage(){
        return damage;
    }
    public void reanimate(){
        status = true;
    }
}
