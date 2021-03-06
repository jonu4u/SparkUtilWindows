/**
* SBT main configuration
*
* 
*/
lazy val commonSettings = Seq( 
  version := "0.1.0",
  scalaVersion := "2.10.4"   
)

//Assembly plugin settings
lazy val assemblySettings = Seq(
)



//Repositories
lazy val repositories = Seq(
  //resolvers += "Central Maven" at "http://central.maven.org/maven2/"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(assemblySettings: _*).
  settings(repositories: _*).
  settings(
    name := "SparkUtilsWindows",
     
    libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",	
	libraryDependencies += "org.apache.commons" % "commons-pool2" % "2.0",
	libraryDependencies += "org.apache.spark" % "spark-sql_2.10" % "1.6.3" ,
	libraryDependencies += "org.apache.spark" % "spark-core_2.10" % "1.6.3",
	libraryDependencies += "org.apache.spark" % "spark-streaming-kafka_2.10" % "1.6.3",
	libraryDependencies += "org.apache.spark" % "spark-streaming_2.10" % "1.6.3",
	libraryDependencies += "org.apache.spark" % "spark-streaming-flume_2.10" % "1.6.3",
	libraryDependencies += "com.databricks" %% "spark-xml" % "0.4.0"
	
  )
  
  
  