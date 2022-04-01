package service;

import java.util.Collection;

public interface AirportCSVService {
    Collection<String> geDataAirports(String query, int numberColumn);

    long getSearchExecutionTime();
}
