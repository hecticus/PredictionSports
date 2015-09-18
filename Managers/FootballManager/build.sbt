import play.PlayJava

name := """FootballManager"""

version := "1.0-SNAPSHOT"

lazy val root = project.in(file(".")).enablePlugins(PlayJava).aggregate(jobCore).dependsOn(jobCore)

lazy val jobCore = project.in(file("modules/JobCore")).enablePlugins(PlayJava)

scalaVersion := "2.10.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "com.jolbox" % "bonecp-spring" % "0.8.0.RELEASE",
  "bouncycastle" % "bcprov-jdk15" % "140",
  "commons-net" % "commons-net" % "3.3",
  "org.apache.sanselan" % "sanselan" %"0.97-incubator",
  "org.apache.commons" % "commons-io" % "1.3.2",
  "org.apache.jclouds.driver" % "jclouds-slf4j" % "1.8.0",
  "org.apache.jclouds.driver" % "jclouds-sshj" % "1.8.0",
  "org.apache.jclouds.provider" % "rackspace-cloudservers-us" % "1.8.0",
  "org.apache.jclouds.provider" % "cloudfiles-us" % "1.8.0",
  "org.apache.jclouds.provider" % "rackspace-cloudblockstorage-us" % "1.8.0",
  "org.apache.jclouds.provider" % "rackspace-clouddns-us" % "1.8.0",
  "org.apache.jclouds.provider" % "rackspace-cloudloadbalancers-us" % "1.8.0",
  "org.apache.jclouds.provider" % "rackspace-cloudservers-uk" % "1.8.0",
  "org.apache.jclouds.provider" % "cloudfiles-uk" % "1.8.0",
  "org.apache.jclouds.provider" % "rackspace-cloudblockstorage-uk" % "1.8.0",
  "org.apache.jclouds.provider" % "rackspace-clouddns-uk" % "1.8.0",
  "org.apache.jclouds.provider" % "rackspace-cloudloadbalancers-uk" % "1.8.0",
  "org.apache.jclouds" % "jclouds-compute" % "1.8.0",
  "be.objectify"  %% "deadbolt-java"     % "2.3.0-RC1",
  "com.feth"      %% "play-authenticate" % "0.6.5-SNAPSHOT",
  "org.jsoup" % "jsoup" % "1.8.1"
)

resolvers ++= Seq(
    "Maven1 Repository" at "http://repo1.maven.org/maven2/net/vz/mongodb/jackson/play-mongo-jackson-mapper_2.10/1.1.0/",
    "Apache" at "http://repo1.maven.org/maven2/",
    "jBCrypt Repository" at "http://repo1.maven.org/maven2/org/",
    "play-easymail (release)" at "http://joscha.github.io/play-easymail/repo/releases/",
    "play-easymail (snapshot)" at "http://joscha.github.io/play-easymail/repo/snapshots/",
    Resolver.url("Objectify Play Repository", url("http://schaloner.github.io/releases/"))(Resolver.ivyStylePatterns),
    "play-authenticate (release)" at "http://joscha.github.io/play-authenticate/repo/releases/",
    "play-authenticate (snapshot)" at "http://joscha.github.io/play-authenticate/repo/snapshots/"
)


