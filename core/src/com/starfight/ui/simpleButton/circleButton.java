package com.starfight.ui.simpleButton;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class circleButton {
    private Vector2 position;
    private Texture buttonUp;
    private Texture buttonDown;
    private Circle body;
    public circleButton(float x,float y,int radius,Texture buttonUp,Texture buttonDown){
        position = new Vector2(x,y);
        body = new Circle();
        body.set(x,y,radius);
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;
    }
    public Circle getBody(){
        return body;
    }
    public Texture getButtonUp(){
        return buttonUp;
    }
    public Texture getButtonDown(){
        return buttonDown;
    }



}