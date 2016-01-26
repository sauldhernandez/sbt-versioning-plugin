
name := "sbt-versioning-plugin"

organization := "com.sauldhernandez"

version := "1.0.0-SNAPSHOT"

sbtPlugin := true

scalaVersion in Global := "2.10.5"

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.5.0")

// Settings to build a nice looking plugin site
site.settings

com.typesafe.sbt.SbtSite.SiteKeys.siteMappings <+= baseDirectory map { dir =>
  val nojekyll = dir / "src" / "site" / ".nojekyll"
  nojekyll -> ".nojekyll"
}

site.sphinxSupport()

site.includeScaladoc()

// enable github pages
ghpages.settings

git.remoteRepo := "git@github.com:sauldhernandez/sbt-versioning-plugin.git"

// Scripted - sbt plugin tests
scriptedSettings

scriptedLaunchOpts <+= version apply { v => "-Dproject.version="+v }

useGpg := true

usePgpKeyHex("34de53dd")

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomIncludeRepository := { _ => false }

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