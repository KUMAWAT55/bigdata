import org.apache.spark.sql.functions.regexp_replace
val Prescriber=spark.read.format("com.databricks.spark.csv")
  .option("header","true")
  .option("inferschema","true")
  .load("/FileStore/tables/prescriber_info-b6d97.csv")

val Overdoses_temp=spark.read.format("com.databricks.spark.csv")
  .option("header","true")
  .option("inferschema","true")
  .load("/FileStore/tables/overdoses.csv")

val Overdoses_temp_2=Overdoses_temp
  .withColumn("Population", regexp_replace(Overdoses_temp("Population"), ",", "").cast("Long"))
  .withColumn("Deaths", regexp_replace(Overdoses_temp("Deaths"), ",", "").cast("Long"))

val Joined_table=Prescriber.join(Overdoses_temp_2,Prescriber("State")===Overdoses_temp_2("Abbrev"),"Inner")
Joined_table.printSchema
Joined_table.createOrReplaceTempView("Joined_table")
val Opioid_Prescribers=sqlContext.sql(
  """ select * from Joined_table where `Opioid.Prescriber`=1"""
                          )
