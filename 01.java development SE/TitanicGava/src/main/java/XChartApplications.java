import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class XChartApplications {

    public void graphPassengerAges(List<Passenger> passengerList) {//filter to get an array of passenger ages
        List<Float> pAges = passengerList.stream().map(Passenger::getAge).limit(8).collect(Collectors.toList());
        List<String> pNames = passengerList.stream().map(Passenger::getName).limit(8).collect(Collectors.toList());

        CategoryChart chart = new CategoryChartBuilder().width(1024).height(768).title("Age Histogram").xAxisTitle("Names").yAxisTitle("Age").build();

        // 2.Customize Chart

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideN);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setStacked(true);
        // 3.Series
        chart.addSeries("Passenger's Ages", pNames, pAges);
        // 4.Show it
        new SwingWrapper(chart).displayChart();
    }

    public void graphPassengerClass(List<Passenger> passengerList) {

        Map<Integer, Long> result = passengerList.stream().collect(Collectors.groupingBy(Passenger::getPclass, Collectors.counting()));

        PieChart chart = new PieChartBuilder().width(800).height(600).title("Classes on the ship").build();

        Color[] sliceColors = new Color[]{new Color(180, 68, 50), new Color(130, 105, 120), new Color(80, 143, 160)};
        chart.getStyler().setSeriesColors(sliceColors);

        chart.addSeries("First Class", result.get(1));
        chart.addSeries("Second Class", result.get(2));
        chart.addSeries("Third Class", result.get(3));

        new SwingWrapper(chart).displayChart();
    }

    public void graphClass(List<Passenger> passengerList) {

//        Object[] subJson = passengerList
//                .stream()
//                .filter(student -> ((Map) student).get("City").equals("Delhi"))
//                .toArray();

        Map<String, Long> result = passengerList.stream().filter(c -> c.getSurvived() == 1).collect(Collectors.groupingBy(Passenger::getSex, Collectors.counting()));
        PieChart chart = new PieChartBuilder().width(800).height(600).title(getClass().getSimpleName()).build();

        Color[] sliceColors = new Color[]{new Color(180, 68, 50), new Color(130, 105, 120)};
        chart.getStyler().setSeriesColors(sliceColors);

        chart.addSeries("Survived female", result.get("female"));
        chart.addSeries("Survived male", result.get("male"));

        new SwingWrapper(chart).displayChart();
    }


    public void graphPassengerSurvived(List<Passenger> passengerList) {

        Map<Integer, Long> result = passengerList.stream().collect(Collectors.groupingBy(Passenger::getSurvived, Collectors.counting()));
        PieChart chart = new PieChartBuilder().width(800).height(600).title("Passenger Survived").build();

        Color[] sliceColors = new Color[]{new Color(180, 68, 50), new Color(130, 105, 120)};
        chart.getStyler().setSeriesColors(sliceColors);

        chart.addSeries("not Survived", result.get(0));
        chart.addSeries("Survived", result.get(1));
        new SwingWrapper(chart).displayChart();
    }

    public void graphPassengerSurvivedGender(List<Passenger> passengerList) {

        Map<String, Long> result = passengerList.stream().filter(c -> c.getSurvived() == 1).collect(Collectors.groupingBy(Passenger::getSex, Collectors.counting()));
        PieChart chart = new PieChartBuilder().width(800).height(600).title("Survived Male/Female").build();

        Color[] sliceColors = new Color[]{new Color(180, 68, 50), new Color(130, 105, 120)};
        chart.getStyler().setSeriesColors(sliceColors);

        chart.addSeries("Survived female", result.get("female"));
        chart.addSeries("Survived male", result.get("male"));

        new SwingWrapper(chart).displayChart();
    }

}




