### Spark interactive content
-->To run one of the Java or Scala sample programs:

	use "bin/run-example <class> <params>"
in the top-level Spark directory. (Behind the scenes, this invokes the more general spark-submit script for launching applications)
	./bin/run-example SparkPi 10  // 10 is Number of Partitions
-->You can also run Spark interactively through a modified version of the Scala shell. This is a great way to learn the framework.The --master option specifies the master URL for a distributed cluster, or local to run locally with one thread, or local[N] to run locally with N threads.
	./bin/spark-shell --master local[2]
-->Spark also provides a Python API. To run Spark interactively in a Python interpreter, use bin/pyspark:
	./bin/pyspark --master local[2]
Example [./bin/spark-submit examples/src/main/python/pi.py 10]

### Bundling Your Application’s Dependencies       
If your code depends on other projects, you will need to package them alongside your application in order to distribute
the code to a Spark cluster. To do this, create an assembly jar (or “uber” jar) containing your code and its dependencies. 
Both sbt and Maven have assembly plugins. When creating assembly jars, list Spark and Hadoop as ""provided"" dependencies; these
need not be bundled since they are provided by the cluster manager at runtime. Once you have an assembled jar you can call 
the ""bin/spark-submit"" script as shown here while passing your jar.For Python, you can use the ""py-files"" argument of ""spark-submit"" to add .py, .zip or .egg 
files to be distributed with your application. If you depend on multiple Python files we recommend packaging them into a .zip or .egg.  

### Launching Applications with spark-submit
Once a user application is bundled, it can be launched using the bin/spark-submit script. This script takes
care of setting up the classpath with Spark and its dependencies, and can support different cluster managers
and deploy modes that Spark supports:                     
	
	./bin/spark-submit \
  --class <main-class> \
  --master <master-url> \
  --deploy-mode <deploy-mode> \
  --conf <key>=<value> \
  ... # other options
  <application-jar> \
  [application-arguments]

--class: The entry point for your application (e.g. org.apache.spark.examples.SparkPi)

--master: The master URL for the cluster (e.g. spark://23.195.26.187:7077)

--deploy-mode: Whether to deploy your driver on the worker nodes (cluster) or locally as an external client (client) (default: client) †

--conf: Arbitrary Spark configuration property in key=value format. For values that contain spaces wrap “key=value” in quotes (as shown).

application-jar: Path to a bundled jar including your application and all dependencies. The URL must be globally visible inside of your cluster, for instance, an hdfs:// path or a file:// path that is present on all nodes.

application-arguments: Arguments passed to the main method of your main class, if any   

#### Run on a Spark standalone cluster in cluster deploy mode with supervise
./bin/spark-submit \
  --class org.apache.spark.examples.SparkPi \
  --master spark://207.184.161.138:7077 \
  --deploy-mode cluster \
  --supervise \
  --executor-memory 20G \
  --total-executor-cores 100 \
  /path/to/examples.jar \
  1000    








































