package net.keplercore.test.lib.schematic

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import net.keplercore.lib.schematic.SchematicBlock

@RunWith(classOf[JUnitRunner])
class SchematicBlockTest extends FlatSpec with Matchers {

  "Air Block" should "return true if it is an air block." in {
    val schematic = new SchematicBlock(0, 0, 0, 0, 0)
    schematic.isAir shouldBe true
  }

}
