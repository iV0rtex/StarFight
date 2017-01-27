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
    public simpleButton(float x, float y, int width,int height, Texture buttonUp, Texture buttonDown) {
        body = new Rectangle();
        body.set(x,y,width,height);
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;
        currentState = buttonState.UP;
    }
    public Texture getButtonTexture(){
        if(currentState == buttonState.UP){
            return buttonUp;
        }else{
            return buttonDown;
        }
    }
    public boolean click(int upOrDown, GameWorld world){
        if(upOrDown == 1){
            currentState = buttonState.DOWN;
            return true;
        }else if(upOrDown == 0 && currentState == buttonState.DOWN){
            currentState = buttonState.UP;
            world.setSlowGame();

        }
        return false;
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
