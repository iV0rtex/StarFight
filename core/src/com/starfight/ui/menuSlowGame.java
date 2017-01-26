package com.starfight.ui;

import com.badlogic.gdx.graphics.Texture;
import com.starfight.assets.AssetsLoader;
import com.starfight.ui.simpleButton.circleButton;
import com.starfight.world.GameWorld;

import java.util.ArrayList;

public class menuSlowGame {
    private ArrayList<circleButton> buttonList;
    public menuSlowGame(float gameWidth, float gameHeight, AssetsLoader assets){
        buttonList = new ArrayList<circleButton>();
        buttonList.add(new circleButton(gameWidth*.85f,gameHeight*.91f,(int)((gameHeight/2)*.1f),(Texture) assets.get("data/pauseUp.png"),(Texture) assets.get("data/pauseDown.jpg")));
    }
    public ArrayList getButtonList(){
        return buttonList;
    }
    public boolean checkClick(float x, float y, GameWorld world){
        for(circleButton button:buttonList){
            if(button.getBody().contains(x,y)){
                return true;
            }
        }
        return false;
    }
}
