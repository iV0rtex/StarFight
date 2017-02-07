package com.starfight.ui.simpleButton;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.starfight.ui.simpleButton.interfaceButt.ButtonInterface;
import com.starfight.world.GameWorld;

public class simpleButton implements ButtonInterface {
    private Rectangle body;
    private Texture buttonUp;
    private Texture buttonDown;
    private buttonState currentState;
    private String returnMethod;
    public simpleButton(float x, float y, int width,int height, Texture buttonUp, Texture buttonDown, String returnMethod) {
        body = new Rectangle();
        body.set(x,y,width,height);
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;
        currentState = buttonState.UP;
        this.returnMethod = returnMethod;
    }
    public Texture getButtonTexture(){
        if(currentState == buttonState.UP){
            return buttonUp;
        }else{
            return buttonDown;
        }
    }
    public String click(int upOrDown){
        if(upOrDown == 1){
            currentState = buttonState.DOWN;
        }else if(upOrDown == 0 && currentState == buttonState.DOWN){
            currentState = buttonState.UP;
            return this.returnMethod;
        }
        return " ";
    }
    public void setButtonUp(){
        currentState = buttonState.UP;
    }

    @Override
    public Circle getBody() {
        return null;
    }

    @Override
    public Rectangle getBody(int e){
        return body;
    }



    public enum buttonState{
        DOWN,UP
    }
}
