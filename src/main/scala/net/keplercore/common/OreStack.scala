package net.keplercore.common

import java.util.ArrayList

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.oredict.OreDictionary

case class OreStack(var stackSize: Int, var oreID: Int) {
	def this(stack: ItemStack, size: Int) = this(OreDictionary.getOreID(stack), size)

	def this(stack: ItemStack) = this(stack, 1)

	def this(block: Block, size: Int) = this(new ItemStack(block), size)

	def this(block: Block) = this(block, 1)

	def this(item: Item, size: Int) = this(new ItemStack(item), size)

	def this(item: Item) = this(item.itemID, 1)

	def this(name: String, size: Int) = this(OreDictionary.getOreID(name), size)

	def this(name: String) = this(name, 1)

	protected def this() = this(0, 0)

	/**
	 * Remove the argument from the stack size. Return a new stack object with argument size.
	 */
	def splitStack(par1: Int): OreStack =
		{
			val oreStack: OreStack = OreStack(oreID, par1)

			stackSize -= par1
			oreStack
		}

	/**
	 * Returns the object corresponding to the stack.
	 */
	def getItem(): ArrayList[ItemStack] = OreDictionary.getOres(oreID)

	/**
	 * Write the stack fields to a NBT object. Return the new NBT object.
	 */
	def writeToNBT(par1NBTTagCompound: NBTTagCompound): NBTTagCompound =
		{
			par1NBTTagCompound.setShort("id", oreID.asInstanceOf[Short])
			par1NBTTagCompound.setByte("Count", stackSize.asInstanceOf[Byte])

			par1NBTTagCompound
		}

	/**
	 * Read the stack fields from a NBT object.
	 */
	def readFromNBT(par1NBTTagCompound: NBTTagCompound): Unit =
		{
			oreID = par1NBTTagCompound.getShort("id")
			stackSize = par1NBTTagCompound.getByte("Count")
		}

	/**
	 * Returns a new stack with the same properties.
	 */
	def copy(): OreStack = OreStack(oreID, stackSize)

	/**
	 * compares OreStack argument to the instance OreStack returns true if both OreStacks are equal
	 */
	private def isOreStackEqual(oreStack: OreStack): Boolean =
		{
			if (stackSize != oreStack.stackSize || oreID != oreStack.oreID)
				false
			else true
		}

	def isItemEqual(oreStack: OreStack): Boolean = oreID == oreStack.oreID

	override def toString(): String = stackSize + "x" + OreDictionary.getOreName(oreID)
}

object OreStack {
	def loadOreStackFromNBT(_NBTTagCompound: NBTTagCompound): OreStack =
		{
			var oreStack: OreStack = new OreStack()
			oreStack.readFromNBT(_NBTTagCompound)
			oreStack
		}

	/**
	 * compares OreStack argument1 with OreStack argument2 returns true if both OreStacks are equal
	 */
	def areOreStacksEqual(oreStack1: OreStack, oreStack2: OreStack): Boolean =
		{
			if (oreStack1 == null && oreStack2 == null)
				true
			else if (oreStack1 == null || oreStack2 == null)
				false
			else oreStack1.isOreStackEqual(oreStack2)
		}

	/**
	 * Creates a copy of a OreStack, a null parameters will return a null.
	 */
	def copyOreStack(oreStack: OreStack): OreStack =
		{
			if (oreStack == null)
				null
			else oreStack.copy
		}
}