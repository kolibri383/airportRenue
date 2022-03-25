import model.Airport;
import service.AirportCSVService;
import service.AirportCSVServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class AirportApplication {

    private static String inputRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку");
        String request = scanner.nextLine();
        while (request.isEmpty()){
            System.out.println("Поисковый запрос не может быть пустым");
            request = scanner.nextLine();
        }
        return request;
    }

    public static void main(String[] args) {
        String request = inputRequest();

        AirportCSVService airportCSVService = new AirportCSVServiceImpl();
        ArrayList<Airport> result;
        long startTime = System.currentTimeMillis();
        result = (args.length==1)? airportCSVService.geDataAirports(request,Integer.parseInt(args[0])):airportCSVService.geDataAirports(request);
        long searchExecutionTime = System.currentTimeMillis() - startTime;

        result.forEach(it-> System.out.println(it.getData()));
        System.out.println("Количество найденых строк: "+result.size());
        System.out.println("Время, затраченное на поиск: "+searchExecutionTime+" ms");
    }
}
