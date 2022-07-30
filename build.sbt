ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val commonLibraries = Seq(
  "com.google.inject" % "guice"     % "5.1.0",
  "org.scalatest"    %% "scalatest" % "3.2.12" % Test
)

lazy val `lambda-port-adapter-sample` = (project in file("lambda-port-adapter-sample"))
  .settings(
    name := "lambda-port-adapter-sample",
    libraryDependencies ++= commonLibraries
  )

lazy val `play-port-adapter-sample` = (project in file("play-port-adapter-sample"))
  .enablePlugins(PlayScala)
  .settings(
    name := "play-port-adapter-sample",
    libraryDependencies ++= commonLibraries
  )
