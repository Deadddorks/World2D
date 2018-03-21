package com.deaddorks.world2d.game.shader;

import org.lwjgl.BufferUtils;
import sun.plugin.dom.exception.InvalidStateException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.IntBuffer;

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
	
	private int id;
	private final String vertex, fragment;
	
	public Shader(final String vertex, final String fragment)
	{
		this.vertex = vertex;
		this.fragment = fragment;
	}
	
	public void compile()
	{
		int id = glCreateProgram();
		int vs = compileShader(GL_VERTEX_SHADER, vertex);
		int fs = compileShader(GL_FRAGMENT_SHADER, fragment);
		
		glAttachShader(id, vs);
		glAttachShader(id, fs);
		glLinkProgram(id);
		glValidateProgram(id);
		
		glDeleteShader(vs);
		glDeleteShader(fs);
		
		this.id = id;
	}
	
	public void destroy()
	{
		// unbind ... ?
		glDeleteProgram(id);
		glUseProgram(0);
		id = -1;
	}
	
	public void use()
	{
		glUseProgram(getId());
	}
	
	private static int compileShader(final int type, final String code)
	{
		int id = glCreateShader(type);
		
		glShaderSource(id, code);
		glCompileShader(id);
		
		// Check for proper compilation (Int buffer?)
		IntBuffer result = BufferUtils.createIntBuffer(1);
		glGetShaderiv(id, GL_COMPILE_STATUS, result);
		if (result.get() == GL_FALSE)
		{
			System.out.println("Error Compiling shader: " + glGetShaderInfoLog(id));
			
		}
		
		return id;
	}
	
	public int getId()
	{
		if (id == -1)
		{
			throw new InvalidStateException("Shader is not active");
		}
		return id;
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
