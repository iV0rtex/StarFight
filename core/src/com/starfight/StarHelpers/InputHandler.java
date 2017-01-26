package com.starfight.StarHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.starfight.world.GameWorld;

public class InputHandler implements InputProcessor{
    private GameWorld world;
    private float scaleFactorX;
    OrthographicCamera cam;
    Vector3 touchPos;
    public InputHandler(GameWorld world, float scaleFactorX, OrthographicCamera cam){
        this.world = world;
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
        touchPos.set(screenX,screenY,(float)0);
        cam.unproject(touchPos);
        world.setNormalGame();
        world.touch(touchPos.x,1);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touchPos.set(screenX,screenY,(float)0);
        world.setSlowGame();
        cam.unproject(touchPos);
        world.touch(touchPos.x,0);

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touchPos.set(screenX,screenY,(float)0);
       cam.unproject(touchPos);
        world.touch(touchPos.x,1);

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
