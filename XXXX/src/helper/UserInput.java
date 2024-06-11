package src.helper;

import java.time.LocalDate;
import java.util.Scanner;
public class UserInput {
    private final Scanner scanner;

    public UserInput() {
        this.scanner = new Scanner(System.in);
    }

    public int getJaar(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voer het jaar in: ");
        return scanner.nextInt();
    }
    public int getMaand(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voer de maand in: ");
        return scanner.nextInt();
    }
    public LocalDate getDate(){
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
}