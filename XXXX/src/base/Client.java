package src.base;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String clientNaam;
    private float kiloMeters;
    private ArrayList<Project> projecten = new ArrayList<>();

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
    public void setProjecten(ArrayList<Project> projecten) {
        this.projecten = projecten;
    }

    public float getKiloMeters(){
        return kiloMeters;
    }
    public ArrayList<Project> getProjecten() {
        return projecten;
    }
    public void addProject(Project project){
        // kijk of project al bestaat
        boolean exists = false;
        for (Project bestaandProject : projecten) {
            if (bestaandProject.getProjectNaam().equals(project.getProjectNaam())) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            projecten.add(project);
            System.out.println("Project toegevoegd!");
        } else {
            System.out.println("Project bestaat al.");
        }
    }
    public String getProjectNaam(int i) {
        return projecten.get(i).getProjectNaam();
    }
    public Project selectProject() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecteer een project:");
        for (int i = 0; i < getProjecten().size(); i++) {
            System.out.println(i + ". " + getProjectNaam(i));
        }

        System.out.print("Maak een keuze: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        if (choice >= 0 && choice < getProjecten().size()) {
            return getProjecten().get(choice);
        } else {
            System.out.println("Ongeldige keuze voor project.");
            return null;
        }
    }
    public String getClientNaam() {
        return clientNaam;
    }
}
