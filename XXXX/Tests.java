
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.io.PrintStream;


public class Tests {

    @Test
    public void dagConstructor_Test() {
        // Prepare test data
        float gewerkteUren = 8.0f;
        String omschrijving = "Test omschrijving";
        LocalDate savedDate = LocalDate.of(2024, 5, 15);

        // Create a Dag object
        Dag dag = new Dag(gewerkteUren, omschrijving, savedDate);

        // Assert that fields are set correctly
        assertEquals(gewerkteUren, dag.getGewerkteUren());
        assertEquals(omschrijving, dag.getOmschrijving());
        assertEquals(savedDate, dag.getSavedDate());
    }

    @Test
    public void maandOverzicht_Test() {
        Gebruiker gebruiker = new Gebruiker("12345");
        Werkgever werkgever = new Werkgever("TestWerkgever", 10.0f);
        Project project = new Project("TestProject", 20.0f);
        Dag dag1 = new Dag(8.0f, "Test omschrijving", LocalDate.of(2024, 5, 15));
        Dag dag2 = new Dag(7.5f, "Test 1", LocalDate.of(2024, 5, 15));

        gebruiker.addWerkgever(werkgever);
        werkgever.addProject(project);
        project.addDag(dag1);
        project.addDag(dag2);

        //maandoverzicht maken
        MaandOverzicht maandOverzicht = new MaandOverzicht(gebruiker.getWerkgevers());
        int jaar = 2000;
        int maand = 10;
        maandOverzicht.printDagenVanDeMaand(jaar, maand, werkgever, project);

        // maak ByteArrayOutputStream om de output te lezen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        // Call the method
        maandOverzicht.printDagenVanDeMaand(2024, 5, werkgever, project);

        // Restore original System.out
        System.setOut(originalOut);

        // Get the captured output
        String printedOutput = outputStream.toString();

        // Define the expected output
        String expectedOutput =
                "Maandoverzicht voor 2024-5 \n" +
                "Werkgever: TestWerkgever Project: TestProject--------------------------------------------\n" +
                "Werkgever en project: TestWerkgever, TestProject\n" +
                "Datum: 2024-05-15\n" +
                "Gewerkte uren: 8.0\n" +
                "Omschrijving: Test omschrijving\n" +
                "--------------------------------------------\n" +
                "--------------------------------------------\n" +
                "Werkgever en project: TestWerkgever, TestProject\n" +
                "Datum: 2024-05-15\n" +
                "Gewerkte uren: 7.5\n" +
                "Omschrijving: Test 1\n" +
                "--------------------------------------------\n"+
                "uurloon\n"+
                "Totaal gereden kilometers: 20.0\n"+
                "Totaal aantal gewerkte uren: 15.5\n"+
                "Totaal verdient: 310.0\n\n";

        // Assert that the printed output matches the expected output
        assertEquals(expectedOutput, printedOutput);
    }

    @Test
    public void jaarOverzicht_Test() {
        Gebruiker gebruiker = new Gebruiker("12345");
        Werkgever werkgever = new Werkgever("TestWerkgever", 10.0f);
        Project project = new Project("TestProject", 20.0f);
        Dag dag1 = new Dag(8.0f, "Test omschrijving", LocalDate.of(2024, 5, 15));
        Dag dag2 = new Dag(7.5f, "Test 1", LocalDate.of(2024, 5, 15));

        gebruiker.addWerkgever(werkgever);
        werkgever.addProject(project);
        project.addDag(dag1);
        project.addDag(dag2);

        //maandoverzicht maken
        JaarOverzicht jaarOverzicht = new JaarOverzicht(gebruiker.getWerkgevers());
        int jaar = 2000;
        jaarOverzicht.printDagenVanHetJaar(jaar, werkgever, project);

        // maak ByteArrayOutputStream om de output te lezen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        // Call the method
        jaarOverzicht.printDagenVanHetJaar(2024, werkgever, project);

        // Restore original System.out
        System.setOut(originalOut);

        // Get the captured output
        String printedOutput = outputStream.toString();

        // Define the expected output
        String expectedOutput =
                        "Jaaroverzicht voor 2024 \n" +
                        " Werkgever: TestWerkgever Project: TestProject--------------------------------------------\n" +
                        "Werkgever en project: TestWerkgever, TestProject\n" +
                        "Datum: 2024-05-15\n" +
                        "Gewerkte uren: 8.0\n" +
                        "Omschrijving: Test omschrijving\n" +
                        "--------------------------------------------\n" +
                        "--------------------------------------------\n" +
                        "Werkgever en project: TestWerkgever, TestProject\n" +
                        "Datum: 2024-05-15\n" +
                        "Gewerkte uren: 7.5\n" +
                        "Omschrijving: Test 1\n" +
                        "--------------------------------------------\n"+
                        "uurloon\n"+
                        "Totaal gereden kilometers: 20.0\n"+
                        "Totaal aantal gewerkte uren: 15.5\n"+
                        "Totaal verdient: 310.0\n\n";

        // Assert that the printed output matches the expected output
        assertEquals(expectedOutput, printedOutput);
    }
}