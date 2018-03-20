package com.deaddorks.world2d.game.world;

import com.deaddorks.world2d.game.level.Level;
import com.deaddorks.world2d.game.level.LevelLoader;

public class World
{
	
	private Level level;
	private LevelLoader loader;
	
	public World()
	{
		loader = new LevelLoader("levels/", ".tdl");
		
		level = loader.load("level-1");
	}
	
}
