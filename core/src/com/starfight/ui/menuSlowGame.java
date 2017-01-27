package com.starfight.ui;

import com.badlogic.gdx.graphics.Texture;
import com.starfight.assets.AssetsLoader;
import com.starfight.ui.simpleButton.circleButton;
import com.starfight.ui.simpleButton.interfaceButt.ButtonInterface;
import com.starfight.ui.simpleButton.simpleButton;
import com.starfight.world.GameWorld;

import java.util.ArrayList;

public class menuSlowGame{
    protected ArrayList<ButtonInterface> buttonList;
    protected ButtonInterface buttonClicked = null;
    public menuSlowGame(float gameWidth, float gameHeight, AssetsLoader assets){
        buttonList = new ArrayList<ButtonInterface>();
        setButtonList(gameWidth,gameHeight,assets);
    }
    public void setButtonList(float gameWidth, float gameHeight, AssetsLoader assets){
        buttonList.add(new circleButton(gameWidth*.85f,gameHeight*.91f,(int)((gameHeight/2)*.1f),(Texture) assets.get("data/pauseUp.png"),(Texture) assets.get("data/pauseDown.png")));
    }
    public ArrayList getButtonList(){
        return buttonList;
    }
    public boolean onClick(float x, float y, int upOrDown, GameWorld world){
        if(buttonClicked == null){
            for(ButtonInterface button:buttonList){
                if(button instanceof simpleButton){
                    if(button.getBody(1).contains(x,y)){
                        button.click(upOrDown, world);
                        buttonClicked = button;
                        return true;
                    }
                }else{
                    if(button.getBody().contains(x,y)){
                        button.click(upOrDown, world);
                        buttonClicked = button;
                        return true;
                    }
                }
            }
        }else{
            if(buttonClicked instanceof simpleButton){
                if(buttonClicked.getBody(1).contains(x,y)){
                    buttonClicked.click(upOrDown, world);
                }else{
                    buttonClicked.setButtonUp();
                }
            }else{
                if(buttonClicked.getBody().contains(x,y)){
                    buttonClicked.click(upOrDown, world);
                }else{
                    buttonClicked.setButtonUp();
                }
            }

            buttonClicked = null;
            return true;
        }

        return false;
    }
}
