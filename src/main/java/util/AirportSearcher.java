package util;

import model.Airport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class AirportSearcher {
    private ArrayList<Airport> dataSource;


    public AirportSearcher(ArrayList<Airport> dataSource){this.dataSource = dataSource;}
    public ArrayList<Airport> getDataSource() {return dataSource;}
    public void setDataSource(ArrayList<Airport> dataSource) {
        this.dataSource = dataSource;
    }

    //поиск элемента с совподающим началом строки
    private int binarySearch(String request){
        if(request.isEmpty())
            return -1;
        int low = 0;
        int high = dataSource.size() - 1;
        while (low<=high){
            int mid = (low + high) >>> 1;
            String midVal = dataSource.get(mid).getData().toLowerCase();
            if(midVal.startsWith(request))
                return mid;
            else if(midVal.compareTo(request)<0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    private ArrayList<Integer> findItemsByRangeAndRequest(int startIndex, String request, boolean isDown){
        ArrayList<Integer> result = new ArrayList<>();
        int increment = (isDown)? 1: -1; //Опредлеям направления прохода по массиву
        int i = startIndex;
        //получаем элементы совподающие с запросом
        while (i>=0&&i<dataSource.size()&&dataSource.get(i).getData().toLowerCase().startsWith(request.toLowerCase())){
            result.add(i);
            i+=increment;
        }
        return result;
    }

    public ArrayList<Integer> searchLineByRequest(String request) {
        Collections.sort(dataSource);
        ArrayList<Integer> result = new ArrayList<>();
        int number = binarySearch(request.toLowerCase()); //ищем удолитвроящий запросу элемент
        if(number>=0){
            //Находим оставшиеся элементы
            result.addAll(findItemsByRangeAndRequest(number-1,request,false));
            result.add(number);
            result.addAll(findItemsByRangeAndRequest(number+1,request,true));
        }
        if(!result.isEmpty())//Получаем нужные номера строк
            result = result.stream().map( it -> dataSource.get(it).getId())
                    .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }

}
