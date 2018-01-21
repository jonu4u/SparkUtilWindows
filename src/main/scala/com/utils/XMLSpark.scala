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
 * This class readsXML and flattens it out on multiple
 * levels.
 *
 * @author Janmenjoy Deb
 *
 */
object XMLSpark {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("XMLSpark").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)
    //Read the XML into a dataframe. rowTag is used to signify the row.Here <PLANT></PLANT> is a row.
    val xmlDF = sqlContext.read.format("com.databricks.spark.xml").option("rowTag", "PLANT").load("resources/example.xml")

    //Since we want to traverse the elements inside Light,it can be accessed by LIGHT.<the field> .
    val flattened = xmlDF.select(col("COMMON"), col("BOTANICAL"), col("ZONE"), col("LIGHT.DAYLIGHT"), col("LIGHT.MOONLIGHT"),
      col("LIGHT.RAINY"), col("PRICE"), col("AVAILABILITY"))

    flattened.show()
   
  }
}