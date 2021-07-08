package com.mycompany.spktest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

@SuppressWarnings("unchecked")
public class SparkReader {

    public static JavaRDD<String> read(String path) {
        Logger.getLogger("org").setLevel(Level.ERROR);
        // CREATE SPARK CONTEXT
        SparkConf conf = new SparkConf().setAppName("wordCounts").setMaster("local[3]");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        // LOAD DATASETS
        JavaRDD<String> videos = sparkContext.textFile(path);
        return videos;
    }
}
