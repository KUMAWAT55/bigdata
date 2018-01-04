//[SOURCE]

import java.lang.Math
//Reading Files from DFS **[sc]SPArk CONTEXT object**
	val file=sc.textFile("FileStore/tables/Python_Poem_txt-3e0f9.txt")
	file.collect().foreach(println)
	println("------------------FILE----------------------")

//filtering RDD via. transformation with specific word  
	val file_filter=file.filter(line=>line.contains(" is"))

//view the content of RDD
	file_filter.collect().foreach(println)
	println("------------------FILTERED FILE----------------------")
//Map function split each line of file with delimiter.
	val size=file.map(line=>line.split(" ")).count() //count func() counts number of elements in RDD.
	println("size of file:"+" "+size)
	println("After filter count:"+" "+file_filter.count())
	println("Max from Both:"+" "+Math.max(size,file_filter.count()))
	println("------------------COUNT ACTION----------------------")
	val map_file=file.map(line => line.split(' ').size).reduce((a,b) => if (a>b) a else b)
//REduce functionality ((a,b),c,d,...) single value output
	println(map_file)
//Flatmap split lines into words and reduceByKey(_+_)
	val word_count=file.flatMap(line =>line.split(' ')).map(w=>(w,1)).reduceByKey(_+_)
//Reducing to find max frequency of word in file..
	val max_freq=word_count.reduce((x,y)=> if (x._2 > y._2 ) x else y)



