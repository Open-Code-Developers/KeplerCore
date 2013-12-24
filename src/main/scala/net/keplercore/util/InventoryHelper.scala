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

    val f1: Float = 0.7F
		val d: Double = (world.rand.nextFloat() * f1) + (1.0F - f1) * 0.5D
		val d1: Double = (world.rand.nextFloat() * f1) + (1.0F - f1) * 0.5D
		val d2: Double = (world.rand.nextFloat() * f1) + (1.0F - f1) * 0.5D
		val entityitem: EntityItem = new EntityItem(world, i + d, j + d1, k + d2, stack)
		entityitem.delayBeforeCanPickup = 10

		world.spawnEntityInWorld(entityitem)
	}

	def dropItems(world: World, inv: IInventory, i: Int, j: Int, k: Int)
  {
    val slot: Int = 0
		while (slot < inv.getSizeInventory())
    {
			val items: ItemStack = inv.getStackInSlot(slot)

			if (items != null && items.stackSize > 0) {
				dropItems(world, inv.getStackInSlot(slot).copy(), i, j, k)
			}
		}
	}
}
