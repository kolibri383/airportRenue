package service;

import configuration.AirportContext;

import repository.AirportRepository;
import repository.AirportCSVRepository;
import util.AirportSearcher;
import util.AirportSearcherBinary;
import util.Helper;

import java.util.ArrayList;
import java.util.Collection;


public class AirportCSVServiceImpl implements AirportCSVService {
    private AirportContext context;

    public AirportCSVServiceImpl(AirportContext context) {
        this.context = context;
    }

    @Override
    public Collection<String> geDataAirports(String query, int numberColumn) {
        AirportRepository airportRepository = new AirportCSVRepository();
        AirportSearcher airportSearcher = new Helper(new ArrayList<>(airportRepository.getByColumn(numberColumn, context.getFilePath(), query)));
        return airportRepository.getByRows(new ArrayList<>(airportSearcher.searchLineByQuery(query)), context.getFilePath());
    }

}
