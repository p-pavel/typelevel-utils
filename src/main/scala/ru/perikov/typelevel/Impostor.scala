package ru.perikov.typelevel


/**
  * Find something to represent an object
  * @tparam T
  */
trait Impostor[T] extends TypeFunc  {
  def apply(r: Res): T
}
