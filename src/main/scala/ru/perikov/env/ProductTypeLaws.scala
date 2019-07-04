package ru.perikov.env

import ru.perikov.typelevel.ProductType

/**
  * Simulate ZIO-like environment
  */
trait ProductTypeLaws extends EnvironmentLaws {
  type Empty = Any
  type Singleton[T] = IdSingleton[T]
  type Combine[x ,y] = ProductType[x,y]
  type Narrow[from, to] = from <:< to
}





