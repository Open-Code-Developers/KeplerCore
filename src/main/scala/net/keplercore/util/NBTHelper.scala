package net.keplercore.util

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList

object NBTHelper {
	def writeItemStackToNBT(compound: NBTTagCompound, key: String, itemstack: Array[ItemStack]): Unit =
		{
			var list: NBTTagList = new NBTTagList()

			for (i <- 0 to itemstack.length) {
				if (itemstack(i) != null) {
					var tagList: NBTTagCompound = new NBTTagCompound()
					tagList.setByte("Slot", i.asInstanceOf[Byte])
					itemstack(i).writeToNBT(tagList)
					list.appendTag(tagList)
				}
			}

			compound.setTag(key, list)
		}

	def readItemStackFromNBT(compound: NBTTagCompound, key: String): Array[ItemStack] =
		{
			var list: NBTTagList = compound.getTagList(key)
			var retVal: Array[ItemStack] = new Array[ItemStack](list.tagCount())

			for (i <- 0 to list.tagCount()) {
				val tagList: NBTTagCompound = list.tagAt(i).asInstanceOf[NBTTagCompound]
				val slot: Byte = tagList.getByte("Slot")

				if (slot > -1)
					retVal(i) = ItemStack.loadItemStackFromNBT(tagList)
			}

			retVal
		}
}
