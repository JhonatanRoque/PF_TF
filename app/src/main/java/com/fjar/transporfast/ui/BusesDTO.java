package com.fjar.transporfast.ui;

public class BusesDTO {
    private int id;
    private String marca;
    private String Nplaca;
    private String color;
    private int capacidad;

    public BusesDTO() {
        this.id = 0;
        this.marca = "";
        this.Nplaca = "";
        this.color = "";
        this.capacidad = 0;
    }

    public BusesDTO(int id, String marca, String nplaca, String color, int capacidad) {
        this.id = id;
        this.marca = marca;
        Nplaca = nplaca;
        this.color = color;
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNplaca() {
        return Nplaca;
    }

    public void setNplaca(String nplaca) {
        Nplaca = nplaca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
