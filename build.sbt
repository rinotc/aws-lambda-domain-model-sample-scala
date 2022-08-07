ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val commonLibraries = Seq(
  "com.google.inject"      % "guice"     % "5.1.0",
  "software.amazon.awssdk" % "dynamodb"  % "2.17.246",
  "org.scalatest"         %% "scalatest" % "3.2.12" % Test
)

lazy val `lambda-port-adapter-sample` = (project in file("lambda-port-adapter-sample"))
  .dependsOn(`arch`)
  .settings(
    name := "lambda-port-adapter-sample",
    libraryDependencies ++= commonLibraries
  )

lazy val `play-port-adapter-sample` = (project in file("play-port-adapter-sample"))
  .enablePlugins(PlayScala)
  .dependsOn(`arch`)
  .settings(
    name := "play-port-adapter-sample",
    libraryDependencies ++= commonLibraries
  )

lazy val `arch` = (project in file("arch"))
  .settings(
    name := "arch",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.12" % Test
    )
  )
