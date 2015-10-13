package com.gamecell.spacecraft.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gamecell.spacecraft.SpaceCraft;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 1080;
		config.width = 720;
		new LwjglApplication(new SpaceCraft(), config);
	}
}
