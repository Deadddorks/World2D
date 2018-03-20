package com.deaddorks.world2d.game.texture;

public class TextureSection
{
	
	final String name;
	private TexturePoint[] points;
	
	public TextureSection(final String name, final TexturePoint p1, final TexturePoint p2, final TexturePoint p3, final TexturePoint p4)
	{
		this.name = name;
		points = new TexturePoint[] {p1, p2, p3, p4};
	}
	
	public TexturePoint[] getPoints()
	{
		return points;
	}
	
}
