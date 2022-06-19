ThisBuild / tlBaseVersion := "0.0"
ThisBuild / tlUntaggedAreSnapshots := false

ThisBuild / organization := "com.armanbilge"
ThisBuild / organizationName := "Arman Bilge"
ThisBuild / developers := List(
  tlGitHubDev("armanbilge", "Arman Bilge")
)

ThisBuild / tlSonatypeUseLegacyHost := false

ThisBuild / crossScalaVersions := Seq("3.1.2")
ThisBuild / scalacOptions ++= Seq("-new-syntax", "-indent", "-source:future")

ThisBuild / githubWorkflowJavaVersions := Seq(JavaSpec.temurin("17"))
ThisBuild / tlJdkRelease := Some(8)

val catsVersion = "2.8.0"
val catsEffectVersion = "3.3.12"
val fs2Version = "3.2.8"
val http4sVersion = "0.23.12"

val munitVersion = "0.7.29"
val munitCEVersion = "1.0.7"
val disciplineMunitVersion = "1.0.9"
val scalaCheckVersion = "1.16.0"
val scalaCheckEffectVersion = "1.0.4"

lazy val root = tlCrossRootProject.aggregate(core)

lazy val core = crossProject(JVMPlatform, JSPlatform)
  .crossType(CrossType.Pure)
  .in(file("core"))
  .settings(
    name := "http4s-grpc-core",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % catsVersion,
      "org.typelevel" %%% "cats-effect" % catsEffectVersion,
      "co.fs2" %%% "fs2-io" % fs2Version,
      "org.http4s" %%% "http4s-core" % http4sVersion,
      "org.typelevel" %%% "cats-laws" % catsVersion % Test,
      "org.scalameta" %%% "munit-scalacheck" % munitVersion % Test,
      "org.typelevel" %%% "munit-cats-effect-3" % munitCEVersion % Test,
      "org.typelevel" %%% "discipline-munit" % disciplineMunitVersion % Test,
      "org.scalacheck" %%% "scalacheck" % scalaCheckVersion % Test,
      "org.typelevel" %%% "scalacheck-effect-munit" % scalaCheckEffectVersion % Test
    )
  )
