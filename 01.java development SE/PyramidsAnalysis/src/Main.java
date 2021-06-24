
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args)throws IOException {

        PyramidCSVDAO Pdao = new PyramidCSVDAO("pyramids.csv");

        // create the list to use it if needed
        List<Pyramid> listOfPyramids = Pdao.readPyramidsFromCSV();

        // sort height

        Pdao.sortByHeight();

        // pirnt all pyramids

        Pdao.display();

        // create the map
        Map siteCount = Pdao.mapSiteLocations();

        System.out.println(siteCount);
	// write your code here
    }
}
