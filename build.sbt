name := "iteratees"

organization := "jml"

version := "1.0.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.0" % "test",
    "org.scalacheck" %% "scalacheck" % "1.13.3" % "test",
    "com.typesafe.play" % "play-iteratees_2.11" % "2.6.0",
    "org.scala-lang.plugins" % "scala-continuations-library_2.11" % "1.0.2"
)

scalacOptions ++= Seq(
    "-target:jvm-1.8",
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:experimental.macros",
    "-unchecked",
    "-Ywarn-unused-import",
    "-Ywarn-nullary-unit",
    "-Xfatal-warnings",
    "-Xlint",
    "-Yinline-warnings",
    "-Ywarn-dead-code",
    "-Xfuture")

initialCommands := "import jml.iteratees._"
