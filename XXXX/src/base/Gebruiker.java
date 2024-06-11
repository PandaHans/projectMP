package src.base;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Gebruiker implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String inlogNaam;
    private ArrayList<Client> clienten = new ArrayList<>();

    public Gebruiker(String inlogNaam) {
        this.inlogNaam = inlogNaam;
    }

    public boolean isGebruiker(String inlogNaam){
        return this.inlogNaam.equals(inlogNaam);
    }
    public String getInlogNaam() {
        return inlogNaam;
    }
    public void setInlogNaam(String inlogNaam) {
        this.inlogNaam = inlogNaam;
    }
    public ArrayList<Client> getClienten() {
        return clienten;
    }
    public void setClienten(ArrayList<Client> clienten) {
        this.clienten = clienten;
    }

    public ArrayList<Client> getClient() {
        return clienten;
    }
    public void addClient(Client client){
        // kijk of client al bestaat
        boolean exists = false;
        for (Client bestaandeClient : clienten) {
            if (bestaandeClient.getClientNaam().equals(client.getClientNaam())) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            clienten.add(client);
            System.out.println("Client toegevoegd!");
        } else {
            System.out.println("Client bestaat al.");
        }
    }
    public String getClientNaam(int i){
        return clienten.get(i).getClientNaam();
    }

    public Client selectClient() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selecteer een Client:");
        for (int i = 0; i < getClienten().size(); i++) {
            System.out.println(i + ". " + getClientNaam(i));
        }

        System.out.print("Maak een keuze: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 0 && choice < getClienten().size()) {
            return getClienten().get(choice);
        } else {
            System.out.println("Ongeldige keuze voor client.");
            return null;
        }
    }
}
