import android.Keys._

name := "scala-seapal"

version := "0.5"

scalaVersion := "2.10.3"

android.Plugin.androidBuild

proguardOptions in Android ++= Seq(
			"-dontwarn com.google.android.gms.**",
			"-dontwarn org.codehaus.jackson.map.ext.**",
			"-dontwarn org.apache.commons.logging.**",
			"-dontwarn org.apache.http.**",
			"-dontnote",
			"-dontnote org.apache.http.**",
			"-ignorewarnings",
			"-keep class * extends java.utils.ListResourceBundle { protected Object[][] getContents(); }",
			"-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable { public static final *** NULL; }",
			"-keepnames @com.google.android.gms.common.annotation.KeepName class *",
			"-keepclassmembernames class * { @ccom.google.android.gms.common.annotation.KeepName *; }",
			"-keepnames class * implements android.os.Parcelable { public static final ** CREATOR; }"
)

resolvers += "Couchbase" at "http://files.couchbase.com/maven2/"

libraryDependencies ++= Seq(
  "com.android.support" % "support-v4" % "18.0.0",
  "com.couchbase.cblite" % "CBLite" % "1.0.0-beta",
  "com.couchbase.cblite" % "CBLiteEktorp" % "1.0.0-beta",
  "org.scaloid" %% "scaloid" % "3.2-8",
  "com.google.android.gms" % "play-services" % "4.0.30",
  "org.scalatest" % "scalatest_2.10" % "2.0" % "compile",
  "org.scalatest" % "scalatest_2.10" % "2.0" % "test",
)

run <<= run in Android

install <<= install in Android
