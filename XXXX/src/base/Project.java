package src.base;

import src.dag.Dag;

import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable {
    private String projectNaam;
    private float uurLoon;
    private ArrayList<Dag> dagen = new ArrayList<>();

    public Project(String projectNaam, float uurLoon) {
        this.projectNaam = projectNaam;
        this.uurLoon = uurLoon;
    }
    public void setDagen(ArrayList<Dag> dagen) {
        this.dagen = dagen;
    }
    public void setProjectNaam(String projectNaam) {
        this.projectNaam = projectNaam;
    }
    public void setUurLoon(float uurLoon) {
        this.uurLoon = uurLoon;
    }
    public ArrayList<Dag> getDagen() {
        return dagen;
    }
    public void addDag(Dag dag){
        dagen.add(dag);
    }
    public float getUurLoon() {
        return uurLoon;
    }
    public String getProjectNaam() {
        return projectNaam;
    }
}
