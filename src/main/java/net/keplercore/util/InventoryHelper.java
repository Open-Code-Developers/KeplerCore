package net.keplercore.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class InventoryHelper
{
	public static void dropItems(World world, ItemStack stack, int i, int j, int k) {
		if (stack.stackSize <= 0) {
			return;
		}

		float f1 = 0.7F;
		double d = (world.rand.nextFloat() * f1) + (1.0F - f1) * 0.5D;
		double d1 = (world.rand.nextFloat() * f1) + (1.0F - f1) * 0.5D;
		double d2 = (world.rand.nextFloat() * f1) + (1.0F - f1) * 0.5D;
		EntityItem entityitem = new EntityItem(world, i + d, j + d1, k + d2, stack);
		//TODO pick one
		entityitem.func_145769_d(10);
		entityitem.func_145781_i(10);

		world.spawnEntityInWorld(entityitem);
	}

	public static void dropItems(World world, IInventory inv, int i, int j, int k) {
		for (int slot = 0; slot < inv.getSizeInventory(); ++slot) {
			ItemStack items = inv.getStackInSlot(slot);

			if (items != null && items.stackSize > 0) {
				dropItems(world, inv.getStackInSlot(slot).copy(), i, j, k);
			}
		}
	}
}
