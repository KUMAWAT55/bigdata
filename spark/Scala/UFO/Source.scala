val InputFile=spark.read.format("com.databricks.spark.csv")
  .option("header","true")
  .option("inferschema","true")
  .load("/FileStore/tables/complete.csv")

InputFile.createOrReplaceTempView("UFO")
       
val max_country=sqlContext.sql(
                """select country,cntCountry from (
                select country, count(*) as cntCountry, max(count(*)) over () as maxcnt from UFO 
                group by country) as tmp where tmp.cntCountry = tmp.maxcnt""")
max_country.write.mode(SaveMode.Overwrite).format("csv").save("/FileStore/tables/out/Country.csv")
var country=max_country.head.getString(0)
val max_state=sqlContext.sql(
                s"""select state, cntState from (
                select state, count(*) as cntState, max(count(*)) over () as maxcnt 
                from UFO where country='$country' group by state ) as tmp where tmp.cntState = tmp.maxcnt""")
max_state.write.mode(SaveMode.Overwrite).format("csv").save("/FileStore/tables/out/State.csv")


val UFO_TYPES=sqlContext.sql("""select shape from UFO group by shape""")
UFO_TYPES.write.mode(SaveMode.Overwrite).format("csv").save("/FileStore/tables/out/Types.csv")

      val temp_file=InputFile.withColumnRenamed("duration (seconds)","duration (seconds) old")
      val temp_file_1=temp_file.withColumn("duration (seconds)",temp_file.col("duration (seconds) old").cast("Long")).drop("duration (seconds) old")
      temp_file_1.createOrReplaceTempView("UFO_1")

val Max_Time=sqlContext.sql("""select * from UFO_1 where `duration (seconds)`=(select Max(`duration (seconds)`) from UFO_1) """)
Max_Time.write.mode(SaveMode.Overwrite).format("csv").save("/FileStore/tables/out/Max_Stay_Time.csv")

