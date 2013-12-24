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

object RenderHelper
{
	def renderItemOnTop(stack: ItemStack)
	{
		val d: Double = 2
		val d1: Double = 1 / d
		GL11.glScaled(d, d, d)

		GL11.glColor4f(1, 1, 1, 1)

		GL11.glRotatef(90F, 1F, 0F, 0F)

		val entityItem: EntityItem = new EntityItem(null)
		entityItem.setEntityItemStack(stack)
		entityItem.hoverStart = 0

		val renderItem: RenderItem = new RenderItem()
		{
      override def shouldBob(): Boolean =
      {
        false
      }
		}

		renderItem.setRenderManager(RenderManager.instance)
		renderItem.doRenderItem(entityItem, 0, 0.14, -0.14, 0, 0)

		GL11.glScaled(d1, d1, d1)
	}
}
