package com.starfight.gameObject;



import com.starfight.object.FightObject;

public class StaticAttack extends FightObject{

    public StaticAttack(float x, float y,int gameWidth,int gameHeight){
        this.setAllOptions(1,(int)(gameWidth*0.01f),(int)((gameWidth*0.01f)*2),gameWidth,gameHeight,x,y);
    }

    @Override
    public void setAllOptions(int health,int widthObj,int heightObj,int gameWidth, int gameHeight,float positX,float positY) {
        this.setSizeGame("width",gameWidth);
        this.setSizeGame("height",gameHeight);

        this.setOption("width",widthObj);
        this.setOption("height",heightObj);

        this.setHealth(health);
        staticVelocity.add(0,(int)(gameHeight*0.3f));
        this.registerBodyHealth(health,(float)getOption("width"),2f);
        this.registerDropResources();
        this.getBody().set(positX, positY,(float) getOption("width"),(float) getOption("height"));
        position.add(positX-(this.getOption("width")/2.0f),positY);
        this.fSettedAllOptions();
    }

}
