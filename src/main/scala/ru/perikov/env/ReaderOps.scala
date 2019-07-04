package ru.perikov.env

import ru.perikov.typelevel.TypeFunc

trait SingletonEnvironment[Elem] extends TypeFunc {
  def apply(r: Res)
}

/**
  * Extending this trait provides a way to get a reader specialized for specific [[EnvironmentLaws]].
  *
  * @see subclasses
  *
  * @tparam Laws environment laws used
  */
trait ReaderOps[Laws <: EnvironmentLaws] {
  type Reader[E,A] = GenericReader[Laws, E,A ]
  def pure[A](x: A): Reader[Laws#Empty,A] = GenericReader(Function.const(x))
  def ask[T](implicit ev: Laws#Singleton[T]): Reader[ev.Res,T] = GenericReader(ev.apply)
  class Asks[T] {
    def apply[B](f: T ⇒ B)(implicit s: Laws#Singleton[T]): Reader[s.Res,B] = ask[T].map(f)
  }
  def asks[T]: Asks[T] = new Asks[T]
}
