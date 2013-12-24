package net.keplercore.util

object ArrayHelper
{
	def joinStringArray(oldArray: Array[String], oldArray2: String*): Array[String] =
  {
		val newArray: Array[String] = new Array(oldArray.length + oldArray2.length)
		for (string: String <- oldArray2)
    {
      newArray.+(string)
    }
		newArray
	}
}
