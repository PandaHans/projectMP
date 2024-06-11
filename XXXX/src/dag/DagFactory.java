package src.dag;

public class DagFactory{
    public Dag maakDag(String nieuweDagType){
        Dag nieuweDag = null;
        if (nieuweDagType.equals("N")){
            return new NormaleDag();
        } else if (nieuweDagType.equals("A")) {
            return new AparteDag();
        }else return null;
    }
}
