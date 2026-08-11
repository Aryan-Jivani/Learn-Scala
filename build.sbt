ThisBuild / version := "0.1.0-SNAPSHOT"

//ThisBuild / scalaVersion := "3.3.8"

val pekkoVersion = "1.1.5"
val pekkoHttpVersion = "1.3.0"

ThisBuild/ scalaVersion:= "2.12.20"
lazy val root = (project in file("."))
  .settings(
    name := "rockthejvm",
    libraryDependencies ++=Seq(
      "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion, // manages the application runtime.
      "org.apache.pekko" %% "pekko-stream" % pekkoVersion, // handles asynchronous request/response streams.
      "org.apache.pekko" %% "pekko-http" % pekkoHttpVersion, // supplies the HTTP server and routing DSL.
      "org.apache.pekko" %% "pekko-http-spray-json" % pekkoHttpVersion // converts case classes to and from JSON.
    )
  )
