
package org.ccgv.nlp

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD}
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.util.MLUtils


object SVMExample{
	def main(args: Array[String]) { 
		val conf = new SparkConf().setAppName("SVM Example")
	    val sc = new SparkContext(conf)
	    
		val data = MLUtils.loadLibSVMFile(sc, "sample_libsvm_data.txt")

		// Split data into training (60%) and test (40%).
		val splits = data.randomSplit(Array(0.6, 0.4), seed = 11L)
		val training = splits(0).cache()
		val test = splits(1)

		// Run training algorithm to build the model
		val numIterations = 100
		val model = SVMWithSGD.train(training, numIterations)

		// Clear the default threshold.
		model.clearThreshold()

		// Compute raw scores on the test set.
		val scoreAndLabels = test.map { point =>
		  val score = model.predict(point.features)
		  (score, point.label)
		}

		// Get evaluation metrics.`
		val metrics = new BinaryClassificationMetrics(scoreAndLabels)
		val auROC = metrics.areaUnderROC()

		println("Area under ROC = " + auROC)

		// Save and load model
		model.save(sc, "target/tmp/scalaSVMWithSGDModel")
		val sameModel = SVMModel.load(sc, "target/tmp/scalaSVMWithSGDModel")
	}
}
