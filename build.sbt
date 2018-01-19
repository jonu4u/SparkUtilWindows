/**
* SBT main configuration
*
* 
*/
lazy val commonSettings = Seq(
  organization := "com.ericsson",
  version := "0.1.0",
  scalaVersion := "2.10.4",
  mainClass in assembly := Some("com.ericsson.mtn.OOBUsageAnalysisMonthly")
 //mainClass in assembly := Some("com.ericsson.mtn.ESRStaging")  
  //mainClass in assembly := Some("com.ericsson.mtn.XDRStaging")  
)

//Assembly plugin settings
lazy val assemblySettings = Seq(
assemblyMergeStrategy in assembly := {
  case PathList("javax", "xml", "EventHandler")         => MergeStrategy.first
  case PathList("org", "objectweb", "SignatureVisitor")         => MergeStrategy.first
  case "application.conf"                            => MergeStrategy.concat
  case "unwanted.txt"                                => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
},
  excludedJars in assembly := 
	  {val cp = (fullClasspath in assembly).value
    cp filter {_.data.getName == "unused-1.0.0.jar"}}
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
    name := "IntProjectSparkStream",
     
    libraryDependencies += "com.databricks" % "spark-csv_2.10" % "1.5.0",
    libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",	
	libraryDependencies += "org.apache.spark" % "spark-hive_2.10" % "1.6.3"  % "provided",
	libraryDependencies += "org.apache.spark" % "spark-sql_2.10" % "1.6.3" % "provided",
	libraryDependencies += "org.apache.commons" % "commons-pool2" % "2.0",
	libraryDependencies += "org.apache.spark" % "spark-core_2.10" % "1.6.3",
	libraryDependencies += "org.apache.spark" % "spark-streaming-kafka_2.10" % "1.6.3",
	libraryDependencies += "org.apache.spark" % "spark-streaming_2.10" % "1.6.3",
	libraryDependencies += "org.apache.spark" % "spark-streaming-flume_2.10" % "1.6.3"
	
  )
  
  
  