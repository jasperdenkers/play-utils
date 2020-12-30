name := """play-utils"""

organization := "com.jasperdenkers"

scalaVersion := "2.13.4"

crossScalaVersions := Seq("2.12.12", "2.13.4")

homepage := Some(url("https://gitlab.com/jasperdenkers/play-utils"))

licenses := Seq("MIT" -> url("https://opensource.org/licenses/mit-license"))

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  // Testing
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
)

publishTo := sonatypePublishToBundle.value

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

import xerial.sbt.Sonatype._

sonatypeProjectHosting := Some(GitLabHosting("jasperdenkers", "play-utils", "jasperdenkers@gmail.com"))

developers := List(
  Developer(
    id="jasperdenkers",
    name="Jasper Denkers",
    email="jasperdenkers@gmailcom",
    url=url("http://jasperdenkers.com")
  )
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
  releaseStepCommandAndRemaining("+publishSigned"),
  releaseStepCommand("sonatypeBundleRelease"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)

Global / onChangedBuildSource := ReloadOnSourceChanges