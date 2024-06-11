package src;

import java.time.LocalDate;
import java.util.Scanner;

public class Start {
    public static void startProgram(Gebruiker gebruiker) {
        DataWriter dataWriter = new DataWriter();
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

                    System.out.println("Hoeveel uren heb je gewerkt: ");
                    float gewerkteUren = scanner.nextFloat();
                    scanner.nextLine();

                    System.out.println("Geef een kleine omschrijving van je dag: ");
                    String omschrijving = scanner.nextLine();

                    System.out.println("Voer de datum in (YYYY-MM-DD): ");
                    String inputDate = scanner.nextLine();
                    LocalDate savedDate = LocalDate.parse(inputDate);

                    Dag nieuwedag = dagFactory.maakDag("A");

                    nieuwedag.setOmschrijving(omschrijving);
                    nieuwedag.setGewerkteUren(gewerkteUren);
                    nieuwedag.setSavedDate(savedDate);

                    project.addDag(nieuwedag);

                    dataWriter.writeData(gebruiker);
                    break;

                case "2":
                    // Voeg Client toe
                    System.out.println("Enter client details:");
                    System.out.print("Naam: ");
                    String naam = scanner.nextLine();

                    System.out.print("Hoe ver weg is het?: ");
                    float kilometers = Float.parseFloat(scanner.nextLine());

                    gebruiker.addClient(new Client(naam, kilometers));
                    dataWriter.writeData(gebruiker);
                    break;

                case "3":
                    // Voeg Project toe
                    Client client1 = gebruiker.selectClient();

                    System.out.println("Voer het project naam in: ");
                    String projectNaam = scanner.nextLine();

                    System.out.print("Voer uurloon in: ");
                    float uurloon = scanner.nextFloat();

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
