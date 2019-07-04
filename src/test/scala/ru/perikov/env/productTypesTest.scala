package ru.perikov.env

import org.scalatest.FunSuite

class productTypesTest extends FunSuite {
  import productTypes._
  test("Usage") {
    val t1 = pure(10)
    val t2 = asks[Int](_ + 1)
    val t3 = asks[String](_.reverse)
    val t4: GenericReader[ProductTypeLaws, Int with String, Int] = for {
      a ← t1
      b ← t2
      _ ← t3
    } yield a + b

  }

}
