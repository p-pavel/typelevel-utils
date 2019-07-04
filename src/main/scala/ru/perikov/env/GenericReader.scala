package ru.perikov.env

/**
  * Generalization of the reader of environment abstracting environment composition
  *
  * @param run the reader of the environment
  * @tparam Laws laws to combine environments. We need Laws#Narrow to provide widening of type
  * @tparam Environment the environment
  * @tparam Res value to compute
  */
final case class GenericReader[Laws <: EnvironmentLaws, Environment, +Res](run: Environment ⇒ Res) extends AnyVal {
  def apply[E](env: E)(implicit ev: Laws#Narrow[E, Environment]): Res = run(ev(env))
  def flatMap[E, B](f: Res ⇒ GenericReader[Laws, E, B])
                   (implicit combine: Laws#Combine[Environment, E]): GenericReader[Laws, combine.Res, B]
  = GenericReader { combinedEnv: combine.Res ⇒
    val env: Environment = combine.project1(combinedEnv)
    val res: Res = run(env)
    val other: GenericReader[Laws, E, B] = f(res)
    val e: E = combine.project2(combinedEnv)
    val b: B = other.run(e)
    b
  }
  def map[B](f: Res⇒B): GenericReader[Laws, Environment, B] = GenericReader(run andThen f)
}

object GenericReader {

  implicit def widen[L <: EnvironmentLaws, Narrow, Wide, R](r: GenericReader[L,Narrow,R])
                                                           (implicit ev: L#Narrow[Wide,Narrow])
    : GenericReader[L, Wide, R] =  GenericReader(e ⇒ r.run(ev(e)))

}



