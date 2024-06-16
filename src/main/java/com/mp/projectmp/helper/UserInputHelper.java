package com.mp.projectmp.helper;

import com.mp.projectmp.base.Client;
import com.mp.projectmp.base.Gebruiker;
import com.mp.projectmp.base.LoonType;
import com.mp.projectmp.base.Project;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;

public class UserInputHelper{
    private final Scanner scanner;

    public UserInputHelper() {
        this.scanner = new Scanner(System.in);
    }

    public int getJaar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voer het jaar in: ");
        return scanner.nextInt();
    }
    public int getMaand() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voer de maand in: ");
        return scanner.nextInt();
    }
    public LocalDate getDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voer de datum in (YYYY-MM-DD): ");
        return LocalDate.parse(scanner.next());
    }

    public int getIntInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }
    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    public float getFloatInput(String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextFloat()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        float input = scanner.nextFloat();
        scanner.nextLine(); // consume newline
        return input;
    }

    public Client selectClient(Gebruiker gebruiker) {
        System.out.println("Selecteer een Client:");
        for (int i = 0; i < gebruiker.getClienten().size(); i++) {
            System.out.println(i + ". " + gebruiker.getClientNaam(i));
        }

        System.out.print("Maak een keuze: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        if (choice >= 0 && choice < gebruiker.getClienten().size()) {
            return gebruiker.getClienten().get(choice);
        } else {
            System.out.println("Ongeldige keuze voor client.");
            return null;
        }

    }
    public Project selectProject(Client client) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecteer een project:");
        for (int i = 0; i < client.getProjecten().size(); i++) {
            System.out.println(i + ". " + client.getProjectNaam(i));
        }

        System.out.print("Maak een keuze: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 0 && choice < client.getProjecten().size()) {
            return client.getProjecten().get(choice);
        } else {
            System.out.println("Ongeldige keuze voor project.");
            return null;
        }
    }

    public LoonType kiesLoonType(){
        System.out.println("Kies loontype: ");

        System.out.println("1. uurloon");
        System.out.println("2. vasteprijs");

        int choice = getIntInput("Uw keuze: ");
        LoonType loonType = null;

        switch (choice) {
            case 1:
                loonType = LoonType.UURLOON;
                break;
            case 2:
                loonType = LoonType.VASTEPRIJS;
                break;
            // Add other cases for different wage types
            default:
                System.out.println("Ongeldige keuze");
        }
        // error als je 3 bv geeft

        if (loonType != null) {
            float loon = getFloatInput("Voer het loonbedrag in: ");
            loonType = new LoonType(loonType.getType(), loon);
        }
        return loonType;
    }
}