package com.example.trovastart.models;

public class Peluqueria {

    private String nombre;
    private String direccion;
    private Double longitud;
    private Double latitud;
    private String promo;
    private String image_direction;
    private String distancia;
    private String telefono;
    private String horario;
    private int hora_apertura, hora_cierre;
    private String precio;
    private String facebook, instagram, mapa;




    public Peluqueria() {
    }



    public Peluqueria(String nombre, String direccion, Double latitud, Double longitud, String promo, String image_direction,
                      String distancia, String telefono, String horario, int hora_apertura, int hora_cierre, String precio,
                      String facebook, String instagram, String mapa) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.promo = promo;
        this.distancia = distancia;
        this.image_direction = image_direction;
        this.telefono = telefono;
        this.horario = horario;
        this.hora_apertura = hora_apertura;
        this.hora_cierre = hora_cierre;
        this.precio = precio;
        this.facebook = facebook;
        this.instagram = instagram;
        this.mapa = mapa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getHora_apertura() {
        return hora_apertura;
    }

    public void setHora_apertura(int hora_apertura) {
        this.hora_apertura = hora_apertura;
    }

    public int getHora_cierre() {
        return hora_cierre;
    }

    public void setHora_cierre(int hora_cierre) {
        this.hora_cierre = hora_cierre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getImage_direction() {
        return image_direction;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public void setImage_direction(String image_direction) {
        this.image_direction = image_direction;
    }

    public String getfacebook() {
        return facebook;
    }

    public void setfacebook(String fileFacebook) {
        this.facebook = fileFacebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }
}
