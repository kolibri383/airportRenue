package model;

import lombok.Data;

@Data
public class Airport implements Comparable<Airport> {
    private int id;
    private String data;

    public Airport(int numberColumn, String data) {
        this.id = numberColumn;
        this.data = data;
    }

    @Override
    public int compareTo(Airport o) {
        return this.getData().compareTo(o.getData());
    }
}
