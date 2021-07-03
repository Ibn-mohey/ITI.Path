import joinery.DataFrame;
import tech.tablesaw.api.Table;

import java.io.IOException;

public class Display {
    public static void main(String args[]) throws IOException {

        //   System.out.print(new ReadFileJson().readPassengersJsonFile());
//        List<Passenger> pages = ReadFileJson.readPassengersJsonFile();
//        DataFrame<Object> df = BeanToJoinery.convert(pages, Passenger.class);
//        df = df.retain("bodyContentLength", "titleLength", "numberOfHeaders");
//        DataFrame<Object> describe = df.describe();
//        System.out.println(describe.toString());
        /*
        List<Passenger> passengerList = new ReadFileJson().readPassengersJsonFile();
//        System.out.println(passengersDataset);

        XChartApplications Charts = new XChartApplications();
        Charts.graphPassengerAges(passengerList);
        Charts.graphPassengerClass(passengerList);
        Charts.graphPassengerSurvived(passengerList);
        Charts.graphPassengerSurvivedGender(passengerList);
*/

        DataFrame df = new ReadFileCSV().readPassengersCSVFile1();
        System.out.println(df.retain("age", "sibsp", "parch", "fare").max());
        System.out.println(df.retain("age", "sibsp", "parch", "fare").min());
        System.out.println("that is odd to have 0 fare if it was python, r or excel i would have checked using it for these value and how time it occurred");
        System.out.println(df.retain("age", "sibsp", "parch", "fare").stddev());
        System.out.println(df.retain("age", "sibsp", "parch", "fare").sum());
        Table table = new ReadFileCSV().readPassengersCSVFile2();
        System.out.println(table.summary());

    }

}
