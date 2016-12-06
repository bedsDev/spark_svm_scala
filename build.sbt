name := "SVM"

version := "1.0"

scalaVersion := "2.11.8"

// unmanagedBase := file("/softwares/spark-2.0.0-bin-hadoop2.7/jars")
// unmanagedBase := file("/workspaces/spark-2.0.2-bin-hadoop2.7")

// https://mvnrepository.com/artifact/org.apache.spark/spark-core_2.10
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.0.2"
// https://mvnrepository.com/artifact/org.apache.spark/spark-mllib_2.11
libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.0.2"

