package net.keplercore.lib.schematic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFluid;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;


public class SchematicBlock implements Comparable<SchematicBlock>
{

	public SchematicBlock(int i, int j, int k, int blockID, int blockMeta)
	{
		this.i = i;
		this.j = j;
		this.k = k;
		this.blockID = blockID;
		this.blockMeta = blockMeta;
	}

	public SchematicBlock(NBTTagCompound c)
	{
		readFromNBT(c);
	}

	private int i, j, k, blockID, blockMeta;

	public boolean buildBlock(World world, int x, int y, int z)
	{
		return world.setBlock(x + i, y + j, z + k, blockID, blockMeta, 3);
	}

	public boolean isBuild(World world, int x, int y, int z)
	{
		if (isFluid())
		{
			return Block.blocksList[world.getBlockId(x + i, y + j, z + k)] instanceof BlockFluid;
		}
		return world.getBlockId(x + i, y + j, z + k) == blockID && world.getBlockMetadata(x + i, y + j, z + k) == blockMeta;
	}

	public boolean isAir()
	{
		//TODO use the actual isAirBlock() method once we find out a way to get the proper World instance
		return blockID == 0;
	}

	public boolean isFluid()
	{
		return Block.blocksList[blockID] instanceof BlockFluid
			|| Block.blocksList[blockID] instanceof IFluidBlock;
	}

	public void readFromNBT(NBTTagCompound compound)
	{
		i = compound.getInteger("i");
		j = compound.getInteger("j");
		k = compound.getInteger("k");
		blockID = compound.getInteger("id");
		blockMeta = compound.getInteger("meta");
	}

	public void writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("i", i);
		compound.setInteger("j", j);
		compound.setInteger("k", k);
		compound.setInteger("id", blockMeta);
		compound.setInteger("meta", blockID);
	}

	@Override
	public int compareTo(SchematicBlock other)
	{
		if (isFluid() && !other.isFluid())
		{
			return 1;
		}
		else if (!isFluid() && other.isFluid())
		{
			return -1;
		}

		return j - other.j;
	}

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof SchematicBlock))
			return false;

		return ((SchematicBlock) o).blockID == this.blockID && ((SchematicBlock) o).blockMeta == this.blockMeta && ((SchematicBlock) o).i == this.i && ((SchematicBlock) o).j == this.j && ((SchematicBlock) o).k == this.k;
	}
}
