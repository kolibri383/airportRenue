
import configuration.AirportYAMLConfig;
import service.AirportCSVService;
import service.AirportCSVServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class AirportApplication {

    public static void main(String[] args) {
        var config = AirportYAMLConfig.loadConfig().getContext();
        String query = inputQuery().toLowerCase();
        AirportCSVService airportCSVService = new AirportCSVServiceImpl(config);
        ArrayList<String> result;
        int numberColumn = config.getNumberColumn();
        if (args.length == 1) {
            int userNumberColumn = Integer.parseInt(args[0]);
            if (userNumberColumn > 0 && userNumberColumn < 13)
                numberColumn = userNumberColumn;
        }
        System.out.println("Поиск будет выполнен по столбцу номер: " + numberColumn);
        long startTime = System.currentTimeMillis();
        result = new ArrayList<>(airportCSVService.geDataAirports(query, numberColumn));
        long searchExecutionTime = System.currentTimeMillis() - startTime;
        result.forEach(System.out::println);
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
