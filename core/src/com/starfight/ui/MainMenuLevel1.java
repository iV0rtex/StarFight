package com.starfight.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.starfight.assets.AssetsLoader;
import com.starfight.ui.simpleButton.circleButton;
import com.starfight.ui.simpleButton.interfaceButt.ButtonInterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainMenuLevel1 {
    private Rectangle divLeft;
    private Rectangle divRight;
    private float gameWidth,gameHeight;
    private AssetsLoader assets;
    private ArrayList<ButtonInterface> buttonList;
    public MainMenuLevel1(float gameWidth, float gameHeight, AssetsLoader assets, Rectangle divLeft,Rectangle divRight){
        this.divLeft = divLeft;
        this.divRight = divRight;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.assets = assets;
        buttonList.add(new circleButton<Rectangle>(divLeft.x,((divLeft.y + divLeft.getHeight())-divLeft.getWidth()/2),(int)divLeft.getWidth(),(int)divLeft.getWidth()/2,(Texture) assets.get("data/backToGame.png"),(Texture) assets.get("data/backToGame.png"),"methodSlow",new Rectangle()));
        buttonList.add(new circleButton<Circle>(divLeft.x,divLeft.y,(int)(divLeft.getWidth()*.1),(Texture) assets.get("data/pauseUp.png"),(Texture) assets.get("data/pauseDown.png"),"methodPause",new Circle()));
        buttonList.add(new circleButton<Rectangle>(divRight.x,((divRight.y + divRight.getHeight())-divRight.getWidth()/2),(int)divRight.getWidth(),(int)divRight.getWidth()/2,(Texture) assets.get("data/backToGame.png"),(Texture) assets.get("data/backToGame.png"),"methodSlow",new Rectangle()));
    }
    public void updateButtons(float delta,int upOrDown){
        for (ButtonInterface button:buttonList){

        }
    }
    private void eventClick(String var){
        if(!var.equals(" ")){
            Method clickedButtMethod;
            try {
                clickedButtMethod = this.getClass().getMethod(var);
                clickedButtMethod.invoke(this);
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
}
