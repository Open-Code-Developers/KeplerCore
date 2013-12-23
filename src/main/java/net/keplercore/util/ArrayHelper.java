package net.keplercore.util;

public class ArrayHelper
{
	public static String[] joinStringArray(String[] oldArray, String... oldArray2)
	{
		String[] newArray = new String[oldArray.length + oldArray2.length];
		int i;

		for (i = 0; i < oldArray.length; ++i)
		{
			newArray[i] = oldArray[i];
		}

		for (i = 0; i < oldArray2.length; ++i)
		{
			newArray[i + oldArray.length] = oldArray2[i];
		}

		return newArray;
	}
}
