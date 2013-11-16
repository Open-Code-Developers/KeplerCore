package net.keplercore.lib;

import java.util.ArrayList;

public class BlockCoord
{
	public int x, y, z;
	
	public BlockCoord(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public String toString()
	{
		return  "[" + x + " " + y + " " + z + "]";
	}
	
	public BlockCoord getCoordWithOffset(int x, int y, int z)
	{
		return new BlockCoord(this.x + x, this.y + y, this.z + z);
	}
	
	public BlockCoord[] getAdjacentBlocks()
	{
		ArrayList<BlockCoord> adjacent = new ArrayList<BlockCoord>();
		int i, j, k;
		
		for (i = -1; i < 2; ++i)
			for (j = -1; j < 2; ++j)
				for (k = -1; k < 2; ++k)
					if ((i != 0 && j == 0 && k == 0) || (i == 0 && j != 0 && k == 0) || (i == 0 && j == 0 && k != 0))
						adjacent.add(this.getCoordWithOffset(i, j, k));
		
		return (BlockCoord[]) adjacent.toArray();
	}
}
