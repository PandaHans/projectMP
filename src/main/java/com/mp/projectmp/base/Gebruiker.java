package com.mp.projectmp.base;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Gebruiker implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String inlogNaam;
    private ArrayList<Client> clienten = new ArrayList<>();

    public Gebruiker(String inlogNaam) {
        this.inlogNaam = inlogNaam;
    }

    public boolean isGebruiker(String inlogNaam) {
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
    public void addClient(Client client) {
        if (client.getClientNaam().isEmpty()) {
            System.out.println("Clientgegevens bestaan niet.");
            return;
        }
        if (client.getKiloMeters() <= 0){
            System.out.println("Kilometers mogen niet onder de 0 liggen.");
            return;
        }
        //vheck of al bestaat
        for (Client client1 : clienten) {
            if (client1.getClientNaam().equals(client.getClientNaam())) {
                System.out.println("Client bestaat al.");
                return;
            }
        }

        clienten.add(client);
        System.out.println("Client toegevoegd!");
    }
    public String getClientNaam(int i) {
        return clienten.get(i).getClientNaam();
    }
}
