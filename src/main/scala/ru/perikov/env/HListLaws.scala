package ru.perikov.env

import ru.perikov.typelevel.Impostor


/**
  * Laws for the environment represented as HList of required types
  */
trait HListLaws extends EnvironmentLaws {
  import ru.perikov.typelevel.hlist.{HNil, Merge, Sublist}

  /** Singleton environment */
  override type Singleton[T] = HListLaws.Singleton[T]
  override type Empty = HNil
  override type Narrow[from, to] = Sublist[from,to]
  override type Combine[l1,l2] = Merge[l1,l2]
}

object HListLaws {
  import ru.perikov.typelevel.hlist._
  class Singleton[T] extends Impostor[T] {
    override type Res = T :: HNil
    override def apply(r: T :: HNil): T = r.head
  }
  object Singleton {
    implicit def it[T]: Singleton[T] = new Singleton[T]
  }
}


