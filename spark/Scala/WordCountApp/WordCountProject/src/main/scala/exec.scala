import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object WordCount
{
def main(args: Array[String]){
val conf = new SparkConf().setAppName("WordCountApp")

val sc=new SparkContext(conf)

val PoemFile=sc.textFile("/user/saikalyan14088546/ROHIT_1357997/Poem.txt.txt")
val WordCount=PoemFile.flatMap(line => line.split(" ")).map(word =>(word,1)).reduceByKey((a,b) => a+b).collect()
WordCount.foreach(println)
}
}

