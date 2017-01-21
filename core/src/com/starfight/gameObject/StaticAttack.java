package com.starfight.gameObject;


import com.starfight.object.FhightObject;

public class StaticAttack extends FhightObject{
    StaticAttack(float x, float y,int gameWidth,int gameHeight){
        option.put("width",(int)(gameWidth*0.01f));
        option.put("height",(int)(option.get("width")*2));
        body.set( x, y,(float) getOption("width"),(float) getOption("height"));
        position.add(x - (this.getOption("width")/2.0f),y);
        staticVelocity.add(0,(int)(gameHeight*0.3f));
    }

    @Override
    public void attack() {

    }

    @Override
    public void setAllOptions(int health) {

    }

}
