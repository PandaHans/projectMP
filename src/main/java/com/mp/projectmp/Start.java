package com.mp.projectmp;

import com.mp.projectmp.base.Client;
import com.mp.projectmp.base.Gebruiker;
import com.mp.projectmp.base.Project;

import com.mp.projectmp.dag.Dag;
import com.mp.projectmp.dag.DagFactory;

import com.mp.projectmp.data.DataWriter;
import com.mp.projectmp.helper.UserInput;
import com.mp.projectmp.invoice.Invoice;
import com.mp.projectmp.invoice.JaarInvoice;
import com.mp.projectmp.invoice.MaandInvoice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Start{
    public static void startProgram(Gebruiker gebruiker) throws IOException {
        DataWriter dataWriter = new DataWriter();
        UserInput userInput = new UserInput();
        Scanner scanner = new Scanner(System.in);

        Client client = checkForClient(gebruiker, dataWriter, userInput);
        Project project = checkForProject(gebruiker, client, dataWriter, userInput);

        while (true) {
            printMenu();
            String choice = scanner.nextLine();
            handleChoice(choice, gebruiker, client, project, dataWriter, userInput);
        }
    }

    private static Client checkForClient(Gebruiker gebruiker, DataWriter dataWriter, UserInput userInput){
        if (gebruiker.getClienten().isEmpty()){
            System.out.println("Er zijn geen clienten gevonden maak er eerst een.");
            addClient(gebruiker, dataWriter, userInput);
        }
        return userInput.selectClient(gebruiker);
    }
    private static Project checkForProject(Gebruiker gebruiker, Client client, DataWriter dataWriter, UserInput userInput){
        if (client.getProjecten().isEmpty()){
            System.out.println("Er zijn geen projecten gevonden maak er eerst een.");
            addProject(gebruiker, client, dataWriter, userInput);
        }
        return userInput.selectProject(client);
    }

    private static void printMenu() {
        System.out.println("----------------------------");
        System.out.println("Kies een optie:");
        System.out.println("1. Voeg dag toe");
        System.out.println("1.1 Voeg snel een dag toe (Uren: 6, Datum: Vandaag, Omschrijving: Super leuk)");
        System.out.println("2. Voeg client toe");
        System.out.println("3. Voeg project toe");
        System.out.println("4. Genereer een maand overzicht");
        System.out.println("5. Genereer een jaar overzicht");
        System.out.println("0. Exit");
        System.out.println("Maak een keuze: ");
        System.out.println("----------------------------");
    }
    private static void handleChoice(String choice, Gebruiker gebruiker, Client client, Project project, DataWriter dataWriter, UserInput userInput) throws IOException {
        switch (choice) {
            case "1":
                addNewDay(gebruiker, project, dataWriter, userInput );
                break;
            case "1.1":
                addFastDay(gebruiker, project, dataWriter);
                break;
            case "2":
                addClient(gebruiker, dataWriter, userInput);
                break;
            case "3":
                addProject(gebruiker, client, dataWriter, userInput);
                break;
            case "4":
                generateMonthlyInvoice(client, project);
                break;
            case "5":
                generateYearlyInvoice(client, project);
                break;
            case "0":
                System.out.println("Uitloggen...");
                break;
            default:
                System.out.println("Probeer opnieuw.");
        }
    }

    private static void addNewDay(Gebruiker gebruiker, Project project, DataWriter dataWriter, UserInput userInput) {
        DagFactory dagFactory = new DagFactory();
        float gewerkteUren = userInput.getFloatInput("Hoeveel uren heb je gewerkt: ");
        String omschrijving = userInput.getStringInput("Geef een kleine omschrijving van je dag: ");
        LocalDate savedDate = userInput.getDate();

        Dag nieuwedag = dagFactory.maakDag("N");
        nieuwedag.setOmschrijving(omschrijving);
        nieuwedag.setGewerkteUren(gewerkteUren);
        nieuwedag.setSavedDate(savedDate);

        project.addDag(nieuwedag);
        dataWriter.writeData(gebruiker);
    }
    private static void addFastDay(Gebruiker gebruiker, Project project, DataWriter dataWriter) {
        DagFactory dagFactory = new DagFactory();
        Dag snelleDag = dagFactory.maakDag("S");

        System.out.println(snelleDag.getSavedDate());

        project.addDag(snelleDag);
        dataWriter.writeData(gebruiker);
    }
    private static void addClient(Gebruiker gebruiker, DataWriter dataWriter, UserInput userInput) {
        System.out.println("Voer client informatie in:");
        String naam = userInput.getStringInput("Naam: ");
        float kilometers = userInput.getFloatInput("Hoe ver weg is het?(in km): ");

        gebruiker.addClient(new Client(naam, kilometers));
        dataWriter.writeData(gebruiker);
    }
    private static void addProject(Gebruiker gebruiker, Client client, DataWriter dataWriter, UserInput userInput) {
        String projectNaam = userInput.getStringInput("Voer het project naam in: ");
        float uurloon = userInput.getFloatInput("Voer uurloon in: ");

        client.addProject(new Project(projectNaam, uurloon));
        dataWriter.writeData(gebruiker);
    }
    private static void generateMonthlyInvoice(Client client, Project project) throws IOException {
        Invoice maandInvoice = new MaandInvoice();
        maandInvoice.createInvoicePDF(client, project);
    }
    private static void generateYearlyInvoice(Client client, Project project) throws IOException {
        Invoice jaarInvoice = new JaarInvoice();
        jaarInvoice.createInvoicePDF(client, project);
    }
}