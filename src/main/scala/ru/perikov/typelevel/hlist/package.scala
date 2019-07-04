package ru.perikov.typelevel

package object hlist {
  trait HList {
    def ::[A](x: A): A :: this.type = HCons(x,this)

  }
  trait ::[+Head, +Tail <: HList] extends HList {
    val head: Head
    val tail: Tail
  }
  final case class HCons[+Head, +Tail <: HList](head: Head, tail: Tail) extends (Head :: Tail) {
    override def toString: String = s"$head :: $tail"
  }

  final case object  HNil extends HList {
    override def ::[A](x: A): A :: HNil = super.::(x) // attempt (failed) to work around Idea's type inference
  }

  type HNil = HNil.type


}
