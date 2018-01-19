# SparkUtilWindows
This is a onestopshop for Windows integration of spark streaming and spark batch to other hadoop ecosystem projects.
# Spark-Kafka Integration
Please see the kafkaRunner folder for setting up Kafka producer in Windows. Once Kafka is set up as producer Spark can be used from eclipse as a streaming to Kafka to consume messages and perform actions on it.
Desired Class: KafkaSparkStream.scala

# Spark-Flume Integration
Please see the flumeRunner folder for setting up Flume spooling to a directory in Windows. Once Flume is set up  Spark can be used from eclipse as a streaming to Flume and consume flume events.
Desired Class: FlumeSparkStream.scala
