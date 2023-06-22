ThisBuild / version := "0.3.6-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

lazy val root = (project in file("."))
  .settings(
    name := "scalagraphics",
    organization := "com.github.jarlah.scalagraphics",
    idePackagePrefix := Some("com.github.jarlah.scalagraphics"),
    libraryDependencies ++= Seq(
      "org.lwjgl" % "lwjgl" % "3.3.1" % "provided",
      "org.lwjgl" % "lwjgl-opengl" % "3.3.1" % "provided",
      "org.lwjgl" % "lwjgl-vulkan" % "3.3.1" % "provided",
      "org.lwjgl" % "lwjgl-nanovg" % "3.3.1" % "provided",
      "org.joml" % "joml" % "1.10.5" % "provided"
    ),
    libraryDependencies += "org.typelevel" %% "cats-free" % "2.9.0",
    libraryDependencies += "org.typelevel" %% "cats-effect" % "3.5.0",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.16",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.16" % "test",
    libraryDependencies += "org.scalatestplus" %% "mockito-4-6" % "3.2.15.0" % "test",
    publishMavenStyle := true,
    Test / publishArtifact := false,
    pomIncludeRepository := { _ => false },
    publishTo := Some(
      "GitHub Package Registry" at s"https://maven.pkg.github.com/jarlah/${name.value}"
    ),
    licenses := List("MIT" -> url("http://opensource.org/licenses/MIT")),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/jarlah/scalagraphics"),
        "scm:git:git@github.com:jarlah/scalagraphics.git"
      )
    ),
    developers := List(
      Developer(
        id = "jarlah",
        name = "Jarl André Hübenthal",
        email = "jarlah@protonmail.com",
        url = url("https://github.com/jarlah")
      )
    )
  )
