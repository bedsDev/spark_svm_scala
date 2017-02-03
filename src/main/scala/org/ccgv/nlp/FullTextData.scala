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


import org.json4s._
// import org.json4s.native.JsonMethods._
import org.json4s.jackson.JsonMethods._

import org.apache.commons.io.IOUtils;

object FullTextData{
	def main(args: Array[String]) { 

		val conf = new SparkConf()
			.setAppName("Reading Data")
	    val sc = new SparkContext(conf)


		println("FullTextData Class")
		val inputFnm:String = "data/drinventordata.json.gz"
		val drData = gis(inputFnm)
		val jsonString = IOUtils.toString(drData)
		// println(jsonString.substring(0,100))

		val json = parse(jsonString)
		// println(json)
		// for(jj <-json){
		// 	println(jj("doi"))
		// }
		// json.map(jj => println(jj("doi")))
		val first = json(0)
		println(first)
		// println(first(0))
		// json.foreach(x => println(x._1))
		println(json.getClass)
		val list = json.values.asInstanceOf[List[Map[String,String]]]
		for(obj <- list){
			println(obj("doi"))
			println(obj("year"))
			println(obj("title"))
		}
		sc.stop()
	}


	def gis(s:String) = new GZIPInputStream(new BufferedInputStream(new FileInputStream(s)))
}
