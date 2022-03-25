package service;

import model.Airport;
import java.util.ArrayList;

public interface AirportCSVService {
    ArrayList<Airport> geDataAirports(String request, int numberColumn);
    ArrayList<Airport> geDataAirports(String request);
}
