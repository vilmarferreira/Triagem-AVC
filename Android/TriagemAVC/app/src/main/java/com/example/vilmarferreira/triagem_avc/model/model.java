package com.example.vilmarferreira.triagem_avc.model;

public class model {
    int id;
    String nome;
    String lag,longi;

    public model() {
    }

    public model(int id, String nome, String lag, String longi) {
        this.id = id;
        this.nome = nome;
        this.lag = lag;
        this.longi = longi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLag() {
        return lag;
    }

    public void setLag(String lag) {
        this.lag = lag;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }
}
