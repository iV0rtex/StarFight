package com.starfight.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.starfight.StarHelpers.InputHandler;
import com.starfight.assets.AssetsLoader;
import com.starfight.world.GameRander;
import com.starfight.world.GameWorld;

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRander rander;
    private OrthographicCamera cam;
    public GameScreen(AssetsLoader assets){
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = screenWidth;
        float gameHeight = screenHeight;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, gameWidth, gameHeight);

        int midPointX = (int)(gameWidth / 2.0F);
        world = new GameWorld(midPointX,gameWidth,gameHeight,assets);
        Gdx.input.setInputProcessor(new InputHandler(world,screenWidth/gameWidth,cam));
        rander = new GameRander(this.world,cam,assets,gameWidth,gameHeight);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.update(delta);
        rander.render(delta);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        //TODO: There should be logic when game is roll down
    }

    @Override
    public void resume() {
        //TODO: There should be logic when game is roll up
    }

    @Override
    public void hide() {
        //TODO: There should be logic when game is close;
    }

    @Override
    public void dispose() {

    }
}
