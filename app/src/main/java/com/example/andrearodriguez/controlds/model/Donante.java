package com.example.andrearodriguez.controlds.model;

/**
 * Created by andrearodriguez on 2/3/18.
 */

public class Donante {

    private String id;
    private String name;
    private String lastname;
    private String age;
    private String tipoSangre;
    private String rh;
    private String estatura;
    private String peso;

    public Donante(String id, String name, String lastname, String age, String tipoSangre, String rh, String estatura, String peso) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.tipoSangre = tipoSangre;
        this.rh = rh;
        this.estatura = estatura;
        this.peso = peso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
}
