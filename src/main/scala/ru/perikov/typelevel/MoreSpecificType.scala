package ru.perikov.typelevel

import scala.annotation.implicitNotFound


/** Chooses more specific type from x and y. if x &lt;: then Res = x and vice versa */
@implicitNotFound("Types ${x} and ${y} are unrelated")
trait MoreSpecificType[x,y] extends Decompose2[x,y]

trait MoreSpecificTypeLowPri {
  implicit def toSecond[A, B <: A]: MoreSpecificType[A, B] { type Res = B } = new MoreSpecificType[A,B] {
    override type Res = B
    override def project1(r: B): A = r
    override def project2(r: B): B = r
  }
}

object MoreSpecificType extends MoreSpecificTypeLowPri {
  implicit def toFirst[A, B <: A]: MoreSpecificType[B, A] { type Res = B } = new MoreSpecificType[B,A] {
    type Res = B
    override def project1(r: B): B = r
    override def project2(r: B): A = r
  }
}