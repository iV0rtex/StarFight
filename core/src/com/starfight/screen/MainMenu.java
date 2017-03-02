package com.starfight.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.starfight.StarHelpers.InputHandler;
import com.starfight.assets.AssetsLoader;
import com.starfight.gameObject.PlayerShipMenu;
import com.starfight.ui.MainMenuLevel1;
import com.starfight.world.MainMenuRander;

public class MainMenu<T> implements Screen{
    private OrthographicCamera cam;
    private AssetsLoader assets;
    private MainMenuRander rander;
    private PlayerShipMenu playerShip;
    private MenuStatus status;
    private MenuStatusSlide statusSlide;
    private Rectangle divRight;
    private Rectangle divLeft;
    private Vector2 newPosit;
    private float gameWidth,gameHeight;
    private T MenuNow;
    public MainMenu(AssetsLoader assets,InputHandler controlInput){
        newPosit = new Vector2();
        status = MenuStatus.LAVEL1;
        statusSlide = MenuStatusSlide.OPEN;
        this.assets = assets;
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        this.gameWidth = screenWidth;
        this.gameHeight = screenHeight;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, gameWidth, gameHeight);
        controlInput.setConfig(this,screenWidth/gameWidth,cam);
        Gdx.input.setInputProcessor(controlInput);
        int midPointX = (int)(gameWidth / 2.0F);
        int midPointY = (int)(gameHeight / 2.0F);
        this.createMenu();
        this.playerShip = new PlayerShipMenu((Texture) assets.get("data/userplain.png"),midPointX,midPointY,0,gameWidth,gameHeight);
        this.rander = new MainMenuRander(assets,gameWidth,gameHeight,cam,this.playerShip,this.divLeft,this.divRight,MenuNow);
    }
    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {

        this.divSetPosit(delta);
        if(MenuNow instanceof MainMenuLevel1){
            playerShip.setPosit(delta);
            MainMenuLevel1 menuNow = (MainMenuLevel1) MenuNow;
            menuNow.updateButtons(0,0,-1,this);
        }
        rander.render(delta);
    }
    private void divSetPosit(float delta){
        if(status == MenuStatus.LAVEL1){
            this.divSetPosit1(delta);
        }
    }
    private void divSetPosit1(float delta){
        if(statusSlide == MenuStatusSlide.OPEN || statusSlide == MenuStatusSlide.CLOSE ){
            newPosit.set(gameWidth*.3f,0);
            newPosit = newPosit.cpy().scl(delta);
            if(newPosit.x + divLeft.x < 0){
                divLeft.setX(newPosit.x+divLeft.x);
            }else{
                divLeft.setX(0);
            }
            if((divRight.x -newPosit.x)+divRight.width> gameWidth ){
                divRight.setX(divRight.x -newPosit.x);
            }else{
                divRight.setX(gameWidth - divRight.width);
                statusSlide = MenuStatusSlide.STOP;
            }
        }
    }
    public void touch(float x,float y,int upOrDown){
        MainMenuLevel1 menuNow = (MainMenuLevel1) MenuNow;
        menuNow.updateButtons(x,y,upOrDown,this);
    }
    private void createMenu(){
        if(status == MenuStatus.LAVEL1){
            this.menuCreate1();
        }
    }
    private void menuCreate1(){
        divRight = new Rectangle();
        divLeft = new Rectangle();
        float widthDiv =  gameWidth*.35f;
        divRight.set(gameWidth,0,widthDiv,gameHeight);
        divLeft.set(0-widthDiv,0,widthDiv,gameHeight);
        MenuNow = (T) new MainMenuLevel1(gameWidth,gameHeight,assets,divRight,divLeft);
    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
    public boolean isLevel1(){
        return status == status.LAVEL1;
    }
    public enum MenuStatus{
        LAVEL1
    }
    public enum MenuStatusSlide{
        OPEN,STOP,CLOSE;
    }
}
