package com.deaddorks.world2d.game.exceptions;

import java.io.EOFException;
import java.io.IOException;

public class FileReadException extends LevelLoadException
{
	public FileReadException(final String levelName, final IOException e)
	{
		super(levelName, e.getMessage());
	}
}
