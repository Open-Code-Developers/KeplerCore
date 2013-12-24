package net.keplercore.util

import net.minecraft.entity.item.EntityItem
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.world.World

object InventoryHelper
{
	def dropItems(world: World, stack: ItemStack, i: Int, j: Int, k: Int)
  {
		if (stack.stackSize <= 0) {
			return
		}

		float f1 = 0.7F
		double d = (world.rand.nextFloat() * f1) + (1.0F - f1) * 0.5D
		double d1 = (world.rand.nextFloat() * f1) + (1.0F - f1) * 0.5D
		double d2 = (world.rand.nextFloat() * f1) + (1.0F - f1) * 0.5D
		EntityItem entityitem = new EntityItem(world, i + d, j + d1, k + d2, stack)
		entityitem.delayBeforeCanPickup = 10

		world.spawnEntityInWorld(entityitem)
	}

	def dropItems(world: World, inv: IInventory, i: Int, j: Int, k: Int)
  {
		for (int slot = 0; slot < inv.getSizeInventory(); ++slot) {
			ItemStack items = inv.getStackInSlot(slot)

			if (items != null && items.stackSize > 0) {
				dropItems(world, inv.getStackInSlot(slot).copy(), i, j, k)
			}
		}
	}
}
