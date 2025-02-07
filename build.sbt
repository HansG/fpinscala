name := "fpinscala"

ThisBuild / scalaVersion := "3.5.1"

ThisBuild / githubWorkflowBuild := Seq(WorkflowStep.Sbt(name = Some("Build project"), commands = List("test:compile")))

ThisBuild / scalacOptions ++= List("-feature", "-deprecation", "-Ykind-projector:underscores", "-source:future")

ThisBuild / libraryDependencies += "org.typelevel" %% "munit-cats-effect" % "2.0.0-M1" //% Test
//ThisBuild / libraryDependencies += "org.typelevel" %%  "munit-cats-effect-3" % "1.0.7" // % Test

libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
ThisBuild / libraryDependencies += "junit" % "junit" % "4.13.2"
ThisBuild / libraryDependencies  += "io.github.quafadas" %% "scautable" % "0.0.8" //% Test

ThisBuild / libraryDependencies += "com.github.pathikrit" %% "better-files" % "3.9.2"
ThisBuild / libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.18"
//ThisBuild / libraryDependencies += "org.scala-lang" %% "scala3-compiler" % "3.5.0"
ThisBuild / libraryDependencies += "com.lihaoyi" % "ammonite_3.5.1" % "3.0.0"
//ThisBuild / libraryDependencies += "com.lihaoyi" % "ammonite-cross-23-ops_2.13" % "2.4.1"
ThisBuild / libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.10.1"
//ThisBuild / libraryDependencies += "com.lihaoyi" %% "ammonite" % "2.4.0" % "test" cross CrossVersion.full