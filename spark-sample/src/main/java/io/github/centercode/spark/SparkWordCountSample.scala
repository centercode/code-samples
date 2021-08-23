package io.github.centercode.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

/**
 * https://spark.apache.org/examples.html
 */
object SparkWordCountSample {

  val spark = SparkSession.builder()
    .master("local")
    .getOrCreate()

  val sc = spark.sparkContext

  def main(args: Array[String]): Unit = {
    wordCountUsingRDDAPI()
    textSearchUsingDataFrameAPI()
  }

  def wordCountUsingRDDAPI(): Unit = {
    val textFile = sc.textFile("spark-sample/src/main/resources/example.csv")
    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
    counts.saveAsTextFile("file:///tmp/rdd_output.txt")
  }

  def textSearchUsingDataFrameAPI(): Unit = {
    import spark.implicits._
    val textFile = sc.textFile("spark-sample/src/main/resources/example.csv")
    // Creates a DataFrame having a single column named "line"
    val df = textFile.toDF("line")
    val errors = df.filter(col("line").like("%ERROR%"))
    // Counts all the errors
    errors.count()
    // Counts errors mentioning MySQL
    errors.filter(col("line").like("%MySQL%")).count()
    // Fetches the MySQL errors as an array of strings
    errors.filter(col("line").like("%MySQL%")).collect()
  }
}
