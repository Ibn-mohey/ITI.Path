import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.*;


public class Main {

    public static void main(String[] args)throws IOException {

        // country obj

        // created by mohey
        CountryCSVDAO Countrydao = new CountryCSVDAO("./Data/Countries.csv");
        Countrydao.readCountriesCSV();


        // city obj

        // created by bavaly

        CityCSVDAO CityDao = new CityCSVDAO();
        List<City> listcity = CityDao.readCitiesFromCSV("./Data/Cities.csv");


        //For a given country code (from Console) sort the cities according to the population

        //created by bavaly
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter Desired County code : ");

        List<City> sortedCities = CityDao.sortCities(sc.nextLine().toUpperCase());

        System.out.println(sortedCities);

        System.out.println("\n");
        System.out.println("------------------ -------------------");
        // Get a List of countries population
        // created by mohey
        System.out.println("County Popultuion list is " + Countrydao.getCountriesPopulation());
        System.out.println("\n");
        System.out.println("------------------ -------------------");

        // Get the average countries population
        // created by mohey

        System.out.println("average countries population is " + Countrydao.calculateAverage());
        System.out.println("\n");
        System.out.println("------------------ -------------------");

//        // Get the maximum countries population
//        // created by mohey
        System.out.println("maximum countries population is " + (long)Countrydao.calculateMax());

        System.out.println("\n");
        System.out.println("------------------ -------------------");

        // Highest population capital

        // created by mohey
        List<Country> listcountry = Countrydao.getCountries();
        System.out.println("Highest population capital is "+CityDao.HighestPopulationCapital(listcountry));
        System.out.println("\n");
        System.out.println("------------------ -------------------");

//        // Create a map that uses the country code as keys and a list of cities as the value for
//        //each country.
            // created by bavaly
        Map<String, List<City>> CountryCityMap = CityDao.getCountryCity();
        System.out.println("Key TO cities MAP ");
        CountryCityMap.forEach((k, v) -> System.out.println("key="+ k+ ", value="+ v));
        System.out.println("\n");
        System.out.println("------------------ -------------------");
//
//
//        //Highest population city of each country
//        // created by bavaly
        List<City> x1 = CityDao.getHeighestPopulation();
		x1.forEach(System.out::println);

    }
}
