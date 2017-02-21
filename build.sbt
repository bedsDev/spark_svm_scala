name := "SVM"

version := "1.0"

scalaVersion := "2.11.8"


/**
 to use unamagedBase to direct the dependency libary in a certain location,
 so that the compilation does not need to download the library online. 
 */

/**this is local laptop dependency libary path */
// unmanagedBase := file("../../../softwares/spark-2.0.0-bin-hadoop2.7/jars")
// unmanagedBase := file("../../../softwares/spark-2.1.0-bin-hadoop2.7/jars")
// unmanagedBase := file("/workspaces/spark-2.0.2-bin-hadoop2.7")

/* spark downloaded position in University computer*/
// unmanagedBase := file("../../../software/spark-2.1.0-bin-hadoop2.7/jars")

unmanagedBase := baseDirectory.value / "../../../software/spark-2.1.0-bin-hadoop2.7/jars"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core_2.10
//libraryDependencies += "org.apache.spark" %% "spark-core" % "2.0.2"
// https://mvnrepository.com/artifact/org.apache.spark/spark-mllib_2.11
//libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.0.2"

val latestVersion = "3.5.0"

val json4sJackson = "org.json4s" %% "json4s-jackson" % "3.5.0"

// libraryDependencies += json4sJackson
// libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.0"

