package com.deaddorks.world2d.game.exceptions;

public class LevelSizeTooSmallException extends LevelLoadException
{
	public LevelSizeTooSmallException(final String levelName, final int width, final int height,
									  final int minWidth, final int minHeight)
	{
		super(levelName, "Level too small ["+ width +", "+ height +"]. Min is ["+ minWidth +", "+ minHeight +"]");
	}
}
