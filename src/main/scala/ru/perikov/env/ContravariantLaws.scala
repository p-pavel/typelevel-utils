package ru.perikov.env

/**
  * Simulate contravariant environment like Reader[-E, +A]
  */
trait ContravariantLaws extends EnvironmentLaws {
  import ru.perikov.typelevel.MoreSpecificType

  override type Empty = Any

  /** Singleton environment */
  override type Singleton[T] = IdSingleton[T]
  override type Combine[x,y] = MoreSpecificType[x,y]
  override type Narrow[from,to] = from <:< to
}




