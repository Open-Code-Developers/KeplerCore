package net.keplercore.test.common

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.enablers.Size
import net.keplercore.common.OreStack
import net.minecraftforge.oredict.OreDictionary
import net.minecraft.nbt.NBTTagCompound
import java.util.ArrayList

@RunWith(classOf[JUnitRunner])
class OreStackTest extends FlatSpec with Matchers
{
	"An Ore Stack in the form of 2 integers" should "be returned with reduced stackSize." in
	{
		val stack = OreStack(1, 64)
		stack.splitStack(32) should have size 32
	}

	/* Crashes because of faulty classloading, to be revisited
	it should "return all the entries for the oreID." in
	{
		val stack = OreStack(1, 64)
		stack.getItem() should === (OreDictionary.getOres(stack.oreID))
	}
	*/

	it should "return an NBT compound tag with the stackSize and oreID." in
	{
		val stack = OreStack(64, 1)
		var compound = new NBTTagCompound

		compound.setShort("id", stack.oreID.asInstanceOf[Short])
		compound.setByte("Count", stack.stackSize.asInstanceOf[Byte])

		stack.writeToNBT(new NBTTagCompound) should === (compound)
	}
	
	implicit val oreStackSize = new Size[OreStack]
	{
		def sizeOf(stack: OreStack): Long = stack.stackSize
	}
}
