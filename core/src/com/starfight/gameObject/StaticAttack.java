package com.starfight.gameObject;


import com.starfight.object.FhightObject;

public class StaticAttack extends FhightObject{
    StaticAttack(float x, float y){
        option.put("width",2);
        option.put("height",3);
        body.set( x, y,(float) getOption("width"),(float) getOption("height"));
        position.add(x - (this.getOption("width")/2.0f),y);
        staticVelocity.add(0,100);
    }

    @Override
    public void attack() {

    }

    @Override
    public void setAllOptions() {

    }

}
