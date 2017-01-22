package com.starfight.gameObject;

import com.badlogic.gdx.Gdx;
import com.starfight.object.FhightObject;

import java.util.ArrayList;

public class PlayerShip extends FhightObject{
    private String rout;
    private ArrayList<StaticAttack> attacks;
    private float nextPosit = 0;
    private int gameWidth;
    private int gameHeight;
    private int midPointX;
    public PlayerShip(int midPointX,float gameWidth,float gameHeight) {
        this.midPointX = midPointX;
        this.gameWidth = (int)gameWidth;
        this.gameHeight = (int)gameHeight;
        this.setAllOptions(4);
    }

    @Override
    public void update(float delta) {
        this.position.add(this.velocity.cpy().scl(delta));
        body.set(position.x,position.y,(float) getOption("width"),(float) getOption("height"));
        if(rout.equals("right") && (position.x + (this.option.get("width")/2)) >= nextPosit){
            velocity.x = 0;
        }else if(rout.equals("left") && (position.x + (this.option.get("width")/2)) <= nextPosit){
            velocity.x = 0;
        }

        this.timeAttack += delta;
        this.attack();
        int size = attacks.size();
        for (int i =0; i< size;i++){
            StaticAttack attack = attacks.get(i);
            attack.update(delta);
            if(attack.position.y > gameHeight){
                attacks.remove(i);
                i--;
                size--;
            }

        }
    }

    @Override
    public void attack() {
        if(timeAttack >= speedAttack){
            attacks.add(new StaticAttack(position.x + (this.getOption("width")/2.0f),position.y+this.getOption("height"),gameWidth,gameHeight));
            timeAttack = 0;
        }
    }

    @Override
    public void setAllOptions(int health) {
        this.health = health;
        rout = "noOne";
        option.put("width",(int)(gameWidth*0.11f));
        option.put("height",(int)(option.get("width")*1.4f));
        body.set((midPointX - (this.getOption("width")/2.0f)),3f,(float) getOption("width"),(float) getOption("height"));
        position.add((midPointX - (this.getOption("width")/2.0f)),3f);
        attacks = new ArrayList<StaticAttack>();
        speedAttack = 1;
        staticVelocity.add(gameWidth*0.5f,0);
    }


    public ArrayList getListAttack(){
        return attacks;
    }

    public void touch(float x,int upOrDown){
        if(upOrDown == 1){
            float scalPosit = position.x+(this.getOption("width")/2.0f);
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

}
