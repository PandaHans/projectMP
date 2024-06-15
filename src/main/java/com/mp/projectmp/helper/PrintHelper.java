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
}
