package src.dag;

import java.io.Serializable;
import java.time.LocalDate;

//Factory method design pattern
public abstract class Dag implements Serializable {
    private float gewerkteUren;
    private String omschrijving;
    LocalDate savedDate;

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
        this.gewerkteUren = gewerkteUren;
    }
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
    public void setSavedDate(LocalDate savedDate) {
        this.savedDate = savedDate;
    }
}
