package ru.perikov.typelevel

trait AssertT2[Func[_,_] <: TypeFunc] {
  def apply[A1,A2,Expected](implicit ev: Func[A1,A2] {type Res = Expected}): Func[A1, A2] { type Res = Expected }
  = ev
}
