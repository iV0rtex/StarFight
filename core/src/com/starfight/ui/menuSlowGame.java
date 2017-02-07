package com.starfight.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.starfight.assets.AssetsLoader;
import com.starfight.ui.simpleButton.circleButton;
import com.starfight.ui.simpleButton.interfaceButt.ButtonInterface;
import com.starfight.world.GameWorld;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class menuSlowGame{
    protected ArrayList<ButtonInterface> buttonList;
    protected ButtonInterface buttonClicked = null;
    public menuSlowGame(float gameWidth, float gameHeight, AssetsLoader assets){
        buttonList = new ArrayList<ButtonInterface>();
        setButtonList(gameWidth,gameHeight,assets);
    }
    public void setButtonList(float gameWidth, float gameHeight, AssetsLoader assets){
        buttonList.add(new circleButton<Circle>(gameWidth*.85f,gameHeight*.91f,(int)((gameHeight/2)*.1f),(Texture) assets.get("data/pauseUp.png"),(Texture) assets.get("data/pauseDown.png"),"methodPause",new Circle()));
    }
    public ArrayList getButtonList(){
        return buttonList;
    }
    public boolean onClick(float x, float y, int upOrDown, GameWorld world){
        if(buttonClicked == null){
            for(ButtonInterface button:buttonList){
                if(button.getBody() instanceof Rectangle){
                    Rectangle body = (Rectangle) button.getBody();
                    if(body.contains(x,y)){
                       this.eventClick(button.click(upOrDown),world);
                        buttonClicked = button;
                        return true;
                    }
                }else{
                    Circle body = (Circle) button.getBody();
                    if(body.contains(x,y)){
                        this.eventClick(button.click(upOrDown),world);
                        buttonClicked = button;
                        return true;
                    }
                }
            }
        }else{
            if(buttonClicked.getBody() instanceof Rectangle){
                Rectangle body = (Rectangle) buttonClicked.getBody();
                if(body.contains(x,y)){
                    this.eventClick(buttonClicked.click(upOrDown),world);
                }else{
                    buttonClicked.setButtonUp();
                }
            }else{
                Circle body = (Circle) buttonClicked.getBody();
                if(body.contains(x,y)){
                    this.eventClick(buttonClicked.click(upOrDown),world);
                }else{
                    buttonClicked.setButtonUp();
                }
            }

            buttonClicked = null;
            return true;
        }

        return false;
    }
    private void eventClick(String var,GameWorld world){
        if(!var.equals(" ")){
            Method clickedButtMethod;
            try {
                clickedButtMethod = this.getClass().getMethod(var,world.getClass());
                clickedButtMethod.invoke(this,world);
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
    public void methodPause(GameWorld world){
        world.setPause();
    }
    public void methodSlow(GameWorld world){
        world.setSlowGame();
    }
}
