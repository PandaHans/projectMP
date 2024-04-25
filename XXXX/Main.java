import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;

class Gebruiker implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String inlogNaam;
    private ArrayList<Werkgever> werkgevers = new ArrayList<>();
    private ArrayList<Project> projecten = new ArrayList<>();
    private ArrayList<Dag> dagen = new ArrayList<>();

    public Gebruiker(String inlogNaam) {
        this.inlogNaam = inlogNaam;
    }

    public ArrayList<Project> getProjecten() {
        return projecten;
    }

    public ArrayList<Werkgever> getWerkgevers() {
        return werkgevers;
    }


    public void addWerkgever(Werkgever werkgever){
        // kijk of werkgever al bestaat
        boolean exists = false;
        for (Werkgever bestaandeWerkgever : werkgevers) {
            if (bestaandeWerkgever.getNaam().equals(werkgever.getNaam())) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            werkgevers.add(werkgever);
            System.out.println("Werkgever toegevoegd!");
        } else {
            System.out.println("Werkgever bestaat al.");
        }
    }

    public void addProject(Project project){
        // kijk of project al bestaat
        boolean exists = false;
        for (Project bestaandProject : projecten) {
            if (bestaandProject.getNaam().equals(project.getNaam())) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            projecten.add(project);
            System.out.println("Project toegevoegd!");
        } else {
            System.out.println("Project bestaat al.");
        }
    }

    public ArrayList<Dag> getDagen() {
        return dagen;
    }

    public void addDag(Dag dag){
        dagen.add(dag);
    }

    public boolean isGebruiker(String inlogNaam){
        return this.inlogNaam.equals(inlogNaam);
    }

    public String getWerkgeverNaam(int i){
        return werkgevers.get(i).getNaam();
    }

    public String getProjectNaam(int i) {
        return projecten.get(i).getNaam();
    }

    public Werkgever selectWerkgever(Scanner scanner) {
        System.out.println("Selecteer een werkgever:");
        for (int i = 0; i < getWerkgevers().size(); i++) {
            System.out.println(i + ". " + getWerkgeverNaam(i));
        }

        System.out.print("Maak een keuze: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 0 && choice < getWerkgevers().size()) {
            return getWerkgevers().get(choice);
        } else {
            System.out.println("Ongeldige keuze voor werkgever.");
            return null;
        }
    }

    public Project selectProject(Scanner scanner) {
        System.out.println("Selecteer een project:");
        for (int i = 0; i < getProjecten().size(); i++) {
            System.out.println(i + ". " + getProjectNaam(i));
        }

        System.out.print("Maak een keuze: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        if (choice >= 0 && choice < getProjecten().size()) {
            return getProjecten().get(choice);
        } else {
            System.out.println("Ongeldige keuze voor project.");
            return null;
        }
    }

}

class Werkgever implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String naam;
    private float kiloMeters;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Werkgever(String naam, float kiloMeters) {
        this.naam = naam;
        this.kiloMeters = kiloMeters;
    }
}

class Project implements Serializable {
    private String naam;
    private Boolean uurLoonTORF;
    private float uurLoon;
    private float vastePrijs;

    public Project(String naam, float uurLoon) {
        this.naam = naam;
        this.uurLoon = uurLoon;
    }

    public float getUurLoon() {
        return uurLoon;
    }

    public Project(String naam, float vastePrijs, boolean uurLoonTORF) {
        this.naam = naam;
        this.vastePrijs = vastePrijs;
        this.uurLoonTORF = uurLoonTORF;
    }

    public String getNaam() {
        return naam;
    }
}

class Dag implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private float gewerkteUren;
    private String omschrijving;
    LocalDate savedDate;

    public LocalDate getSavedDate() {
        return savedDate;
    }

    public Dag(float gewerkteUren, String omschrijving, LocalDate savedDate) {
        this.gewerkteUren = gewerkteUren;
        this.omschrijving = omschrijving;
        this.savedDate = savedDate;

    }

    public float getGewerkteUren() {
        return gewerkteUren;
    }

    public String getOmschrijving() {
        return omschrijving;
    }
}

abstract class Factuur {
    protected ArrayList<Dag> dagen = new ArrayList<>();
    protected ArrayList<Werkgever> werkgevers = new ArrayList<>();
    protected ArrayList<Project> projecten = new ArrayList<>();

    public Factuur(ArrayList<Dag> dagen, ArrayList<Werkgever> werkgevers, ArrayList<Project> projecten) {
        this.dagen.addAll(dagen);
        this.werkgevers.addAll(werkgevers);
        this.projecten.addAll(projecten);
    }
}

class MaandOverzicht extends Factuur {
    public MaandOverzicht(ArrayList<Dag> dagen, ArrayList<Werkgever> werkgevers, ArrayList<Project> projecten) {
        super(dagen, werkgevers, projecten);
    }

    public void printDagenVanDeMaand(Gebruiker gebruiker,int jaar, int maand, Werkgever werkgever, Project project) {
        System.out.printf("Maandoverzicht voor %d-%d \n Werkgever: %s \n Project: %s%n", jaar, maand, werkgever.getNaam(), project.getNaam());

        float totaalGewerkteUren = 0;
        for (Dag dag : dagen) {
            LocalDate date = dag.getSavedDate();
            if (date.getYear() == jaar && date.getMonthValue() == maand) {
                System.out.println("--------------------------------------------");
                System.out.println("Datum: " + date);
                System.out.println("Gewerkte uren: " + dag.getGewerkteUren());
                System.out.println("Omschrijving: " + dag.getOmschrijving());
                System.out.println("Uurloon: " + project.getUurLoon());
                System.out.println("--------------------------------------------");
                totaalGewerkteUren += dag.getGewerkteUren();
            }
        }

        float uurLoon = gebruiker.getProjecten().isEmpty() ? 0 : gebruiker.getProjecten().get(0).getUurLoon();

        float totaalVerdient = totaalGewerkteUren * uurLoon;

        System.out.println("Totaal aantal gewerkte uren: " + totaalGewerkteUren);
        System.out.println("Totaal verdient deze maand: " + totaalVerdient);
    }
}

class JaarOverzicht extends Factuur {
    public JaarOverzicht(ArrayList<Dag> dagen, ArrayList<Werkgever> werkgevers, ArrayList<Project> projecten) {
        super(dagen, werkgevers, projecten);
    }

    public void printDagenVanHetJaar(Gebruiker gebruiker, int jaar) {
        System.out.println("Jaaroverzicht voor " + jaar);

        float totaalGewerkteUren = 0;
        for (Dag dag : dagen) {
            LocalDate date = dag.getSavedDate();
            if (date.getYear() == jaar) {
                System.out.println("Datum: " + date);
                System.out.println("Gewerkte uren: " + dag.getGewerkteUren());
                System.out.println("Omschrijving: " + dag.getOmschrijving());
                System.out.println();
                totaalGewerkteUren += dag.getGewerkteUren();
            }
        }

        float uurLoon = gebruiker.getProjecten().isEmpty() ? 0 : gebruiker.getProjecten().get(0).getUurLoon();

        float totaalVerdient = totaalGewerkteUren * uurLoon;

        System.out.println("Totaal aantal gewerkte uren: " + totaalGewerkteUren);
        System.out.println("Totaal verdient deze maand: " + totaalVerdient);
    }
}


class Data {
    // Read from file
    public Gebruiker getData() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("data.dat"))) {
            Gebruiker gebruiker = (Gebruiker) input.readObject();
            System.out.println("Data gelezen");
            return gebruiker;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return null;
    }

    // Write to file
    public void writeData(Gebruiker gebruiker) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("data.dat"))) {
            output.writeObject(gebruiker);
            System.out.println("Data opgeslagen.");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

class Start{
    Scanner scanner = new Scanner(System.in);
    Data data = new Data();

    public void startProgram(Gebruiker gebruiker){
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Voeg dagen toe");
            System.out.println("2. Voeg werkgever toe");
            System.out.println("3. Voeg project toe");
            System.out.println("4. Genereer een maand overzicht");
            System.out.println("5. Genereer een jaar overzicht");

            System.out.println("0. Exit");

            System.out.print("Maak een keuze: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    //Voeg dag toe
                    System.out.println("Hoeveel uren heb je gewerkt: ");
                    float gewerkteUren = scanner.nextFloat();
                    scanner.nextLine();

                    System.out.println("Geef een kleine omschrijving van je dag: ");
                    String omschrijving = scanner.nextLine();

                    System.out.println("Voer de datum in (YYYY-MM-DD): ");
                    String inputDate = scanner.nextLine();
                    LocalDate savedDate = LocalDate.parse(inputDate);

                    gebruiker.addDag(new Dag(gewerkteUren, omschrijving, savedDate));

                    data.writeData(gebruiker);
                    break;

                case "2":
                    // Voeg Werkgever toe
                    System.out.println("Enter werkgever details:");
                    System.out.print("Naam: ");
                    String naam = scanner.nextLine();
                    System.out.print("Hoe ver weg is het?: ");
                    float kilometers = Float.parseFloat(scanner.nextLine());

                    gebruiker.addWerkgever(new Werkgever(naam, kilometers));

                    data.writeData(gebruiker);
                    break;

                case "3":
                    // Voeg Project toe
                    System.out.println("Voer het project naam in: ");
                    String projectNaam = scanner.nextLine();

                    System.out.println("Heb je een uurloon of een vastprijs");
                    System.out.println("1. Uurloon");
                    System.out.println("2. Vastprijs");

                    String loonChoice = scanner.nextLine();

                    float uurloon;
                    float vastprijs;

                    switch (loonChoice) {
                        case "1":
                            System.out.print("Voer uurloon in: ");
                            uurloon = scanner.nextFloat();
                            gebruiker.addProject( new Project(projectNaam, uurloon));
                            break;
                        case "2":
                            System.out.print("Voer vastprijs in: ");
                            vastprijs = scanner.nextFloat();
                            gebruiker.addProject(new Project(projectNaam, vastprijs, false));
                            break;
                        default:
                            System.out.println("Error: Voer 1 of 2 in");
                            break;
                    }
                        data.writeData(gebruiker);
                    break;

                case "4":
                    MaandOverzicht maandOverzicht = new MaandOverzicht(gebruiker.getDagen(), gebruiker.getWerkgevers(), gebruiker.getProjecten());

                    Werkgever werkgever = gebruiker.selectWerkgever(scanner);
                    Project project = gebruiker.selectProject(scanner);


                    System.out.println("Voer een maand in: ");
                    int maand = scanner.nextInt();

                    System.out.println("Voer een jaar in: ");
                    int jaar = scanner.nextInt();

                    maandOverzicht.printDagenVanDeMaand(gebruiker, jaar, maand, werkgever ,project);
                    break;


                case "5":
                    JaarOverzicht jaarOverzicht = new JaarOverzicht(gebruiker.getDagen(), gebruiker.getWerkgevers(), gebruiker.getProjecten());

                    System.out.println("Voer een jaar in: ");
                    int jaar1 = scanner.nextInt();

                    jaarOverzicht.printDagenVanHetJaar(gebruiker,jaar1);
                    break;

                case "0":
                    // Exit
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Data data = new Data();
        Start start = new Start();
        ArrayList<Gebruiker> gebruikers = new ArrayList<>();

        Gebruiker maarten = new Gebruiker("12345");
        gebruikers.add(maarten);

        System.out.println("Voer je inlog naam in: ");
        String inlogNaam = scanner.nextLine();

        // Lees eerst data
        // Start dan programma
        for (Gebruiker gebruiker : gebruikers) {
            if (gebruiker.isGebruiker(inlogNaam)) {
                Gebruiker loadedGebruiker = data.getData();
                if (loadedGebruiker != null) {
                    gebruiker = loadedGebruiker;
                }
                start.startProgram(gebruiker);
            }
        }
    }
}