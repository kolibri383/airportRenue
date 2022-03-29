package util;

import model.Airport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Helper implements AirportSearcher {
    private ArrayList<Airport> dataSource;

    public Helper(ArrayList<Airport> dataSource) {
        this.dataSource = dataSource;
    }

    public Collection<Airport> getDataSource() {
        return dataSource;
    }

    public void setDataSource(ArrayList<Airport> dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<Integer> searchLineByQuery(String query) {
        Collections.sort(dataSource);
        List<Integer> result = new ArrayList<>();
        for (Airport airport : dataSource)
            result.add(airport.getId());
        return result;}

}
