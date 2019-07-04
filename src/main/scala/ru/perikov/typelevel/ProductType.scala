package ru.perikov.typelevel

/**
  * Product of types x & y (expressed as x with y in Scala 2)
  * @tparam x
  * @tparam y
  */
class ProductType[x,y] extends Decompose2[x,y] {
  type Res = x with y
  override def project1(r: Res): x = r
  override def project2(r: Res): y = r
}

object ProductType {
  implicit def create[x,y]: ProductType[x,y]  = new ProductType[x,y]
}