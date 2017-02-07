package com.starfight.ui.simpleButton;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.starfight.ui.simpleButton.interfaceButt.ButtonInterface;
import com.starfight.world.GameWorld;

public class circleButton<T> implements ButtonInterface<T>{
    private Texture buttonUp;
    private Texture buttonDown;
    private T body;
    private buttonState currentState;
    private String returnMethod;

    public circleButton(float x,float y,int radius,Texture buttonUp,Texture buttonDown,String returnMethod,T Circle){
        body = Circle;
        Circle bodyCircle = (Circle) body;
        bodyCircle.set(x,y,radius);
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;
        currentState = buttonState.UP;
        this.returnMethod = returnMethod;
    }
    public circleButton(float x, float y, int width,int height, Texture buttonUp, Texture buttonDown, String returnMethod,T Rectangle){
        body = Rectangle;
        Rectangle bodyRect = (Rectangle) body;
        bodyRect.set(x,y,width,height);
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;
        currentState = buttonState.UP;
        this.returnMethod = returnMethod;
    }
    @Override
    public T getBody(){
        return body;
    }

    public Texture getButtonTexture(){
        if(currentState == buttonState.UP){
            return buttonUp;
        }else{
            return buttonDown;
        }

    }
    @Override
    public String click(int upOrDown){
        if(upOrDown == 1){
            currentState = buttonState.DOWN;
        }else if(upOrDown == 0 && currentState == buttonState.DOWN){
            currentState = buttonState.UP;
            return this.returnMethod;
        }
        return " ";
    }
    @Override
    public void setButtonUp(){
        currentState = buttonState.UP;
    }

    public enum buttonState{
        DOWN,UP
    }

}