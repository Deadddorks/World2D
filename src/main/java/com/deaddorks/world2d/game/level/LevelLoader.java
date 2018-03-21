package com.deaddorks.world2d.game.level;

import com.deaddorks.world2d.game.blocks.BlockInfo;
import com.deaddorks.world2d.game.exceptions.FileReadException;
import com.deaddorks.world2d.game.exceptions.LevelFormatException;
import com.deaddorks.world2d.game.exceptions.LevelSizeTooSmallException;
import com.deaddorks.world2d.game.exceptions.VariedLineLengthException;
import com.deaddorks.world2d.game.texture.TextureSection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LevelLoader
{
	
	/*
	-------------------------
	' ' = Air
	'D' = Dirt
	'S' = Stone
	'L' = Lava
	'E' = End of level
	-------------------------
	 */
	
	private static final int MIN_LEVEL_WIDTH = 10;
	private static final int MIN_LEVEL_HEIGHT = 10;
	
	private final int blockSize, windowWidth, windowHeight;
	private final Map<String, TextureSection> sectionMap;
	private final String locPath, extension;
	
	public LevelLoader(final Map<String, TextureSection> sectionMap,
					   final int blockSize, final int windowWidth, final int widowHeight,
					   final String locPath, final String extension)
	{
		this.sectionMap = sectionMap;
		
		this.blockSize = blockSize;
		this.windowWidth = windowWidth;
		this.windowHeight = widowHeight;
		
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
		System.out.println(size.toString());
		validateLevelFormat(levelName, lines);
		List<String> processed = preProcessLines(lines);
		char[][] array = listToArray(levelName, size, processed);
		BlockInfo[][] blocks = arrayToBlockInfo(levelName, size, array);
		
		return new Level(levelName, blocks);
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
	
	private static List<String> preProcessLines(final List<String> lines)
	{
		List<String> newLines = new ArrayList<>();
		
		for (String l : lines)
		{
			newLines.add(l.replaceAll("[-+|]", "S"));
		}
		
		return newLines;
	}
	
	private static char[][] listToArray(final String levelName, final LevelSize size, final List<String> lines)
	{
		char[][] array = new char[size.getHeight()][];
		
		for (int i = 0; i < lines.size(); i++)
		{
			array[i] = lines.get(i).toCharArray();
		}
		
		return array;
	}
	
	private BlockInfo[][] arrayToBlockInfo(final String levelName, final LevelSize size, final char[][] array)
	{
		BlockInfo[][] blocks = new BlockInfo[size.getHeight()][size.getWidth()];
		
		BlockInfo b = null;
		char c;
		int[][] adj;
		TextureSection center, top, right, bottom, left, topRight, bottomRight, bottomLeft, topLeft;
		for (int i = 0; i < size.getHeight(); i++)
		{
			for (int j = 0; j < size.getWidth(); j++)
			{
				c = array[i][j];
				
				switch (c)
				{
					case 'D':
						center = sectionMap.get("dirt-center");
						adj = new int[][] {
								{array[i-1][i-1] == ' ' ? 1 : 0, array[i-1][i] == ' ' ? 10 : 0, array[i-1][i+1] == ' ' ? 1 : 0},
								{array[i][i-1] == ' ' ? 100 : 0, 0, array[i][i+1] == ' ' ? 100 : 0},
								{array[i+1][i-1] == ' ' ? 1 : 0, array[i+1][i] == ' ' ? 10 : 0, array[i+1][i+1] == ' ' ? 1 : 0}
						};
						
						// Top
						if (adj[0][1] == 10)
						{
							top = sectionMap.get("grass-top");
						}
						else
						{
							top = sectionMap.get("dirt-top");
						}
						// Bottom
						if (adj[2][1] == 10)
						{
							bottom = sectionMap.get("grass-bottom");
						}
						else
						{
							bottom = sectionMap.get("dirt-bottom");
						}
						// Left
						if (adj[1][0] == 10)
						{
							left = sectionMap.get("grass-left");
						}
						else
						{
							left = sectionMap.get("dirt-left");
						}
						// Right
						if (adj[1][2] == 10)
						{
							right = sectionMap.get("grass-right");
						}
						else
						{
							right = sectionMap.get("dirt-right");
						}
						
						// Top-Left
						switch (adj[0][0] + adj[0][1] + adj[1][0])
						{
							case 0:
								topLeft = sectionMap.get("dirt-top-left");
								break;
							case 1:
								topLeft = sectionMap.get("adj-grass-top-left");
								break;
							default:
								topLeft = sectionMap.get("grass-top-left");
						}
						// Top-Right
						switch (adj[0][2] + adj[0][1] + adj[1][2])
						{
							case 0:
								topRight = sectionMap.get("dirt-top-right");
								break;
							case 1:
								topRight = sectionMap.get("adj-grass-top-right");
								break;
							default:
								topRight = sectionMap.get("grass-top-right");
						}
						// Bottom-Left
						switch (adj[2][0] + adj[2][1] + adj[1][0])
						{
							case 0:
								bottomLeft = sectionMap.get("dirt-bottom-left");
								break;
							case 1:
								bottomLeft = sectionMap.get("adj-grass-bottom-left");
								break;
							default:
								bottomLeft = sectionMap.get("grass-bottom-left");
						}
						// Bottom-Right
						switch (adj[2][2] + adj[2][1] + adj[1][2])
						{
							case 0:
								bottomRight = sectionMap.get("dirt-bottom-right");
								break;
							case 1:
								bottomRight = sectionMap.get("adj-grass-bottom-right");
								break;
							default:
								bottomRight = sectionMap.get("grass-bottom-right");
						}
						
						b = new BlockInfo(i, j, center, top, right, bottom, left, topRight, bottomRight, bottomLeft, topLeft);
						break;
					case 'E':
						center = sectionMap.get("portal-center");
						adj = new int[][] {
								{0, array[i-1][i] != 'E' ? 10 : 0, 0},
								{array[i][i-1] != 'E' ? 100 : 0, 0, array[i][i+1] != 'E' ? 100 : 0},
								{0, array[i+1][i] != 'E' ? 10 : 0, 0}
						};
						
						// Top
						if (adj[0][1] == 10)
						{
							top = sectionMap.get("portal-edge-top");
						}
						else
						{
							top = sectionMap.get("portal-top");
						}
						// Bottom
						if (adj[2][1] == 10)
						{
							bottom = sectionMap.get("portal-edge-bottom");
						}
						else
						{
							bottom = sectionMap.get("portal-bottom");
						}
						// Left
						if (adj[1][0] == 10)
						{
							left = sectionMap.get("portal-edge-left");
						}
						else
						{
							left = sectionMap.get("portal-left");
						}
						// Right
						if (adj[1][2] == 10)
						{
							right = sectionMap.get("portal-edge-right");
						}
						else
						{
							right = sectionMap.get("portal-right");
						}
						
						// Top-Left
						switch (adj[0][0] + adj[0][1] + adj[1][0])
						{
							case 0:
								topLeft = sectionMap.get("portal-top-left");
								break;
							default:
								topLeft = sectionMap.get("portal-edge-top-left");
						}
						// Top-Right
						switch (adj[0][2] + adj[0][1] + adj[1][2])
						{
							case 0:
								topRight = sectionMap.get("portal-top-right");
								break;
							default:
								topRight = sectionMap.get("portal-edge-top-right");
						}
						// Bottom-Left
						switch (adj[2][0] + adj[2][1] + adj[1][0])
						{
							case 0:
								bottomLeft = sectionMap.get("portal-bottom-left");
								break;
							default:
								bottomLeft = sectionMap.get("portal-edge-bottom-left");
						}
						// Bottom-Right
						switch (adj[2][2] + adj[2][1] + adj[1][2])
						{
							case 0:
								bottomRight = sectionMap.get("portal-bottom-right");
								break;
							default:
								bottomRight = sectionMap.get("portal-edge-bottom-right");
						}
						
						b = new BlockInfo(i, j, center, top, right, bottom, left, topRight, bottomRight, bottomLeft, topLeft);
						break;
					default:
						switch (c)
						{
							case 'A':
								b = new BlockInfo(i, j, sectionMap.get("air-all"));
								break;
							case 'S':
								b = new BlockInfo(i, j, sectionMap.get("stone-all"));
								break;
							case 'L':
								b = new BlockInfo(i, j, sectionMap.get("lava-all"));
								break;
							case 'W':
								b = new BlockInfo(i, j, sectionMap.get("water-all"));
								break;
							default:
								b = null;
						}
				}
				blocks[i][j] = b;
			}
		}
		
		return blocks;
	}
	
}
