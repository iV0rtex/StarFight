package com.starfight.gameObject.enemies;

import com.starfight.object.FhightObject;


public class EnemyV1 extends FhightObject{
    private int sHealth = 4;
    private int gameWidth,gameHeight;
    public EnemyV1(int x,int y,int gameWidth,int gameHeight){
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.setAllOptions(sHealth);
        body.set((float) x,(float) y,(float) getOption("width"),(float) getOption("height"));
        position.add(x - (this.getOption("width")/2.0f),y);
    }

    @Override
    public void attack() {

    }

    @Override
    public void setAllOptions(int health) {
        option.put("width",(int)(gameWidth*0.09f));
        option.put("height",(int)(option.get("width")*1.4));
        this.health = sHealth;
        staticVelocity.add(0,-(int)(gameHeight*0.2f));
        this.registerBodyHealth(sHealth,(float)getOption("width"),2f);
    }

}
