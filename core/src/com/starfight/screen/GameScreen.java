package com.starfight.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.starfight.StarHelpers.InputHandler;
import com.starfight.world.GameRander;
import com.starfight.world.GameWorld;

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRander rander;
    public GameScreen(){
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136.0f;
        float gameHeight = screenHeight / (screenWidth/gameWidth);
        int midPointX = (int)(gameWidth / 2.0F);
        world = new GameWorld(midPointX);
        Gdx.input.setInputProcessor(new InputHandler(world,screenWidth/gameWidth));
        rander = new GameRander(this.world, gameWidth, gameHeight);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.update(delta);
        rander.render();
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
