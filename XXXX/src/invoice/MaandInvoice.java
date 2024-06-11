package src.invoice;

import src.base.Client;
import src.base.Project;
import src.dag.Dag;

import java.time.LocalDate;

public class MaandInvoice extends Invoice {
    public void addDagen(Client client, Project project, int maand, int jaar){
        float totaalGewerkteUren = 0;
        float totaalGeredenKiloMeters = 0;

        for (Dag dag : project.getDagen()) {
            LocalDate date = dag.getSavedDate();
            if (date.getYear() == jaar && date.getMonthValue() == maand) {
                System.out.println("Datum: " + date);
                System.out.println("Gewerkte uren: " + dag.getGewerkteUren());
                System.out.println("Omschrijving: " + dag.getOmschrijving() + "\n");

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
