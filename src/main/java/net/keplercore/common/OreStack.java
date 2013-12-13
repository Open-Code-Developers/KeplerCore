package net.keplercore.common;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

public final class OreStack
{
	/** Size of the stack. */
    public int stackSize;

    /** ID of the ore. */
    public int oreID;

    public OreStack(Block block)
    {
        this(block, 1);
    }

    public OreStack(Block block, int size)
    {
        this(new ItemStack(block), size);
    }

    public OreStack(Item item)
    {
        this(item.itemID, 1);
    }

    public OreStack(Item item, int size)
    {
        this(new ItemStack(item), size);
    }
    
    public OreStack(ItemStack stack)
    {
    	this(stack, 1);
    }
    
    public OreStack(ItemStack stack, int size)
    {
    	this(OreDictionary.getOreID(stack), size);
    }
    
    public OreStack(String name)
    {
    	this(name, 1);
    }
    
    public OreStack(String name, int size)
    {
    	this(OreDictionary.getOreID(name), size);
    }

    public OreStack(int id, int size)
    {
        oreID = id;
        stackSize = size;
    }

    public static OreStack loadOreStackFromNBT(NBTTagCompound par0NBTTagCompound)
    {
        OreStack OreStack = new OreStack();
        OreStack.readFromNBT(par0NBTTagCompound);
        return OreStack.getItem() != null ? OreStack : null;
    }

    private OreStack() {}

    /**
     * Remove the argument from the stack size. Return a new stack object with argument size.
     */
    public OreStack splitStack(int par1)
    {
        OreStack OreStack = new OreStack(oreID, par1);

        stackSize -= par1;
        return OreStack;
    }

    /**
     * Returns the object corresponding to the stack.
     */
    public ArrayList<ItemStack> getItem()
    {
        return OreDictionary.getOres(oreID);
    }

    /**
     * Write the stack fields to a NBT object. Return the new NBT object.
     */
    public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setShort("id", (short)oreID);
        par1NBTTagCompound.setByte("Count", (byte)stackSize);

        return par1NBTTagCompound;
    }

    /**
     * Read the stack fields from a NBT object.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        oreID = par1NBTTagCompound.getShort("id");
        stackSize = par1NBTTagCompound.getByte("Count");
    }

    /**
     * Returns a new stack with the same properties.
     */
    public OreStack copy()
    {
        OreStack OreStack = new OreStack(oreID, stackSize);

        return OreStack;
    }

    /**
     * compares OreStack argument1 with OreStack argument2; returns true if both OreStacks are equal
     */
    public static boolean areOreStacksEqual(OreStack par0OreStack, OreStack par1OreStack)
    {
        return par0OreStack == null && par1OreStack == null ? true : (par0OreStack != null && par1OreStack != null ? par0OreStack.isOreStackEqual(par1OreStack) : false);
    }

    /**
     * compares OreStack argument to the instance OreStack; returns true if both OreStacks are equal
     */
    private boolean isOreStackEqual(OreStack par1OreStack)
    {
        return stackSize != par1OreStack.stackSize ? false : (oreID != par1OreStack.oreID ? false : true);
    }
    
    public boolean isItemEqual(OreStack par1OreStack)
    {
        return oreID == par1OreStack.oreID;
    }

    /**
     * Creates a copy of a OreStack, a null parameters will return a null.
     */
    public static OreStack copyOreStack(OreStack par0OreStack)
    {
        return par0OreStack == null ? null : par0OreStack.copy();
    }

    public String toString()
    {
        return stackSize + "x" + OreDictionary.getOreName(oreID);
    }
}
