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
		(SchematicBlock(0, 0, 0, 0, 0) isAir) shouldBe true
	}

	it should "return false if a block is not an air block." in
	{
		(SchematicBlock(0, 0, 0, 1, 0) isAir) shouldBe false
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
		SchematicBlock(0, 0, 0, 1, 0) equals
			SchematicBlock(0, 0, 0, 1, 0) shouldBe true
	}

	they should "return false if they are different." in
	{
		SchematicBlock(0, 0, 0, 1, 0) equals
			SchematicBlock(0, 0, 0, 2, 0) shouldBe false
	}

	they should "return false if the other one is null." in
	{
		SchematicBlock(0, 0, 0, 1, 0) equals null shouldBe false
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
		new SchematicBlock(compound) writeToNBT compound2
		
		compound2 shouldEqual compound
	}
	
	/*
	it should "compare 0 to itself" in
	{
		val block = SchematicBlock(0, 0, 0, 0, 0)
		block compareTo block shouldBe 0
	}
	*/
	
	it should "compare positive to something greater" in
	{
		SchematicBlock(0, 0, 0, 0, 0) compareTo
			SchematicBlock(1, 2, 3, 4, 5) should be > 0
	}
	
	it should "compare positive to something lesser" in
	{
		SchematicBlock(0, 0, 0, 0, 0) compareTo
			SchematicBlock(1, 2, 3, 4, 5) should be < 0
	}
}
