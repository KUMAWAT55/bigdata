
//Loading file
val Emp_table=spark.read.format("com.databricks.spark.csv")
    .option("header","true")
    .option("inferschema","true")
    .load("/FileStore/tables/temp.csv")

val Emp_table_1=spark.read.format("com.databricks.spark.csv")
    .option("header","true")
    .option("inferschema","true")
    .load("/FileStore/tables/temp1.csv")

//Join operation on table Emp_table with Emp_table_1 on Age column
val Emp_Table_Join=Emp_table.join(Emp_table_1,Emp_table.col("Age")===Emp_table_1.col("Age"))
    .drop(Emp_table_1.col("Age"))

//Save output file
Emp_Table_Join.write.format("csv").save("/FileStore/tables/Output.csv")
