package com.starfight.StarHelpers;

import com.badlogic.gdx.InputProcessor;
import com.starfight.world.GameWorld;

public class InputHandler implements InputProcessor{
    private GameWorld world;
    private float scaleFactorX;
    public InputHandler(GameWorld world,float scaleFactorX){
        this.world = world;
        this.scaleFactorX = scaleFactorX;
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
        world.touch(scaleX(screenX),1);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        world.touch(scaleX(screenX),0);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    private int scaleX(int screenX) {
        return (int) (screenX / this.scaleFactorX);
    }
}
