package src;

import src.base.Gebruiker;
import src.data.DataReader;

import java.util.ArrayList;
import java.util.Scanner;

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

// connent Dag aan project terwijl kijken naar umls van video DONE
// Start class veranderen DONE
// invoice class invullen DONE
// alle classes public maken DONE
// javafx toevoegen NOT DOING
// Kijken naar userclass? DONE
// Tests kijken
// comments toevoegen bij design patterns
// kijken naar sigrid
// kijken naar solid principles
// kijken naar data save method NOT DOING
// alles nl veranderen

// https://www.newthinktank.com/2012/09/factory-design-pattern-tutorial/
// https://www.newthinktank.com/2012/10/template-method-design-pattern-tutorial/
// https://www.newthinktank.com/2012/10/composite-design-pattern-tutorial/
// https://www.youtube.com/watch?v=9XnsOpjclUg&list=PLF206E906175C7E07&index=9
// https://www.youtube.com/watch?v=vNHpsC5ng_E&list=PLF206E906175C7E07
