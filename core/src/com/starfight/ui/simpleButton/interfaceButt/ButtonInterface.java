package com.starfight.ui.simpleButton.interfaceButt;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.starfight.world.GameWorld;

public interface ButtonInterface  {
    Circle getBody();
    Rectangle getBody(int e);
    String click(int upOrDown);
    void setButtonUp();
}
