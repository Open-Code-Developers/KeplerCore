package net.keplercore.common

import net.minecraftforge.common.ForgeDirection
import java.util

class BlockCoord(x: Int, y: Int, z: Int)
{
  def this(direction: ForgeDirection)
  {
    this(direction.offsetX, direction.offsetY, direction.offsetZ)
  }

  def this(coords: Array[Int])
  {
    this(coords(0), coords(1), coords(2))
  }

  override def toString(): String = "[" + x + " " + y + " " + z + "]"

  override def equals(obj: Object): Boolean =
  {
    if (obj == null || !(obj instanceof BlockCoord))
    {
      false
    }

    BlockCoord coord = (BlockCoord) obj

    return coord.x == x && coord.y == y && coord.z == z
  }

  override def hashCode(): Int = (x + 128) << 16 | (y + 128) << 8 | (z + 128)

  override def clone(): BlockCoord = new BlockCoord(x, y, z)

  def offset(x: Int, y: Int, z: Int)
  {
    this.x += x
    this.y += y
    this.z += z
  }

  def offset(direction: ForgeDirection, distance: Int) = offset(direction.offsetX * distance, direction.offsetY * distance, direction.offsetZ * distance)

  def offset(direction: ForgeDirection) = offset(direction, 1)

  def offset(offsets: Array[Int]) = offset(offsets(0), offsets(1), offsets(2))

  def offset(offset: BlockCoord) = offset(offset.x, offset.y, offset.z)

  def getCoordWithOffset(offsetX: Int, offsetY: Int, offsetZ: Int): BlockCoord = new BlockCoord(this.x + offsetX, this.y + offsetY, this.z + offsetZ)

  def getAdjacentCoord(direction: ForgeDirection): BlockCoord = this.getAdjacentCoord(direction, 1)

  def getAdjacentCoord(direction: ForgeDirection, distance: Int): BlockCoord = this.getCoordWithOffset(direction.offsetX * distance, direction.offsetY * distance, direction.offsetZ * distance)

  def getAdjacentCoords(): Array[BlockCoord] =
  {
    util.ArrayList<BlockCoord> adjacent = new util.ArrayList<BlockCoord>()

    for (i <- -1 until 2)
      for (j <- -1 until 2)
        for (k <- -1 until 2)
          if (i != j && j != k && i != k)
            adjacent.add(getCoordWithOffset(i, j, k))

    return (Array[BlockCoord]) adjacent
  }

  def getCoordsArray(): Array[Int] =
  {
    Array(this.x, this.y, this.z)
  }
}