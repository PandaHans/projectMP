import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

class Gebruiker implements Serializable {

    private static final long serialVersionUID = 1L;
    private String inlogNaam;
    private ArrayList<Werkgever> werkgevers = new ArrayList<>();
    private ArrayList<Dag> dagen = new ArrayList<>();

    public Gebruiker() {

    }

    public Gebruiker(String inlogNaam) {
        this.inlogNaam = inlogNaam;
    }

    public ArrayList<Werkgever> getWerkgevers() {
        return werkgevers;
    }

    public void addWerkgever(Werkgever werkgever){
        werkgevers.add(werkgever);
    }

    public ArrayList<Dag> getDagen() {
        return dagen;
    }

    public Dag getDag(int i){
        return dagen.get(i);
    }

    public void addDag(Dag dag){
        dagen.add(dag);
    }

    public boolean isGebruiker(String inlogNaam){
        return this.inlogNaam.equals(inlogNaam);
    }


    @Override
    public String toString() {
        return "Gebruiker{" +
                "inlogNaam='" + inlogNaam + '\'' +
                ", werkgevers=" + werkgevers +
                ", dagen=" + dagen +
                '}';
    }
}

class Werkgever implements Serializable {
    private static final long serialVersionUID = 1L;
    private String naam;
    private float kiloMeters;
    private float uurLoon;
    private float vastPrijs;

    // Factuur Adress

    public float getUurLoon() {
        return uurLoon;
    }
    public void setUurLoon(float uurLoon) {
        this.uurLoon = uurLoon;
    }

    public float getVastPrijs() {
        return vastPrijs;
    }
    public void setVastPrijs(float vastPrijs) {
        this.vastPrijs = vastPrijs;
    }

    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Werkgever(String naam, float kiloMeters, float uurLoon, float vastPrijs) {
        this.naam = naam;
        this.kiloMeters = kiloMeters;
        this.uurLoon = uurLoon;
        this.vastPrijs = vastPrijs;
    }

    @Override
    public String toString() {
        return "Werkgever = "+ naam + " Kilometers = " + kiloMeters + " Uurloon = " + uurLoon + " Vastprijs = " + vastPrijs;
    }
}

class Project implements Serializable {
    // Naam
    // Uurloon true or false
    // Uurloon
    // VastePrijs
}

class Dag implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient DateTimeFormatter dtf;
    private float gewerkteUren;
    private String locatie;
    private transient LocalDateTime now;

    public Dag(float gewerkteUren, String locatie) {
        this.gewerkteUren = gewerkteUren;
        this.locatie = locatie;
        initializeDateTime(); // Initialize transient fields
    }

    private void initializeDateTime() {
        this.dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.now = LocalDateTime.now();
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); // Perform default deserialization
        initializeDateTime(); // Initialize transient fields after deserialization
    }

    @Override
    public String toString() {
        return "Datum = " + dtf.format(now) + " Gewerkteuren = " + gewerkteUren + " locatie = " + locatie;
    }
}

class Maand implements Serializable {

}

class Data{
    //read from file
    public void getData(Gebruiker gebruiker){
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("data.dat"))) {
            gebruiker = (Gebruiker) input.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    //write to file
    public void writeData(Gebruiker gebruiker){
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("data.dat"))) {
            output.writeObject(gebruiker);
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
        // Tab selection loop
        while (true) {


            // Verander werkgever naar opdrachtgever
            // Locatie veranderen naar klant en locatie eronder te zetten

            // 1.0 Voer uren in
                // 1.1 Selecteer werkgever                  Hergebruik voor 3 en 5
                    // Komt lijst met alle werkgevers
                    // 1.1.1 Noble-
                        //project
                    // 1.1.2 Alta
                // 1.2 Kies dag
                    // 1.2.1 Dag van vandaag
                    // 1.2.2 Of een andere dag
                // 1.3 Kies aantal uur
                    // in float
                // 1.4 Schrijf omschrijving
                    // String

            // 2.0 Pas uren aan
                // W.I.P.

            // 3.0 Genereer overzicht
                // Maand overzicht
                // nog naar kijken

            // 4.0 Werkgever
                // Voeg werkgever toe
                    //Naam
                    //Contact gegevens
                    //Kilometers
                // Wijzig gegevens Werkgever

            // 5.0 Project
                // 5.1 Voeg project toe
                    //5.1.1 Selecteer werkgever
                        //Lijst met alle werkgevers kijk 1.1
                        // voeg naam toe
                        // Uurloon ja of nee
                        // Vasteprijs:
                        // Uurloon:
                // 5.2 Wijzig gegevens Project

            System.out.println("Select an option:");
            System.out.println("1. Add Werkgever");
            System.out.println("2. Add Dag");
            System.out.println("3. Show dagen");
            System.out.println("0. Exit");

            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Add Werkgever
                    System.out.println("Enter werkgever details:");
                    System.out.print("Naam: ");
                    String naam = scanner.nextLine();
                    System.out.print("Kilometers: ");
                    float kilometers = Float.parseFloat(scanner.nextLine());
                    System.out.print("Uurloon: ");
                    float uurloon = Float.parseFloat(scanner.nextLine());
                    System.out.print("Vastprijs: ");
                    float vastprijs = Float.parseFloat(scanner.nextLine());

                    gebruiker.addWerkgever(new Werkgever(naam, kilometers, uurloon, vastprijs));
                    System.out.println("Werkgever added successfully!");
                    data.writeData(gebruiker);
                    break;

                case "2":
                    // Eerst select Werkgever dan add dag
                    System.out.println("Enter dag details:");
                    System.out.print("Gewerkte uren: ");
                    float gewerkteUren = Float.parseFloat(scanner.nextLine());
                    System.out.print("Locatie: ");
                    String locatie = scanner.nextLine();

                    gebruiker.addDag(new Dag(gewerkteUren, locatie));
                    System.out.println("Dag added successfully!");
                    data.writeData(gebruiker);
                    break;

                case "3":
                    // Remove dag

                    break;

                case "0":
                    // Exit
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice. Please select again.");
            }
            if (choice.equals("0")) {
                break;
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

        for (Gebruiker gebruiker : gebruikers) {
            if (gebruiker.isGebruiker(inlogNaam)) {
                data.getData(gebruiker);
                start.startProgram(gebruiker);
                data.writeData(gebruiker);
            }
        }
    }
}
