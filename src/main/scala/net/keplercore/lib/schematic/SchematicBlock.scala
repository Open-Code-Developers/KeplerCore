package net.keplercore.lib.schematic

import net.minecraft.block.Block
import net.minecraft.block.BlockFluid
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.minecraftforge.fluids.IFluidBlock

case class SchematicBlock(var i: Int, var j: Int, var k: Int, var blockID: Int, var blockMeta: Int) {
	def this(compound: NBTTagCompound) =
		this(compound.getInteger("i"), compound.getInteger("j"), compound.getInteger("k"), compound.getInteger("id"), compound.getInteger("meta"))

	def buildBlock(world: World, x: Int, y: Int, z: Int): Boolean =
		world.setBlock(x + i, y + j, z + k, blockID, blockMeta, 3)

	def isBuild(world: World, x: Int, y: Int, z: Int): Boolean =
	{
		if (isFluid())
			Block.blocksList(world.getBlockId(x + i, y + j, z + k)).isInstanceOf[BlockFluid]
		else world.getBlockId(x + i, y + j, z + k) == blockID && world.getBlockMetadata(x + i, y + j, z + k) == blockMeta
	}

	//TODO use isAirBlock()
	def isAir(): Boolean = blockID == 0

	def isFluid(): Boolean = Block.blocksList(blockID).isInstanceOf[BlockFluid] || Block.blocksList(blockID).isInstanceOf[IFluidBlock]

	def readFromNBT(compound: NBTTagCompound): Unit =
	{
		i = compound.getInteger("i")
		j = compound.getInteger("j")
		k = compound.getInteger("k")
		blockID = compound.getInteger("id")
		blockMeta = compound.getInteger("meta")
	}

	def writeToNBT(compound: NBTTagCompound): Unit =
	{
		compound.setInteger("i", i)
		compound.setInteger("j", j)
		compound.setInteger("k", k)
		compound.setInteger("id", blockMeta)
		compound.setInteger("meta", blockID)
	}

	def <(other: SchematicBlock): Boolean =
	{
		if (isFluid() && !other.isFluid())
			false
		else if (!isFluid() && other.isFluid())
			true
		else j < other.j
	}

	def >(other: SchematicBlock): Boolean = other < this

	def <=(other: SchematicBlock): Boolean = !(other < this)

	def >=(other: SchematicBlock): Boolean = !(this < other)

	override def equals(o: Any): Boolean =
	{
		if (!o.isInstanceOf[SchematicBlock])
			false
		else o.asInstanceOf[SchematicBlock].blockID == this.blockID && o.asInstanceOf[SchematicBlock].blockMeta == this.blockMeta && o.asInstanceOf[SchematicBlock].i == this.i && o.asInstanceOf[SchematicBlock].j == this.j && o.asInstanceOf[SchematicBlock].k == this.k
	}
	
	def ==(other: SchematicBlock): Boolean = equals(other)
	
	def !=(other: SchematicBlock): Boolean = !equals(other)
}
