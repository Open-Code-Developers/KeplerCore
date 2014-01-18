package net.keplercore.lib.schematic;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidBlock;

public class SchematicBlock implements Comparable<SchematicBlock>
{
    private int i, j, k;
    private String blockName;
    private int blockMeta;
    
    public SchematicBlock(int i, int j, int k, String blockName, int blockMeta)
    {
        this.i = i;
        this.j = j;
        this.k = k;
        this.blockName = blockName;
        this.blockMeta = blockMeta;
    }
    
    public SchematicBlock(int i, int j, int k, int blockID, int blockMeta)
    {
        this.i = i;
        this.j = j;
        this.k = k;
        this.blockName = Block.field_149771_c.func_148750_c(Block.func_149729_e(blockID));
        this.blockMeta = blockMeta;
    }

	public SchematicBlock(NBTTagCompound c)
	{
		readFromNBT(c);
	}

	public boolean buildBlock(World world, int x, int y, int z)
	{
		return world.func_147465_d(x + i, y + j, z + k,
		        getBlock(), blockMeta, 3);
	}

	public boolean isBuilt(World world, int x, int y, int z)
	{
		if (isFluid())
		{
			return world.func_147439_a(x + i, y + j, z + k) instanceof BlockLiquid;
		}
		return world.func_147439_a(x + i, y + j, z + k) == getBlock()
		        && world.getBlockMetadata(x + i, y + j, z + k) == blockMeta;
	}

	public boolean isAir()
	{
		return getBlock().isAir(null, 0, 0, 0);
	}

	public boolean isFluid()
	{
		return getBlock() instanceof BlockLiquid
			|| getBlock() instanceof IFluidBlock;
	}

    public Block getBlock()
    {
        return Block.func_149684_b(blockName);
    }

	public void readFromNBT(NBTTagCompound compound)
	{
		i = compound.getInteger("i");
		j = compound.getInteger("j");
		k = compound.getInteger("k");
		blockName = compound.getString("name");
		//Legacy support
		if (blockName == null || blockName.isEmpty())
		{
		    blockName = Block.field_149771_c.func_148750_c(Block.func_149729_e(compound.getInteger("id")));
		}
		blockMeta = compound.getInteger("meta");
	}

	public void writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("i", i);
		compound.setInteger("j", j);
		compound.setInteger("k", k);
		compound.setString("name", blockName);
		compound.setInteger("meta", blockMeta);
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

		return ((SchematicBlock) o).blockName == this.blockName
		        && ((SchematicBlock) o).blockMeta == this.blockMeta
		        && ((SchematicBlock) o).i == this.i
		        && ((SchematicBlock) o).j == this.j
		        && ((SchematicBlock) o).k == this.k;
	}
}
