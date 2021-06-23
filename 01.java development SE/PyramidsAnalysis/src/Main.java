
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args)throws IOException {

        PyramidCSVDAO Pdao = new PyramidCSVDAO("pyramids.csv");
        List<Pyramid> listOfPyramids = Pdao.readPyramidsFromCSV();
        Pdao.sortByHeight();
        // pirnt all pyramids

        int i = 0;
        for (Pyramid p : listOfPyramids)
            System.out.println("#" + i++ + p);
        // create the map
        Map siteCount = Pdao.mapSiteLocations(listOfPyramids);
        System.out.println(siteCount);
	// write your code here
    }
}
