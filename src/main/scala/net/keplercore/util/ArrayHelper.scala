package net.keplercore.util

class ArrayHelper
{
	def joinStringArray(oldArray: Array, oldArray2: String*): Array = {
		String[] newArray = new String[oldArray.length + oldArray2.length]
		int i
		for (i = 0; i < oldArray.length; ++i) {
			newArray[i] = oldArray[i]
		}
		for (i = 0; i < oldArray2.length; ++i) {
			newArray[i + oldArray.length] = oldArray2[i]
		}
		newArray
	}
}
