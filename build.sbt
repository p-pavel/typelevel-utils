name := "typelevel-utils"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.8"
organization := "ru.perikov"
description := "My growing collection of type level utils scavenged from several projects. Will try to keep dependencies minimal "
scalacOptions ++= Seq(
  "-Ypartial-unification",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-feature",
  "-deprecation",
  "-Xfatal-warnings",
)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

