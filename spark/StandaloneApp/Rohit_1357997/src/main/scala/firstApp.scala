import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object firstApp{
  def main(args: Array[String]) {
    val logFile = "/user/saikalyan14088546/sample.txt" 
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("---------------------------------------------> Lines with a: %s".format(numAs))
    println("---------------------------------------------> Lines with b: %s".format(numBs))
  }
}
