package net.keplercore.test.common

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import net.keplercore.common.BlockCoord
import net.minecraftforge.common.ForgeDirection
import java.util.ArrayList
import java.util.Arrays

@RunWith(classOf[JUnitRunner])
class BlockCoordTest extends FlatSpec with Matchers {

	"A Block Coordinate instance" should "be returned in String form." in {
		val coord = new BlockCoord
		coord.toString shouldBe "[0 0 0]"
	}

	it should "be returned in Hash form." in {
		val coord = new BlockCoord
		coord.hashCode() shouldBe 8421504
	}

	it should "be returned if cloned." in
	{
		val coord = new BlockCoord
		coord.clone() shouldBe new BlockCoord
	}
	
	it should "not equal null" in
	{
		val coord = new BlockCoord
		coord.equals(null) shouldBe false
	}
	
	it should "offset properly" in
	{
		val coord = new BlockCoord
		coord.offset(1, 2, 3);
		coord shouldEqual BlockCoord(1, 2, 3)
	}
	
	it should "offset with an Array properly" in
	{
		val coord = new BlockCoord
		coord.offset(Array[Int](1, 2, 3));
		coord shouldEqual BlockCoord(1, 2, 3)
	}
	
	it should "offset with a ForgeDirection properly" in
	{
		val coord = new BlockCoord
		coord.offset(ForgeDirection.NORTH);
		coord shouldEqual BlockCoord(0, 0, -1)
	}
	
	it should "offset with ForgeDirection and a distance properly" in
	{
		val coord = new BlockCoord
		coord.offset(ForgeDirection.NORTH, 2);
		coord shouldEqual BlockCoord(0, 0, -2)
	}
	
	it should "offset with a BlockCoord properly" in
	{
		val coord = new BlockCoord
		coord.offset(BlockCoord(1, 2, 3));
		coord shouldEqual BlockCoord(1, 2, 3)
	}
	
	it should "get the adjacent coord properly" in
	{
		val coord = new BlockCoord
		coord.getAdjacentCoord(ForgeDirection.NORTH) shouldEqual BlockCoord(0, 0, -1)
	}
	
	it should "get the coordinate array properly" in
	{
		val coord = new BlockCoord
		coord.getCoordsArray shouldEqual Array[Int](0, 0, 0)
	}
	
	it should "get the adjacent coords list properly" in
	{
		val coord = new BlockCoord
		coord.getAdjacentCoords shouldEqual new ArrayList[BlockCoord](
			Arrays.asList(
				BlockCoord(-1, 0, 1), BlockCoord(-1, 1, 0),
				BlockCoord(0, -1, 1), BlockCoord(0, 1, -1),
				BlockCoord(1, -1, 0), BlockCoord(1, 0, -1)))
	}

	"A Block Coordinate in the form of an array of integers" should "be constructed properly." in
	{
		new BlockCoord(Array(0, 0, 0)) shouldEqual (new BlockCoord)
	}

	"A Block Coordinate supplied by a ForgeDirection" should "be constructed properly." in
	{
		new BlockCoord(ForgeDirection.NORTH) shouldEqual (BlockCoord(0, 0, -1))
	}

}
