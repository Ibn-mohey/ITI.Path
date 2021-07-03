import joinery.DataFrame;
import tech.tablesaw.api.Table;

import java.io.IOException;

public class ReadFileCSV {

    public DataFrame readPassengersCSVFile1() throws IOException {
        DataFrame df = DataFrame.readCsv("./data/titanic.csv").retain("pclass", "survived", "name", "sex", "age", "sibsp", "parch", "ticket", "fare", "cabin", "embarked", "boat", "body", "home.dest");
        return df;
    }

    public Table readPassengersCSVFile2() throws IOException {

        Table table = Table.read().csv("./data/titanic.csv");
        return table;
    }
}
