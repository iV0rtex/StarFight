package com.starfight.game;

import com.badlogic.gdx.Game;
import com.starfight.screen.GameScreen;

public class StarGame extends Game {

	@Override
	public void create() {
		this.setScreen(new GameScreen());
	}

	@Override
	public void dispose(){
		super.dispose();
	}
}
