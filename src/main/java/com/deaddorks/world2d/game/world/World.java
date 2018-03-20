package com.deaddorks.world2d.game.world;

import com.deaddorks.world2d.game.level.Level;
import com.deaddorks.world2d.game.level.LevelLoader;
import com.deaddorks.world2d.game.texture.TextureMap;

public class World
{
	
	private Level level;
	private LevelLoader loader;
	private TextureMap textureMap;
	
	public World()
	{
		loader = new LevelLoader("levels/", ".tdl");
		
		textureMap = new TextureMap(16, 8);
		
		initTextures();
		
		level = loader.load("level-1");
	}
	
	private void initTextures()
	{
		// ----- Add points -----
		textureMap.addPoint(0, 0);
		textureMap.addPoint(4, 0);
		textureMap.addPoint(8, 0);
		textureMap.addPoint(12, 0);
		textureMap.addPoint(14, 0);
		textureMap.addPoint(15, 0);
		textureMap.addPoint(16, 0);
		
		textureMap.addPoint(12, 2);
		textureMap.addPoint(14, 2);
		textureMap.addPoint(15, 2);
		textureMap.addPoint(16, 2);
		
		textureMap.addPoint(12, 3);
		textureMap.addPoint(14, 3);
		textureMap.addPoint(15, 3);
		textureMap.addPoint(16, 3);
		
		textureMap.addPoint(0, 4);
		textureMap.addPoint(1, 4);
		textureMap.addPoint(2, 4);
		textureMap.addPoint(4, 4);
		textureMap.addPoint(5, 4);
		textureMap.addPoint(6, 4);
		textureMap.addPoint(8, 4);
		textureMap.addPoint(12, 4);
		textureMap.addPoint(13, 4);
		textureMap.addPoint(14, 4);
		textureMap.addPoint(15, 4);
		textureMap.addPoint(16, 4);
		
		textureMap.addPoint(4, 5);
		textureMap.addPoint(5, 5);
		textureMap.addPoint(6, 5);
		textureMap.addPoint(8, 5);
		textureMap.addPoint(14, 5);
		textureMap.addPoint(15, 5);
		textureMap.addPoint(16, 5);
		
		textureMap.addPoint(0, 6);
		textureMap.addPoint(1, 6);
		textureMap.addPoint(2, 6);
		textureMap.addPoint(4, 6);
		textureMap.addPoint(5, 6);
		textureMap.addPoint(6, 6);
		textureMap.addPoint(7, 6);
		textureMap.addPoint(8, 6);
		textureMap.addPoint(12, 6);
		textureMap.addPoint(13, 6);
		textureMap.addPoint(14, 6);
		
		textureMap.addPoint(0, 7);
		textureMap.addPoint(1, 7);
		textureMap.addPoint(2, 7);
		textureMap.addPoint(4, 7);
		textureMap.addPoint(6, 7);
		textureMap.addPoint(7, 7);
		textureMap.addPoint(8, 7);
		textureMap.addPoint(12, 7);
		textureMap.addPoint(14, 7);
		
		textureMap.addPoint(0, 8);
		textureMap.addPoint(1, 8);
		textureMap.addPoint(2, 8);
		textureMap.addPoint(4, 8);
		textureMap.addPoint(5, 8);
		textureMap.addPoint(6, 8);
		textureMap.addPoint(7, 8);
		textureMap.addPoint(8, 8);
		textureMap.addPoint(12, 8);
		textureMap.addPoint(14, 8);
		
		// ----- Add Sections -----
		// Full
		textureMap.addSection("lava-all", 0, 1, 7, 8);
		textureMap.addSection("water-all", 1, 2, 8, 9);
		textureMap.addSection("air-all", 2, 3, 9, 10);
		textureMap.addSection("stone-all", 9, 10, 61, 62);
		// Dirt
		textureMap.addSection("dirt-center", 17, 18, 36, 37);
		textureMap.addSection("dirt-top", 47, 48, 56, 57);
		textureMap.addSection("dirt-bottom", 36, 37, 47, 48);
		textureMap.addSection("dirt-left", 15, 16, 34, 35);
		textureMap.addSection("dirt-right", 16, 17, 35, 36);
		textureMap.addSection("dirt-top-left", 0, 0, 0, 0);
		textureMap.addSection("dirt-top-right", 0, 0, 0, 0);
		textureMap.addSection("dirt-bottom-left", 0, 0, 0, 0);
		textureMap.addSection("dirt-bottom-right", 0, 0, 0, 0);
		// Grass
		textureMap.addSection("grass-top", 29, 30, 39, 40);
		textureMap.addSection("grass-bottom", 20, 21, 29, 30);
		textureMap.addSection("grass-left", 37, 38, 57, 58);
		textureMap.addSection("grass-right", 38, 39, 58, 59);
		textureMap.addSection("grass-top-left", 49, 50, 59, 60);
		textureMap.addSection("grass-top-right", 50, 51, 60, 61);
		textureMap.addSection("grass-bottom-left", 39, 40, 49, 50);
		textureMap.addSection("grass-bottom-right", 40, 41, 50, 51);
		textureMap.addSection("adj-grass-top-left", 27, 28, 37, 38);
		textureMap.addSection("adj-grass-top-right", 28, 29, 38, 39);
		textureMap.addSection("adj-grass-bottom-left", 18, 19, 27, 28);
		textureMap.addSection("adj-grass-bottom-right", 19, 20, 28, 29);
		// Portal Center
		textureMap.addSection("portal-center", 3, 4, 7, 8);
		textureMap.addSection("portal-top", 11, 12, 22, 23);
		textureMap.addSection("portal-bottom", 7, 8, 11, 12);
		textureMap.addSection("portal-left", 4, 5, 8, 9);
		textureMap.addSection("portal-right", 5, 6, 9, 10);
		textureMap.addSection("portal-top-left", 12, 13, 24, 25);
		textureMap.addSection("portal-top-right", 13, 14, 25, 26);
		textureMap.addSection("portal-bottom-left", 8, 9, 12, 13);
		textureMap.addSection("portal-bottom-right", 9, 10, 13, 14);
		// Portal Edge
		textureMap.addSection("portal-edge-top", 52, 53, 62, 63);
		textureMap.addSection("portal-edge-bottom", 42, 43, 52, 53);
		textureMap.addSection("portal-edge-left", 22, 23, 42, 43);
		textureMap.addSection("portal-edge-right", 23, 24, 43, 44);
		textureMap.addSection("portal-edge-top-left", 31, 32, 42, 43);
		textureMap.addSection("portal-edge-top-right", 32, 33, 43, 44);
		textureMap.addSection("portal-edge-bottom-left", 24, 25, 31, 32);
		textureMap.addSection("portal-edge-bottom-right", 25, 26, 32, 33);
		
		// --- Print ---
		System.out.println(textureMap.toString());
		textureMap.printRowStarts();
	}
	
}
