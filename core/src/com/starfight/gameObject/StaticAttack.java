package com.starfight.gameObject;


import com.starfight.object.FhightObject;

public class StaticAttack extends FhightObject{
    private int gameWidth;
    private int gameHeight;
    public StaticAttack(float x, float y,int gameWidth,int gameHeight){
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        body.set( x, y,(float) getOption("width"),(float) getOption("height"));
        position.set(x - (this.getOption("width")/2.0f),y);
        this.setAllOptions(1);
    }

    @Override
    public void attack() {

    }

    @Override
    public void setAllOptions(int health) {
        this.health = health;
        option.put("width",(int)(gameWidth*0.01f));
        option.put("height",(int)(option.get("width")*2));
        staticVelocity.add(0,(int)(gameHeight*0.3f));
    }

}
