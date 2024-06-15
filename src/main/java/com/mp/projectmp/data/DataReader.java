package com.mp.projectmp.data;

import com.mp.projectmp.base.Gebruiker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataReader implements DataReaderInterface {
    public Gebruiker readData() {
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
}
