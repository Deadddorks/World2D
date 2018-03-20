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
		textureMap.addSection("dirt-center", 0, 0, 0, 0);
		textureMap.addSection("dirt-top", 0, 0, 0, 0);
		textureMap.addSection("dirt-bottom", 0, 0, 0, 0);
		textureMap.addSection("dirt-left", 0, 0, 0, 0);
		textureMap.addSection("dirt-right", 0, 0, 0, 0);
		textureMap.addSection("dirt-top-left", 0, 0, 0, 0);
		textureMap.addSection("dirt-top-right", 0, 0, 0, 0);
		textureMap.addSection("dirt-bottom-left", 0, 0, 0, 0);
		textureMap.addSection("dirt-bottom-right", 0, 0, 0, 0);
		// Grass
		textureMap.addSection("grass-top", 0, 0, 0, 0);
		textureMap.addSection("grass-bottom", 0, 0, 0, 0);
		textureMap.addSection("grass-left", 0, 0, 0, 0);
		textureMap.addSection("grass-right", 0, 0, 0, 0);
		textureMap.addSection("grass-top-left", 0, 0, 0, 0);
		textureMap.addSection("grass-top-right", 0, 0, 0, 0);
		textureMap.addSection("grass-bottom-left", 0, 0, 0, 0);
		textureMap.addSection("grass-bottom-right", 0, 0, 0, 0);
		textureMap.addSection("adj-grass-top-left", 0, 0, 0, 0);
		textureMap.addSection("adj-grass-top-right", 0, 0, 0, 0);
		textureMap.addSection("adj-grass-bottom-left", 0, 0, 0, 0);
		textureMap.addSection("adj-grass-bottom-right", 0, 0, 0, 0);
		// Portal Center
		textureMap.addSection("portal-center", 0, 0, 0, 0);
		textureMap.addSection("portal-top", 0, 0, 0, 0);
		textureMap.addSection("portal-bottom", 0, 0, 0, 0);
		textureMap.addSection("portal-left", 0, 0, 0, 0);
		textureMap.addSection("portal-right", 0, 0, 0, 0);
		textureMap.addSection("portal-top-left", 0, 0, 0, 0);
		textureMap.addSection("portal-top-right", 0, 0, 0, 0);
		textureMap.addSection("portal-bottom-left", 0, 0, 0, 0);
		textureMap.addSection("portal-bottom-right", 0, 0, 0, 0);
		// Portal Edge
		textureMap.addSection("portal-edge-top", 0, 0, 0, 0);
		textureMap.addSection("portal-edge-bottom", 0, 0, 0, 0);
		textureMap.addSection("portal-edge-left", 0, 0, 0, 0);
		textureMap.addSection("portal-edge-right", 0, 0, 0, 0);
		textureMap.addSection("portal-edge-top-left", 0, 0, 0, 0);
		textureMap.addSection("portal-edge-top-right", 0, 0, 0, 0);
		textureMap.addSection("portal-edge-bottom-left", 0, 0, 0, 0);
		textureMap.addSection("portal-edge-bottom-right", 0, 0, 0, 0);
		
		// --- Print ---
		System.out.println(textureMap.toString());
		textureMap.printRowStarts();
	}
	
}
