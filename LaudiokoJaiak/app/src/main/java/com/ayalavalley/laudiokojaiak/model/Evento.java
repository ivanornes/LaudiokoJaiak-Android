/**
 * Created by ivanornes on 08/07/14.
 */
package com.ayalavalley.laudiokojaiak.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Evento {

    @JsonProperty("id")
    private int id;

    @JsonProperty("title_es")
    private String titleES;

    @JsonProperty("title_eu")
    private String titleEU;

    @JsonProperty("hora_es")
    private String horaES;

    @JsonProperty("hora_eu")
    private String horaEU;

    private int day;

    @JsonProperty("descripcion_es")
    private String descripcionES;

    @JsonProperty("descripcion_eu")
    private String descripcionEU;

    private int favorito;

    private String hashtag;

    private String icono;

    private float latitud;

    private float longitud;

    @JsonProperty("lugar_es")
    private String lugarES;

    @JsonProperty("lugar_eu")
    private String lugarEU;

    private int orden;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleES() {
        return titleES;
    }

    public void setTitleES(String titleES) {
        this.titleES = titleES;
    }

    public String getTitleEU() {
        return titleEU;
    }

    public void setTitleEU(String titleEU) {
        this.titleEU = titleEU;
    }

    public String getHoraES() {
        return horaES;
    }

    public void setHoraES(String horaES) {
        this.horaES = horaES;
    }

    public String getHoraEU() {
        return horaEU;
    }

    public void setHoraEU(String horaEU) {
        this.horaEU = horaEU;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDescripcionES() {
        return descripcionES;
    }

    public void setDescripcionES(String descripcionES) {
        this.descripcionES = descripcionES;
    }

    public String getDescripcionEU() {
        return descripcionEU;
    }

    public void setDescripcionEU(String descripcionEU) {
        this.descripcionEU = descripcionEU;
    }

    public int getFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public String getLugarES() {
        return lugarES;
    }

    public void setLugarES(String lugarES) {
        this.lugarES = lugarES;
    }

    public String getLugarEU() {
        return lugarEU;
    }

    public void setLugarEU(String lugarEU) {
        this.lugarEU = lugarEU;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

}
