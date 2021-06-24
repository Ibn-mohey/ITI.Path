
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args)throws IOException {

        PyramidCSVDAO Pdao = new PyramidCSVDAO("pyramids.csv");
        List<Pyramid> listOfPyramids = Pdao.readPyramidsFromCSV();
        Pdao.sortByHeight();
        // pirnt all pyramids

        Pdao.display();

        // create the map
        Map siteCount = Pdao.mapSiteLocations(listOfPyramids);

        System.out.println(siteCount);
	// write your code here
    }
}
