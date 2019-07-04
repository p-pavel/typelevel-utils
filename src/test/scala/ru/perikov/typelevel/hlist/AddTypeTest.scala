package ru.perikov.typelevel.hlist

import org.scalatest.FunSuite
import ru.perikov.typelevel.AssertT2

class AddTypeTest extends FunSuite {


  object check extends AssertT2[AddType]

  test("list contains supertype of element") {
    check[Int :: AnyRef :: Float :: HNil, String, Int :: String :: Float :: HNil]

  }

  test("list does not contain related types") {
    check[Int :: Float :: HNil, String, Int :: Float :: String :: HNil]

  }

  test("list contains subtype of argument") {
    check[Float :: String :: Int ::  HNil, AnyRef, Float :: String :: Int :: HNil]
  }

  test("adding to empty list") {
    check[HNil, Int, Int :: HNil]

  }

}
