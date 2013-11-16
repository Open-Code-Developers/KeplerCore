package net.keplercore.client.texture;

import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.Resource;
import net.minecraft.client.resources.ResourceManager;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;

public class TextureRuntimeUnsticher extends TextureAtlasSprite
{
	private final int index;
	private final int rows;
	private final int columns;

	public static Icon[] unstitchIcons(IconRegister register, String name, int numIcons)
	{
		return unstitchIcons(register, name, numIcons, 1);
	}

	public static Icon[] unstitchIcons(IconRegister register, String name, int columns, int rows)
	{
		TextureMap textureMap = (TextureMap) register;
		int numIcons = rows * columns;
		Icon[] icons = new Icon[numIcons];
		for (int i = 0; i < numIcons; i++)
		{
			String textureName = name + "." + i;
			TextureRuntimeUnsticher texture = new TextureRuntimeUnsticher(textureName, i, rows,
				columns);
			textureMap.setTextureEntry(textureName, texture);
			icons[i] = texture;
		}
		return icons;
	}

	private TextureRuntimeUnsticher(String name, int index, int rows, int columns)
	{
		super(name);
		this.index = index;
		this.rows = rows;
		this.columns = columns;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean load(ResourceManager manager, ResourceLocation location) throws IOException
	{
		String fileName = location.getResourcePath().replace("." + index, "");
		BufferedImage image;
		try
		{
			Resource resource = manager.getResource(new ResourceLocation(location
				.getResourceDomain(),
				fileName));
			image = ImageIO.read(resource.getInputStream());
		}
		catch (IOException ex)
		{
			System.err.println("Failed to load sub-texture from " + fileName + ": " +
				ex.getLocalizedMessage());
			return true;
		}
		int size = image.getHeight() / this.rows;
		int x = index % columns;
		int y = index / columns;
		BufferedImage subImage;
		try
		{
			subImage = image.getSubimage(x * size, y * size, size, size);
		}
		catch (RasterFormatException ex)
		{
			System.err.println("Failed to load sub-texture from " + fileName +
				" - " + image.getWidth() + "x" + image.getHeight() + ": " +
				ex.getLocalizedMessage());
			throw ex;
		}
		height = subImage.getHeight();
		width = subImage.getWidth();
		int[] imageData = new int[height * width];
		subImage.getRGB(0, 0, width, height, imageData, 0, width);
		framesTextureData.add(imageData);
		return true;
	}
}