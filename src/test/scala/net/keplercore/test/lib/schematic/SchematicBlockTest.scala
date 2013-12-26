package net.keplercore.test.lib.schematic

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import net.keplercore.lib.schematic.SchematicBlock
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.block.Block

@RunWith(classOf[JUnitRunner])
class SchematicBlockTest extends FlatSpec with Matchers
{
	"Air Block" should "return true if a block is an air block." in
	{
		val schematic: SchematicBlock = SchematicBlock(0, 0, 0, 0, 0)
		schematic.isAir shouldBe true
	}

	it should "return false if a block is not an air block." in
	{
		val schematic: SchematicBlock = SchematicBlock(0, 0, 0, 1, 0)
		schematic.isAir shouldBe false
	}

	/* fails because of faulty classloading, to be revisited
	"Fluid Block" should "return true if a block is a fluid block." in
	{
		val schematic: SchematicBlock = SchematicBlock(0, 0, 0, Block.waterMoving.blockID, 0)
		schematic.isFluid shouldBe true
	}

	it should "return false if a block is not a fluid block." in
	{
		val schematic: SchematicBlock = SchematicBlock(0, 0, 0, 1, 0)
		schematic.isFluid shouldBe false
	}*/

	"Same Blocks" should "return true if they are equal." in
	{
		val schematic1: SchematicBlock = SchematicBlock(0, 0, 0, 1, 0)
		val schematic2: SchematicBlock = SchematicBlock(0, 0, 0, 1, 0)
		schematic1 equals schematic2 shouldBe true
	}

	it should "return false if they are different." in
	{
		val schematic1: SchematicBlock = SchematicBlock(0, 0, 0, 1, 0)
		val schematic2: SchematicBlock = SchematicBlock(0, 0, 0, 2, 0)
		schematic1 equals schematic2 shouldBe false
	}

	it should "return false if the other one is null." in
	{
		val schematic: SchematicBlock = SchematicBlock(0, 0, 0, 1, 0)
		schematic equals null shouldBe false
	}
	
	"A Block" should "be read from NBT properly" in
	{
		var compound = new NBTTagCompound
		compound setInteger("i", 1)
		compound setInteger("j", 2)
		compound setInteger("k", 3)
		compound setInteger("id", 4)
		compound setInteger("meta", 5)
		
		new SchematicBlock(compound) shouldEqual SchematicBlock(1, 2, 3, 4, 5)
	}
	
	it should "write to NBT properly" in
	{
		var compound = new NBTTagCompound
		compound setInteger("i", 1)
		compound setInteger("j", 2)
		compound setInteger("k", 3)
		compound setInteger("id", 4)
		compound setInteger("meta", 5)
		
		var compound2 = new NBTTagCompound
		new SchematicBlock(compound).writeToNBT(compound2)
		
		compound2 shouldEqual compound
	}
}
