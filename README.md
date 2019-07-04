# typelevel-utils
Collection of type-level utils I've collected from my projects with minimal dependencies

Currently contains the implementation of HList and ReaderMonad generalized on the way how the environments are combined.

Supported are:
* contravariant environments (Reader[-Env, +A])
* ZIO-style environments based on product types Reader[String with Int with MyConfig, A]
* HList based environments combining HList of required types
