package net.keplercore.util

class ArrayHelper
{
	def joinStringArray(oldArray: Array[String], oldArray2: String*): Array[String] = {
		val newArray: Array[String] = new String[oldArray.length + oldArray2.length]
		val i: Int = _
		for (i = 0; i < oldArray.length; ++i) {
			newArray[i] = oldArray[i]
		}
		for (i = 0; i < oldArray2.length; ++i) {
			newArray[i + oldArray.length] = oldArray2[i]
		}
		newArray
	}
}
