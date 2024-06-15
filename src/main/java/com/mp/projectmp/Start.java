package com.mp.projectmp;

import com.mp.projectmp.base.Client;
import com.mp.projectmp.base.Gebruiker;
import com.mp.projectmp.base.Project;

import com.mp.projectmp.dag.Dag;
import com.mp.projectmp.dag.DagFactory;

import com.mp.projectmp.data.DataWriter;
import com.mp.projectmp.data.DataWriterInterface;
import com.mp.projectmp.helper.UserInputHelper;
import com.mp.projectmp.helper.UserInputInterface;
import com.mp.projectmp.invoice.DagList;
import com.mp.projectmp.invoice.Invoice;
import com.mp.projectmp.invoice.JaarInvoice;
import com.mp.projectmp.invoice.MaandInvoice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Start {
    private final DataWriterInterface dataWriter;
    private final UserInputInterface userInput;

    public Start(DataWriterInterface dataWriter, UserInputInterface userInput) {
        this.dataWriter = dataWriter;
        this.userInput = userInput;
    }
    public void startProgram(Gebruiker gebruiker) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Client client = checkForClient(gebruiker);
        Project project = checkForProject(gebruiker, client);

        while (true) {
            printMenu();
            String choice = scanner.nextLine();
            handleChoice(choice, gebruiker, client, project);
        }
    }

    private Client checkForClient(Gebruiker gebruiker) {
        if (gebruiker.getClienten().isEmpty()) {
            System.out.println("Er zijn geen clienten gevonden. Maak er eerst een.");
            addClient(gebruiker);
        }
        return userInput.selectClient(gebruiker);
    }
    private Project checkForProject(Gebruiker gebruiker, Client client) {
        if (client.getProjecten().isEmpty()) {
            System.out.println("Er zijn geen projecten gevonden. Maak er eerst een.");
            addProject(gebruiker, client);
        }
        return userInput.selectProject(client);
    }

    private void printMenu() {
        System.out.println("----------------------------");
        System.out.println("Kies een optie:");
        System.out.println("1. Voeg dagen toe");
        System.out.println("2. Voeg client toe");
        System.out.println("3. Voeg project toe");
        System.out.println("4. Genereer een maand overzicht");
        System.out.println("5. Genereer een jaar overzicht");
        System.out.println("0. Exit");
        System.out.println("Maak een keuze: ");
        System.out.println("----------------------------");
    }
    private void handleChoice(String choice, Gebruiker gebruiker, Client client, Project project) throws IOException {
        switch (choice) {
            case "1":
                addDay(gebruiker, project);
                break;
            case "1.1":
                addFastDay(gebruiker, project);
                break;
            case "2":
                addClient(gebruiker);
                break;
            case "3":
                addProject(gebruiker, client);
                break;
            case "4":
                generateMonthlyInvoice(client, project);
                break;
            case "5":
                generateYearlyInvoice(client, project);
                break;
            case "6":
                generateDayList(client, project);
                break;
            case "0":
                System.out.println("Uitloggen...");
                return;
            default:
                System.out.println("Ongeldige keuze. Probeer opnieuw.");
        }
    }

    private void addDay(Gebruiker gebruiker, Project project) {
        DagFactory dagFactory = new DagFactory();
        float gewerkteUren = userInput.getFloatInput("Hoeveel uren heb je gewerkt: ");
        String omschrijving = userInput.getStringInput("Geef een kleine omschrijving van je dag: ");
        LocalDate savedDate = userInput.getDate();

        Dag nieuwedag = dagFactory.maakDag("A");
        nieuwedag.setOmschrijving(omschrijving);
        nieuwedag.setGewerkteUren(gewerkteUren);
        nieuwedag.setSavedDate(savedDate);

        project.addDag(nieuwedag);
        dataWriter.writeData(gebruiker);
    }
    private void addFastDay(Gebruiker gebruiker, Project project){
        DagFactory dagFactory = new DagFactory();
        Dag nieuwedag = dagFactory.maakDag("S");

        project.addDag(nieuwedag);
        dataWriter.writeData(gebruiker);
    }

    private void addClient(Gebruiker gebruiker) {
        System.out.println("Voer client informatie in:");
        String naam = userInput.getStringInput("Naam: ");
        float kilometers = userInput.getFloatInput("Hoe ver weg is het?: ");

        gebruiker.addClient(new Client(naam, kilometers));
        dataWriter.writeData(gebruiker);
    }
    private void addProject(Gebruiker gebruiker, Client client) {
        String projectNaam = userInput.getStringInput("Voer het project naam in: ");
        float uurloon = userInput.getFloatInput("Voer uurloon in: ");

        client.addProject(new Project(projectNaam, uurloon));
        dataWriter.writeData(gebruiker);
    }

    private void generateMonthlyInvoice(Client client, Project project) throws IOException {
        Invoice maandInvoice = new MaandInvoice();
        maandInvoice.createInvoicePDF(client, project);
    }
    private void generateYearlyInvoice(Client client, Project project) throws IOException {
        Invoice jaarInvoice = new JaarInvoice();
        jaarInvoice.createInvoicePDF(client, project);
    }
    private void generateDayList(Client client, Project project) throws IOException {
        Invoice dagList = new DagList();
        dagList.createInvoicePDF(client, project);
    }
}
