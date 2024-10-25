name := "fpinscala"

ThisBuild / scalaVersion := "3.3.0"

ThisBuild / githubWorkflowBuild := Seq(WorkflowStep.Sbt(name = Some("Build project"), commands = List("test:compile")))

ThisBuild / scalacOptions ++= List("-feature", "-deprecation", "-Ykind-projector:underscores", "-source:future")

ThisBuild / libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
ThisBuild / libraryDependencies += "junit" %% "junit" % "4.13.2"
ThisBuild / libraryDependencies += "org.typelevel" %%  "munit-cats-effect-3" % "1.0.7" // % Test
ThisBuild / libraryDependencies  += "io.github.quafadas" %% "scautable" % "0.0.8" //% Test

ThisBuild / libraryDependencies += "com.github.pathikrit" %% "better-files" % "3.9.2"
ThisBuild / libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.18"
//ThisBuild / libraryDependencies += "org.scala-lang" %% "scala3-compiler" % "3.5.0"
ThisBuild / libraryDependencies += "com.lihaoyi" %% "ammonite" % "3.0.0-M2"
//ThisBuild / libraryDependencies += "com.lihaoyi" %% "ammonite" % "2.4.0" % "test" cross CrossVersion.full