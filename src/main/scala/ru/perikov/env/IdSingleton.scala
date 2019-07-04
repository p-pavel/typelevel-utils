package ru.perikov.env

import ru.perikov.typelevel.Impostor

/** singleton environment represented with the type itself */
class IdSingleton[T] extends Impostor[T] {
  override type Res = T
  override def apply(r: T): T = r
}

object IdSingleton {
  implicit def itself[T]: IdSingleton[T] = new IdSingleton[T]
}