package com.utils.streaming

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka.KafkaUtils

/**This class is a consumer for the Kafka producer listening to the single partitioned 
 * topic sparkInjestor.
 * Please see the kafkaRunner folder for the producer setup steps.
 *  
 *@author Janmenjoy Deb
 */
object KafkaSparkStream {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("KafkaIntegration").setMaster("local[*]")
    val sc = new SparkContext(conf)
    //Polling the kafka producer after every 10 seconds.
    val ssc = new StreamingContext(sc, Seconds(10))
    //Creating an RDD from the data received from the sparkInjestor Topic with a single partition
    //and running on zookeeper localhost:2181(host:port)
    val lines=KafkaUtils.createStream(ssc,"localhost:2181","sparkInjestor",Map("sparkInjestor"->1))
    //Performing a wordcount on the data received
    lines.foreachRDD{x=>{
     val count=x.flatMap(x=>x._2.split(" ")).map { x =>(x,1)}.reduceByKey(_+_).collect
     count.foreach(println)
      
    }}
    ssc.start()
    ssc.awaitTermination()
   
  }
}