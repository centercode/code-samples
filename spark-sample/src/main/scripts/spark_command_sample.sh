# simplest example
./bin/spark-submit \
  --class org.apache.spark.examples.SparkPi\
  --master spark://localhost:7077\
  --deploy-mode cluster\
  ./examples/jars/spark-examples_2.11-2.4.0.jar