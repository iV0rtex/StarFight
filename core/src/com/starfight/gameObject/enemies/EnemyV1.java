package com.starfight.gameObject.enemies;

import com.starfight.object.FhightObject;

public class EnemyV1 extends FhightObject{
    public EnemyV1(int x,int y){
        option.put("width",12);
        option.put("height",15);
        body.set((float) x,(float) y,(float) getOption("width"),(float) getOption("height"));
        position.add(x - (this.getOption("width")/2.0f),y);
        staticVelocity.add(0,-50);
    }

    @Override
    public void attack() {

    }
}
