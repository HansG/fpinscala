name := "fpinscala"

ThisBuild / scalaVersion := "3.2.0"

ThisBuild / githubWorkflowBuild := Seq(WorkflowStep.Sbt(name = Some("Build project"), commands = List("test:compile")))

ThisBuild / scalacOptions ++= List("-feature", "-deprecation", "-Ykind-projector:underscores", "-source:future")

ThisBuild / libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
ThisBuild / libraryDependencies += "org.typelevel" %%  "munit-cats-effect-3" % "1.0.7"  % Test
ThisBuild / libraryDependencies  += "io.github.quafadas" %% "scautable" % "0.0.5" % Test
