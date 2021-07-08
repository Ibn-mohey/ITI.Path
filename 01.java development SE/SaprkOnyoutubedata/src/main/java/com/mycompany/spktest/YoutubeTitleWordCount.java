package com.mycompany.spktest;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.api.java.JavaRDD;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class YoutubeTitleWordCount {
    public static List<Map.Entry> count(JavaRDD<String> videos) throws IOException {


        // TRANSFORMATIONS
        JavaRDD<String> titles = videos
                .map(YoutubeTitleWordCount::extractTitle)
                .filter(StringUtils::isNotBlank);
        // JavaRDD<String>
        JavaRDD<String> words = titles.flatMap(title -> Arrays.asList(title
                .toLowerCase()
                .trim()
                .replaceAll("\\p{Punct}", "")
                .split(" ")).iterator());
        System.out.println(words.toString());
        // COUNTING
        Map<String, Long> wordCounts = words.countByValue();
        List<Map.Entry> sorted = wordCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        return sorted;
    }

    // DISPLAY
    public static void display(List<Map.Entry> sorted) {
        for (Map.Entry<String, Long> entry : sorted) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static String extractTitle(String videoLine) {
        try {
            return videoLine.split(",")[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }

    }
}
