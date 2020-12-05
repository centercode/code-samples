package io.github.centercode.spark

import org.apache.spark.sql.SparkSession

/**
 * https://stackoverflow.com/questions/39444493/how-to-create-sparksession-with-hive-support-fails-with-hive-classes-are-not-f
 */
object SparkBasicSample {

  /**
   * -Dspark.master=local
   */
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local")
      .appName("SparkSample")
      .enableHiveSupport()
      .getOrCreate()
    val df1 = getListDataFrame(spark)
    df1.printSchema()
    df1.show()

    val df2 = getCSVDataFrame(spark)
    df2.printSchema()
    df2.show()
  }

  def getListDataFrame(spark: SparkSession) = {
    import spark.implicits._
    List(("1", "foo", "foo1"), ("2", "bar", "bar2"))
      .toDF("key1", "value1", "value2")
  }

  def getCSVDataFrame(spark: SparkSession) = {
    spark.read.csv("spark-sample/src/main/resources/example.csv")
      .toDF("key2", "value3", "value4")
  }
}
