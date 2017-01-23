package com.starfight.object;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.starfight.gameObject.enemies.DropSpares;
import com.starfight.healthObject.HealthObjects;
import com.starfight.object.objInterface.FhightInterface;
import java.util.ArrayList;

import java.util.Hashtable;
import java.util.Map;

//TODO: In a future need rewrite this class, and all his heirs, perhaps; This is mess;
public abstract class FhightObject implements FhightInterface{
    protected Map<String,Integer> option;
    public Vector2 position,velocity,staticVelocity;
    public Rectangle body;
    protected int speedAttack;
    protected float timeAttack;
    protected int health;
    protected boolean status;
    protected static final int staticDamage = -1;
    protected int scalarDamage = 1;
    protected HealthObjects healthBody;
    protected boolean dropResources = false;
    private boolean registeredBodyHealth = false;
    protected ArrayList<DropSpares> spares;
    protected FhightObject(){
        option = new Hashtable<String, Integer>();
        body = new Rectangle();
        position = new Vector2();
        velocity = new Vector2();
        staticVelocity = new Vector2();
        status = true;
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
        if(registeredBodyHealth){
            healthBody.setPosit(position.x,position.y+option.get("height")+2);
        }
    }
    public void setHealth(int val){
        this.health += val;
        if(health <= 0){
            this.die();
        }else if(!this.getObjectStatus() && this.health >0){
            this.reanimate();
        }
        if(registeredBodyHealth){
            float sWidth = healthBody.getBody().width*(((float)((100 -Math.abs(val) * 100 / healthBody.getHealth())))/100.0f);
            healthBody.setWidthHeight(sWidth,2);
        }
    }
    public int getHealth(){
        return health;
    }
    public void setDamage(int damage){
        this.setHealth(damage);
    }
    public void die(){
        if(dropResources){

            for(DropSpares spar : spares){
                int toRandomValFrom = (int)(this.position.x - (getOption("width")/2));
                if(toRandomValFrom < 0){
                    toRandomValFrom = 1;
                }
                int toRandomValTo = (int)((this.position.x+getOption("width")) + (getOption("width")/2));
                if(toRandomValTo > Gdx.graphics.getWidth()){
                    toRandomValTo = Gdx.graphics.getWidth();
                }
                int randomPositX = (int)MathUtils.random(toRandomValFrom,toRandomValTo);
                toRandomValFrom = (int)(this.position.y - (getOption("height")/2));
                if(toRandomValFrom < 0){
                    toRandomValFrom = 1;
                }
                toRandomValTo = (int)((this.position.y+getOption("height")) + (getOption("height")/2));
                if(toRandomValTo > Gdx.graphics.getHeight()){
                    toRandomValTo = Gdx.graphics.getHeight();
                }
                int randomPositY = (int)MathUtils.random(toRandomValFrom,toRandomValTo);
                spar.setPosition(randomPositX,randomPositY, (Gdx.graphics.getHeight()/2)*0.008f);
            }
        }
        status = false;
    }
    public boolean getObjectStatus(){
        return status;
    }
    public int getToDamage(){
        return (staticDamage * scalarDamage);
    }
    public void reanimate(){
        status = true;
    }
    public boolean registerBodyHealth(int staticHealth,float width,float health){
        healthBody = new HealthObjects();
        healthBody.setHealth(staticHealth);
        healthBody.setWidthHeight(width,health);
        registeredBodyHealth = true;
        return registeredBodyHealth;
    }
    public boolean registerDropResources(){
        int randomDrop = MathUtils.random(1,3);
        int sum = 1000;//TODO: This sum should be dynamic.
        int settedSum = 0;
        spares = new ArrayList<DropSpares>();
        for(int i = 0;i<randomDrop;i++){
            int randomSum = 0;
            if(i+1 != randomDrop){
                randomSum= MathUtils.random(3,sum-settedSum);
                randomSum -= 2;

            }else{
                randomSum= sum-settedSum;
            }
            settedSum += randomSum;

            spares.add(new DropSpares(randomSum));

        }

        dropResources = true;
        return dropResources;
    }
    public void setScalarDamage(int d){
        this.scalarDamage = d;
    }
    public ArrayList getListSpares(){
        return spares;
    }
}
