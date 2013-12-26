package net.keplercore.util

object ArrayHelper
{
	def joinStringArray(oldArray: Array[String], oldArray2: String*): Array[String] =
	{
		oldArray ++ oldArray2
	}
}