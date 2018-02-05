mkdir lib project target
mkdir -p src/{main,test}/{java,resource,scala}
echo 'name := "First Project"
version := "1.0"
scalaVersion := "2.10.4"
libraryDependencies += "org.apache.spark" %% "spark-core" % "1.5.2"
' >> build.sbt
