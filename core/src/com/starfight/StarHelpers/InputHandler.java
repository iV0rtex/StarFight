package com.starfight.StarHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.starfight.game.StarGame;
import com.starfight.screen.MainMenu;
import com.starfight.world.GameWorld;

public class InputHandler implements InputProcessor{
    private StarGame start;
    private GameWorld world;
    private MainMenu mainMenu;
    private float scaleFactorX;
    OrthographicCamera cam;
    Vector3 touchPos;
    public InputHandler(StarGame start){
        this.start = start;
    }
    public void setConfig(GameWorld world, float scaleFactorX, OrthographicCamera cam){
        this.world = world;
        this.scaleFactorX = scaleFactorX;
        this.cam = cam;
        touchPos = new Vector3();
    }
    public void setConfig(MainMenu mainMenu, float scaleFactorX, OrthographicCamera cam){
        this.mainMenu = mainMenu;
        this.scaleFactorX = scaleFactorX;
        this.cam = cam;
        touchPos = new Vector3();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPos.set(screenX, screenY, (float) 0);
        cam.unproject(touchPos);
        if(start.isGame()) {
            if (world.isNormalGame()) {
                world.touch(touchPos.x, 1);
            } else if (world.isSlow()) {
                world.touchSlow(touchPos.x, touchPos.y, 1);
            } else if (world.isPause()) {
                world.touchPause(touchPos.x, touchPos.y, 1);
            }
        }else if(start.isMain()){
                mainMenu.touch(touchPos.x, touchPos.y, 1);
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touchPos.set(screenX,screenY,(float)0);
        cam.unproject(touchPos);
        if(start.isGame()) {
            if (world.isNormalGame()) {
                world.setSlowGame();
                world.touch(touchPos.x, 0);
            } else if (world.isSlow()) {
                world.touchSlow(touchPos.x, touchPos.y, 0);
            } else if (world.isPause()) {
                world.touchPause(touchPos.x, touchPos.y, 0);
            }
        }else if(start.isMain()){
            mainMenu.touch(touchPos.x, touchPos.y, 0);
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touchPos.set(screenX,screenY,(float)0);
        cam.unproject(touchPos);
        if(start.isGame()) {
            if (world.isNormalGame()) {
                world.touch(touchPos.x, 1);
            }
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    private float scaleX(float screenX) {
        return (float) (screenX / this.scaleFactorX);
    }
}
