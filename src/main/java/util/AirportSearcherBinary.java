package util;

import model.Airport;

import java.util.*;
import java.util.stream.Collectors;

public class AirportSearcherBinary implements AirportSearcher {
    private ArrayList<Airport> dataSource;
    private long searchingTime;

    public AirportSearcherBinary(ArrayList<Airport> dataSource) {
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
        Timer timer = new Timer();
        Collections.sort(dataSource);
        timer.start();
        List<Integer> result = new ArrayList<>();
        int number = binarySearch(query); //ищем удолитвроящий запросу элемент
        if (number >= 0) {

            //Находим оставшиеся элементы
            result.addAll(findItemsByRangeAndQuery(number - 1, query, false));
            result.add(number);
            result.addAll(findItemsByRangeAndQuery(number + 1, query, true));

        }
        if (!result.isEmpty())//Получаем нужные номера строк
            result = result.stream().map(it ->
                            dataSource.get(it).getId())
                    .collect(Collectors.toList());
        timer.stop();
        searchingTime = timer.getRangeTime();
        return result;
    }

    //поиск элемента с совподающим началом строки
    private int binarySearch(String query) {
        if (query.isEmpty())
            return -1;
        int low = 0;
        int high = dataSource.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            String midVal = dataSource.get(mid).getData().toLowerCase();
            if (midVal.startsWith(query))
                return mid;
            else if (midVal.compareTo(query) < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    private boolean isInOfRangeDataSource(int currentIndex) {
        return currentIndex >= 0 && currentIndex < dataSource.size();
    }

    private boolean isLineStartWith(String query, int lineIndex) {
        return dataSource
                .get(lineIndex)
                .getData()
                .toLowerCase()
                .startsWith(query);
    }

    private List<Integer> findItemsByRangeAndQuery(int startIndex, String query, boolean isDown) {
        ArrayList<Integer> result = new ArrayList<>();
        int increment = (isDown) ? 1 : -1; //Опредлеям направления прохода по массиву
        int i = startIndex;
        //получаем элементы совподающие с запросом
        while (isInOfRangeDataSource(i) && isLineStartWith(query, i)) {
            result.add(i);
            i += increment;
        }
        return result;
    }

    @Override
    public long getSearchExecutionTime() {
        return searchingTime;
    }
}
