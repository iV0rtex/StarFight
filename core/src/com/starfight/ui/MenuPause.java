package com.starfight.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.starfight.assets.AssetsLoader;
import com.starfight.ui.simpleButton.circleButton;

public class MenuPause extends menuSlowGame{

    public MenuPause(float gameWidth, float gameHeight, AssetsLoader assets) {
        super(gameWidth, gameHeight, assets);
    }
    @Override
    public void setButtonList(float gameWidth, float gameHeight, AssetsLoader assets){
        buttonList.add(new circleButton<Rectangle>(((gameWidth/2f))/2f,gameHeight/2f,(int)(gameWidth/2),(int)((gameHeight/2) * .15f),(Texture) assets.get("data/backToGame.png"),(Texture) assets.get("data/backToGame.png"),"methodSlow",new Rectangle()));
    }
}
