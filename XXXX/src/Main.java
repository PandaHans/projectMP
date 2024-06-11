package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
/*
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
}*/

/*
//Composite design pattern
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

    private ArrayList<ClientComponent> components = new ArrayList<>();

    private String clientNaam;
    private float kiloMeters;

    public Client(String clientNaam, float kiloMeters) {
        this.clientNaam = clientNaam;
        this.kiloMeters = kiloMeters;
    }

    public String getClientNaam() {
        return clientNaam;
    }
    public float getKiloMeters() {
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

    public float getKiloMeters(){
        return kiloMeters;
    }

    public ClientComponent getComponent(int componentIndex) {
        return components.get(componentIndex);
    }
    public String getClientNaam() {
        return clientNaam;
    }


    public void displayClientInfo() {
        System.out.println(getClientNaam() + " " + getKiloMeters() + "\n");
        for (ClientComponent clientInfo : components) {
            clientInfo.displayClientInfo();
        }
        Iterator<ClientComponent> clientIterator = components.iterator();
        while (clientIterator.hasNext()) {
            ClientComponent clientInfo = clientIterator.next();
            clientInfo.displayClientInfo();
        }

    }
}
class Project extends ClientComponent implements Serializable {
    private String projectNaam;
    private float projectUurloon;
    private ArrayList<Dag> dagen = new ArrayList<>();

    public Project(String projectNaam, float projectUurloon) {
        this.projectNaam = projectNaam;
        this.projectUurloon = projectUurloon;
    }


    public String getProjectNaam() {
        return projectNaam;
    }
    public float getProjectUurloon() {
        return projectUurloon;
    }
    public ArrayList<Dag> getDagen() {
        return dagen;
    }
    public void addDag(Dag dag){
        dagen.add(dag);
    }

    public void displayProjectInfo() {
        System.out.println(getProjectNaam() + " " + getProjectUurloon() + "\n");
        for (Dag dag : dagen) {
            System.out.println("    Dag: " + dag.getOmschrijving() + ", Uren: " + dag.getGewerkteUren());
        }
    }
}
*/

class Gebruiker implements Serializable {
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
class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String clientNaam;
    private float kiloMeters;
    private ArrayList<Project> projecten = new ArrayList<>();

    public Client(String clientNaam, float kiloMeters) {
        this.clientNaam = clientNaam;
        this.kiloMeters = kiloMeters;
    }

    public void setClientNaam(String clientNaam) {
        this.clientNaam = clientNaam;
    }
    public void setKiloMeters(float kiloMeters) {
        this.kiloMeters = kiloMeters;
    }
    public void setProjecten(ArrayList<Project> projecten) {
        this.projecten = projecten;
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
            if (bestaandProject.getProjectNaam().equals(project.getProjectNaam())) {
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
        return projecten.get(i).getProjectNaam();
    }
    public Project selectProject() {
        Scanner scanner = new Scanner(System.in);
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
    public String getClientNaam() {
        return clientNaam;
    }
}
class Project implements Serializable {
    private String projectNaam;
    private float uurLoon;
    private ArrayList<Dag> dagen = new ArrayList<>();

    public Project(String projectNaam, float uurLoon) {
        this.projectNaam = projectNaam;
        this.uurLoon = uurLoon;
    }
    public void setDagen(ArrayList<Dag> dagen) {
        this.dagen = dagen;
    }
    public void setProjectNaam(String projectNaam) {
        this.projectNaam = projectNaam;
    }
    public void setUurLoon(float uurLoon) {
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
    public String getProjectNaam() {
        return projectNaam;
    }
}

//Factory method design pattern
abstract class Dag implements Serializable {
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

//Template design pattern
abstract class Invoice {
    final void makeInvoice(Client client, Project project){
        int maand = getMaand();
        int jaar = getJaar();
        addHeader();
        if (wantsDagen()){
            addDagen(client, project, maand, jaar);
        }
        if (wantsClient()){
            addClient(client);
        }
        if (wantsProject()){
            addProject(project);
        }
    }

    abstract void addDagen(Client client, Project project, int maand, int jaar);
    abstract void addClient(Client client);
    abstract void addProject(Project project);

    boolean wantsDagen() {return true;}
    boolean wantsClient() {return true;}
    boolean wantsProject() {return true;}

    public void addHeader(){
        System.out.println("Description of activities\n");
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

    public void printTotals(Client client, float totaalGewerkteUren, float totaalGeredenKiloMeters) {
        float uurLoon = client.getProjecten().isEmpty() ? 0 : client.getProjecten().getFirst().getUurLoon(); // assuming getProjecten() and getUurLoon() exist in Client and Project classes
        float totaalVerdiend = totaalGewerkteUren * uurLoon;

        System.out.println("Totaal gereden kilometers: " + totaalGeredenKiloMeters);
        System.out.println("Totaal aantal gewerkte uren: " + totaalGewerkteUren);
        System.out.println("Totaal verdiend: " + totaalVerdiend + "\n");
        System.out.println();
    }
/*
//////////////////////////////////////////////
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
    public void addDagen(Client client, Project project, int maand, int jaar){
        float totaalGewerkteUren = 0;
        float totaalGeredenKiloMeters = 0;

        for (Dag dag : project.getDagen()) {
            LocalDate date = dag.getSavedDate();
            if (date.getYear() == jaar && date.getMonthValue() == maand) {
                System.out.println("Datum: " + date);
                System.out.println("Gewerkte uren: " + dag.getGewerkteUren());
                System.out.println("Omschrijving: " + dag.getOmschrijving() + "\n");

                totaalGeredenKiloMeters += client.getKiloMeters();
                totaalGewerkteUren += dag.getGewerkteUren();
            }
        }
            printTotals(client, totaalGewerkteUren, totaalGeredenKiloMeters);
    }


    public void addClient(Client client) {
        System.out.println(client.getClientNaam());
    }
    public void addProject(Project project) {
        System.out.println(project.getProjectNaam());
    }
}
class JaarInvoice extends Invoice  {
    public void addDagen(Client client, Project project, int maand, int jaar){
    float totaalGewerkteUren = 0;
    float totaalGeredenKiloMeters = 0;

    for (Dag dag : project.getDagen()) {
        LocalDate date = dag.getSavedDate();
        if (date.getYear() == jaar) {
            System.out.println("Datum: " + date);
            System.out.println("Gewerkte uren: " + dag.getGewerkteUren());
            System.out.println("Omschrijving: " + dag.getOmschrijving());

            totaalGeredenKiloMeters += client.getKiloMeters();
            totaalGewerkteUren += dag.getGewerkteUren();
        }
    }
    printTotals(client, totaalGewerkteUren, totaalGeredenKiloMeters);
}


    public void addClient(Client client) {
        System.out.println(client.getClientNaam());
    }
    public void addProject(Project project) {
        System.out.println(project.getProjectNaam());
    }
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
            System.out.println("2. Voeg client toe");
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
                    Client client = gebruiker.selectClient();
                    Project project = client.selectProject();

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
                    // Voeg Client toe
                    System.out.println("Enter client details:");
                    System.out.print("Naam: ");
                    String naam = scanner.nextLine();

                    System.out.print("Hoe ver weg is het?: ");
                    float kilometers = Float.parseFloat(scanner.nextLine());

                    gebruiker.addClient(new Client(naam, kilometers));
                    dataWriter.writeData(gebruiker);
                    break;

                case "3":
                    // Voeg Project toe
                    Client client1 = gebruiker.selectClient();

                    System.out.println("Voer het project naam in: ");
                    String projectNaam = scanner.nextLine();

                    System.out.print("Voer uurloon in: ");
                    float uurloon = scanner.nextFloat();

                    client1.addProject(new Project(projectNaam, uurloon));

                    dataWriter.writeData(gebruiker);
                    break;

                case "4":
                    Invoice maandInvoice = new MaandInvoice();
                    Client client2 = gebruiker.selectClient();
                    Project project2 = client2.selectProject();
                    maandInvoice.makeInvoice(client2, project2);
                    break;

                case "5":
                    Invoice jaarInvoice = new JaarInvoice();
                    Client client3 = gebruiker.selectClient();
                    Project project3 = client3.selectProject();
                    jaarInvoice.makeInvoice(client3, project3);
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

// connent Dag aan project terwijl kijken naar umls van video
// Start class veranderen
// invoice class invullen
// alle classes public maken
// javafx toevoegen
// Kijken naar userclass?
// comments toevoegen bij design patterns
// kijken naar sigrid
// kijken naar solid principles
// kijken naar data save method
// alles nl veranderen

/*class UserInput {
    private Scanner scanner;

    public UserInput() {
        this.scanner = new Scanner(System.in);
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
        System.out.print(prompt);
        while (!scanner.hasNextFloat()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        float input = scanner.nextFloat();
        scanner.nextLine(); // consume newline
        return input;
    }
}*/
/*class Start {
    public static void startProgram(Gebruiker gebruiker) {
        DataWriter dataWriter = new DataWriter();
        UserInput userInput = new UserInput();
        DagFactory dagFactory = DagFactory.getInstance();

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Voeg dagen toe");
            System.out.println("2. Voeg werkgever toe");
            System.out.println("3. Voeg project toe");
            System.out.println("4. Genereer een maand overzicht");
            System.out.println("5. Genereer een jaar overzicht");
            System.out.println("0. Exit");
            String choice = userInput.getStringInput("Maak een keuze: ");

            switch (choice) {
                case "1":
                    Client client = gebruiker.selectWerkgever(userInput);
                    Project project = client.selectProject(userInput);

                    float gewerkteUren = userInput.getFloatInput("Hoeveel uren heb je gewerkt: ");
                    String omschrijving = userInput.getStringInput("Geef een kleine omschrijving van je dag: ");
                    String inputDate = userInput.getStringInput("Voer de datum in (YYYY-MM-DD): ");
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
                    String naam = userInput.getStringInput("Enter werkgever details:\nNaam: ");
                    float kilometers = userInput.getFloatInput("Hoe ver weg is het?: ");
                    gebruiker.addWerkgever(new Client(naam, kilometers));
                    dataWriter.writeData(gebruiker);
                    break;

                case "3":
                    // Voeg Project toe
                    Client client1 = gebruiker.selectWerkgever(userInput);
                    String projectNaam = userInput.getStringInput("Voer het project naam in: ");
                    float uurloon = userInput.getFloatInput("Voer uurloon in: ");
                    client1.addProject(new Project(projectNaam, uurloon));
                    dataWriter.writeData(gebruiker);
                    break;

                case "4":
                    MaandInvoice maandInvoice = new MaandInvoice();
                    Client client2 = gebruiker.selectWerkgever(userInput);
                    Project project2 = client2.selectProject(userInput);
                    int jaar = userInput.getIntInput("Voer een jaar in: ");
                    int maand = userInput.getIntInput("Voer een maand in: ");
                    maandInvoice.makeInvoice();
                    break;

                case "5":
                    JaarInvoice jaarInvoice = new JaarInvoice();
                    Client client3 = gebruiker.selectWerkgever(userInput);
                    Project project3 = client3.selectProject(userInput);
                    int jaar1 = userInput.getIntInput("Voer een jaar in: ");
                    jaarInvoice.makeInvoice();
                    break;

                case "0":
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }
}*/

// https://www.newthinktank.com/2012/09/factory-design-pattern-tutorial/
// https://www.newthinktank.com/2012/10/template-method-design-pattern-tutorial/
// https://www.newthinktank.com/2012/10/composite-design-pattern-tutorial/
// https://www.youtube.com/watch?v=9XnsOpjclUg&list=PLF206E906175C7E07&index=9
// https://www.youtube.com/watch?v=vNHpsC5ng_E&list=PLF206E906175C7E07
