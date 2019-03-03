
name := "EXTAPI"

version := "1.0"

lazy val `root` = (project in file(".")).enablePlugins(
  PlayJava,
  PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  javaWs,
  specs2 % Test,
  "mysql" % "mysql-connector-java" % "5.1.47",
  "org.modelmapper" % "modelmapper" % "0.7.7",
  "com.typesafe.play" % "play-mailer_2.11" % "5.0.0",
  filters
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

