import java.io.IOException;
import java.util.List;

public class Display {
    public static void main(String args[]) throws IOException {

        //   System.out.print(new ReadFileJson().readPassengersJsonFile());
//        List<Passenger> pages = ReadFileJson.readPassengersJsonFile();
//        DataFrame<Object> df = BeanToJoinery.convert(pages, Passenger.class);
//        df = df.retain("bodyContentLength", "titleLength", "numberOfHeaders");
//        DataFrame<Object> describe = df.describe();
//        System.out.println(describe.toString());
        List<Passenger> passengerList = new ReadFileJson().readPassengersJsonFile();
//        System.out.println(passengersDataset);
        XChartApplications Charts = new XChartApplications();
        Charts.graphPassengerAges(passengerList);
        Charts.graphPassengerClass(passengerList);
        Charts.graphPassengerSurvived(passengerList);
        Charts.graphPassengerSurvivedGender(passengerList);


    }

}
