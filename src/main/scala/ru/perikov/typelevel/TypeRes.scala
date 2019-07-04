package ru.perikov.typelevel

class TypeRes[A]
object TypeRes {
  def apply[T <: TypeFunc](implicit ev: T): TypeRes[ev.Res] = new TypeRes
}
