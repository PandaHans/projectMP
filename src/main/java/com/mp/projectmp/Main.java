package com.mp.projectmp;

import com.mp.projectmp.base.Gebruiker;
import com.mp.projectmp.data.DataReader;
import com.mp.projectmp.data.DataWriter;
import com.mp.projectmp.data.DataWriterInterface;
import com.mp.projectmp.helper.UserInputHelper;
import com.mp.projectmp.helper.UserInputInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        DataReader dataReader = new DataReader();

        DataWriterInterface dataWriter = new DataWriter(); // Concrete implementation
        UserInputInterface userInput = new UserInputHelper(); // Concrete implementation

        Start start = new Start(dataWriter, userInput);

        ArrayList<Gebruiker> gebruikers = new ArrayList<>();

        Gebruiker maarten = new Gebruiker("12345");
        gebruikers.add(maarten);

        System.out.println("Voer je inlog naam in: ");
        String inlogNaam = scanner.nextLine();

        Gebruiker currentGebruiker = null;

        for (Gebruiker gebruiker : gebruikers) {
            if (gebruiker.isGebruiker(inlogNaam)) {
                currentGebruiker = dataReader.readData();
                if (currentGebruiker == null) {
                    currentGebruiker = gebruiker;
                }
                break;
            }
        }

        if (currentGebruiker != null) {
            start.startProgram(currentGebruiker);
        } else {
            System.out.println("Gebruiker niet gevonden. Probeer opnieuw.");
        }
    }
}
