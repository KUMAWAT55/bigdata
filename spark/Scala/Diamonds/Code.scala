//Input File
diamonds=spark.read.format("com.databricks.spark.csv")
  	.option("header","true")
 	.option("inferschema", "true")
  	.load("FileStore/tables/export__1_-d35a2.csv")
//counts total number or rows
	println("Count:" +diamonds.count())
//groupBy cut and color and avg(price) of all
	val df1 = diamonds.groupBy("cut", "color").avg("price")
// save the file for spar sqlContext
	df1.write.format("csv").save("FileStore/tables/df1.csv")

	println(df1.count())
//join operation on table df1 with diamonds
	val df2 = df1.join(diamonds, diamonds.col("color") === df1.col("color"))
//removing duplicate columns
	.drop(diamonds.col("color"))
	.drop(diamonds.col("cut"))
	.select("*")
	df2.printSchema
	df2.count()
//save the file for spark sqlContext
	df2.write.format("csv").save("FileStore/tables/df2.csv")
