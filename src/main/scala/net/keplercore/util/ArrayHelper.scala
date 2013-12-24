package net.keplercore.util

object ArrayHelper {
  def joinStringArray(oldArray: Array[String], oldArray2: String*): Array[String] =
  {
    val newArray = new Array[String](oldArray.length + oldArray2.length)
    var i: Int = 0
    i = 0
    while (i < oldArray.length)
    {
      newArray(i) = oldArray(i)
      i
    }
    i = 0
    while (i < oldArray2.length)
    {
      newArray(i + oldArray.length) = oldArray2(i)
      i
    }
    newArray
  }
}