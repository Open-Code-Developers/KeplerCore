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
	"An Ore Stack class instance" should "be returned with reduced stackSize." in
	{
		val stack = OreStack(1, 64)
		stack.splitStack(32) should have ('stackSize (32))
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
		val stack = OreStack(1, 64)
		var compound = new NBTTagCompound

		compound.setShort("id", stack.oreID.asInstanceOf[Short])
		compound.setByte("Count", stack.stackSize.asInstanceOf[Byte])

		stack.writeToNBT(new NBTTagCompound) should === (compound)
	}
	
	it should "equal to it's copy" in
	{
		val stack = OreStack(1, 64)
		stack should === (stack.copy)
	}
	
	it should "equal by item to it's copy" in
	{
		val stack = OreStack(1, 64)
		stack.isItemEqual(stack.copy) shouldBe true
	}
	
	it should "read the NBT tag properly" in
	{
		val stack = OreStack(1, 64)
		var compound = new NBTTagCompound
		
		compound = stack.writeToNBT(compound)

		var stack2 = OreStack(2, 32)
		stack2.readFromNBT(compound)
		
		stack2 should === (stack)
	}
	
	/*Classloading...
	it should "convert to String properly" in
	{
		val stack = OreStack(1, 64)
		stack.toString shouldEqual "64xlogWood"
	}*/
	
	"The OreStack object" should "load from the NBT tag properly" in
	{
		val stack = OreStack(1, 64)
		var compound = new NBTTagCompound
		
		compound = stack.writeToNBT(compound)

		var stack2 = OreStack.loadOreStackFromNBT(compound)
		
		stack2 should === (stack)
	}
	
	it should "statically test equality properly for same objects" in
	{
		val stack1 = OreStack(1, 64)
		val stack2 = OreStack(1, 64)
		OreStack.areOreStacksEqual(stack1, stack2) shouldBe true
	}
	
	it should "statically test equality properly for different objects" in
	{
		val stack1 = OreStack(1, 64)
		val stack2 = OreStack(2, 32)
		OreStack.areOreStacksEqual(stack1, stack2) shouldBe false
	}
	
	it should "statically test equality properly if one is null" in
	{
		val stack = OreStack(1, 64)
		OreStack.areOreStacksEqual(stack, null) shouldBe false
	}
	
	it should "statically test equality properly if both are null" in
	{
		OreStack.areOreStacksEqual(null, null) shouldBe true
	}
	
	it should "statically copy the OreStack instance properly" in
	{
		val stack = OreStack(1, 64)
		OreStack.areOreStacksEqual(stack, OreStack.copyOreStack(stack)) shouldBe true
	}
}
