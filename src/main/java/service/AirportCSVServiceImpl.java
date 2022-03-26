package service;

import model.Airport;

import repository.AirportRepository;
import repository.CSVRepositoryImpl;
import util.AirportSearcher;
import util.AirportSearcherBinary;

import java.util.ArrayList;


public class AirportCSVServiceImpl implements AirportCSVService {
    @Override
    public ArrayList<Airport> geDataAirports(String query, int numberColumn) {
        AirportRepository airportRepository = new CSVRepositoryImpl();
        AirportSearcher airportSearcher = new AirportSearcherBinary(airportRepository.getByColumn(numberColumn));
        return airportRepository.getByRows(airportSearcher.searchLineByQuery(query));
    }

    @Override
    public ArrayList<Airport> geDataAirports(String query) {
        AirportRepository airportRepository = new CSVRepositoryImpl();
        AirportSearcherBinary airportSearcher = new AirportSearcherBinary(airportRepository.getByColumn());
        return airportRepository.getByRows(airportSearcher.searchLineByQuery(query));
    }
}
