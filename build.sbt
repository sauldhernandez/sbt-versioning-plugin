name := "sbt-versioning-plugin"

organization := "com.sauldhernandez"

version := "1.0.1"

sbtPlugin := true

scalaVersion in Global := "2.10.5"

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.7.0")

bintrayRepository := "sbt-tooling"

lazy val credentialsFile = file("bintray.credentials")
lazy val writeCredentials = TaskKey[Unit]("write-credentials")

writeCredentials := {
  val content =
    s"""
      |realm = Bintray API Realm
      |host = api.bintray.com
      |user = ${sys.env.getOrElse("BINTRAY_USERNAME", "")}
      |password = ${sys.env.getOrElse("BINTRAY_PASSWORD", "")}
    """.stripMargin

  IO.write(credentialsFile, content)
}

bintrayEnsureCredentials := (bintrayEnsureCredentials dependsOn writeCredentials).value

bintrayCredentialsFile := credentialsFile

publishMavenStyle := true

pomExtra := <url>https://github.com/sauldhernandez/sbt-versioning-plugin</url>
  <licenses>
    <license>
      <name>MIT</name>
      <url>http://opensource.org/licenses/MIT</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:sauldhernandez/sbt-versioning-plugin.git</url>
    <connection>scm:git:git@github.com:sauldhernandez/sbt-versioning-plugin.git</connection>
  </scm>
  <developers>
    <developer>
      <id>sauldhernandez</id>
      <name>Saul Hernandez</name>
      <url>http://github.com/sauldhernandez</url>
    </developer>
  </developers>

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))