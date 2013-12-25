package net.keplercore.common

import net.minecraftforge.common.ForgeDirection
import java.util.ArrayList

case class BlockCoord(var x: Int, var y: Int, var z: Int)
{
  def this(direction: ForgeDirection)
  {
    this(direction.offsetX, direction.offsetY, direction.offsetZ)
  }

  def this(coords: Array[Int])
  {
    this(coords(0), coords(1), coords(2))
  }

  override def toString: String = "[" + x + " " + y + " " + z + "]"

  override def equals(obj: Object): Boolean =
  {
    if (obj == null || !obj.isInstanceOf[BlockCoord])
    {
      false
    }

    val coord: BlockCoord = obj.asInstanceOf[BlockCoord]

    coord.x == x && coord.y == y && coord.z == z
  }

  override def hashCode(): Int = (x + 128) << 16 | (y + 128) << 8 | (z + 128)

  override def clone(): BlockCoord = new BlockCoord(x, y, z)

  def offset(x: Int, y: Int, z: Int)
  {
    this.x += x
    this.y += y
    this.z += z
  }

  def offset(direction: ForgeDirection, distance: Int): Unit = offset(direction.offsetX * distance, direction.offsetY * distance, direction.offsetZ * distance)

  def offset(direction: ForgeDirection): Unit = offset(direction, 1)

  def offset(offsets: Array[Int]): Unit = offset(offsets(0), offsets(1), offsets(2))

  def offset(theOffset: BlockCoord): Unit = offset(theOffset.x, theOffset.y, theOffset.z)

  def getCoordWithOffset(offsetX: Int, offsetY: Int, offsetZ: Int): BlockCoord = new BlockCoord(this.x + offsetX, this.y + offsetY, this.z + offsetZ)

  def getAdjacentCoord(direction: ForgeDirection): BlockCoord = this.getAdjacentCoord(direction, 1)

  def getAdjacentCoord(direction: ForgeDirection, distance: Int): BlockCoord = this.getCoordWithOffset(direction.offsetX * distance, direction.offsetY * distance, direction.offsetZ * distance)

  def getAdjacentCoords: ArrayList[BlockCoord] =
  {
    var adjacent: ArrayList[BlockCoord] = new ArrayList[BlockCoord]()

    for (i <- -1 until 2)
      for (j <- -1 until 2)
        for (k <- -1 until 2)
          if (i != j && j != k && i != k)
            adjacent.add(getCoordWithOffset(i, j, k))

    adjacent
  }

  def getCoordsArray: Array[Int] =
  {
    Array(this.x, this.y, this.z)
  }
}