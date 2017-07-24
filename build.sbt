name := """play-utils"""

organization := "com.jasperdenkers"

scalaVersion := "2.11.8"

crossScalaVersions := Seq("2.11.8", "2.12.2")

homepage := Some(url("https://gitlab.com/jasperdenkers/play-utils"))

licenses := Seq("MIT" -> url("https://opensource.org/licenses/mit-license"))

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  // Testing
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.1" % Test
)

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <scm>
    <url>git@gitlab.com:jasperdenkers/play-utils.git</url>
    <connection>scm:git:git@gitlab.com:jasperdenkers/play-utils.git</connection>
    <developerConnection>scm:git:git@gitlab.com:jasperdenkers/play-utils.git</developerConnection>
  </scm>
  <developers>
    <developer>
      <id>jasperdenkers</id>
      <name>Jasper Denkers</name>
      <url>http://jasperdenkers.com</url>
    </developer>
  </developers>
)

import ReleaseTransformations._

releaseCrossBuild := true

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(action = Command.process("publishSigned", _)),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(action = Command.process("sonatypeReleaseAll", _)),
  pushChanges
)