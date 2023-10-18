package actividad1_09;

import java.io.Serializable;

public class Car implements Serializable
{
    private String brand;
    private String model;
    private int year;
    private String color;
    private boolean mate;

    protected Car (String brand, String model, int year, String color, boolean mate) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.mate = mate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isMate() {
        return mate;
    }

    public void setMate(boolean mate) {
        this.mate = mate;
    }
}
