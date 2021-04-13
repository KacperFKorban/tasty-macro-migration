val scala3Version = "3.0.0-RC2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "tasty-macro-migration",
    version := "0.1.0",

    scalaVersion := scala3Version
  )
