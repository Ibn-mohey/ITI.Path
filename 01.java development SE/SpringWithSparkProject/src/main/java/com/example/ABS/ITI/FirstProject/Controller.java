package com.example.ABS.ITI.FirstProject;

import org.apache.commons.io.IOUtils;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import wazaf.DataSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

//    @Autowired
//    private Dataset<Row> mDataset;
    DataSet mydata = new DataSet();

    //    1. Read data set and convert it to dataframe or Spark RDD
    //    and display some from it.
//
//    @RequestMapping("/{someID}")
//    public @ResponseBody int getAttr(@PathVariable(value="someID") String id,
//                                     @RequestParam String someAttr) {
//    }
//
//    @RequestMapping(value = "/show"  , produces     =  MediaType.TEXT_PLAIN_VALUE)
//    public String showData10() {
//        return mydata.head(10);
//    }

    @RequestMapping(value = {"/show" ,"/show/{n}"}  , produces     =  MediaType.TEXT_PLAIN_VALUE)
    public String showData(@PathVariable(required = false, value="n") Integer n) {
//        if (n != null){
//        return mydata.head(n);}
//        else{ return mydata.head(10);
//        }
        return ((n != null) ? mydata.head(n) : mydata.head(10));
    }

//2. Display structure and summary of the data.
    @RequestMapping(value = "/structure" , produces     =  MediaType.TEXT_PLAIN_VALUE )
    public String getStructure(){
        return mydata.getStructure();
    }

    @RequestMapping(value = "/summary" , produces     =  MediaType.TEXT_PLAIN_VALUE )
    public String Summary(){
        return mydata.getSummary();
    }


//3. Clean the data (null, duplications)
    // included in the initialization

//4. Count the jobs for each company and display that in order
//            (What are the most demanding companies for jobs?)

    @RequestMapping(value = { "/jobsforeachcompany","/jobsforeachcompany/{n}"}, produces     =  MediaType.TEXT_PLAIN_VALUE )
    public String compy(@PathVariable(required = false,value="n") Integer  n) throws IOException {
//        if (n == null){
//           return mydata.plotCompanyPieChart(10).getKey();
//        }else {
//        return mydata.plotCompanyPieChart(n).getKey();
        return ((n != null) ? mydata.plotCompanyPieChart(n).getKey() : mydata.plotCompanyPieChart(10).getKey());
    }

//5. Show step 4 in a pie chart
@RequestMapping(value = {"/JobsPerCompanyPieChart","/JobsPerCompanyPieChart/{n}"}, method = RequestMethod.GET,
        produces = MediaType.IMAGE_JPEG_VALUE)
public @ResponseBody byte[] getCompaniesPie(@PathVariable(required = false,value="n") Integer  n) throws IOException {

    Path path  = Paths.get(((n != null) ? mydata.plotCompanyPieChart(n).getValue() : mydata.plotCompanyPieChart(10).getValue()));
//    if (Files.exists(path) && !Files.isDirectory(path)) {
//        System.out.println("exists!");
//
//        InputStream in = Files.newInputStream(path, StandardOpenOption.READ);
//        return IOUtils.toByteArray(in);
//    }
    InputStream in = Files.newInputStream(path, StandardOpenOption.READ);
    return IOUtils.toByteArray(in);
}


//6. Find out What are it the most popular job titles?
@RequestMapping(value = {"/mostpopularjobtitles","/mostpopularjobtitles/{n}"}, produces     =  MediaType.TEXT_PLAIN_VALUE )
public String PopularTitles(@PathVariable(required = false,value="n") Integer  n) throws IOException {

    return ((n != null) ? mydata.PlotTitleForCompany(n).getKey() : mydata.PlotTitleForCompany(10).getKey());
}
//            7. Show step 6 in bar chart
@RequestMapping(value = {"/JobsTitlesBarChart","/JobsTitlesBarChart/{n}"}, method = RequestMethod.GET,
        produces = MediaType.IMAGE_JPEG_VALUE)
public @ResponseBody byte[] getTitleBar(@PathVariable(required = false,value="n") Integer  n) throws IOException {

    Path path  = Paths.get(((n != null) ? mydata.PlotTitleForCompany(n).getValue() : mydata.PlotTitleForCompany(10).getValue()));
//    if (Files.exists(path) && !Files.isDirectory(path)) {
//        System.out.println("exists!");
//
//        InputStream in = Files.newInputStream(path, StandardOpenOption.READ);
//        return IOUtils.toByteArray(in);
//    }
    InputStream in = Files.newInputStream(path, StandardOpenOption.READ);
    return IOUtils.toByteArray(in);
}



//8. Find out the most popular areas?
@RequestMapping(value = {"/mostpopularareas","/mostpopularareas/{n}"}, produces     =  MediaType.TEXT_PLAIN_VALUE )
public String PopularAreas(@PathVariable(required = false,value="n") Integer  n) throws IOException {
    return ((n != null) ? mydata.plotAreaBarChart(n).getKey() : mydata.plotAreaBarChart(10).getKey());
//    return mydata.plotAreaBarChart(10).getKey();
}
//            9. Show step 8 in bar chart
@RequestMapping(value = {"/PopularAreasBarChart","/PopularAreasBarChart/{n}"}, method = RequestMethod.GET,
        produces = MediaType.IMAGE_JPEG_VALUE)
public @ResponseBody byte[] getAreasBar(@PathVariable(required = false,value="n") Integer  n) throws IOException {
    Path path  = Paths.get(((n != null) ? mydata.plotAreaBarChart(n).getValue() : mydata.plotAreaBarChart(10).getValue()));
//    Path path  = Paths.get(mydata.plotAreaBarChart(10).getValue());
//    if (Files.exists(path) && !Files.isDirectory(path)) {
//        System.out.println("exists!");
//
//        InputStream in = Files.newInputStream(path, StandardOpenOption.READ);
//        return IOUtils.toByteArray(in);
//    }
    InputStream in = Files.newInputStream(path, StandardOpenOption.READ);
    return IOUtils.toByteArray(in);
}
//
//
//10. Print skills one by one and how many each repeated and
//    order the output to find out the most important skills
//    required?
//
    @RequestMapping(value = {"/skills","/skills/{n}"}, produces     =  MediaType.TEXT_PLAIN_VALUE )
    public String Skills(@PathVariable(required = false,value="n") Integer  n) throws IOException {
        return  ((n != null) ? "Top" + n +"skills\n" + mydata.getMostImportantSkills(n) : "Top" + 30 +"skills\n" +mydata.getMostImportantSkills(30));
    }

    @RequestMapping(value = {"/Factorizedyears","/Factorizedyears/{n}"}, produces     =  MediaType.TEXT_PLAIN_VALUE )
    public String Factorizedyears(@PathVariable(required = false,value="n") Integer  n) throws IOException {
        return ((n != null) ? mydata.getFactorizedYearsOfExp(n) :mydata.getFactorizedYearsOfExp(10));
    }

    @RequestMapping(value = {"/K-means","/K-means/{n}"}, produces     =  MediaType.TEXT_PLAIN_VALUE )
    public String Kmeans(@PathVariable(required = false,value="n") Integer  n) throws IOException {
        return ((n != null) ? mydata.kMeansAlgorithm(n) :mydata.kMeansAlgorithm(3));
    }



}
