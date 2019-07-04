package ru.perikov.env

import ru.perikov.typelevel.hlist._

/**
  * WARNING! This kind of Reader can be very slow due to HList manipulations.
  * The intended usage is to run it in non-performance-critical part of the program,
  * e.g. building monadic value from configuration etc.
  *
  * Environment based on [[ru.perikov.typelevel.hlist.HList]]
  */
object hlistEnvironment extends ReaderOps[HListLaws] {
  def runOnImplicits[T <: HList , A](r: Reader[T, A])(implicit env: HListFromImplicits[T]): A =
    r.run(env.res)
}
