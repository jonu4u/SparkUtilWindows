package com.utils.streaming

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka.KafkaUtils

object KafkaSparkStream {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Test").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(10))
    val lines=KafkaUtils.createStream(ssc,"localhost:2181","topc",Map("topc"->1))
    lines.foreachRDD{x=>{
     val count=x.flatMap(x=>x._2.split(" ")).map { x =>(x,1)}.reduceByKey(_+_).collect
     count.foreach(println)
      
    }}
    ssc.start()
    ssc.awaitTermination()
   
  }
}