package com.starfight.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.starfight.gameObject.StaticAttack;
import com.starfight.gameObject.enemies.DropSpares;
import com.starfight.healthObject.HealthObjects;
import com.starfight.object.objInterface.ObjectInterface;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public abstract class FightObject implements ObjectInterface{
    private Map<String,Integer> option;
    public Vector2 position,velocity,staticVelocity;
    private Rectangle body;
    private int speedAttack;
    private float timeAttack;
    private int health;
    private boolean status;
    private static final int staticDamage = -1;
    private int scalarDamage = 1;
    private HealthObjects healthBody;
    private boolean dropResources = false;
    private boolean registeredBodyHealth = false;
    private ArrayList<DropSpares> spares;
    private Map<String,Integer> sizeGame;
    private ArrayList<StaticAttack> attacks;
    private boolean settedAllOptions = false;
    private boolean attackRegistered = false;
    protected FightObject(){
        option = new Hashtable<String, Integer>();
        body = new Rectangle();
        position = new Vector2();
        velocity = new Vector2();
        staticVelocity = new Vector2();
        sizeGame = new Hashtable<String, Integer>();
        status = true;
    }
    protected void fSettedAllOptions(){
        settedAllOptions = true;
    }
    public int getOption(String key){
        if(option.containsKey(key)) {
            return option.get(key);
        }else{
            return 0;
        }
    }
    public void setOption(String key,int val){
        option.put(key,val);
    }
    public void setSizeGame(String key,int val){
        sizeGame.put(key,val);
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
        if(attackRegistered){
            this.attack(delta);
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
    public Rectangle getBody(){
        return body;
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
                int randomPositX = (int) MathUtils.random(toRandomValFrom,toRandomValTo);
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
    public ArrayList getListSpares(){
        return spares;
    }
    public void registerAttack(int speedAttack){
        attacks = new ArrayList<StaticAttack>();
        this.speedAttack = speedAttack;
        attackRegistered = true;
    }
    public void attack(float delta) {
        this.timeAttack += delta;
        if(timeAttack >= speedAttack){
            attacks.add(new StaticAttack(position.x + (this.getOption("width")/2.0f),position.y+this.getOption("height"),sizeGame.get("width"),sizeGame.get("height")));
            timeAttack = 0;
        }
        int size = attacks.size();
        for (int i =0; i< size;i++){
            StaticAttack attack = attacks.get(i);
            attack.update(delta);
            if(attack.position.y > sizeGame.get("height") || attack.position.y < 0){
                attacks.remove(i);
                i--;
                size--;
            }
        }
    }
    public ArrayList getListAttack(){
        return attacks;
    }


}
