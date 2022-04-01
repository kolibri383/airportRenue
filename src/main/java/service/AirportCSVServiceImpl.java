package service;

import configuration.AirportContext;

import repository.AirportRepository;
import repository.AirportCSVRepository;
import util.AirportSearcher;
import util.AirportSearcherBinary;

import java.util.ArrayList;
import java.util.Collection;


public class AirportCSVServiceImpl implements AirportCSVService {
    private AirportContext context;
    private long searchExecutionTime;

    public AirportCSVServiceImpl(AirportContext context) {
        this.context = context;
    }

    @Override
    public Collection<String> geDataAirports(String query, int numberColumn) {
        AirportRepository airportRepository = new AirportCSVRepository();
        AirportSearcher airportSearcher = new AirportSearcherBinary(new ArrayList<>(airportRepository.getByColumn(numberColumn, context.getFilePath())));
        ArrayList<Integer> findColumn = new ArrayList<>(airportSearcher.searchLineByQuery(query));
        this.searchExecutionTime = airportSearcher.getSearchExecutionTime();
        return airportRepository.getByRows(findColumn, context.getFilePath());
    }

    @Override
    public long getSearchExecutionTime() {
        return searchExecutionTime;
    }

}
