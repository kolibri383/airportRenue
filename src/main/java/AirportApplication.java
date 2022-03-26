import model.Airport;
import service.AirportCSVService;
import service.AirportCSVServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class AirportApplication {

    public static void main(String[] args) {
        String query = inputQuery();
        AirportCSVService airportCSVService = new AirportCSVServiceImpl();
        ArrayList<Airport> result;
        long startTime = System.currentTimeMillis();
        if (args.length == 1)
            result = airportCSVService.geDataAirports(query, Integer.parseInt(args[0]));
        else
            result = airportCSVService.geDataAirports(query);
        long searchExecutionTime = System.currentTimeMillis() - startTime;
        result.forEach(it -> System.out.println(it.getData()));
        System.out.println("Количество найденых строк: " + result.size());
        System.out.println("Время, затраченное на поиск: " + searchExecutionTime + " ms");
    }

    private static String inputQuery() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку");
        String query = scanner.nextLine();
        while (query.isEmpty()) {
            System.out.println("Поисковый запрос не может быть пустым");
            query = scanner.nextLine();
        }
        return query;
    }


}
