package com.utils.streaming
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.storage.StorageLevel
/**This class is a sink for the Flume source.
 * Please see the flumeRunner folder for the flume setup steps.
 *  
 *@author Janmenjoy Deb
 */
object FlumeSparkStream {
   def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("FlumeSpark").setMaster("local[*]")
    val sc = new SparkContext(conf)
     //Polling from flume after every 10 seconds.
    val ssc = new StreamingContext(sc, Seconds(10))
    //FLume is running on port 7748 in localhost and is spooling a directory.
    //Whatever file is put into the spooling directory it is consumed by spark as flume events.
    val lines=FlumeUtils.createPollingStream(ssc,"localhost", 7748, StorageLevel.MEMORY_ONLY)
   //A wordCount from the flume events
    lines.foreachRDD{x=>{
      val count=x.map(x=>new String(x.event.getBody.array())).flatMap(x=>x.split(" ")).map { x =>(x,1)}.reduceByKey(_+_).collect
      count.foreach(println)
      
    
      }
    }
    ssc.start()
    ssc.awaitTermination()
   
  }
}