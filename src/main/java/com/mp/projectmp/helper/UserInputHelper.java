package com.mp.projectmp.helper;

import com.mp.projectmp.base.Client;
import com.mp.projectmp.base.Gebruiker;
import com.mp.projectmp.base.Project;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInputHelper implements UserInputInterface{
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

}