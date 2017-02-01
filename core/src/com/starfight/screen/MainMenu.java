package com.starfight.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.starfight.assets.AssetsLoader;
import com.starfight.gameObject.PlayerShipMenu;
import com.starfight.world.MainMenuRander;

public class MainMenu implements Screen{
    private OrthographicCamera cam;
    private AssetsLoader assets;
    private MainMenuRander rander;
    private PlayerShipMenu playerShip;
    public MainMenu(AssetsLoader assets){
        this.assets = assets;
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 480;
        float gameHeight = 800;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, gameWidth, gameHeight);
        int midPointX = (int)(gameWidth / 2.0F);
        int midPointY = (int)(gameHeight / 2.0F);
        this.playerShip = new PlayerShipMenu((Texture) assets.get("data/userplain.png"),midPointX,midPointY,gameWidth,gameHeight);
        this.rander = new MainMenuRander(assets,gameWidth,gameHeight,cam,this.playerShip);

    }
    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        rander.render(delta);
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
}
