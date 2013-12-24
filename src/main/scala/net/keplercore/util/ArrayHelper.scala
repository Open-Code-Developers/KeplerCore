package net.keplercore.util

object ArrayHelper {
  def joinStringArray(oldArray: Array[String], oldArray2: String*): Array[String] =
  {
    var newArray = new Array[String](oldArray.length + oldArray2.length)
    newArray = oldArray ++ oldArray2
    newArray
  }
}