package com.deaddorks.world2d.game.exceptions;

public class VariedLineLengthException extends LevelLoadException
{
	public VariedLineLengthException(final String levelName)
	{
		super(levelName, "Varied line lengths");
	}
}
