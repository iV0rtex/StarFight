package com.starfight.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.starfight.assets.AssetsLoader;
import com.starfight.screen.MainMenu;
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
    private ButtonInterface buttonClicked = null;
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
    public boolean updateButtons(float x, float y, int upOrDown, MainMenu menu){
        if(upOrDown == -1){
            for (ButtonInterface button:buttonList){
                this.eventClick(button.getMethod(),false,button,menu);
            }
        }else{
            if(buttonClicked == null){
                for(ButtonInterface button:buttonList){
                    if(button.getBody() instanceof Rectangle){
                        Rectangle body = (Rectangle) button.getBody();
                        if(body.contains(x,y)){
                            this.eventClick(button.click(upOrDown),true,button,menu);
                            buttonClicked = button;
                            return true;
                        }
                    }else{
                        Circle body = (Circle) button.getBody();
                        if(body.contains(x,y)){
                            this.eventClick(button.click(upOrDown),true,button,menu);
                            buttonClicked = button;
                            return true;
                        }
                    }
                }
            }else{
                if(buttonClicked.getBody() instanceof Rectangle){
                    Rectangle body = (Rectangle) buttonClicked.getBody();
                    if(body.contains(x,y)){
                        this.eventClick(buttonClicked.click(upOrDown),true,buttonClicked,menu);
                    }else{
                        buttonClicked.setButtonUp();
                    }
                }else{
                    Circle body = (Circle) buttonClicked.getBody();
                    if(body.contains(x,y)){
                        this.eventClick(buttonClicked.click(upOrDown),true,buttonClicked,menu);
                    }else{
                        buttonClicked.setButtonUp();
                    }
                }

                buttonClicked = null;
                return true;
            }
        }
        return false;
    }
    private void eventClick(String var,boolean statusClick,ButtonInterface button,MainMenu menu){
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
        }else{
            Gdx.app.log("methodSetMenuGarage","");
        }
    }
    public void methodSetMenuLevels(boolean statusClick,ButtonInterface button){
        if(!statusClick){
            Rectangle body = (Rectangle) button.getBody();
            body.x = divRight.x;
        }else{
            Gdx.app.log("methodSetMenuLevels","");
        }
    }
    public void methodExitGame(boolean statusClick,ButtonInterface button){
        if(!statusClick){
            Circle body = (Circle) button.getBody();
            body.x = (float)(divLeft.x+(divLeft.getWidth()*.1));
        }else{
            Gdx.app.log("methodExitGame","");
        }
    }
    public ArrayList getButtonList(){
        return buttonList;
    }
}
