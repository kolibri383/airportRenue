package service;

import model.Airport;

import repository.AirportRepository;
import repository.AirportCSVRepository;
import util.AirportSearcher;
import util.AirportSearcherBinary;

import java.util.ArrayList;
import java.util.Collection;


public class AirportCSVServiceImpl implements AirportCSVService {
    @Override
    public Collection<Airport> geDataAirports(String query, int numberColumn) {
        AirportRepository airportRepository = new AirportCSVRepository();
        AirportSearcher airportSearcher = new AirportSearcherBinary(new ArrayList<>(airportRepository.getByColumn(numberColumn)));
        return airportRepository.getByRows(new ArrayList<>(airportSearcher.searchLineByQuery(query)));
    }

    @Override
    public Collection<Airport> geDataAirports(String query) {
        AirportRepository airportRepository = new AirportCSVRepository();
        AirportSearcherBinary airportSearcher = new AirportSearcherBinary(new ArrayList<>(airportRepository.getByColumn()));
        return airportRepository.getByRows(new ArrayList<>(airportSearcher.searchLineByQuery(query)));
    }
}
