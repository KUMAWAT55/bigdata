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
  .withColumn("State Name",Overdoses_temp.col("State"))
  .drop("State")


val Joined_table=Prescriber.join(Overdoses_temp_2,Prescriber("State")===Overdoses_temp_2("Abbrev"),"Inner").drop("Abbrev")
Joined_table.createOrReplaceTempView("Joined_table")
val Opioid_Prescribers=sqlContext.sql
        (
    """select * from Joined_table where `Opioid.Prescriber`=1 """
        )
Opioid_Prescribers.write
  .mode(SaveMode.Overwrite)
  .format("com.databricks.spark.csv")
  .save("/FileStore/tables/Medical_Output/Opioid_Prescriber.csv")

