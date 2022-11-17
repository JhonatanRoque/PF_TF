package com.fjar.transporfast.ui;

public class RutaDTO {
    private int id;
   private String nombre;

    public RutaDTO() {
        this.id = 0;
        this.nombre = "";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
