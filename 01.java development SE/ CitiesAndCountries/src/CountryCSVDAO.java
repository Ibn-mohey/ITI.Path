import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class CountryCSVDAO {
    String path;

    public CountryCSVDAO(String path) {
        this.path = path;
    }

    private List<Country> listOFCountries = new ArrayList<>();

    // read The countries
    public void readCountriesCSV() throws IOException
    {        // create the obj

        BufferedReader reader = new BufferedReader(new FileReader(path));
        // get the header
        String header = reader.readLine();

        // for each pyramid in the csv file
        String line = "";
        while (line != null) //true to the end
        {
            line = reader.readLine();
            if (line!=null)
            {
                String[] metadata = line.split(",");
                //Country(String countryCode, String countryName, String continent,  )
                Country newCountry = new Country(metadata[0].trim(), metadata[1], metadata[2],
                        // double population
                        Integer.parseInt(metadata[3]),
                        // double surfaceArea ,double gnp
                        Double.parseDouble(metadata[4]), Double.parseDouble(metadata[5]),
                        //int capitalId
                        Integer.parseInt(metadata[6]));

                listOFCountries.add(newCountry);
            }
        }
        reader.close();
    }


    // Get a List of countries population

    public List<Double> getCountriesPopulation(){
        List<Double> CountriesPopulation = new ArrayList<Double>();
        listOFCountries.forEach(c -> CountriesPopulation.add(c.getPopulation()));
        return CountriesPopulation;
    }


    //  Get the average countries population
    public  double calculateAverage() {
        List<Double> CP = getCountriesPopulation();
        return CP.stream().mapToDouble(d -> d).average().orElse(Double.NaN);
    }

    // Get the maximum countries population
    public  double calculateMax() {
        List<Double> CP = getCountriesPopulation();
        return CP.stream().mapToDouble(d -> d).max().orElse(Double.NaN);
    }

    public List<Country> getCountries() {
        return listOFCountries;
    }
}
