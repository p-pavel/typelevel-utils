package ru.perikov.env

import org.scalatest.FunSuite

class TypeclassLikeInference extends FunSuite {
  import hlistEnvironment._
  implicit class Cmp[A](a: A)(implicit o: Ordering[A]) {
    def <(b: A): Boolean = o.lt(a,b)
  }
  def max[A](a: A, b: A) = asks[Ordering[A]]{ implicit o ⇒
    if (a < b) b else a
  }
  def inc[A](a: A) = asks[Numeric[A]]{ implicit n ⇒
    n.plus(a, n.fromInt(1))
  }
  def incMax[A](a: A, b: A) = for  {
    t1 ← max(a,b)
    t2 ← inc(t1)
  } yield t2

  def showIncMax[A](a: A, b: A) = incMax(a,b).flatMap{r ⇒ asks[Show[A]](_.show(r))}

  class  Show[A] {
    def show(a: A): String = a.toString
  }

  implicit def mkShow[A] = new Show[A]


  test("running") {
    val s = showIncMax(5,6)
    assertResult("7")(runOnImplicits(s))
  }

}
