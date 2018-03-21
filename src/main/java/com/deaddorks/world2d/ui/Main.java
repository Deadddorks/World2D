package com.deaddorks.world2d.ui;

import com.deaddorks.world2d.game.shader.Shader;
import com.deaddorks.world2d.game.world.World;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.*;

public class Main
{
	
	private static final int WIDTH = 720;
	private static final int HEIGHT = 480;
	
	public static void main(String[] args)
	{
		
		if (!glfwInit())
		{
			throw new IllegalStateException("glfwInit()");
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_COMPAT_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		// glfwWindowHint(GLFW_DECORATED, GLFW_FALSE);
		
		long window = glfwCreateWindow(WIDTH, HEIGHT, "LWJGL", 0, 0);
		if (window == 0)
		{
			throw new IllegalStateException("glfwCreateWindow()");
		}
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidMode.width() - WIDTH) / 2, (vidMode.height() - HEIGHT) / 2);
		glfwSetWindowIcon(window, null);
		
		System.out.println("<Version>: " + glGetString(GL_VERSION));
		System.out.println("primary monitor -> width: ["+ vidMode.width() +"], height: ["+ vidMode.height() +"]");
		
		Shader shader = Shader.parseShaderFromFile("shaders/world.shader");
		System.out.println(shader.toString());
		
		World world = new World(WIDTH, HEIGHT);
		
		// Show window
		glfwShowWindow(window);
		// Game Loop
		while (!glfwWindowShouldClose(window))
		{
			glClear(GL_COLOR_BUFFER_BIT);
			
			world.draw();
			
			glfwSwapBuffers(window);
			
			glfwPollEvents();
		}
		
		glfwTerminate();
		
	}
	
}
