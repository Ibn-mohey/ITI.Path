public class Country {

    private String countryCode;
    private String countryName;
    private String continent;
    private long population;
    private double surfaceArea;
    private double gnp;
    private int capitalId;

    public Country(String countryCode, String countryName, String continent, long population, double surfaceArea, double gnp, int capitalId) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.continent = continent;
        this.population = population;
        this.surfaceArea = surfaceArea;
        this.gnp = gnp;
        this.capitalId = capitalId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getContinent() {
        return continent;
    }

    public long getPopulation() {
        return population;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public double getGnp() {
        return gnp;
    }

    public int getCapitalId() {
        return capitalId;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", continent='" + continent + '\'' +
                ", population=" + population +
                ", surfaceArea=" + surfaceArea +
                ", gnp=" + gnp +
                ", capitalId=" + capitalId +
                '}';
    }
}


