package repository;

import model.Airport;

import java.util.ArrayList;
import java.util.Collection;


public interface AirportRepository {
    Collection<Airport> getByColumn(int numberColumn, String filePath);

    Collection<String> getByRows(ArrayList<Integer> lineNumbers, String filePath);
}
