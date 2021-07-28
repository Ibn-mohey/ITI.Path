/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wazaf;



/**
 *
 * @author shehab
 */


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.ml.clustering.KMeans;
import org.apache.spark.ml.clustering.KMeansModel;
import org.apache.spark.ml.feature.StringIndexer;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.json.simple.JSONObject;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import scala.collection.JavaConverters;




import java.util.List;


import java.util.*;
import java.io.IOException;

import java.util.Arrays;

import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.*;
import static org.apache.spark.sql.functions.regexp_replace;

@SuppressWarnings("unchecked")
public class DataSet {
    private Dataset<Row> jobsDF;
    private SparkSession sparkSession;
    public DataSet() {
        Logger.getLogger("org").setLevel(Level.ERROR);
        sparkSession = SparkSession.builder()
                .appName("wazaf_jobs")
                .master("local[*]")
                .config("spark.some.config.option", "some-value")
                .getOrCreate();

        jobsDF = sparkSession.read().option("header", true).csv("src\\main\\resources\\Wuzzuf_Jobs.csv");
        jobsDF = jobsDF.na().drop();
        jobsDF = jobsDF.dropDuplicates().filter((FilterFunction<Row>) row -> !row.get(5).equals("null Yrs of Exp"));
    }


//    public List<String> RowToString(List<Row> list){
//
//
//    }

    // and display some from it.
//    public List<String[]> head(int n)
//    public List<Row> head(int n)
    public String head(int n)
    {
        String headValues = jobsDF.showString(10,40,false);

//        List<String[]> headValuesStrings = new ArrayList<>();
//        for(Row row : headValues) {
//            String[] eachrow = row.toString()
//                    .replace("]", "").replace("[", "")
//                    .split(",", jobsDF.columns().length);
//            headValuesStrings.add(eachrow);
//        }
//        List<String> listOne = jobsDF.map(row -> row.mkString(), Encoders.STRING()).collectAsList();
        return headValues;
        // showstring
    }


    // 2. Display structure and summary of the data.
    public String getStructure()
    {
        StructType structure = jobsDF.schema();
        return structure.prettyJson();
    }

//    public  List<Row> getSummary()
//    {
//        Dataset<Row> str = jobsDF.summary();
//        List<Row> SummaryList = str.collectAsList();
//        return SummaryList;
//        //
//}
    public String getSummary()
    {
//        Dataset<Row> str =
////        List<Row> SummaryList = str.collectAsList();
        return jobsDF.summary().showString(1,40,false) +"\n" + "The data has no max no min So no Summary";
        //
    }

//4. Count the jobs for each company and display that in order
//            (What are the most demanding companies for jobs?
    //5. Show step 4 in a pie chart
//    Pair<Integer, String>
//    public String plotCompanyPieChart(int n) throws IOException
    public Pair<String, String> plotCompanyPieChart(int n) throws IOException
    {
        Dataset<Row> groupedByCompany = jobsDF.groupBy("Company")
                .count()
                .orderBy(col("count").desc())
                .limit(n);

        List<String> companies = groupedByCompany.select("Company").as(Encoders.STRING()).collectAsList();
        List<String> counts = groupedByCompany.select("count").as(Encoders.STRING()).collectAsList();

        PieChart chart = new PieChartBuilder().width(1400).height(700).title("Companies Pie-Chart").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        chart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);

        for (int i = 0; i < companies.size() ; i++)
            chart.addSeries(companies.get(i), Integer.parseInt(counts.get(i)));

        BitmapEncoder.saveBitmap(chart, "src/main/resources/company_pie_chart.JPG", BitmapEncoder.BitmapFormat.JPG);
        return new Pair<String, String>( groupedByCompany.showString(n,40,false), "src/main/resources/company_pie_chart.JPG");
    }

    public List<Row> getMostDemandingCompanies(int n)
    {
        Dataset<Row> groupedByCompany = jobsDF.groupBy("Company")
                .count()
                .orderBy(col("count").desc())
                .limit(n);
        List<Row> mostDemandingCompanies = groupedByCompany.collectAsList();

        return mostDemandingCompanies;
    }

//    6. Find out What are it the most popular job titles?
//            7. Show step 6 in bar chart

    public Pair<String, String> PlotTitleForCompany(int n) throws IOException
    {
        Dataset<Row> groupedByCompany = jobsDF.groupBy("Title")
                .count()
                .orderBy(col("count").desc())
                .limit(n);
        List<String> titles = groupedByCompany.select("Title").as(Encoders.STRING()).collectAsList();
        List<String> counts = groupedByCompany.select("count").as(Encoders.STRING()).collectAsList();

        List<Float> toFloats = new ArrayList<>();

        for(String s : counts)
            toFloats.add(Float.valueOf(s));

        CategoryChart chart = new CategoryChartBuilder().width (1400).height (700).title ("Titles Bar-chart").xAxisTitle("Locations").yAxisTitle("frequency").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        chart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setStacked(true);
        chart.addSeries("Locations", titles, toFloats);

        BitmapEncoder.saveBitmap(chart, "src/main/resources/title_bar_chart.png", BitmapEncoder.BitmapFormat.PNG);
        return  new Pair<String, String>( groupedByCompany.showString(n,40,false), "src/main/resources/title_bar_chart.png") ;
    }


//              8. Find out the most popular areas?
//            9. Show step 8 in bar chart

    public  Pair<String, String>  plotAreaBarChart(int n) throws IOException
    {
        Dataset<Row> groupByLocations = jobsDF.groupBy("Location")
                .count()
                .orderBy(col("count").desc())
                .limit(n);

        List<String> Areas = groupByLocations.select("Location").as(Encoders.STRING()).collectAsList();
        List<String> counts = groupByLocations.select("count").as(Encoders.STRING()).collectAsList();
        List<Float> toFloats = new ArrayList<>();

        for(String s : counts)
            toFloats.add(Float.valueOf(s));

        CategoryChart chart = new CategoryChartBuilder().width (1400).height (700).title ("Locations Bar-chart").xAxisTitle("Locations").yAxisTitle("frequency").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        chart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setStacked(true);
        chart.addSeries("Locations", Areas, toFloats);

        BitmapEncoder.saveBitmap(chart, "src/main/resources/Areas_Bar_chart.png", BitmapEncoder.BitmapFormat.PNG);
        return new Pair<String, String>( groupByLocations.showString(n,40,false), "src/main/resources/Areas_Bar_chart.png");
    }


//    10. Print skills one by one and how many each repeated and  order the output to find out the most important skills
//    required?



        public String  getMostImportantSkills(int n ) throws JsonProcessingException {

        JavaRDD<String> skillByRow = jobsDF.select("Skills").as(Encoders.STRING()).javaRDD();

        JavaRDD<String> skills = skillByRow.flatMap(skill ->
            Arrays.asList(skill.trim()
                    .toLowerCase()
                    .trim()
                    .split(","))
                    .iterator());
            skills = skills.flatMap(skill ->
                    Arrays.asList(skill.trim()
                            .toLowerCase()
                            .trim()
                            .split(","))
                            .iterator());

        List<Map.Entry> skillsCounts = skills
                .countByValue()
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
            Collections.reverse(skillsCounts);
            int size = skillsCounts.size();
            String Wen = "";

            for(int i = 0; i <n;i++)
            {
               Wen += ("#" + (i) + " - " + skillsCounts.get(i).getKey() + " : " + skillsCounts.get(i).getValue()) + "\n";
            }
//        List<String> SkillsList =new ArrayList<>();
//        List<Long> SkillCount =new ArrayList<>();
//        for (int i = skillsCounts.size()-1; i>0; i--) {
//            SkillsList.add((String) skillsCounts.get(i).getKey());
//            SkillCount.add((Long) skillsCounts.get(i).getValue());
//        }

//        Map<String,Long> oneMap = new HashMap<>();

//        for (int i = skillsCounts.size()-1; i>0; i--) {
//            oneMap.put((String) skillsCounts.get(i).getKey(), (Long) skillsCounts.get(i).getValue());
//        }
//        LinkedHashMap<String, Long> sortedMap = new LinkedHashMap<>();
//        oneMap.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));




//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(sortedMap);

//        JSONObject jsonObject = new JSONObject(json);
//        JavaConverters.asScalaIteratorConverter(inputList.iterator()).asScala().toSeq()
//        Dataset<Row> finalDF = oneMap.toSeq.toDF('namd','count')
//
//        List<String> skillsName = new ArrayList<>();
//        List<String> skillsCount = new ArrayList<>();
//        for(Map.Entry entry : skillsCounts){
//            skillsName.add(entry.getKey().toString());
//            skillsCount.add(entry.getValue().toString());
//        }

//        Dataset<Row> skillsNameDF = sparkSession.createDataset(skillsName, Encoders.STRING()).toDF();
//        Dataset<Row> skillsCountDF = sparkSession.createDataset(skillsName, Encoders.STRING()).toDF();
//        Dataset<Row>  All =  skillsNameDF.columns.toSet ++ skillsCountDF.columns.toSet
//        df.withColumn('Count',skillsCount);
//        Dataset<Row> ds = sparkSession.;



        return Wen;
    }


//    11. Factorize the YearsExp feature and convert it to numbers
//    in new col. (Bounce )

    public String getFactorizedYearsOfExp(int n)
    {
//        Dataset<Row> factorizedYears = new StringIndexer()
//                .setInputCol("YearsExp")
//                .setOutputCol("FactorizedYears")
//                .fit(jobsDF)
//                .transform(jobsDF);
//
//        String[] cols = {"YearsExp", "FactorizedYears"};
//        Dataset<Row> yearsOfExp = factorizedYears.select("YearsExp", "FactorizedYears");
        Dataset<Row> mDatasetFactorized = jobsDF.withColumn("YearExp_Factorized",
                regexp_replace(trim(regexp_replace(jobsDF.col("YearsExp"), "[A-Za-z]", "")), "^$", "0"));
        Dataset<Row> yearsOfExp = mDatasetFactorized.select("YearsExp","YearExp_Factorized");
        return yearsOfExp.showString(n,40,false);
    }



//    12. Apply K-means for job title and companies (Bounce )


    public String kMeansAlgorithm()
    {
        Dataset<Row> dataset = jobsDF.as("data");
        String[] cols = {"Title", "Company"};
        String[] factorizedCols = {"TitleFactorized", "CompanyFactorized"};

        for(int i = 0; i < cols.length; i++)
        {
            StringIndexer indexer = new StringIndexer();
            indexer.setInputCol(cols[i]).setOutputCol(factorizedCols[i]);
            dataset = indexer.fit(dataset).transform(dataset);
        }

        for(int i = 0; i < cols.length; i++)
            dataset = dataset.withColumn(factorizedCols[i], dataset.col(factorizedCols[i]).cast("double"));


        VectorAssembler vectorAssembler = new VectorAssembler();
        vectorAssembler.setInputCols(factorizedCols).setOutputCol("features");
        Dataset<Row> trainData = vectorAssembler.transform(dataset);

        KMeans kmeans = new KMeans().setK(3).setSeed(1L);
        kmeans.setFeaturesCol("features");
        KMeansModel model = kmeans.fit(trainData);

        return "Model Distance Measure: " + model.getDistanceMeasure()
                + "\nNumber of Features: " + model.numFeatures()
               +
                "\nNumber of iterations: " + model.getMaxIter()
               +
                "\nModel Centers:" + Arrays.toString(model.clusterCenters())
               ;
    }


}
