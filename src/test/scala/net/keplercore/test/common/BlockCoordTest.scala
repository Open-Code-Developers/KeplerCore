package net.keplercore.test.common

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import net.keplercore.common.BlockCoord

@RunWith(classOf[JUnitRunner])
class BlockCoordTest extends FlatSpec with Matchers {

  "A Block Coordinate" should "be returned in String form." in {
    val coord = new BlockCoord(0, 0, 0)
    coord.toString shouldBe "[0 0 0]"
  }

}
