package com.starfight.ui.simpleButton.interfaceButt;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.starfight.world.GameWorld;

public interface ButtonInterface<T>  {
    T getBody();
    String click(int upOrDown);
    String getMethod();
    void setButtonUp();
}
