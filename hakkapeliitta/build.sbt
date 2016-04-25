import com.typesafe.sbt.packager.universal.UniversalPlugin.autoImport._

name := "hakkapeliitta"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

resolvers += Resolver.jcenterRepo

libraryDependencies ++= Seq(
  cache
  , "com.stripe" % "stripe-java" % "1.42.0"
  , "com.sendgrid" % "sendgrid-java" % "2.2.1"
  , "com.typesafe.play" %% "play-slick" % "2.0.0"
  , "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0"
  , "org.postgresql" % "postgresql" % "9.4.1208"
  , "com.typesafe.slick" %% "slick-codegen" % "3.1.1" % "compile"
  , "com.github.tminglei" %% "slick-pg" % "0.13.0"
  , "com.github.tminglei" %% "slick-pg_play-json" % "0.13.0"
  )


routesGenerator := InjectedRoutesGenerator

val copyPackage = TaskKey[Unit]("copyPackage", "Copies tarball to the Docker directory")
copyPackage <<= (packageZipTarball in Universal) map { tarball =>
  val destinationDir = "../docker/app/temp/"
  val destinationFile = new File(destinationDir + tarball.getName)
  IO.copyFile(tarball, destinationFile)
  println(s"Copying $tarball to $destinationFile")
}