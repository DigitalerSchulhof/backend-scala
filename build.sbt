ThisBuild / scalaVersion := Dependencies.scalaVersion
ThisBuild / version := Versioning.version
ThisBuild / organization := "dsh"
ThisBuild / organizationName := "Digitaler Schulhof"

Compile / PB.targets := Seq(
  scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"
)

lazy val root = (project in file("."))
  .settings(
    name := "backend",
    libraryDependencies ++= Dependencies.dependencies
  )
