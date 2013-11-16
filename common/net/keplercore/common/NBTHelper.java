package net.keplercore.common;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTHelper
{
	public static void writeItemStackToNBT(NBTTagCompound compound, String key, ItemStack[] itemstack)
	{
		NBTTagList list = new NBTTagList();

		for (int i = 0; i < itemstack.length; ++i)
		{
			if (itemstack[i] != null)
			{
				NBTTagCompound tagList = new NBTTagCompound();
				tagList.setByte("Slot", (byte) i);
				itemstack[i].writeToNBT(tagList);
				list.appendTag(tagList);
			}
		}

		compound.setTag(key, list);
	}

	public static ItemStack[] readItemStackFromNBT(NBTTagCompound compound, String key)
	{
		NBTTagList list = compound.getTagList(key);
		ItemStack[] retVal = new ItemStack[list.tagCount()];

		for (int i = 0; i < list.tagCount(); ++i)
		{
			NBTTagCompound tagList = (NBTTagCompound) list.tagAt(i);
			byte slot = tagList.getByte("Slot");

			if (slot > -1)
				retVal[i] = ItemStack.loadItemStackFromNBT(tagList);
		}

		return retVal;
	}
}
