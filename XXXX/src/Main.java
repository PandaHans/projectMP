package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataReader dataReader = new DataReader();

        ArrayList<Gebruiker> gebruikers = new ArrayList<>();

        Gebruiker maarten = new Gebruiker("12345");
        gebruikers.add(maarten);

        System.out.println("Voer je inlog naam in: ");
        String inlogNaam = scanner.nextLine();

        for (Gebruiker gebruiker : gebruikers) {
            if (gebruiker.isGebruiker(inlogNaam)) {
                Gebruiker loadedGebruiker = dataReader.readData();
                if (loadedGebruiker != null) {
                    gebruiker = loadedGebruiker;
                }
                Start.startProgram(gebruiker);
            }
        }
    }
}

// connent Dag aan project terwijl kijken naar umls van video
// Start class veranderen
// invoice class invullen
// alle classes public maken
// javafx toevoegen
// Kijken naar userclass?
// comments toevoegen bij design patterns
// kijken naar sigrid
// kijken naar solid principles
// kijken naar data save method
// alles nl veranderen

/*class UserInput {
    private Scanner scanner;

    public UserInput() {
        this.scanner = new Scanner(System.in);
    }

    public int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return input;
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public float getFloatInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextFloat()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        float input = scanner.nextFloat();
        scanner.nextLine(); // consume newline
        return input;
    }
}*/
/*class Start {
    public static void startProgram(Gebruiker gebruiker) {
        DataWriter dataWriter = new DataWriter();
        UserInput userInput = new UserInput();
        DagFactory dagFactory = DagFactory.getInstance();

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Voeg dagen toe");
            System.out.println("2. Voeg werkgever toe");
            System.out.println("3. Voeg project toe");
            System.out.println("4. Genereer een maand overzicht");
            System.out.println("5. Genereer een jaar overzicht");
            System.out.println("0. Exit");
            String choice = userInput.getStringInput("Maak een keuze: ");

            switch (choice) {
                case "1":
                    Client client = gebruiker.selectWerkgever(userInput);
                    Project project = client.selectProject(userInput);

                    float gewerkteUren = userInput.getFloatInput("Hoeveel uren heb je gewerkt: ");
                    String omschrijving = userInput.getStringInput("Geef een kleine omschrijving van je dag: ");
                    String inputDate = userInput.getStringInput("Voer de datum in (YYYY-MM-DD): ");
                    LocalDate savedDate = LocalDate.parse(inputDate);

                    Dag nieuwedag = dagFactory.maakDag("A");
                    nieuwedag.setOmschrijving(omschrijving);
                    nieuwedag.setGewerkteUren(gewerkteUren);
                    nieuwedag.setSavedDate(savedDate);

                    project.addDag(nieuwedag);
                    dataWriter.writeData(gebruiker);
                    break;

                case "2":
                    // Voeg Werkgever toe
                    String naam = userInput.getStringInput("Enter werkgever details:\nNaam: ");
                    float kilometers = userInput.getFloatInput("Hoe ver weg is het?: ");
                    gebruiker.addWerkgever(new Client(naam, kilometers));
                    dataWriter.writeData(gebruiker);
                    break;

                case "3":
                    // Voeg Project toe
                    Client client1 = gebruiker.selectWerkgever(userInput);
                    String projectNaam = userInput.getStringInput("Voer het project naam in: ");
                    float uurloon = userInput.getFloatInput("Voer uurloon in: ");
                    client1.addProject(new Project(projectNaam, uurloon));
                    dataWriter.writeData(gebruiker);
                    break;

                case "4":
                    MaandInvoice maandInvoice = new MaandInvoice();
                    Client client2 = gebruiker.selectWerkgever(userInput);
                    Project project2 = client2.selectProject(userInput);
                    int jaar = userInput.getIntInput("Voer een jaar in: ");
                    int maand = userInput.getIntInput("Voer een maand in: ");
                    maandInvoice.makeInvoice();
                    break;

                case "5":
                    JaarInvoice jaarInvoice = new JaarInvoice();
                    Client client3 = gebruiker.selectWerkgever(userInput);
                    Project project3 = client3.selectProject(userInput);
                    int jaar1 = userInput.getIntInput("Voer een jaar in: ");
                    jaarInvoice.makeInvoice();
                    break;

                case "0":
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }
}*/

// https://www.newthinktank.com/2012/09/factory-design-pattern-tutorial/
// https://www.newthinktank.com/2012/10/template-method-design-pattern-tutorial/
// https://www.newthinktank.com/2012/10/composite-design-pattern-tutorial/
// https://www.youtube.com/watch?v=9XnsOpjclUg&list=PLF206E906175C7E07&index=9
// https://www.youtube.com/watch?v=vNHpsC5ng_E&list=PLF206E906175C7E07
