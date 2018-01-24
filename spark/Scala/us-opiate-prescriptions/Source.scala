import org.apache.spark.sql.functions.regexp_replace
val Prescriber=spark.read.format("com.databricks.spark.csv")
  .option("header","true")
  .option("inferschema","true")
  .load("/FileStore/tables/prescriber_info-b6d97.csv")

val Overdoses_temp=spark.read.format("com.databricks.spark.csv")
  .option("header","true")
  .option("inferschema","true")
  .load("/FileStore/tables/overdoses.csv")
