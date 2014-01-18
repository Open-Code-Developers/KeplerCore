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
        this(new ItemStack(block, size));
    }

    public OreStack(Item item)
    {
        this(item, 1);
    }

    public OreStack(Item item, int size)
    {
        this(new ItemStack(item, size));
    }
    
    public OreStack(ItemStack stack)
    {
    	this(OreDictionary.getOreID(stack), stack.stackSize);
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
    public OreStack splitStack(int n)
    {
        OreStack OreStack = new OreStack(oreID, n);

        stackSize -= n;
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
        return new OreStack(oreID, stackSize);
    }

    /**
     * compares OreStack argument1 with OreStack argument2; returns true if both OreStacks are equal
     */
    public static boolean areOreStacksEqual(OreStack stack1, OreStack stack2)
    {
        return stack1 == null && stack2 == null ? true : (stack1 != null && stack2 != null ? stack1.isOreStackEqual(stack2) : false);
    }

    /**
     * compares OreStack argument to the instance OreStack; returns true if both OreStacks are equal
     */
    private boolean isOreStackEqual(OreStack stack)
    {
        return stackSize != stack.stackSize ? false : (oreID != stack.oreID ? false : true);
    }
    
    public boolean isItemEqual(OreStack stack)
    {
        return oreID == stack.oreID;
    }

    /**
     * Creates a copy of a OreStack, a null parameters will return a null.
     */
    public static OreStack copyOreStack(OreStack stack)
    {
        return stack == null ? null : stack.copy();
    }

    public String toString()
    {
        return stackSize + "x" + OreDictionary.getOreName(oreID);
    }
}
