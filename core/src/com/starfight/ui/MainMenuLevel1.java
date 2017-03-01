package com.starfight.ui;

import com.badlogic.gdx.Gdx;
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
    public MainMenuLevel1(float gameWidth, float gameHeight, AssetsLoader assets, Rectangle divRight,Rectangle divLeft){
        this.divLeft = divLeft;
        this.divRight = divRight;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.assets = assets;
        buttonList = new ArrayList<ButtonInterface>();
        buttonList.add(new circleButton<Rectangle>(divLeft.x,(divLeft.y + divLeft.getHeight()/2),(int)divLeft.getWidth(),(int)divLeft.getWidth()/2,(Texture) assets.get("data/toGarage.png"),(Texture) assets.get("data/toGarage.png"),"methodSetMenuGarage",new Rectangle()));
        buttonList.add(new circleButton<Circle>((float)(divLeft.x+(divLeft.getWidth()*.1)),(float)(divLeft.y+(divLeft.getWidth()*.1)),(int)(divLeft.getWidth()*.2),(Texture) assets.get("data/pauseUp.png"),(Texture) assets.get("data/pauseDown.png"),"methodExitGame",new Circle()));
        buttonList.add(new circleButton<Rectangle>(divRight.x,(divRight.y + divRight.getHeight()/2),(int)divRight.getWidth(),(int)divRight.getWidth()/2,(Texture) assets.get("data/SetMenuLevels.png"),(Texture) assets.get("data/SetMenuLevels.png"),"methodSetMenuLevels",new Rectangle()));
    }
    public void updateButtons(float delta,int upOrDown){
        for (ButtonInterface button:buttonList){
            if(upOrDown == -1){
                this.eventClick(button.getMethod(),false,button);
            }
        }
    }
    private void eventClick(String var,boolean statusClick,ButtonInterface button){
        if(!var.equals(" ")){
            Method clickedButtMethod;
            try {
                clickedButtMethod = this.getClass().getMethod(var,boolean.class,ButtonInterface.class);
                clickedButtMethod.invoke(this,statusClick,button);
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
    public void methodSetMenuGarage(boolean statusClick, ButtonInterface button){
        if(!statusClick){
            Rectangle body = (Rectangle) button.getBody();
            body.x = divLeft.x;
        }
    }
    public void methodSetMenuLevels(boolean statusClick,ButtonInterface button){
        if(!statusClick){
            Rectangle body = (Rectangle) button.getBody();
            body.x = divRight.x;
        }else{

        }
    }
    public void methodExitGame(boolean statusClick,ButtonInterface button){
        if(!statusClick){
            Circle body = (Circle) button.getBody();
            body.x = (float)(divLeft.x+(divLeft.getWidth()*.1));
        }
    }
    public ArrayList getButtonList(){
        return buttonList;
    }
}
