package net.keplercore.test.common

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import net.keplercore.common.BlockCoord

@RunWith(classOf[JUnitRunner])
class BlockCoordTest extends FlatSpec with Matchers {

  "A Block Coordinate in the form of 3 integers" should "be returned in String form." in {
    val coord = new BlockCoord(0, 0, 0)
    coord.toString shouldBe "[0 0 0]"
  }

  "A Block Coordinate in an array of integers" should "returned in String form." in {
    val coordArray = Array(0, 0, 0)
    val coord = new BlockCoord(coordArray)
    coord.toString shouldBe "[0 0 0]"
  }

}
