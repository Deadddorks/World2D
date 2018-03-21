package com.deaddorks.world2d.game.blocks;

import com.deaddorks.world2d.game.texture.TextureSection;

public class BlockInfo
{
	
	private enum Type {FULL, SPLIT}
	
	private final int x, y;
	private final Type type;
	private final TextureSection center, top, right, bottom, left, topRight, bottomRight, bottomLeft, topLeft;
	
	public BlockInfo(final int x, final int y, final TextureSection section)
	{
		this.type = Type.FULL;
		
		this.x = x;
		this.y = y;
		this.center = section;
		
		this.top = null;
		this.right = null;
		this.bottom = null;
		this.left = null;
		
		this.topRight = null;
		this.bottomRight = null;
		this.bottomLeft = null;
		this.topLeft = null;
	}
	
	public BlockInfo(final int x, final int y, final TextureSection center,
					 final TextureSection top, final TextureSection right, final TextureSection bottom, final TextureSection left,
					 final TextureSection topRight, final TextureSection bottomRight, final TextureSection bottomLeft, final TextureSection topLeft)
	{
		this.type = Type.SPLIT;
		
		this.x = x;
		this.y = y;
		this.center = center;
		
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
		
		this.topRight = topRight;
		this.bottomRight = bottomRight;
		this.bottomLeft = bottomLeft;
		this.topLeft = topLeft;
	}

}
