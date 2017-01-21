package com.starfight.game;

import com.badlogic.gdx.Game;
import com.starfight.assets.AssetsLoader;
import com.starfight.screen.GameScreen;

public class StarGame extends Game {

	@Override
	public void create() {
		AssetsLoader Loader = new AssetsLoader();
		Loader.loadAll();
		this.setScreen(new GameScreen(Loader));
	}

	@Override
	public void dispose(){
		super.dispose();
	}
}
