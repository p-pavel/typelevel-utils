package ru.perikov.env

import ru.perikov.typelevel.{Decompose2, Impostor}

/**
  * Laws that need to be specified for the specific reader implementation
  */

trait EnvironmentLaws {
  /** Singleton environment */
  type Singleton[x] <: Impostor[x]
  /** How to combine two environments */
  type Combine[x ,y] <: Decompose2[x,y]
  /** What is empty environment */
  type Empty
  /** How to cast wider environment to narrower */
  type Narrow[from, to] <: from ⇒ to
}
