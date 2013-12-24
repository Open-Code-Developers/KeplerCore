package net.keplercore.common

import net.minecraftforge.common.ForgeDirection
import java.util
import net.keplercore.common

object BlockCoord
  {
    class BlockCoord(direction: ForgeDirection)
    {
      new BlockCoord(direction.offsetX, direction.offsetY, direction.offsetZ)
    }

    class BlockCoord(coords: Array[Int])
    {
      new BlockCoord(coords(0), coords(1), coords(2))
    }

    class BlockCoord(x: Int, y: Int, z: Int)
    {

      override def toString(): String = "[" + x + " " + y + " " + z + "]"


      override def equals(Object object): Boolean
      {
        if (object == null || !(object instanceof BlockCoord)) {
          false
        }

        BlockCoord coord = (BlockCoord) object

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

      def offset(offsets: Array[Int]) = offset(offsets[0], offsets[1], offsets[2])

      def offset(offset: BlockCoord) =  offset(offset.x, offset.y, offset.z)

      def getCoordWithOffset(x: Int, y: Int, x: Int): BlockCoord = new BlockCoord(this.x + x, this.y + y, this.z + z)

      def getAdjacentCoord(direction: ForgeDirection): BlockCoord = this.getAdjacentCoord(direction, 1)

      def getAdjacentCoord(direction: ForgeDirection, distance: Int): BlockCoord = this.getCoordWithOffset(direction.offsetX * distance, direction.offsetY * distance, direction.offsetZ * distance)

      def getAdjacentCoords(): BlockCoord[] =
      {
        util.ArrayList<BlockCoord> adjacent = new util.ArrayList<BlockCoord>()
        int i, j, k

        for (i = -1 i < 2 ++i)
        for (j = -1 j < 2 ++j)
        for (k = -1 k < 2 ++k)
        if (i != j && j != k && i != k)
        adjacent.add(getCoordWithOffset(i, j, k))

        return (common.BlockCoord[]) adjacent.toArray()
      }

      def int[] getCoordsArray()
      {
        return new int[]
        {
          x,
          y,
          z
        }
      }
    }
  }
}