package repository;

import model.Airport;
import java.util.ArrayList;

public interface AirportRepository {
    ArrayList<Airport> getByColumn(int numberColumn);
    ArrayList<Airport> getByRows(ArrayList<Integer> lineNumbers);
    ArrayList<Airport> getByColumn();
}
