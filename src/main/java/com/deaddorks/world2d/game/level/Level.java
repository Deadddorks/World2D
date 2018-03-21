package com.deaddorks.world2d.game.level;

import com.deaddorks.world2d.game.blocks.BlockInfo;

public class Level
{
	
	private final String levelName;
	private final BlockInfo[][] blocks;
	
	public Level(final String levelName, final BlockInfo[][] blocks)
	{
		this.levelName = levelName;
		this.blocks = blocks;
	}
	
	public void getLevelData(final int blockSize, final int windowWidth, final int windowHeight)
	{
	
	}
	
}
