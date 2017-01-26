package com.starfight.gameObject;

import com.badlogic.gdx.math.Vector2;
import com.starfight.object.FightObject;

public class PlayerShip extends FightObject{
    private String rout;
    private float nextPosit = 0;
    private int score = 0;
    public PlayerShip(int midPointX,float gameWidth,float gameHeight) {
        this.setAllOptions(4,(int)(gameWidth*0.11f),(int)((gameWidth*0.11f)*1.4f),(int)gameWidth,(int)gameHeight,(int)(midPointX - ((gameWidth*0.11f)/2.0f)),3);
    }

    @Override
    public void update(float delta,float gameSpeed) {
        Vector2 positNext = new Vector2();
        positNext.set(this.velocity.x * gameSpeed,this.velocity.y * gameSpeed);
        this.position.add(positNext.cpy().scl(delta));
        this.getBody().set(position.x,position.y,(float) getOption("width"),(float) getOption("height"));
        if(rout.equals("right") && (position.x + (this.getOption("width")/2)) >= nextPosit){
            velocity.x = 0;
        }else if(rout.equals("left") && (position.x + (this.getOption("width")/2)) <= nextPosit){
            velocity.x = 0;
        }
        this.attack(delta,gameSpeed);
    }

    @Override
    public void setAllOptions(int health,int widthObj,int heightObj,int gameWidth, int gameHeight,float positX,float positY) {
        this.setSizeGame("width", gameWidth);
        this.setSizeGame("height", gameHeight);

        this.setOption("width", widthObj);
        this.setOption("height", heightObj);

        this.setHealth(health);
        staticVelocity.add(gameWidth * 0.5f, 0);
        this.registerBodyHealth(health, (float) widthObj, 2f);
        this.registerDropResources();
        this.getBody().set(positX, positY, (float) widthObj, (float) heightObj);
        position.add(positX, positY);
        rout = "noOne";
        this.registerAttack(1);
        this.fSettedAllOptions();
    }
    public void touch(float x,int upOrDown){
        if(upOrDown == 1){
            //float scalPosit = position.x+(this.getOption("width")/2.0f);
            if(velocity.x == 0){
                if(x > position.x+this.getOption("width")){
                    velocity.x = staticVelocity.x;
                    rout = "right";
                }else if(x<position.x){
                    velocity.x = -staticVelocity.x;
                    rout = "left";
                }
                nextPosit = x;
            }
        }else{
            velocity.x = 0;
            rout = "noOne";
        }

    }
    public void setScore(int score){
        this.score += score;
    }

}
