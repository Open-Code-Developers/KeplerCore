package net.keplercore.test.lib.schematic

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import net.keplercore.lib.schematic.SchematicBlock
import net.minecraft.block.Block

@RunWith(classOf[JUnitRunner])
class SchematicBlockTest extends FlatSpec with Matchers
{
	"Air Block" should "return true if a block is an air block." in
		{
			val schematic: SchematicBlock = new SchematicBlock(0, 0, 0, 0, 0)
			schematic.isAir shouldBe true
		}

	it should "return false if a block is not an air block." in
		{
			val schematic: SchematicBlock = new SchematicBlock(0, 0, 0, 1, 0)
			schematic.isAir shouldBe false
		}

	"Fluid Block" should "return true if a block is a fluid block" in
		{
			val schematic: SchematicBlock = new SchematicBlock(0, 0, 0, Block.waterMoving.blockID, 0)
			schematic.isAir shouldBe true
		}

	it should "return false if a block is not a fluid block" in
		{
			val schematic: SchematicBlock = new SchematicBlock(0, 0, 0, 1, 0)
			schematic.isAir shouldBe false
		}

	"Same Blocks" should "return true if the blocks are same" in
		{
			val schematic1: SchematicBlock = new SchematicBlock(0, 0, 0, 1, 0)
			val schematic2: SchematicBlock = new SchematicBlock(0, 0, 0, 1, 0)
			schematic1.equals(schematic2) shouldBe true
		}

	it should "return false if the blocks are different" in
		{
			val schematic1: SchematicBlock = new SchematicBlock(0, 0, 0, 1, 0)
			val schematic2: SchematicBlock = new SchematicBlock(0, 0, 0, 2, 0)
			schematic1.equals(schematic2) shouldBe false
		}
}
