package com.deaddorks.world2d.game.level;

import com.deaddorks.world2d.game.blocks.BlockInfo;
import com.deaddorks.world2d.game.exceptions.FileReadException;
import com.deaddorks.world2d.game.exceptions.LevelFormatException;
import com.deaddorks.world2d.game.exceptions.LevelSizeTooSmallException;
import com.deaddorks.world2d.game.exceptions.VariedLineLengthException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader
{
	
	private static final int MIN_LEVEL_WIDTH = 10;
	private static final int MIN_LEVEL_HEIGHT = 10;
	
	private final String locPath, extension;
	
	public LevelLoader(final String locPath, final String extension)
	{
		this.locPath = locPath;
		this.extension = extension;
	}
	
	public Level load(final String levelName)
	{
		List<String> lines = new ArrayList<>();
		
		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(locPath + levelName + extension))))
		{
			while ((line = reader.readLine()) != null)
			{
				lines.add(line);
			}
		}
		catch (IOException e)
		{
			throw new FileReadException(levelName, e);
		}
		
		LevelSize size = getLevelSize(levelName, lines);
		validateLevelFormat(levelName, lines);
		
		throw new UnsupportedOperationException();
	}
	
	private static LevelSize getLevelSize(final String levelName, final List<String> lines)
	{
		int width = -1, height = lines.size();
		
		for (String line : lines)
		{
			if (width == -1)
			{
				width = line.length();
			}
			else
			{
				if (width != line.length())
				{
					throw new VariedLineLengthException(levelName);
				}
			}
		}
		
		if (width < MIN_LEVEL_WIDTH || height < MIN_LEVEL_HEIGHT)
		{
			throw new LevelSizeTooSmallException(levelName, width, height, MIN_LEVEL_WIDTH, MIN_LEVEL_HEIGHT);
		}
		
		return new LevelSize(width, height);
	}
	
	private static void validateLevelFormat(final String levelName, final List<String> lines)
	{
		String line;
		char first, last;
		String middle;
		
		for (int i = 0; i < lines.size(); i++)
		{
			line = lines.get(i);
			
			first = line.charAt(0);
			last = line.charAt(line.length() - 1);
			middle = line.substring(1, line.length() - 1);
			// System.out.println("first: ["+ Character.toString(first) +"], last: ["+ Character.toString(last) +"], middle: ["+ middle +"]");
			
			if (i == 0 || i == lines.size() - 1)
			{
				if (first != '+' || last != '+')
				{
					throw new LevelFormatException(levelName);
				}
				for (char c : middle.toCharArray())
				{
					if (c != '-')
					{
						throw new LevelFormatException(levelName);
					}
				}
			}
			else
			{
				if (first != '|' || last != '|')
				{
					throw new LevelFormatException(levelName);
				}
				for (char c : middle.toCharArray())
				{
					if (c == '+' || c == '-' || c == '|')
					{
						throw new LevelFormatException(levelName);
					}
				}
			}
		}
		
	}
	
	private static BlockInfo[][] buildBlockInfo(final String levelName, final LevelSize size, final List<String> lines)
	{
		BlockInfo[][] blocks = new BlockInfo[size.getHeight()][size.getWidth()];
		throw new UnsupportedOperationException();
	}
	
}
