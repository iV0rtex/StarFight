package com.starfight.gameObject;

import com.starfight.object.FhightObject;

import java.util.ArrayList;

public class PlayerShip extends FhightObject{
    private String rout;
    private ArrayList<StaticAttack> attacks;
    private float nextPosit = 0;
    public PlayerShip(int midPointX) {
        rout = "noOne";
        option.put("width",12);
        option.put("height",20);
        body.set((midPointX - (this.getOption("width")/2.0f)),3f,(float) getOption("width"),(float) getOption("height"));
        position.add((midPointX - (this.getOption("width")/2.0f)),3f);
        attacks = new ArrayList<StaticAttack>();
        speedAttack = 1;
        staticVelocity.add(100,0);
    }

    @Override
    public void update(float delta) {
        this.position.add(this.velocity.cpy().scl(delta));

        if(rout.equals("right") && position.x >= nextPosit){
            velocity.x = 0;
        }else if(rout.equals("left") && position.x <= nextPosit){
            velocity.x = 0;
        }

        this.timeAttack += delta;
        this.attack();
        int size = attacks.size();
        for (int i =0; i< size;i++){
            StaticAttack attack = attacks.get(i);
            attack.update(delta);
            if(attack.position.y > 200){
                attacks.remove(i);
                i--;
                size--;
            }

        }
    }

    @Override
    public void attack() {
        if(timeAttack >= speedAttack){
            attacks.add(new StaticAttack(position.x + (this.getOption("width")/2.0f),position.y));
            timeAttack = 0;
        }
    }
    public ArrayList getListAttack(){
        return attacks;
    }
    public void touch(int x,int upOrDown){
        if(upOrDown == 1){
            if(position.x < x){
                velocity.x = staticVelocity.x;
                rout = "right";
            }else if(position.x > x){
                velocity.x = -staticVelocity.x;
                rout = "left";
            }else{
                velocity.x = 0;
            }
            nextPosit = x;
        }else{
            velocity.x = 0;
            rout = "noOne";
        }

    }

}
