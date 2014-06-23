name := "camp2014akka"

version := "1.0"

scalaVersion := "2.11.0"

//Typesafe
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

//spray
resolvers += "spray repo" at "http://repo.spray.io"

//dependencies for AKKA 
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.2" withSources()

libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.3.2" withSources()

libraryDependencies += "com.typesafe.akka" %% "akka-testkit"  % "2.3.2" % "test" withSources()

//ScalaTest
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.0" % "test" withSources()

libraryDependencies += "org.scala-lang" % "scala-library-all" % "2.11.0"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)