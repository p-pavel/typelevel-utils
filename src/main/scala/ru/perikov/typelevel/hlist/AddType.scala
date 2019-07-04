package ru.perikov.typelevel.hlist

import ru.perikov.typelevel.Decompose2

import scala.annotation.implicitNotFound

/**
  * Type level adding type to HList.
  *
  *  If L already contains subtype of T, then Res = L
  *
  *  If L contains the supertype of T then it will be changed to T
  *
  *  Otherwise T is added to L
  *
  * @tparam L
  * @tparam T
  */
@implicitNotFound("First argument should be HList, bit got ${L}")
trait AddType[L, T] extends Decompose2[L,T]


trait AddTypeLowLowPri {
  implicit def toEnd[T, Head, Tail <: HList, S <: HList](implicit ev: AddType.Aux[Tail,T,S]):AddType.Aux[Head :: Tail, T, Head :: S] =
    new AddType[Head :: Tail, T] {
      type Res = Head :: S
      override def project2(l: Res): T = ev.project2(l.tail)
      override def project1(l: Head ::  S): Head :: Tail = l.head :: ev.project1(l.tail)
    }

}

trait AddTypeLowPri extends AddTypeLowLowPri {
  implicit def headSubtype[T, Head <: T,Tail <: HList]: AddType.Aux[Head :: Tail, T, Head::Tail] = new AddType[Head :: Tail, T] {
    type Res = Head :: Tail
    override def project2(l: Head :: Tail): T = l.head
    override def project1(l: ::[Head, Tail]): Head :: Tail = l
  }
}

object AddType extends AddTypeLowPri {
  type Aux[L,T,R] = AddType[L,T] {type Res = R}
  implicit def forHNil[T]: Aux[HNil, T, T :: HNil] = new AddType[HNil,T] {
    type Res = T :: HNil
    override def project2(l: Res): T = l.head
    override def project1(l: ::[T, HNil]): HNil = HNil
  }
  implicit def replace[T, Head >: T, Tail <: HList]: AddType.Aux[Head::Tail, T, T :: Tail] = new AddType[Head::Tail, T] {
    type Res = T :: Tail
    override def project2(l: Res): T = l.head
    override def project1(l: ::[T, Tail]): Head :: Tail = l
  }
}

