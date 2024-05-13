package com.example.spinnerexamen;

public class Datos {
    // Atributos
    private String nombreEquipo;
    private String paisEquipo;
    private String añoEquipo;
    private String ciudadEquipo;
    private int imagenResId;


    public Datos(String nombreEquipo, String paisEquipo, String añoEquipo, String ciudadEquipo, int imagenResId) {
        this.nombreEquipo = nombreEquipo;
        this.paisEquipo = paisEquipo;
        this.añoEquipo = añoEquipo;
        this.ciudadEquipo = ciudadEquipo;
        this.imagenResId = imagenResId;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getPaisEquipo() {
        return paisEquipo;
    }

    public void setPaisEquipo(String paisEquipo) {
        this.paisEquipo = paisEquipo;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    public void setImagenResId(int imagenResId) {
        this.imagenResId = imagenResId;
    }

    public String getCiudadEquipo() {
        return ciudadEquipo;
    }

    public void setCiudadEquipo(String ciudadEquipo) {
        this.ciudadEquipo = ciudadEquipo;
    }

    public String getAñoEquipo() {
        return añoEquipo;
    }

    public void setAñoEquipo(String añoEquipo) {
        this.añoEquipo = añoEquipo;
    }

}
