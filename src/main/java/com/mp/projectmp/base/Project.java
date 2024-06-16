package com.mp.projectmp.base;

import com.mp.projectmp.dag.Dag;

import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable {
    private String projectNaam;
    private LoonType loonType;

    private ArrayList<Dag> dagen = new ArrayList<>();

    public LoonType getLoonType() {
        return loonType;
    }

    public void setLoonType(LoonType loonType) {
        this.loonType = loonType;
    }

    public Project(String projectNaam, LoonType loonType) {
        this.projectNaam = projectNaam;
        this.loonType = loonType;
    }

    public void setDagen(ArrayList<Dag> dagen) {
        this.dagen = dagen;
    }
    public void setProjectNaam(String projectNaam) {
        this.projectNaam = projectNaam;
    }

    public ArrayList<Dag> getDagen() {
        return dagen;
    }
    public void addDag(Dag dag) {
        dagen.add(dag);
    }

    public String getProjectNaam() {
        return projectNaam;
    }
}
