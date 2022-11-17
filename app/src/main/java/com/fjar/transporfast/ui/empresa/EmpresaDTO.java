package com.fjar.transporfast.ui.empresa;

public class EmpresaDTO {
    private int id;
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;
    private String codigopostal;
    private String contrasena;

    public EmpresaDTO() {
        this.id = 0;
        this.nombre = "";
        this.telefono = "";
        this.correo = "";
        this.direccion = "";
        this.codigopostal = "";
        this.contrasena = "";
    }

    public EmpresaDTO(int id, String nombre, String telefono, String correo, String direccion, String codigopostal, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.codigopostal = codigopostal;
        this.contrasena = contrasena;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
