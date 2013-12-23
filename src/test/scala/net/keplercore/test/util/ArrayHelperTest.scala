package net.keplercore.test.util

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import net.keplercore.util.ArrayHelper

@RunWith(classOf[JUnitRunner])
class ArrayHelperTest extends FlatSpec with Matchers {

  "ArrayHelper" should "return a String array when joinStringArray is called." in {
    val old1 = Array("this", "is")
    val old2 = "a"
    val old3 = "new"
    val old4 = "array"
    ArrayHelper.joinStringArray(old1, old2, old3, old4) shouldBe Array("this", "is", "a", "new", "array")
  }

}
