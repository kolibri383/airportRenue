package repository;

import au.com.bytecode.opencsv.CSVReader;
import model.Airport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class AirportCSVRepository implements AirportRepository {

    public AirportCSVRepository() {
    }

    @Override
    public Collection<Airport> getByColumn(int numberColumn, String filePath) {
        ArrayList<Airport> dataAirports = new ArrayList<>();
        try {
            dataAirports.addAll(dataColumn(numberColumn, filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataAirports;
    }

    @Override
    public Collection<String> getByRows(ArrayList<Integer> lineNumbers, String filePath) {
        ArrayList<String> result = new ArrayList<>();
        if (lineNumbers.isEmpty()) //Возвращаем пустой массив, если массив номеров строк пустой
            return result;
        ArrayList<Integer> sortingNumbersLine = new ArrayList<Integer>(lineNumbers);
        Collections.sort(sortingNumbersLine); //сортируем исходный массив с номерами строк, чтобы получить результат за один проход по файлу
        HashMap<Integer, String> resultData = getDataByLines(sortingNumbersLine, filePath);//получаем данные по номерам строк
        String currentAirportData;
        for (int i = 0; i < resultData.size(); i++) {
            currentAirportData = resultData.get(lineNumbers.get(i));
            //сохраняем данные соглсано лексигрофическому порядоку
            result.add(currentAirportData);
        }
        return result;
    }

    private ArrayList<Airport> dataColumn(int numberColumn, String filePath) {
        ArrayList<Airport> data = new ArrayList<Airport>();
        try {
            CSVReader reader = new CSVReader(new FileReader(filePath));
            String[] currentAirportData;
            int currentNumberLine = 1;
            while ((currentAirportData = reader.readNext()) != null) {
                if (!currentAirportData[numberColumn].isEmpty())
                    data.add(new Airport(currentNumberLine++, currentAirportData[numberColumn]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    private String formatAnswer(String str) {
        return str.substring(str.indexOf(",")).replaceAll("^\"|\"", "").replaceAll(",", " ").trim();
    }

    private HashMap<Integer, String> getDataByLines(ArrayList<Integer> sortingNumbersLine, String filePath) {
        HashMap<Integer, String> resultData = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            int numberCurrentLine = 0, numberFindLine = sortingNumbersLine.get(0), currentIndex = 0;
            while (numberCurrentLine <= numberFindLine) {
                if (numberCurrentLine != numberFindLine - 1)
                    reader.readLine();
                else {
                    resultData.put(numberFindLine, formatAnswer(reader.readLine()));
                    if (currentIndex < sortingNumbersLine.size() - 1)
                        numberFindLine = sortingNumbersLine.get(++currentIndex);
                }
                numberCurrentLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultData;
    }

}