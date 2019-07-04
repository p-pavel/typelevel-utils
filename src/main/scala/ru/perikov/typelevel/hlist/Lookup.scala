package ru.perikov.typelevel.hlist

import scala.annotation.implicitNotFound

/**
  * Type indexed lookup in [[HList]]
 *
  * @tparam L list to look into
  * @tparam T required type
  */
@implicitNotFound("${L} is not an HList containing element of type ${T} ")
trait Lookup[L,T] extends (L ⇒ T)

trait LookupLowPri {
  implicit def tail[A,Tail <: HList, T](implicit ev: Lookup[Tail,T]): Lookup[A :: Tail, T] =
    (l: A :: Tail) => ev(l.tail)
}
object Lookup extends LookupLowPri {
  implicit def head[A, B <: A, Tail <: HList]: Lookup[B :: Tail, A] = _.head
  class Look[T] {
    def apply[L <: HList](l: L)(implicit ev: Lookup[L,T]): T = ev(l)
  }
  def apply[T]: Look[T] = new Look[T]

}

