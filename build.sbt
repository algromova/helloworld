lazy val appVersion = "0.0.1-SNAPSHOT"

scalaVersion := "2.11.8"

lazy val appSettings = Seq(
  organization := "com.shc",
  version := appVersion,
  name := "analytics",
  scalaVersion := "2.11.8",
  organization := "test",
  packageName in Docker := name.value,
  version in Docker := "latest",
  dockerBaseImage := "openjdk:8-alpine",
  maintainer in Docker := "test",
  daemonUser in Docker := "root",
  dockerExposedPorts := Seq(9000)
)

lazy val autoScalaLibrary = false

lazy val javaVersion = "1.8"

lazy val sparkVersion = "2.2.0"

lazy val scalaTestVersion = "2.2.6"

lazy val scalaCheckVersion = "1.13.5"

unmanagedBase := baseDirectory.value / "lib"

unmanagedJars in Compile := (baseDirectory.value ** "*.jar").classpath

lazy val sparkLib = Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % Provided withSources() withJavadoc(),
  "org.apache.spark" %% "spark-sql" % sparkVersion % Provided withSources() withJavadoc(),
  "org.apache.spark" %% "spark-hive" % sparkVersion % Provided withSources() withJavadoc(),
  "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.5" % Provided
)

lazy val auxLib = Seq(
  "mysql" % "mysql-connector-java" % "5.1.42",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
  "org.slf4j" % "slf4j-api" % "1.7.25",
  "org.slf4j" % "log4j-over-slf4j" % "1.7.25",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe" % "config" % "1.3.1"
)

lazy val testLib = Seq(
  "org.scalatest" %% "scalatest" % scalaTestVersion % Test withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % scalaCheckVersion % Provided withSources() withJavadoc()
)

lazy val playLib = Seq(
  ws,
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",
  "com.h2database" % "h2" % "1.4.187",
  "io.swagger" %% "swagger-play2" % "1.5.3",
  "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % "test",
  "org.mockito" % "mockito-core" % "1.9.5" % "test"
)

crossPaths in Global := false

publishMavenStyle in Global := true

lazy val root = (project in file("."))
  .settings(appSettings: _*)
  .enablePlugins(PlayScala, UniversalPlugin, DockerPlugin, AshScriptPlugin)
  .settings(
    libraryDependencies ++= sparkLib,
    libraryDependencies ++= auxLib,
    libraryDependencies ++= testLib,
    libraryDependencies ++= playLib
  )
 // .settings(addArtifact(artifact in(Compile)).settings: _*)

fork in Global := true




routesGenerator := InjectedRoutesGenerator
