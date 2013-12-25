package net.keplercore.lib.schematic

import java.io.IOException
import java.io.InputStream
import java.util.ArrayList
import java.util.HashMap
import java.util.Map

import net.minecraft.nbt.CompressedStreamTools
import net.minecraft.nbt.NBTTagCompound

class SchematicReader
{
	private var ignoreAir: Boolean = false
	private var changes: Map[Integer, Integer] = new HashMap[Integer, Integer]()
	private var schematicTag: NBTTagCompound = null
	var width: Short = 0
	var length: Short = 0
	var height: Short = 0

	def this(input: InputStream, ignoreAir: Boolean) =
	{
		this()
		schematicTag = CompressedStreamTools.readCompressed(input)
		this.ignoreAir = ignoreAir
	}

	def ReadSchematic(): ArrayList[SchematicBlock] =
	{
		width = schematicTag.getShort("Width")
		length = schematicTag.getShort("Length")
		height = schematicTag.getShort("Height")

		val blocks: Array[Byte] = schematicTag.getByteArray("Blocks")
		val blockData: Array[Byte] = schematicTag.getByteArray("Data")

		var blockList: ArrayList[SchematicBlock] = new ArrayList[SchematicBlock]()
		for (x <- 0 to width)
		{
			for (y <- 0 to height)
			{
				for (z <- 0 to length)
				{
					val index: Int = y * width * length + z * width + x

					var bb: SchematicBlock = null
					if (changes.containsKey(blocks(index) & 0xFF))
					{
						bb = new SchematicBlock(x, y, z, changes.get(blocks(index) & 0xFF), blockData(index) & 0xFF)
					}
					else
					{
						bb = new SchematicBlock(x, y, z, blocks(index) & 0xFF, blockData(index) & 0xFF)
					}

					if (!bb.isAir() || !ignoreAir)
					{
						blockList.add(bb)
					}
				}
			}
		}

		return blockList
	}

	def replaceID(i: Int, j: Int): Unit = changes.put(i, j)
}
