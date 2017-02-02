
package org.ccgv.nlp

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

// import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD}
// import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
// import org.apache.spark.mllib.util.MLUtils
// import org.apache.spark.mllib.fpm.FPGrowth
import org.apache.spark.rdd.RDD
import java.io.{PrintWriter,File}
// import java.io.BufferedReader
import java.io.BufferedInputStream
// import java.io.InputStreamReader
import java.util.zip.GZIPInputStream
import java.io.FileInputStream

object FullTextData{
	def main(args: Array[String]) { 

		val conf = new SparkConf()
			.setAppName("Reading Data")
	    val sc = new SparkContext(conf)


		println("FullTextData Class")
		val inputFnm:String = "data/drinventordata.json.gz"
		val drData = gis(inputFnm)

		println(drData)
		sc.stop()
	}


	def gis(s:String) = new GZIPInputStream(new BufferedInputStream(new FileInputStream(s)))
}
