package ru.perikov.typelevel.hlist

import ru.perikov.typelevel.Decompose2

import scala.annotation.implicitNotFound


/**
  * Type level merge of to HLists. More specific types win.
  *
  * @tparam L1
  * @tparam L2
  */
@implicitNotFound("${L1} and ${L2} should both be HList")
trait Merge[L1, L2] extends Decompose2[L1,L2]

object Merge {
  type Aux[L1, L2, R] = Merge[L1, L2] {type Res = R}

  def apply[L1, L2](implicit ev: Merge[L1,L2]): ev.type  = ev

  implicit def firstEmpty[L]: Aux[HNil, L, L] = new Merge[HNil, L] {
    type Res = L
    override def project1(r: L): HNil = HNil
    override def project2(r: L): L = r
  }

  implicit def nonEmpty[Head, Tail <: HList, L, MergedTail, Merged](implicit ev1: Merge.Aux[Tail, L, MergedTail], ev2: AddType.Aux[MergedTail, Head, Merged]):
  Aux[Head :: Tail, L, Merged] = new Merge[Head :: Tail, L] {
    type Res = Merged

    override def project1(r: Merged): Head :: Tail = {
      val t1: Head = ev2.project2(r)
      val t2: MergedTail = ev2.project1(r)
      val t3: Tail = ev1.project1(t2)
      t1 :: t3
    }

    override def project2(r: Merged): L = {
      val t1: MergedTail = ev2.project1(r)
      val t2: L = ev1.project2(t1)
      t2
    }

  }
}

