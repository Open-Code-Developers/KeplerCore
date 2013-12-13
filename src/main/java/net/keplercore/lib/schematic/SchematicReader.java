package net.keplercore.lib.schematic;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

public class SchematicReader
{

	private boolean ignoreAir = false;
	private Map<Integer, Integer> changes = new HashMap<Integer, Integer>();
	private NBTTagCompound schematicTag;
	public short width;
	public short length;
	public short height;

	public SchematicReader(InputStream input, boolean ignoreAir) throws IOException
	{
		schematicTag = CompressedStreamTools.readCompressed(input);
		this.ignoreAir = ignoreAir;
	}

	public ArrayList<SchematicBlock> ReadSchematic() throws IOException
	{
		this.width = schematicTag.getShort("Width");
		this.length = schematicTag.getShort("Length");
		this.height = schematicTag.getShort("Height");

		byte[] blocks = schematicTag.getByteArray("Blocks");
		byte[] blockData = schematicTag.getByteArray("Data");

		ArrayList<SchematicBlock> blockList = new ArrayList<SchematicBlock>();
		for (int x = 0; x < this.width; x++)
		{
			for (int y = 0; y < this.height; y++)
			{
				for (int z = 0; z < this.length; z++)
				{
					int index = y * this.width * this.length + z * this.width + x;

					SchematicBlock bb;
					if (changes.containsKey(blocks[index] & 0xFF))
					{
						bb = new SchematicBlock(x, y, z, changes.get(blocks[index] & 0xFF), blockData[index] & 0xFF);
					}
					else
					{
						bb = new SchematicBlock(x, y, z, blocks[index] & 0xFF, blockData[index] & 0xFF);
					}

					if (bb.isAir() && ignoreAir)
					{
						continue;
					}

					blockList.add(bb);
				}
			}
		}

		return blockList;
	}

	public void replaceID(int i, int j)
	{
		changes.put(i, j);
	}
}
