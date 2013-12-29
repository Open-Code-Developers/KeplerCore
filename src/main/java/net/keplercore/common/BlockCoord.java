package net.keplercore.common;

import java.util.ArrayList;

import net.minecraftforge.common.util.ForgeDirection;

public class BlockCoord
{
	public int x, y, z;
	
	public BlockCoord(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public BlockCoord()
	{
		this(0, 0, 0);
	}
	
	public BlockCoord(ForgeDirection direction)
	{
		this(direction.offsetX, direction.offsetY, direction.offsetZ);
	}
	
	public BlockCoord(int[] coords)
	{
		this(coords[0], coords[1], coords[2]);
	}
	
	@Override
	public String toString()
	{
		return  "[" + x + " " + y + " " + z + "]";
	}
	
	@Override
	public boolean equals(Object object)
	{
		if (object == null || !(object instanceof BlockCoord))
			return false;
		
		BlockCoord coord = (BlockCoord) object;
		
		return coord.x == x && coord.y == y && coord.z == z;
	}
	
	@Override
	public int hashCode()
	{
		return (x + 128) << 16 | (y + 128) << 8 | (z + 128);
	}
	
	@Override
	public BlockCoord clone()
	{
		return new BlockCoord(x, y, z);
	}
	
	public void offset(int x, int y, int z)
	{
		this.x += x;
		this.y += y;
		this.z += z;
	}
	
	public void offset(ForgeDirection direction, int distance)
	{
		offset(direction.offsetX * distance, direction.offsetY * distance, direction.offsetZ * distance);
	}
	
	public void offset(ForgeDirection direction)
	{
		offset(direction, 1);
	}
	
	public void offset(int[] offsets)
	{
		offset(offsets[0], offsets[1], offsets[2]);
	}
	
	public void offset(BlockCoord offset)
	{
		offset(offset.x, offset.y, offset.z);
	}
	
	public BlockCoord getCoordWithOffset(int x, int y, int z)
	{
		return new BlockCoord(this.x + x, this.y + y, this.z + z);
	}
	
	public BlockCoord getAdjacentCoord(ForgeDirection direction)
	{
		return this.getAdjacentCoord(direction, 1);
	}
	
	public BlockCoord getAdjacentCoord(ForgeDirection direction, int distance)
	{
		return this.getCoordWithOffset(direction.offsetX * distance, direction.offsetY * distance, direction.offsetZ * distance);
	}
	
	public BlockCoord[] getAdjacentCoords()
	{
		ArrayList<BlockCoord> adjacent = new ArrayList<BlockCoord>();
		int i, j, k;
		
		for (i = -1; i < 2; ++i)
			for (j = -1; j < 2; ++j)
				for (k = -1; k < 2; ++k)
					if (i != j && j != k && i != k)
						adjacent.add(getCoordWithOffset(i, j, k));
		
		return (BlockCoord[]) adjacent.toArray();
	}
	
	public int[] getCoordsArray()
	{
		return new int[]
		{
			x,
			y,
			z
		};
	}
}
