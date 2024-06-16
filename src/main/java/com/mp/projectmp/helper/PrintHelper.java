package com.mp.projectmp.helper;

import com.mp.projectmp.dag.Dag;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;
import java.time.LocalDate;

public class PrintHelper {
    public static void printLinesToPdf(PDPageContentStream contentStream, LocalDate date, Dag dag) throws IOException {
        contentStream.showText("Datum: " + date);
        contentStream.newLine();
        contentStream.showText("Gewerkte uren: " + dag.getGewerkteUren());
        contentStream.newLine();
        contentStream.showText("Omschrijving: " + dag.getOmschrijving());
        contentStream.newLine();
        contentStream.newLine();
    }

    public static void printMenu() {
        System.out.println("----------------------------");
        System.out.println("Kies een optie:");
        System.out.println("1. Voeg dagen toe");
        System.out.println("1.1 Voeg snel een dag toe (Uur gewerkt: 6, Datum: Vandaag, Omschrijving: Super leuk)");
        System.out.println("2. Voeg client toe");
        System.out.println("3. Voeg project toe");
        System.out.println("4. Genereer een maand overzicht");
        System.out.println("5. Genereer een jaar overzicht");
        System.out.println("6. Genereer een dagen overzicht");
        System.out.println("0. Exit");
        System.out.println("Maak een keuze: ");
        System.out.println("----------------------------");
    }
}
