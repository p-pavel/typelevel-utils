package ru.perikov.typelevel.hlist

import org.scalatest.FunSuite
import ru.perikov.typelevel.AssertT2

class MergeTest extends FunSuite {

  object check extends AssertT2[Merge]
  test("merge empty list with nonempty") {
    check[HNil, Float :: String :: HNil, Float :: String :: HNil]

  }


  test("check merging non empty lists") {
    type Lst1 = List[Any] :: Int :: HNil
    type Lst2 = Float :: List[String] :: Boolean :: HNil
    val res = Merge[Lst1, Lst2]
    val proj1 = implicitly[Sublist[res.Res, Lst1]]
    val proj2 = implicitly[Sublist[res.Res, Lst2]] // Idea can't infer when types are Int & Float

  }

}
