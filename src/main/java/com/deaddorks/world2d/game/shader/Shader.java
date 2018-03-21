package com.deaddorks.world2d.game.shader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Shader
{
	
	private enum ShaderType
	{
		VERTEX(0),
		FRAGMENT(1),
		NONE(-1);
		
		private int index;
		
		ShaderType(final int index)
		{
			this.index = index;
		}
	}
	
	private final String vertex, fragment;
	
	public Shader(final String vertex, final String fragment)
	{
		this.vertex = vertex;
		this.fragment = fragment;
	}
	
	public static Shader parseShaderFromFile(final String pathName)
	{
		File file = new File(pathName);
		
		ShaderType type = ShaderType.NONE;
		StringBuilder[] strings = new StringBuilder[] {new StringBuilder(), new StringBuilder()};
		
		int lineNum = 0;
		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			while ((line = reader.readLine()) != null)
			{
				lineNum++;
				if (line.contains("# Shader - "))
				{
					if (line.contains("Vertex"))
					{
						type = ShaderType.VERTEX;
					}
					else if (line.contains("Fragment"))
					{
						type = ShaderType.FRAGMENT;
					}
					else
					{
						type = ShaderType.NONE;
					}
				}
				else
				{
					if (type.index != ShaderType.NONE.index)
					{
						strings[type.index].append(line);
						strings[type.index].append('\n');
					}
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Error shader from file [" + file.getPath() + "] on line (" + lineNum + ")");
		}
		
		return new Shader(strings[ShaderType.VERTEX.index].toString(), strings[ShaderType.FRAGMENT.index].toString());
	}
	
	@Override
	public String toString()
	{
		return "--- Vertex ---\n" + vertex + "--- Fragment ---\n" + fragment;
	}
}
