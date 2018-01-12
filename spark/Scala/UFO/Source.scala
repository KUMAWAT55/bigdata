val InputFile=spark.read.format("com.databricks.spark.csv")
  .option("header","true")
  .option("inferschema","true")
  .load("/FileStore/tables/complete.csv")
println(InputFile.count())
//Creating Table view for data file as table 
  InputFile.createOrReplaceTempView("UFO")
 val max_country=sqlContext.sql("""select country, cntCountry from (
                                            select country, count(*) as cntCountry, max(count(*)) over () as maxcnt 
                                            from UFO 
                                            group by country) as tmp
                                            where tmp.cntCountry = tmp.maxcnt""")


display(max_country)
 val max_state=sqlContext.sql("""select state, cntState from (
                                            select state, count(*) as cntState, max(count(*)) over () as maxcnt 
                                            from UFO 
                                            group by state) as tmp
                                            where tmp.cntState = tmp.maxcnt""")

display(max_state)

