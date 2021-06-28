import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CityCSVDAO {
	List<City> cities = new LinkedList<City>();
	public List<City> readCitiesFromCSV(String filename) {
	String[] splitted;
		File f = new File(filename);
		//System.out.println("file exists: "+f.exists());
		if(f.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line;
				line = br.readLine();	// reads the first line of data
				while(line != null) {
					splitted = line.split(",");
					cities.add(createCity(splitted));
					line = br.readLine();
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("file not found!");
		}
		return cities;
	}


	public City createCity(String[] metadata) {
		int cityNumber = Integer.parseInt(metadata[0]);
	    String cityName = metadata[1].trim();
	    int cityPopulation = Integer.parseInt(metadata[2]);
		String countryCode = metadata[3].trim();
		return new City(cityNumber, cityName, cityPopulation, countryCode);
	}


	public List<City> sortCities(String code){
		return cities.stream()
				.filter(c -> c.getCountryCode().trim().equals(code))
				.sorted(Comparator.comparingDouble(City::getCityPopulation).reversed())
				.collect(Collectors.toList());
	}

	public Map<String, List<City>> getCountryCity(){
		Map<String, List<City>> m = new HashMap<>();
		for(City c: cities) {
			if(!m.containsKey(c.getCountryCode())) {
				List<City> l = sortCities(c.getCountryCode());
				m.put(c.getCountryCode(), l);
			}
		}
		return m;
	}


	public List<City> getHeighestPopulation(){
		Map<String, List<City>> m = getCountryCity();
		return m.keySet().stream()
				.map((String x) -> getHighestPopulationByCity(x))
				.collect(Collectors.toList());
	}

	public City getHighestPopulationByCity(String code){
		Map<String, List<City>> m = getCountryCity();
		return m.values().stream()
				.flatMap(List::stream)
				.filter(b->b.getCountryCode().equals(code))
				.limit(1)
				.findAny()
				.get();
	}



	public City HighestPopulationCapital(List<Country> listOfCountries){
		List<Integer> IDsOfCapitals = new ArrayList<Integer>();
		List<City> listOfCapitals= new ArrayList<City>();
		listOfCountries.forEach(c -> IDsOfCapitals.add(c.getCapitalId()));
		cities.stream()
				.forEach(c -> {
					if (IDsOfCapitals.contains(c.getCityNumber())) {
						listOfCapitals.add(c);
					}
				});
		return listOfCapitals.stream()
				.sorted(Comparator.comparingDouble(City::getCityPopulation).reversed())
				.limit(1)
				.findAny()
				.get();
				}
	}

