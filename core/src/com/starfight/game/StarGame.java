package com.starfight.game;

import com.badlogic.gdx.Game;
import com.starfight.assets.AssetsLoader;
import com.starfight.screen.GameScreen;
import com.starfight.screen.MainMenu;

public class StarGame extends Game {
	private StatusGameScreen status;
	@Override
	public void create() {
		createScreenGame();
		/*status = StatusGameScreen.MAIN;
		AssetsLoader Loader = new AssetsLoader();
		Loader.loadMainMenu();
		this.setScreen(new MainMenu(Loader));*/
	}
	public void createScreenGame(){
		status = StatusGameScreen.GAME;
		AssetsLoader Loader = new AssetsLoader();
		Loader.loadAll();
		this.setScreen(new GameScreen(Loader));
	}
	public enum StatusGameScreen{
		MAIN,GAME,STARTMENU
	}
	@Override
	public void dispose(){
		super.dispose();
	}

}
