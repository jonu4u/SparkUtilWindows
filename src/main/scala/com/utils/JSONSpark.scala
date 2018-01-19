package com.utils
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.storage.StorageLevel
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

/**
 * This class reads a nested JSON and flattens it out on multiple
 * levels.
 *
 * @author Janmenjoy Deb
 *
 */
object JSONSpark {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("JSONSpark").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)
    //Read the Json into a dataframe
    val jsonDataFrame = sqlContext.read.json("resources/exampleNested.json")
    //Flatten the json in the first level using explode    
    val jsonDataFrameFirstLevelFlatten = jsonDataFrame.select(col("id"), explode(col("somebussiness")).as("sb"))
    //Extract the Kpis from the next level and again flatten them.
    //However KPI1 and KPI2 cannot be extracted together since both are inside a nested JSon at the same level.
    //So they need  to be extracted in two steps and added to the final flattened data.
    val jsonDataFrameWithKPI1 = jsonDataFrameFirstLevelFlatten.select(col("id"), explode(col("sb.kpis.kpi1")).as("kpi1"))
    jsonDataFrameWithKPI1.registerTempTable("kpi1")

    val jsonDataFrameWithKPI2 = jsonDataFrameFirstLevelFlatten.select(col("id"), explode(col("sb.kpis.kpi2")).as("kpi2"))
    jsonDataFrameWithKPI2.registerTempTable("kpi2")

    //Joining the two flattened data to form the final data
    val flattenedKPI = sqlContext.sql("select kpi1.id,kpi1.cell_name as cellNamekpi1,kpi2.cell_name as cellNamekpi2 from kpi1 join kpi2 on kpi1.id=kpi2.id")
    flattenedKPI.show()

  }
}