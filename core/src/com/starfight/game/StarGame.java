package com.starfight.game;

import com.badlogic.gdx.Game;
import com.starfight.StarHelpers.InputHandler;
import com.starfight.assets.AssetsLoader;
import com.starfight.screen.GameScreen;
import com.starfight.screen.MainMenu;

public class StarGame extends Game {
	private StatusGameScreen status;
	private InputHandler ControlInput;

	@Override
	public void create() {
		ControlInput = new InputHandler(this);
		//createScreenGame();
		status = StatusGameScreen.MAIN;
		AssetsLoader Loader = new AssetsLoader();
		Loader.loadMainMenu();
		this.setScreen(new MainMenu(Loader,ControlInput));
	}
	public void createScreenGame(){
		status = StatusGameScreen.GAME;
		AssetsLoader Loader = new AssetsLoader();
		Loader.loadAll();
		this.setScreen(new GameScreen(Loader,ControlInput));
	}
	public enum StatusGameScreen{
		MAIN,GAME,STARTMENU
	}
	public boolean isGame(){
		return status == status.GAME;
	}
	public boolean isMain(){
		return status == status.MAIN;
	}
	@Override
	public void dispose(){
		super.dispose();
	}

}
