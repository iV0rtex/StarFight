package com.starfight.gameObject.enemies;

import com.starfight.object.FhightObject;


public class EnemyV1 extends FhightObject{
    private int sHealth = 4;
    public EnemyV1(int x,int y,int gameWidth,int gameHeight){
        option.put("width",(int)(gameWidth*0.07f));
        option.put("height",(int)(option.get("width")*1.5));
        health = sHealth;
        body.set((float) x,(float) y,(float) getOption("width"),(float) getOption("height"));
        position.add(x - (this.getOption("width")/2.0f),y);
        staticVelocity.add(0,-(int)(gameHeight*0.2f));
        this.registerBodyHealth(sHealth,(float)getOption("width"),2f);

    }

    @Override
    public void attack() {

    }

    @Override
    public void setAllOptions(int health) {
        //// TODO: Move all settings for object in this function, and remove from parent class 18.01.2017
    }

}
