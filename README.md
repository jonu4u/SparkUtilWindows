# SparkUtilWindows
This is a onestopshop for Windows integration of spark streaming and spark batch to other hadoop ecosystem projects.
# Spark-Kafka Integration
Please see the kafkaRunner folder for setting up Kafka producer in Windows. Once Kafka is set up as producer Spark can be used from eclipse as a streaming to Kafka to consume messages and perform actions on it.
Desired Class: KafkaSparkStream.scala
Dependency: libraryDependencies += "org.apache.spark" % "spark-streaming-kafka_2.10" % "1.6.3"
 

# Spark-Flume Integration
Please see the flumeRunner folder for setting up Flume spooling to a directory in Windows. Once Flume is set up  Spark can be used from eclipse as a streaming to Flume and consume flume events.
Desired Class: FlumeSparkStream.scala
Dependency:	libraryDependencies += "org.apache.spark" % "spark-streaming-flume_2.10" % "1.6.3"

# Flattening Nested JSON Using Spark
This class flattens a multi-level nested json and extracts relevant information.
resources/exampleNested.json is the json used for this exercise.
Desired Class: JSONSpark.scala

# Flattening XML Using Spark
This class flattens a xml and extracts relevant information in a dataframe.
resources/example.xml is the xml used for this exercise.
Desired Class: XMLSpark.scala
Dependency:	libraryDependencies += "com.databricks" %% "spark-xml" % "0.4.0"
rowTag: The row tag of your xml files to treat as a row. For example, in this xml  <PLANT></PLANT> is a row.
The elements inside a sub structure can be accessed from the parent. In this xml the elements inside Light, can be accessed by LIGHT.DAYLIGHT,LIGHT.MOONLIGHT...