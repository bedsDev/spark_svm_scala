
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

		var supportNum = .0008
		if(args.size > 0){
				println("arguments[0] = " + args(0))
				try{
					supportNum = args(0).toDouble
				}
				catch{
					case e:Exception =>{
						println(e)
						supportNum = .0008
					}
				}
				
			}
			
		println(supportNum)

		val conf = new SparkConf()
			.set("spark.driver.memory","20g")
			.setAppName("Parallel Frequent Pattern")
	    val sc = new SparkContext(conf)

	    /* the decomposition for the term frequencies of the documents */
	    val inputFilename:String = "termFrequenceDecomposition.csv"
	    // // val inputFilename:String = "../topic_retrieval/notebooks/results/termFrequenceDecomposition.csv"
	    val outputFilename:String = "outputs/patternOutputTermDecompose.txt"

	    /* the decomposition for the term frequencies of the documents */
	    // val inputFilename:String = "../topic_retrieval/notebooks/results/customFrequenceDecomposition.csv"
	    // val outputFilename:String = "outputs/patternOutputCustomTermDecompose.txt"


	     /* the decomposition for the term frequencies of the documents top_100*/

	    // val inputFilename:String = "termFrequenceDecomposition1000.csv"
	    // val outputFilename:String = "outputs/patternOutputTop1000.txt"

	    // val inputFilename:String = "termFrequenceDecomposition1000.csv"
	    // val outputFilename:String = "outputs/patternOutputTop1000.txt"

	    /* the decomposition for the term frequencies of the documents */
	    // val inputFilename:String = "../topic_retrieval/notebooks/results/customFrequenceDecomposition.csv"
	    // val outputFilename:String = "outputs/customTermPattern.txt"


	    /* Examples */
	    // val inputFilename:String= "sample_fpgrowth.txt"
	    // val outputFilename:String = "patternOutput.txt"
	    
	    val data = sc.textFile(inputFilename)

		val transactions: RDD[Array[String]] = data.map(s => s.trim.split(','))




		val fpg = new FPGrowth().setMinSupport(supportNum).setNumPartitions(100)

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
		println("\n\n\n")
		println("------------------------------------------------")
		println("have written file: " + outputFilename + " ")
		println("++++++++++++++++++++++++++++++++++++++++++++++++")


		// val minConfidence = 0.8
		// model.generateAssociationRules(minConfidence).collect().foreach { rule =>
		//   println(
		//     rule.antecedent.mkString("[", ",", "]")
		//       + " => " + rule.consequent .mkString("[", ",", "]")
		//       + ", " + rule.confidence)
		// }

		sc.stop()

		println("support number is: " + supportNum)
	}
}
