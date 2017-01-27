package com.starfight.ui;

import com.badlogic.gdx.graphics.Texture;
import com.starfight.assets.AssetsLoader;
import com.starfight.ui.simpleButton.simpleButton;

public class MenuPause extends menuSlowGame{

    public MenuPause(float gameWidth, float gameHeight, AssetsLoader assets) {
        super(gameWidth, gameHeight, assets);
    }
    @Override
    public void setButtonList(float gameWidth, float gameHeight, AssetsLoader assets){
        buttonList.add(new simpleButton((gameWidth/2f)*.85f,gameHeight/2f,(int)((gameWidth/2) * .80f),(int)((gameHeight/2) * .80f),(Texture) assets.get("data/pauseUp.png"),(Texture) assets.get("data/pauseDown.jpg")));
    }
}
