package com.sauldhernandez.sbt.versioning

import sbt.Keys._
import sbt._
import sbtbuildinfo.BuildInfoPlugin
import sbtbuildinfo.BuildInfoPlugin.autoImport._

/* Copyright (C) 2016 Synergy-GB LLC.
 * All rights reserved.
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package.
 */
/**
  * Created by saul on 1/26/16.
  */
object VersioningPlugin extends AutoPlugin {

  override def requires = BuildInfoPlugin

  object autoImport {
    sealed trait BuildValueSource
    case class EnvironmentVariable(variable : String) extends BuildValueSource
    case class JavaProperty(propertyName : String) extends BuildValueSource
    case class Version(major : Int, minor : Int, patch : Int)

    lazy val buildNumberSource = SettingKey[BuildValueSource]("build-number-source", "From what value should the build number be read from")
    lazy val releaseSource = SettingKey[BuildValueSource]("release-source", "From what value should the snapshot option should be read from")
    lazy val semanticVersion = SettingKey[Version]("semantic-version", "The version number that will be used.")
  }

  import autoImport._
  override def projectSettings = Seq(
    buildInfoPackage := "versioning",
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion, organization),
    buildNumberSource := EnvironmentVariable("BUILD_NUMBER"),
    releaseSource := EnvironmentVariable("RELEASE"),

    version := {
      val semVer = semanticVersion.value
      val base = s"${semVer.major}.${semVer.minor}.${semVer.patch}"
      val buildNumber = readValue(buildNumberSource.value).map(_.toInt)
      val isRelease = readValue(releaseSource.value).exists(_ == "true")

      buildNumber.map(b => s"$base.$b").getOrElse(base) + (if(!isRelease) "-SNAPSHOT" else "")
    }
  )

  private def readValue(from : BuildValueSource) : Option[String] = from match {
    case EnvironmentVariable(varName) => sys.env.get(varName)
    case JavaProperty(propertyName) => sys.props.get(propertyName)
  }
}
