import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadFileJson {

    public List<Passenger> readPassengersJsonFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//        Passenger passengerList = objectMapper.readValue(new FileInputStream("./data/titanic_csv.json"), Passenger.class);

        List<Passenger> passengerList = objectMapper.readValue(new File("./data/titanic.json"), new TypeReference<List<Passenger>>() {
        });

        return passengerList;
    }
}
