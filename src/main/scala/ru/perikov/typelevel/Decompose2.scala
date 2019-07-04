package ru.perikov.typelevel

/** Find a type that can be decomposed to First and Second */
trait Decompose2[First, Second] extends TypeFunc {
  def project1(r: Res): First
  def project2(r: Res): Second
}
