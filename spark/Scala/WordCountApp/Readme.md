### Word Count Standalone Application(Spark Submit)
(1) Run script file "sbt.sh" to create 'Simple Building Tool Structure:
### PROJECT STRUCTURE(Word Count)

.

|-- build.sbt

|-- lib

|-- project

|-- src

|       |-- main

|       |       |-- java

|       |       |-- resources

|       |       |-- scala

|       |-- test

|               |-- java

|               |-- resources

|               |-- scala

|-- target
      
      sh sbt.sh
The interactive build tool. Use Scala to define your tasks. Then run them in parallel from the shell. 

(2) Scala Source File location will be:

      /src/main/scala/<FILE>  example: (/src/main/scala/exec.scala)
      
(3) Packaging of Project and building Jar:

      sbt package

(4)Submitting the application on cluster/local or more modes:
      
      spark-submit --class "WordCount" --master local[4] target/scala-2.10/wordcountapp_2.10-1.0.jar
         
