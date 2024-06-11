package src;

import java.util.Scanner;

//Template design pattern
public abstract class Invoice {
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
