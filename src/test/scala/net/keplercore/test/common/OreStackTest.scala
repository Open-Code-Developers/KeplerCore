package net.keplercore.test.common

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import net.keplercore.common.OreStack
import net.minecraftforge.oredict.OreDictionary
import net.minecraft.nbt.NBTTagCompound
import java.util.ArrayList

@RunWith(classOf[JUnitRunner])
class OreStackTest extends FlatSpec with Matchers
{
	"An Ore Stack in the form of 2 integers" should "be returned with reduced stackSize." in
	{
		val stack = OreStack(64, 1)
		stack.splitStack(32) shouldBe OreStack(32, stack.oreID)
	}

	it should "return all the entries for the oreID." in
	{
		val stack = OreStack(64, 1)
		stack.getItem() shouldBe OreDictionary.getOres(stack.oreID)
	}

	it should "return an NBT compound tag with the stackSize and oreID." in
	{
		val stack = OreStack(64, 1)
		var compound = new NBTTagCompound

		compound.setShort("id", stack.oreID.asInstanceOf[Short])
		compound.setByte("Count", stack.stackSize.asInstanceOf[Byte])

		stack.writeToNBT(new NBTTagCompound()).equals(compound) shouldBe true
	}
}
