package ru.perikov.typelevel.hlist

trait HListFromImplicits[T ] {
  val res: T
}

object HListFromImplicits {
  def apply[T <: HList](implicit ev: HListFromImplicits[T]): T = ev.res

  implicit object empty extends HListFromImplicits[HNil] {
    override val res: HNil = HNil
  }

  implicit def cons[Head, Tail <: HList] (implicit head: Head, tail: HListFromImplicits[Tail]): HListFromImplicits[Head :: Tail] =
    new HListFromImplicits[Head :: Tail] {
      override val res = head :: tail.res
    }

}
