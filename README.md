# SBT Versioning Plugin

[![Build Status](https://travis-ci.org/sauldhernandez/sbt-versioning-plugin.svg?branch=master)](https://travis-ci.org/sauldhernandez/sbt-versioning-plugin)
## Overview

This plugin allows you to automatically configure the `version` setting of your project based on a semantic version and
environment variables or java properties that indicate the build number and if the version to produce is a snapshot or not.

## Usage

- Add the plugin in your `plugins.sbt`:

```
addSbtPlugin("com.sauldhernandez" % "sbt-versioning-plugin" % "1.0.1")
```

- This plugin is an AutoPlugin, so you must add it to your project in `build.sbt`:

```
lazy val root = (project in file(".")).enablePlugins(VersioningPlugin)
```

- Define a semantic version for your project:

```
semanticVersion := Version(1, 0, 0)
```

- Indicate whether your build will be a release or not. By default, this is indicated via the `RELEASE` environment variable.
  You may change the value used for this by setting the value of the `releaseSource` key.

```
releaseSource := EnvironmentVariable("ISRELEASE")
```

or

```
releaseSource := JavaProperty("release")
```

if you want to use java properties instead of environment variables.

- (Optional) If an environment variable named `BUILD_NUMBER` is defined, it will be used
as the build number for the version and added to it. Otherwise, it will not be shown. You may
change the source of the build number by setting the value of the `buildNumberSource` key.



