package src;

import java.time.LocalDate;

public class JaarInvoice extends Invoice  {
    public void addDagen(Client client, Project project, int maand, int jaar){
    float totaalGewerkteUren = 0;
    float totaalGeredenKiloMeters = 0;

    for (Dag dag : project.getDagen()) {
        LocalDate date = dag.getSavedDate();
        if (date.getYear() == jaar) {
            System.out.println("Datum: " + date);
            System.out.println("Gewerkte uren: " + dag.getGewerkteUren());
            System.out.println("Omschrijving: " + dag.getOmschrijving());

            totaalGeredenKiloMeters += client.getKiloMeters();
            totaalGewerkteUren += dag.getGewerkteUren();
        }
    }
    printTotals(client, totaalGewerkteUren, totaalGeredenKiloMeters);
}


    public void addClient(Client client) {
        System.out.println(client.getClientNaam());
    }
    public void addProject(Project project) {
        System.out.println(project.getProjectNaam());
    }
}
