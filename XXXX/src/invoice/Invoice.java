package src.invoice;

import src.base.Client;
import src.base.Project;
import src.helper.UserInput;

import java.util.Scanner;

//Template design pattern
public abstract class Invoice {
    UserInput userInput = new UserInput();

    final public void makeInvoice(Client client, Project project){
        int maand = userInput.getMaand();
        int jaar = userInput.getJaar();
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

    public void printTotals(Client client, float totaalGewerkteUren, float totaalGeredenKiloMeters) {
        float uurLoon = client.getProjecten().isEmpty() ? 0 : client.getProjecten().getFirst().getUurLoon(); // assuming getProjecten() and getUurLoon() exist in Client and Project classes
        float totaalVerdiend = totaalGewerkteUren * uurLoon;

        System.out.println("Totaal gereden kilometers: " + totaalGeredenKiloMeters);
        System.out.println("Totaal aantal gewerkte uren: " + totaalGewerkteUren);
        System.out.println("Totaal verdiend: " + totaalVerdiend + "\n");
        System.out.println();
    }
}
