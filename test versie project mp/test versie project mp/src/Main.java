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
    import java.time.LocalDate;

    class Gebruiker implements Serializable {

        private static final long serialVersionUID = 1L;
        private String inlogNaam;
        private ArrayList<Werkgever> werkgevers = new ArrayList<>();
        private ArrayList<Dag> dagen = new ArrayList<>();

        public Gebruiker(String inlogNaam) {
            this.inlogNaam = inlogNaam;
        }

        public ArrayList<Werkgever> getWerkgevers() {
            return werkgevers;
        }

        public String getWerkgeverNaam(int i){
            return werkgevers.get(i).getNaam();
        }

        public void addWerkgever(Werkgever werkgever){
            werkgevers.add(werkgever);
            System.out.println("Werkgever added successfully!");
        }

        public Werkgever getWerkgeverByName(String naam) {
            for (Werkgever werkgever : werkgevers) {
                if (werkgever.getNaam().equals(naam)) {
                    return werkgever;
                }
            }
            return null; // Return null if the Werkgever with the given name is not found
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

        private ArrayList<Project> projecten = new ArrayList<>();

        public ArrayList<Project> getProjecten() {
            return projecten;
        }
        public void addProject(Project project){
            projecten.add(project);
        }


        public String getNaam() {
            return naam;
        }
        public void setNaam(String naam) {
            this.naam = naam;
        }

        public Werkgever(String naam, float kiloMeters) {
            this.naam = naam;
            this.kiloMeters = kiloMeters;
        }

        @Override
        public String toString() {
            return "Werkgever = "+ naam + " Kilometers = " + kiloMeters ;
        }
    }

    class Project implements Serializable {
        private String naam;
        private Boolean uurLoonTORF;
        private float uurLoon;
        private float vastePrijs;

        public Project(String naam, Boolean uurLoonTORF, float uurLoon, float vastePrijs) {
            this.naam = naam;
            this.uurLoonTORF = uurLoonTORF;
            this.uurLoon = uurLoon;
            this.vastePrijs = vastePrijs;
        }
    }

    class Dag implements Serializable {
        private static final long serialVersionUID = 1L;
        private transient DateTimeFormatter dtf;
        private float gewerkteUren;
        private String omschrijving;
        private transient LocalDateTime now;
        LocalDate savedDate;

        public Dag(float gewerkteUren, String omschrijving, LocalDate savedDate) {
            this.gewerkteUren = gewerkteUren;
            this.omschrijving = omschrijving;
            this.savedDate = savedDate;
        }

        public Dag(float gewerkteUren, String omschrijving){
            this.gewerkteUren = gewerkteUren;
            this.omschrijving = omschrijving;
            initializeDateTime(); // Initialize transient fields
        }

        private void initializeDateTime() {
            this.dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            this.now = LocalDateTime.now();
        }

        private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
            ois.defaultReadObject(); // Perform default deserialization
            initializeDateTime(); // Initialize transient fields after deserialization
        }

    }

    class Data{
        //read from file
        public void getData(Gebruiker gebruiker){
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("data.dat"))) {
                gebruiker = (Gebruiker) input.readObject();
                System.out.println("Data gelezen");

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
                System.out.println("Data opgeslagen.");
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

            //Check voor data

            while (true) {

                System.out.println("Select an option:");
                System.out.println("1. Voer uren in");
                System.out.println("2. Voeg werkgever toe");
                System.out.println("3. Voeg project toe");
                // meer

                System.out.println("0. Exit");

                System.out.print("Your choice: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.println("Hoeveel uren heb je gewerkt: ");
                        float gewerkteUren = scanner.nextFloat();
                        scanner.nextLine();

                        System.out.println("Geef een kleine omschrijving van je dag: ");
                        String omschrijving = scanner.nextLine();

                        System.out.println("Kies een datum: ");
                        System.out.println("1. Vandaag" ); // nog naar kijken
                        System.out.println("2. Andere datum");
                        String dagChoice = scanner.nextLine();

                        switch (dagChoice){
                            case "1":
                                gebruiker.addDag(new Dag(gewerkteUren, omschrijving));
                            case "2":
                                System.out.println("Enter the date to save (YYYY-MM-DD): ");
                                String inputDate = scanner.nextLine();
                                LocalDate savedDate = LocalDate.parse(inputDate);

                                gebruiker.addDag(new Dag(gewerkteUren, omschrijving, savedDate));
                        }

                        System.out.println("Selecteer een werkgever: ");

                        for (int i = 0; i < gebruiker.getWerkgevers().size(); i++) {
                            System.out.println(i + ". " + gebruiker.getWerkgeverNaam(i));
                        }

                        String werkgeverChoice = scanner.nextLine();

                        data.writeData(gebruiker);
                        break;

                    case "2":
                        // Add Werkgever
                        System.out.println("Enter werkgever details:");
                        System.out.print("Naam: ");
                        String naam = scanner.nextLine();
                        System.out.print("Kilometers: ");
                        float kilometers = Float.parseFloat(scanner.nextLine());

                        boolean vastPrijsToF = false;

                        gebruiker.addWerkgever(new Werkgever(naam, kilometers));

                        data.writeData(gebruiker);
                        break;

                    case "3":
                        // add project
                        System.out.println("Voer het uw project naam in: ");
                        String projectNaam = scanner.nextLine();


                        System.out.println("Heb je een uurloon of een vastprijs");
                        System.out.println("1. Uurloon");
                        System.out.println("2. Vastprijs");

                        String loonChoice = scanner.nextLine();

                        switch (loonChoice){
                            case "1":
                                System.out.print("Voer uurloon in: ");
                                float uurloon = Float.parseFloat(scanner.nextLine());

                                // save uurloon
                            case "2":
                                System.out.print("Voer vastprijs in: ");
                                float vastprijs = Float.parseFloat(scanner.nextLine());

                            default:
                                System.out.println("Error: Voer 1 of 2 in");
                                break;
                        }

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
            Project project = new Project("Aaap", true, 10, 20);

            //moet nog gedelete worden als klaar
            Gebruiker maarten = new Gebruiker("12345");
            gebruikers.add(maarten);

            maarten.addWerkgever(new Werkgever("Noble", 20));
            maarten.addWerkgever(new Werkgever("Alta", 40));

            System.out.println("Voer je inlog naam in: ");
            String inlogNaam = scanner.nextLine();

            //lees eerst data
            //start dan programma
            for (Gebruiker gebruiker : gebruikers) {
                if (gebruiker.isGebruiker(inlogNaam)) {
                    data.getData(gebruiker);
                    start.startProgram(gebruiker);
                }
            }
        }
    }


    // alles naar nederlands veranderen