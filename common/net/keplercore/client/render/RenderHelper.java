package net.keplercore.client.render;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

/**
 * 
 * Class with all the rendering stuff
 *
 */

public class RenderHelper
{
	public static void renderItemOnTop(ItemStack stack)
	{
		double d = 2;
		double d1 = 1 / d;
		GL11.glScaled(d, d, d);

		GL11.glColor4f(1, 1, 1, 1);

		GL11.glRotatef(90F, 1F, 0F, 0F);

		EntityItem entityItem = new EntityItem(null);
		entityItem.setEntityItemStack(stack);
		entityItem.hoverStart = 0;

		RenderItem renderItem = new RenderItem()
		{
			public boolean shouldBob()
			{
				return false;
			}
		};

		renderItem.setRenderManager(RenderManager.instance);
		renderItem.doRenderItem(entityItem, 0, 0.14, -0.14, 0, 0);

		GL11.glScaled(d1, d1, d1);
	}
}
