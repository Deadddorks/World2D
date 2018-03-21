package com.deaddorks.world2d.game.texture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextureMap
{

	private final int width, height;
	private List<TexturePoint> points;
	private List<TextureSection> sections;
	
	public TextureMap(final int width, final int height)
	{
		this.width = width;
		this.height = height;
		
		points = new ArrayList<>();
		sections = new ArrayList<>();
	}
	
	public void addPoint(final int x, final int y)
	{
		if (x < 0 || x > width)
		{
			throw new IllegalArgumentException("x out of bounds. ["+ x +"] (0 <= x <= "+ width +")");
		}
		if (y < 0 || y > height)
		{
			throw new IllegalArgumentException("y out of bounds. ["+ y +"] (0 <= y <= "+ height +")");
		}
		
		points.add(new TexturePoint((float) x / width, (float) y / height));
	}
	
	public void addSection(final String name, final int p1, final int p2, final int p3, final int p4)
	{
		if (p1 < 0 || p1 >= points.size())
		{
			throw new IllegalArgumentException("p1 out of bounds. ["+ p1 +"] (0 <= p1 < "+ points.size() +")");
		}
		if (p2 < 0 || p2 >= points.size())
		{
			throw new IllegalArgumentException("p2 out of bounds. ["+ p2 +"] (0 <= p2 < "+ points.size() +")");
		}
		if (p3 < 0 || p3 >= points.size())
		{
			throw new IllegalArgumentException("p3 out of bounds. ["+ p3 +"] (0 <= p3 < "+ points.size() +")");
		}
		if (p4 < 0 || p4 >= points.size())
		{
			throw new IllegalArgumentException("p4 out of bounds. ["+ p4 +"] (0 <= p4 < "+ points.size() +")");
		}
		
		sections.add(new TextureSection(
				name,
				points.get(p1),
				points.get(p2),
				points.get(p3),
				points.get(p4)
		));
	}
	
	public Map<String, TextureSection> generateSectionMap()
	{
		Map<String, TextureSection> map = new HashMap<>();
		
		for (TextureSection s : sections)
		{
			map.put(s.getName(), s);
		}
		
		return map;
	}
	
	public void printRowStarts()
	{
		float row = -1, row2;
		TexturePoint p;
		
		for (int i = 0; i < points.size(); i++)
		{
			p = points.get(i);
			
			if ((row2 = p.getY() * height) != row)
			{
				row = row2;
				if (row != 0)
				{
					System.out.println(" and ends at ["+ (i - 1) +"]");
				}
				System.out.print("row ("+ ((int) row) +") starts at index ["+ i +"]");
			}
		}
		System.out.println(" and ends at ["+ (points.size() - 1) +"]");
	}
	
	@Override
	public String toString()
	{
		return "{TextureMap} (number of TexturePoints: ["+ points.size() +"], number of TextureSections: ["+ sections.size() +"])";
	}
}
