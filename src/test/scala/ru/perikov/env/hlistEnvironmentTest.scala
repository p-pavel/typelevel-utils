package ru.perikov.env

import org.scalatest.FunSuite
import ru.perikov.typelevel.hlist._

class hlistEnvironmentTest extends FunSuite {
  import hlistEnvironment._

  test("Usage") {
    val v1: Reader[HNil, Int] = pure(10)
    val v2: Reader[Int :: HNil, Int] = ask[Int]
    val v3: Reader[String :: HNil, String] = ask[String]
    val v4: Reader[String :: Int :: HNil, String] = for {
      t1 ← v1
      t2 ← v2
      t3 ← v3
    } yield t1.toString + t2.toString + t3
    val res = v4("asd":: 20  :: HNil)
    assertResult("1020asd")(res)
  }

  test ("Widening on run") {
    assertCompiles( """
    val a: Reader[Int :: String :: HNil, Unit] = ???
    a("asd" :: 12 :: 321.0 :: HNil)
    """)
  }

  test ("Implicit widening") {
    assertCompiles(
      """
    val a: Reader[Int :: String :: HNil, Unit] = ???
    val b: Reader[Float :: Int :: String :: HNil, Unit] = a
      """)
  }
}
