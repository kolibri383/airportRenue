package repository;

import au.com.bytecode.opencsv.CSVReader;
import configuration.AirportContext;
import configuration.AirportYAMLConfig;
import model.Airport;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class CSVRepositoryImpl implements AirportRepository {
    private AirportContext context;

    public CSVRepositoryImpl(){context = AirportYAMLConfig.loadConfig().getContext();}

    private ArrayList<Airport> dataColumn(int numberColumn) throws Exception {
        ArrayList<Airport> data = new ArrayList<Airport>();
        try {
            CSVReader reader = new CSVReader(new FileReader(context.getFilePath()));
            String[] currentAirportData = reader.readNext();
            if(numberColumn>currentAirportData.length-1||numberColumn<0){
                System.out.println(numberColumn
                        +" недопустимое значение для параметра номер строки, поиск будет осущетсвлён по строке "
                        +context.getNumberColumn());
                numberColumn = context.getNumberColumn();
            }
            if(!currentAirportData[numberColumn].isEmpty())
                data.add(new Airport(1,currentAirportData[numberColumn]));
            int currentNumberLine = 2;

            while ((currentAirportData = reader.readNext())!=null){
                if(!currentAirportData[numberColumn].isEmpty())
                    data.add(new Airport(currentNumberLine++,currentAirportData[numberColumn]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public ArrayList<Airport> getByColumn(int numberColumn) {
        ArrayList<Airport> dataAirports = new ArrayList<>();
        try {
            dataAirports.addAll(dataColumn(numberColumn));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dataAirports;
    }

    private HashMap<Integer,String> getDataByLines(ArrayList<Integer> sortingNumbersLine){
        HashMap<Integer, String> resultData = new HashMap<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(context.getFilePath()));
            int numberCurrentLine = 1, numberFindLine = sortingNumbersLine.get(0), currentIndex = 0;
            String[] currentLineData;
            while ((currentLineData = reader.readNext())!=null){
                if(numberCurrentLine == numberFindLine){//ищем нужную строку и получаем данные из нее
                    resultData.put(numberCurrentLine, Arrays.stream(currentLineData)
                            .collect(Collectors.toList())
                            .subList(1,currentLineData.length)
                            .toString());
                    if(currentIndex>=sortingNumbersLine.size()-1) //останавливаем цикл, если нашли все нужные строки
                        break;
                    numberFindLine = sortingNumbersLine.get(++currentIndex); //ищем следующую строку
                }
                numberCurrentLine++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return resultData;
    }

    @Override
    public ArrayList<Airport> getByRows(ArrayList<Integer> lineNumbers) {
        ArrayList<Airport> result = new ArrayList<>();
        if(lineNumbers.isEmpty()) //Возвращаем пустой массив, если массив номеров строк пустой
            return result;
        ArrayList<Integer> sortingNumbersLine = new ArrayList<Integer>(lineNumbers);
        Collections.sort(sortingNumbersLine); //сортируем исходный массив с номерами строк, чтобы получить результат за один проход по файлу
        HashMap<Integer, String> resultData = getDataByLines(sortingNumbersLine);//получаем данные по номерам строк
        String currentAirportData;
        for(int i=0; i < resultData.size(); i++){
            currentAirportData = resultData.get(lineNumbers.get(i)).replace("[","").replace("]","");
            //сохраняем данные соглсано лексигрофическом порядоку порядку
            result.add(new Airport(lineNumbers.get(i),currentAirportData));
        }
        return result;
    }

    @Override
    public ArrayList<Airport> getByColumn() {
        return getByColumn(context.getNumberColumn());
    }

    public static void main(String[] args) {
        CSVRepositoryImpl csvRepository = new CSVRepositoryImpl();
        csvRepository.getByColumn(2).forEach(System.out::println);
    }
}
