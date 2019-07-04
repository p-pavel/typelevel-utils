package ru.perikov.typelevel.hlist

import scala.annotation.implicitNotFound

/**
  * Type-level relation of containment -- the evidence that Super contains all types that mentioned in Sub
  * @tparam Super
  * @tparam Sub
  *
  *             @todo: Does not work with singleton types (see tests)
  */
@implicitNotFound("Can not prove that ${Super} contains all types mentioned in ${Sub} (both ${Super} and ${Sub} should be HList")
trait Sublist[Super, Sub] extends (Super ⇒ Sub)

object Sublist {
  def apply[Super, Sub](implicit ev: Sublist[Super, Sub]): Sublist[Super, Sub] = ev
  implicit def emptyIsSublistOfAnything[S]: Sublist[S, HNil] = _ ⇒ HNil
  implicit def nonEmpty[S <: HList,A, Tail <: HList](implicit look: Lookup[S,A], tail: Sublist[S,Tail] ): Sublist[S, A :: Tail] =
    (v1: S) => look(v1) :: tail(v1)

}