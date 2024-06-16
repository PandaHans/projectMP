package com.mp.projectmp.base;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;


public class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String clientNaam;
    private float kiloMeters;
    private final ArrayList<Project> projecten = new ArrayList<>();

    public Client(String clientNaam, float kiloMeters) {
        this.clientNaam = clientNaam;
        this.kiloMeters = kiloMeters;
    }

    public void setClientNaam(String clientNaam) {
        this.clientNaam = clientNaam;
    }
    public void setKiloMeters(float kiloMeters) {
        this.kiloMeters = kiloMeters;
    }
    public float getKiloMeters() {
        return kiloMeters;
    }
    public ArrayList<Project> getProjecten() {
        return projecten;
    }
    public void addProject(Project project) {
        // check of de naam leeg is
        if (project == null || project.getProjectNaam().isEmpty()) {
            System.out.println("Project naam mag niet leeg zijn.");
            return;
        }

        //check of uurloon boven de 0 is
        if (project.getUurLoon() <= 0) {
            System.out.println("Uurloon moet groter zijn dan 0.");
            return;
        }

        // kijk of project al bestaat
        for (Project bestaandProject : projecten) {
            if (bestaandProject.getProjectNaam().equals(project.getProjectNaam())) {
                System.out.println("Project bestaat al.");
                return;
            }else {
                projecten.add(project);
                System.out.println("Project toegevoegd!");
            }
        }
    }
    public String getProjectNaam(int i) {
        return projecten.get(i).getProjectNaam();
    }
    public String getClientNaam() {
        return clientNaam;
    }
}
