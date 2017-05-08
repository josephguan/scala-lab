organization := "gx"

name := "scala-snippet"

version := "0.1"

scalaVersion := "2.10.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.0" % "test"

libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.2"

libraryDependencies += "org.apache.camel" % "camel-josql" % "2.18.3"
