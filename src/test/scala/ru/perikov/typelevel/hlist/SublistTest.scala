package ru.perikov.typelevel.hlist

import org.scalatest.FunSuite

class SublistTest extends FunSuite {

  test("empty is sublist of anything") {
    val res = Sublist[Float :: Int :: HNil, HNil]
    assert(res(3f :: 10 :: HNil) == HNil)
  }

  test("test contains") {
    type Lst = None.type :: Float :: Int :: HNil
    val lst: None.type :: Float :: Int :: HNil = None :: 3f :: 10  :: HNil
    val sub: Int :: Option[String] :: HNil = 10 :: None :: HNil
    val res = Sublist[Lst , Int :: Option[String] :: HNil]
    assert(res(lst) == sub)

  }

}
