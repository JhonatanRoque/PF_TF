package com.fjar.transporfast.ui.empresa;

public class empleadoDTO {
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String direccion;
    private int empresaID;
    private int rutaID;
    private int autoID;
    private String latitud;
    private String longitud;
    private String contrasena;

    public empleadoDTO() {
        this.id = 0;
        this.nombre = "";
        this.apellido = "";
        this.telefono = "";
        this.correo = "";
        this.direccion = "";
        this.empresaID = 0;
        this.rutaID = 0;
        this.autoID = 0;
        this.latitud = "";
        this.longitud = "";
        this.contrasena = "";
    }

    public empleadoDTO(int id, String nombre, String apellido, String telefono, String correo, String direccion, int empresaID, int rutaID, int autoID, String latitud, String longitud, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.empresaID = empresaID;
        this.rutaID = rutaID;
        this.autoID = autoID;
        this.latitud = latitud;
        this.longitud = longitud;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public int getEmpresaID() {
        return empresaID;
    }

    public void setEmpresaID(int empresaID) {
        this.empresaID = empresaID;
    }

    public int getRutaID() {
        return rutaID;
    }

    public void setRutaID(int rutaID) {
        this.rutaID = rutaID;
    }

    public int getAutoID() {
        return autoID;
    }

    public void setAutoID(int autoID) {
        this.autoID = autoID;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
