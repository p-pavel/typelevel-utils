package ru.perikov.typelevel.hlist

import org.scalatest.FunSuite

class LookupTest extends FunSuite {


  test("Element at the head") {
    assert(Lookup[Int](3 :: "Asd" :: HNil) === 3)
  }

  test("Element in the tail") {
    assert(Lookup[Int]("asd":: 3 :: HNil) == 3)
  }

  test ( "list contains subtype of requested") {
    assert(Lookup[collection.immutable.Seq[Int]](3 :: "asd" :: List(1,2,3) :: HNil  ) == List(1,2,3))
  }

  val k = "ASD"
  Lookup.head[String, k.type , HNil]
  test ("No such element") {
    assertDoesNotCompile("Lookup[Int](\"asd\" :: 3f :: HNil)")
  }

}
