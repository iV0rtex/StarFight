package com.starfight.gameObject.enemies;

import com.starfight.object.FhightObject;


public class EnemyV1 extends FhightObject{
    private int sHealth = 4;
    public EnemyV1(int x,int y){
        option.put("width",12);
        option.put("height",15);
        health = sHealth;
        body.set((float) x,(float) y,(float) getOption("width"),(float) getOption("height"));
        position.add(x - (this.getOption("width")/2.0f),y);
        staticVelocity.add(0,-50);
        this.registerBodyHealth(sHealth,(float)getOption("width"),2f);

    }

    @Override
    public void attack() {

    }

    @Override
    public void setAllOptions() {
        //// TODO: Move all settings for object in this function, and remove from parent class 18.01.2017
    }

}
