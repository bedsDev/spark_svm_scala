
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
	    
	    val data = sc.textFile("sample_fpgrowth.txt")

		val transactions: RDD[Array[String]] = data.map(s => s.trim.split(' '))

		val fpg = new FPGrowth()
		  .setMinSupport(0.2)
		  .setNumPartitions(10)
		val model = fpg.run(transactions)


		val writer = new PrintWriter(new File("patternOutput.txt" ))		

		var patternStr : String = ""

		model.freqItemsets.collect().foreach { itemset =>
			{
				patternStr = (itemset.items.mkString("[", ",", "]") + ", " + itemset.freq)
				writer.write(patternStr +"\n")
		  		println(patternStr)
			}
		}

		writer.close

		// val minConfidence = 0.8
		// model.generateAssociationRules(minConfidence).collect().foreach { rule =>
		//   println(
		//     rule.antecedent.mkString("[", ",", "]")
		//       + " => " + rule.consequent .mkString("[", ",", "]")
		//       + ", " + rule.confidence)
		// }
	}
}
