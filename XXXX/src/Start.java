package src;

import src.base.Client;
import src.base.Gebruiker;
import src.base.Project;
import src.dag.Dag;
import src.dag.DagFactory;
import src.data.DataWriter;
import src.helper.UserInput;
import src.invoice.Invoice;
import src.invoice.JaarInvoice;
import src.invoice.MaandInvoice;

import java.time.LocalDate;
import java.util.Scanner;

public class Start {
    public static void startProgram(Gebruiker gebruiker) {
        DataWriter dataWriter = new DataWriter();
        UserInput userInput = new UserInput();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Voeg dagen toe");
            System.out.println("2. Voeg client toe");
            System.out.println("3. Voeg project toe");
            System.out.println("4. Genereer een maand overzicht");
            System.out.println("5. Genereer een jaar overzicht");
            System.out.println("0. Exit");
            System.out.print("Maak een keuze: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    DagFactory dagFactory = new DagFactory();

                    // Voeg dag toe
                    Client client = gebruiker.selectClient();
                    Project project = client.selectProject();

                    float gewerkteUren = userInput.getFloatInput("Hoeveel uren heb je gewerkt: ");
                    String omschrijving = userInput.getStringInput("Geef een kleine omschrijving van je dag: ");
                    LocalDate savedDate = userInput.getDate();

                    Dag nieuwedag = dagFactory.maakDag("A");

                    nieuwedag.setOmschrijving(omschrijving);
                    nieuwedag.setGewerkteUren(gewerkteUren);
                    nieuwedag.setSavedDate(savedDate);

                    project.addDag(nieuwedag);

                    dataWriter.writeData(gebruiker);
                    break;

                case "2":
                    // Voeg Client toe

                    System.out.println("Voer client informatie in:");
                    String naam = userInput.getStringInput("Naam: ");
                    float kilometers = userInput.getFloatInput("Hoe ver weg is het?: ");

                    gebruiker.addClient(new Client(naam, kilometers));
                    dataWriter.writeData(gebruiker);
                    break;

                case "3":
                    // Voeg Project toe
                    Client client1 = gebruiker.selectClient();

                    String projectNaam = userInput.getStringInput("Voer het project naam in: ");
                    float uurloon = userInput.getFloatInput("Voer uurloon in: ");

                    client1.addProject(new Project(projectNaam, uurloon));
                    dataWriter.writeData(gebruiker);
                    break;

                case "4":
                    Invoice maandInvoice = new MaandInvoice();
                    Client client2 = gebruiker.selectClient();
                    Project project2 = client2.selectProject();
                    maandInvoice.makeInvoice(client2, project2);
                    break;

                case "5":
                    Invoice jaarInvoice = new JaarInvoice();
                    Client client3 = gebruiker.selectClient();
                    Project project3 = client3.selectProject();
                    jaarInvoice.makeInvoice(client3, project3);
                    break;

                case "0":
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }
}
