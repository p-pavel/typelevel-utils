package ru.perikov.env

import org.scalatest.FunSuite

class contravariantTest extends FunSuite {
  test ("Usage") {
    import contravariant._
    val int = pure(10)
    val str = asks[String](_.reverse)
    val res: Reader[String, String] = for {
      t1 ← int
      t2 ← str
    } yield t1.toString + t2

    assertResult("10cba")(res("abc"))
  }

}
