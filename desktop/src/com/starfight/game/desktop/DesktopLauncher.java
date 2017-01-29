package com.starfight.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.starfight.game.StarGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Star fight";
		config.height = 800;
		config.width = 480;
		config.fullscreen = true;
		new LwjglApplication(new StarGame(), config);
	}
}
