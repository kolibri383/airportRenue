package service;

import model.Airport;

import java.util.ArrayList;

public interface AirportCSVService {
    ArrayList<Airport> geDataAirports(String query, int numberColumn);

    ArrayList<Airport> geDataAirports(String query);
}
