package com.mycompany.spktest;

import org.apache.spark.api.java.JavaRDD;

import java.io.IOException;

@SuppressWarnings("unchecked")
public class main {
    public static void main(String[] args) throws IOException {
        JavaRDD<String> videosJavaRDD = SparkReader.read("src/main/resources/data/USvideos.csv");
        YoutubeTagsWordCount.display(YoutubeTagsWordCount.count(videosJavaRDD));
        YoutubeTitleWordCount.display(YoutubeTitleWordCount.count(videosJavaRDD));
    }
}
