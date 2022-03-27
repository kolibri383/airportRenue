package repository;

import model.Airport;

import java.util.ArrayList;
import java.util.Collection;


public interface AirportRepository {
    Collection<Airport> getByColumn(int numberColumn);

    Collection<Airport> getByRows(ArrayList<Integer> lineNumbers);

    Collection<Airport> getByColumn();
}
