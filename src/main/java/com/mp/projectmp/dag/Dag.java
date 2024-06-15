package com.mp.projectmp.dag;

import java.io.Serializable;
import java.time.LocalDate;

//Factory method design pattern
public abstract class Dag implements Serializable {
    private float gewerkteUren;
    private String omschrijving;
    private LocalDate savedDate;

    public LocalDate getSavedDate() {
        return savedDate;
    }
    public float getGewerkteUren() {
        return gewerkteUren;
    }
    public String getOmschrijving() {
        return omschrijving;
    }

    public void setGewerkteUren(float gewerkteUren) {
        if (gewerkteUren >= 0 && gewerkteUren <= 24) {
            this.gewerkteUren = gewerkteUren;
        } else {
            System.out.println("Kilometers moet gewerkteUren moeten tussen de 0 en 24 liggen");
        }
    }
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
    public void setSavedDate(LocalDate savedDate) {
        this.savedDate = savedDate;
    }
}
