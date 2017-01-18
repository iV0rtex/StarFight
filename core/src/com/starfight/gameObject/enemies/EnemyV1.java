package com.starfight.gameObject.enemies;

import com.badlogic.gdx.Gdx;
import com.starfight.object.FhightObject;

public class EnemyV1 extends FhightObject{
    private static final int sHealth = 4;
    public EnemyV1(int x,int y){
        option.put("width",12);
        option.put("height",15);
        health = sHealth;
        body.set((float) x,(float) y,(float) getOption("width"),(float) getOption("height"));
        position.add(x - (this.getOption("width")/2.0f),y);
        staticVelocity.add(0,-50);
        healthBody.setHealth(sHealth);
        healthBody.setWidthHeight(getOption("width"),2);
    }

    @Override
    public void attack() {

    }

    @Override
    public void update(float delta){
        super.update(delta);
        healthBody.setPosit(position.x,position.y+option.get("height")+2);
    }
    @Override
    public void setHealth(int val){
        super.setHealth(val);
        float sWidth = healthBody.getBody().width*(((float)((100 -Math.abs(val) * 100 / sHealth)))/100.0f);
        healthBody.setWidthHeight(sWidth,2);
    }

    @Override
    public void setAllOptions() {

    }

}
