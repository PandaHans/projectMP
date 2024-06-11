package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

class Gebruiker implements Serializable {
    private static final long serialVersionUID = 1L;
    private String inlogNaam;
    private ArrayList<ClientComponent> clients = new ArrayList<>();

    public Gebruiker(String inlogNaam) {
        this.inlogNaam = inlogNaam;
    }

    public boolean isGebruiker(String inlogNaam){
        return this.inlogNaam.equals(inlogNaam);
    }

    public ArrayList<ClientComponent> getWerkgevers() {
        return clients;
    }

    public void addWerkgever(ClientComponent client){
        clients.add(client);
        System.out.println("Werkgever toegevoegd!");
    }

    public String getWerkgeverNaam(int i){
        return clients.get(i).getName();
    }

    public ClientComponent selectWerkgever(Scanner scanner) {
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
}

abstract class ClientComponent {
    // We throw UnsupportedOperationException so that if
    // it doesn't make sense for a song, or song group
    // to inherit a method they can just inherit the
    // default implementation
    public void add(ClientComponent newClientComponent) {

        throw new UnsupportedOperationException();

    }
    public void remove(ClientComponent newClientComponent) {

        throw new UnsupportedOperationException();

    }
    public ClientComponent getComponent(int componentIndex) {

        throw new UnsupportedOperationException();

    }
    public String getName() {

        throw new UnsupportedOperationException();

    }
    public void displayClientInfo() {

        throw new UnsupportedOperationException();

    }
}
class Client extends ClientComponent implements Serializable {
    private static final long serialVersionUID = 1L;
    private String naam;
    private float kiloMeters;
    private ArrayList<ClientComponent> components = new ArrayList<>();

    public Client(String naam, float kiloMeters) {
        this.naam = naam;
        this.kiloMeters = kiloMeters;
    }
    public float getKiloMeters(){
        return kiloMeters;
    }
    public void add(ClientComponent newComponent) {
        components.add(newComponent);
    }
    public void remove(ClientComponent newComponent) {
        components.remove(newComponent);
    }
    public ClientComponent getComponent(int componentIndex) {
        return components.get(componentIndex);
    }
    public String getName() {
        return naam;
    }

    public void displayInfo() {
        System.out.println("Client: " + getName());
        for (ClientComponent component : components) {
            component.displayClientInfo();
        }
    }
}
class Project extends ClientComponent implements Serializable {
    private String naam;
    private float uurLoon;
    private ArrayList<Dag> dagen = new ArrayList<>();

    public Project(String naam, float uurLoon) {
        this.naam = naam;
        this.uurLoon = uurLoon;
    }

    public String getName() {
        return naam;
    }
    public float getUurLoon() {
        return uurLoon;
    }
    public ArrayList<Dag> getDagen() {
        return dagen;
    }
    public void addDag(Dag dag){
        dagen.add(dag);
    }

    public void displayInfo() {
        System.out.println("Project: " + getName() + ", Uurloon: " + getUurLoon());
        for (Dag dag : dagen) {
            System.out.println("    Dag: " + dag.getOmschrijving() + ", Uren: " + dag.getGewerkteUren());
        }
    }
}

/*class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String naam;
    private float kiloMeters;
    private ArrayList<Project> projecten = new ArrayList<>();

    public Client(String naam, float kiloMeters) {
        this.naam = naam;
        this.kiloMeters = kiloMeters;
    }
    public float getKiloMeters(){
        return kiloMeters;
    }
    public ArrayList<Project> getProjecten() {
        return projecten;
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
    public String getProjectNaam(int i) {
        return projecten.get(i).getNaam();
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
    public String getNaam() {
        return naam;
    }
}*/
/*class Project implements Serializable {
    private String naam;
    private float uurLoon;
    private ArrayList<Dag> dagen = new ArrayList<>();

    public Project(String naam, float uurLoon) {
        this.naam = naam;
        this.uurLoon = uurLoon;
    }

    public ArrayList<Dag> getDagen() {
        return dagen;
    }
    public void addDag(Dag dag){
        dagen.add(dag);
    }

    public float getUurLoon() {
        return uurLoon;
    }
    public String getNaam() {
        return naam;
    }
}*/
/*
class Dag implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private float gewerkteUren;
    private String omschrijving;
    LocalDate savedDate;

    public Dag(float gewerkteUren, String omschrijving, LocalDate savedDate) {
        this.gewerkteUren = gewerkteUren;
        this.omschrijving = omschrijving;
        this.savedDate = savedDate;
    }

    public LocalDate getSavedDate() {
        return savedDate;
    }
    public float getGewerkteUren() {
        return gewerkteUren;
    }
    public String getOmschrijving() {
        return omschrijving;
    }

    public void setGewerkteUren(float gewerkteUren) {
        this.gewerkteUren = gewerkteUren;
    }
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
    public void setSavedDate(LocalDate savedDate) {
        this.savedDate = savedDate;
    }
}
*/

abstract class Dag {
    private float gewerkteUren;
    private String omschrijving;
    LocalDate savedDate;

    public LocalDate getSavedDate() {
        return savedDate;
    }
    public float getGewerkteUren() {
        return gewerkteUren;
    }
    public String getOmschrijving() {
        return omschrijving;
    }

    public void setGewerkteUren(float gewerkteUren) {
        this.gewerkteUren = gewerkteUren;
    }
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
    public void setSavedDate(LocalDate savedDate) {
        this.savedDate = savedDate;
    }
}
class NormaleDag extends Dag{
    public NormaleDag(){
        setGewerkteUren(6.0f);
        setSavedDate(LocalDate.now());
        setOmschrijving("Basic Omschrijving");
    }
}
class AparteDag extends Dag{
    public AparteDag(){
        setGewerkteUren(0f);
        setSavedDate(LocalDate.now());
        setOmschrijving("Basic Omschrijving");
    }
}
class DagFactory{
    public Dag maakDag(String nieuweDagType){
        Dag nieuweDag = null;
        if (nieuweDagType.equals("N")){
            return new NormaleDag();
        } else if (nieuweDagType.equals("A")) {
            return new AparteDag();
        }else return null;
    }
}

abstract class Invoice {
    final void makeInvoice(){
        addHeader();
        if (wantsDagen()){
            addDagen();
        }
        if (wantsClient()){
            addClient();
        }
        if (wantsProject()){
            addProject();
        }
    }

    abstract void addClient();
    abstract void addDagen();
    abstract void addProject();

    boolean wantsDagen() {return true;}
    boolean wantsClient() {return true;}
    boolean wantsProject() {return true;}

    public void addHeader(){
        System.out.println("Description of activities\n");
    }
/*
    final void makeInvoice(int jaar, int maand, Client client, Project project) {
        addAddress(client);
        addProjectDetails(client, project);

    }

    abstract void addAddress(Client client);
    abstract void addProjectDetails(Client client, Project project);
    abstract void addDays(int jaar, int maand, Project project);
*/
}
class MaandInvoice extends Invoice {
    void addClient() {    }
    void addDagen() {

    }
    void addProject() {

    }
/*
    void addAddress(Client client) {
        System.out.println("Werkgever: " + client.getNaam());
    }
    void addProjectDetails(Client client, Project project) {
        System.out.println("Project: " + project.getNaam());
    }
    void addDays(int jaar, int maand, Project project) {
        float totaalGewerkteUren = 0;
        float totaalGeredenKiloMeters = 0;

        System.out.println("Dagen in maand " + maand + " van " + jaar + ":");
        for (Dag dag : project.getDagen()) {
            LocalDate date = dag.getSavedDate();
            if (date.getYear() == jaar && date.getMonthValue() == maand) {
                System.out.println("Datum: " + date);
                System.out.println("Gewerkte uren: " + dag.getGewerkteUren());
                System.out.println("Omschrijving: " + dag.getOmschrijving());

                totaalGeredenKiloMeters += werkgever.getKiloMeters();
                totaalGewerkteUren += dag.getGewerkteUren();
            }
        }
        printTotals(werkgever, totaalGewerkteUren, totaalGeredenKiloMeters);
    }

    private void printTotals(Client client, float totaalGewerkteUren, float totaalGeredenKiloMeters) {
        float uurLoon = client.getProjecten().isEmpty() ? 0 : client.getProjecten().get(0).getUurLoon();
        float totaalVerdiend = totaalGewerkteUren * uurLoon;

        System.out.println("Totaal gereden kilometers: " + totaalGeredenKiloMeters);
        System.out.println("Totaal aantal gewerkte uren: " + totaalGewerkteUren);
        System.out.println("Totaal verdiend: " + totaalVerdiend);
        System.out.println();
    }
*/
}
class JaarInvoice extends Invoice  {
    void addClient() {

    }
    void addDagen() {

    }
    void addProject() {

    }
/*
    void addAddress(Client client) {
        System.out.println("Werkgever: " + client.getNaam());
    }
    void addProjectDetails(Client client, Project project) {
        System.out.println("Project: " + project.getNaam());
    }
    void addDays(int jaar, int maand, Project project) {
        float totaalGewerkteUren = 0;
        float totaalGeredenKiloMeters = 0;

        System.out.println("Dagen in jaar " + jaar + ":");
        for (Dag dag : project.getDagen()) {
            LocalDate date = dag.getSavedDate();
            if (date.getYear() == jaar) {
                System.out.println("Datum: " + date);
                System.out.println("Gewerkte uren: " + dag.getGewerkteUren());
                System.out.println("Omschrijving: " + dag.getOmschrijving());

                totaalGeredenKiloMeters += werkgever.getKiloMeters();
                totaalGewerkteUren += dag.getGewerkteUren();
            }
        }
        printTotals(werkgever, totaalGewerkteUren, totaalGeredenKiloMeters);
    }

    private void printTotals(Client client, float totaalGewerkteUren, float totaalGeredenKiloMeters) {
        float uurLoon = client.getProjecten().isEmpty() ? 0 : client.getProjecten().get(0).getUurLoon();
        float totaalVerdiend = totaalGewerkteUren * uurLoon;

        System.out.println("Totaal gereden kilometers: " + totaalGeredenKiloMeters);
        System.out.println("Totaal aantal gewerkte uren: " + totaalGewerkteUren);
        System.out.println("Totaal verdiend: " + totaalVerdiend);
        System.out.println();
    }
*/
}

class DataReader {
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
class DataWriter{
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

class Start {
    public static void startProgram(Gebruiker gebruiker) {
        DataWriter dataWriter = new DataWriter();
        Scanner scanner = new Scanner(System.in);
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
                    DagFactory dagFactory = new DagFactory();

                    // Voeg dag toe
                    Client client = gebruiker.selectWerkgever(scanner);
                    Project project = client.selectProject(scanner);

                    System.out.println("Hoeveel uren heb je gewerkt: ");
                    float gewerkteUren = scanner.nextFloat();
                    scanner.nextLine();

                    System.out.println("Geef een kleine omschrijving van je dag: ");
                    String omschrijving = scanner.nextLine();

                    System.out.println("Voer de datum in (YYYY-MM-DD): ");
                    String inputDate = scanner.nextLine();
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
                    System.out.println("Enter werkgever details:");
                    System.out.print("Naam: ");
                    String naam = scanner.nextLine();

                    System.out.print("Hoe ver weg is het?: ");
                    float kilometers = Float.parseFloat(scanner.nextLine());

                    gebruiker.addWerkgever(werkgeverFactory.createWerkgever(naam, kilometers));
                    dataWriter.writeData(gebruiker);
                    break;

                case "3":
                    // Voeg Project toe
                    Client client1 = gebruiker.selectWerkgever(scanner);
                    System.out.println("Voer het project naam in: ");
                    String projectNaam = scanner.nextLine();

                    System.out.print("Voer uurloon in: ");
                    float uurloon = scanner.nextFloat();
                    client1.addProject(projectFactory.createProject(projectNaam, uurloon));

                    dataWriter.writeData(gebruiker);
                    break;

                case "4":
                    MaandInvoice maandInvoice = new MaandInvoice();
                    Client client2 = gebruiker.selectWerkgever(scanner);
                    Project project2 = client2.selectProject(scanner);
                    System.out.println("Voer een jaar in: ");
                    int jaar = scanner.nextInt();
                    System.out.println("Voer een maand in: ");
                    int maand = scanner.nextInt();
                    maandInvoice.makeInvoice(jaar, maand, client2, project2);
                    break;

                case "5":
                    JaarInvoice jaarInvoice = new JaarInvoice();
                    Client client3 = gebruiker.selectWerkgever(scanner);
                    Project project3 = client3.selectProject(scanner);
                    System.out.println("Voer een jaar in: ");
                    int jaar1 = scanner.nextInt();
                    jaarInvoice.makeInvoice(jaar1, -1, client3, project3);
                    break;

                case "0":
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataWriter dataWriter = new DataWriter();
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
// alles nl veranderen
// alle classes public maken
// https://www.newthinktank.com/2012/09/factory-design-pattern-tutorial/
// https://www.newthinktank.com/2012/10/template-method-design-pattern-tutorial/
// https://www.newthinktank.com/2012/10/composite-design-pattern-tutorial/
// https://www.youtube.com/watch?v=9XnsOpjclUg&list=PLF206E906175C7E07&index=9
// https://www.youtube.com/watch?v=vNHpsC5ng_E&list=PLF206E906175C7E07