package com.mp.projectmp.invoice;

import com.mp.projectmp.base.Client;
import com.mp.projectmp.base.Project;
import com.mp.projectmp.dag.Dag;
import com.mp.projectmp.helper.PrintHelper;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

//Template Method Pattern
public class DagList extends Invoice {

    //nu worden er geen clieenten en projecten toegevoegd
    boolean wantsClient() {
        return false;
    }
    boolean wantsProject() {
        return false;
    }

    public void addDagen(PDPageContentStream contentStream, Client client,Project project, int maand, int jaar) throws IOException {
        ArrayList<Dag> dagen = project.getDagen();

        float totaalGewerkteUren = 0;
        float totaalGeredenKiloMeters = 0;

        for (Dag dag : dagen) {
            LocalDate date = dag.getSavedDate();

            PrintHelper.printLinesToPdf(contentStream, date, dag);

            totaalGeredenKiloMeters += client.getKiloMeters();
            totaalGewerkteUren += dag.getGewerkteUren();
        }

        contentStream.showText("Totaal gereden kilometers: " + totaalGeredenKiloMeters);
        contentStream.newLine();
        contentStream.showText("Totaal aantal gewerkte uren: " + totaalGewerkteUren);
        contentStream.newLine();
    }

    @Override
    void addClient(PDPageContentStream contentStream, Client client){
    }
    @Override
    void addProject(PDPageContentStream contentStream, Project project){

    }
}
