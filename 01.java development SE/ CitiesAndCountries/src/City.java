
public class City
{
    private int cityNumber;
    private String cityName;
    private int cityPopulation;
    private String countryCode;

    public City(int cityNumber, String cityName, int cityPopulation, String cityCode) {
        this.cityNumber = cityNumber;
        this.cityName = cityName;
        this.cityPopulation = cityPopulation;
        this.countryCode = cityCode;
    }

    public int getCityNumber() {
        return cityNumber;
    }
    public String getCityName() {
        return cityName;
    }
    public int getCityPopulation() {
        return cityPopulation;
    }
    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", cityNumber=" + cityNumber +
                ", cityPopulation=" + cityPopulation +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
