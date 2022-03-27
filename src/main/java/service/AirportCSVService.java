package service;

import model.Airport;

import java.util.Collection;

public interface AirportCSVService {
    Collection<Airport> geDataAirports(String query, int numberColumn);

    Collection<Airport> geDataAirports(String query);
}
