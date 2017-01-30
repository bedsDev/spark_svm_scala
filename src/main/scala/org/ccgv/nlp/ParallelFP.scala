
package org.ccgv.nlp

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

// import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD}
// import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
// import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.fpm.FPGrowth
import org.apache.spark.rdd.RDD
import java.io.{PrintWriter,File}

object ParallelFP{
	def main(args: Array[String]) { 
		val conf = new SparkConf().setAppName("Parallel Frequent Pattern")
	    val sc = new SparkContext(conf)

	    /* the decomposition for the term frequencies of the documents */
	    val inputFilename:String = "termFrequenceDecomposition.csv"
	    // val inputFilename:String = "../topic_retrieval/notebooks/results/termFrequenceDecomposition.csv"
	    val outputFilename:String = "outputs/patternOutputTermDecompose.txt"

	    /* the decomposition for the term frequencies of the documents */
	    // val inputFilename:String = "../topic_retrieval/notebooks/results/customFrequenceDecomposition.csv"
	    // val outputFilename:String = "outputs/patternOutputCustomTermDecompose.txt"

	    /* Examples */
	    // val inputFilename:String= "sample_fpgrowth.txt"
	    // val outputFilename:String = "patternOutput.txt"
	    val data = sc.textFile(inputFilename)

		val transactions: RDD[Array[String]] = data.map(s => s.trim.split(','))



		val fpg = new FPGrowth().setMinSupport(0.0002).setNumPartitions(10)
		val model = fpg.run(transactions)


		val writer = new PrintWriter(new File(outputFilename))		

		var patternStr : String = ""

		model.freqItemsets.collect().foreach { itemset =>
			{
				patternStr = (itemset.items.mkString("[", ",", "]") + ", " + itemset.freq)
				writer.write(patternStr +"\n")
		  		// println(patternStr)
			}
		}

		writer.close
		println("have written file: " + outputFilename + " ")
		// val minConfidence = 0.8
		// model.generateAssociationRules(minConfidence).collect().foreach { rule =>
		//   println(
		//     rule.antecedent.mkString("[", ",", "]")
		//       + " => " + rule.consequent .mkString("[", ",", "]")
		//       + ", " + rule.confidence)
		// }

		sc.stop()
	}
}
