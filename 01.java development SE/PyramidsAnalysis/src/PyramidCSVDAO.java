import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PyramidCSVDAO {



    private String path;
    List<Pyramid> listOfPyramids = new ArrayList<Pyramid>();

    // we only need to pass the path

    public PyramidCSVDAO(String path) {
        this.path = path;
    }

    //  Create List of Pyramids objects
    public List<Pyramid> readPyramidsFromCSV() throws IOException
    {

        // create the obj
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
                Pyramid newPyramid = createPyramid(metadata);
                listOfPyramids.add(newPyramid);
            }
        }
        return listOfPyramids;
    }



    // create the rows
    public Pyramid createPyramid(String[] metadata)
    {
        String pharoah = metadata[0];
        String modernName = metadata[2];
        String site = metadata[4];
        double height;

        if(metadata[7] == null || metadata[7].length() == 0)
        {
            height = 0;}
        else
        {
            height = Double.parseDouble(metadata[7]);
        }


        return new Pyramid(pharoah, modernName, site, height);
    }

    // sort the list of pyramids you have by pyramid’s height.
    private class CompareHeights implements Comparator<Pyramid>
    {
        @Override
        public int compare(Pyramid o1, Pyramid o2) {
            return Double.compare(o1.getHeight(), o2.getHeight());
        }
    }
    public void sortByHeight()
    {
        Collections.sort(listOfPyramids, new CompareHeights());
    }

    //  Build a map from the list of pyramids you have that shows all site-location with the number of pyramids in this site

    public  Map<String, Integer>  mapSiteLocations (List<Pyramid> pyramids)
    {
        Map<String, Integer> siteCounts = new HashMap<>();

        for (Pyramid p: pyramids)
        {
            String site= p.getSite();
            Integer count = siteCounts.get(site);

            if (count == null)
                count = 1;
            else
                count++;

            siteCounts.put(site, count);
        }
        return siteCounts;
    }

    // showpyramids

    public void display(List<Pyramid> listOfPyramids)
    {
        int i = 0;
        for (Pyramid p : listOfPyramids)
            System.out.println("#" + i++ + p);
    }
}
