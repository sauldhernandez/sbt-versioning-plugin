# SBT Versioning Plugin

[![Build Status](https://snap-ci.com/sauldhernandez/sbt-versioning-plugin/branch/master/build_image)](https://snap-ci.com/sauldhernandez/sbt-versioning-plugin/branch/master)

## Overview

This plugin allows you to automatically configure the `version` setting of your project based on a semantic version and
environment variables or java properties that indicate the build number and if the version to produce is a snapshot or not.

## Usage

- Add the plugin in your `plugins.sbt`:

```
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
addSbtPlugin("com.sauldhernandez" % "sbt-versioning-plugin" % "1.0.0-SNAPSHOT")
```

- This plugin is an AutoPlugin, so you must add it to your project in `build.sbt`:

```
lazy val root = (project in file(".")).enablePlugins(VersioningPlugin)
```

- Define a semantic version for your project:

```
semanticVersion := Version(1, 0, 0)
```