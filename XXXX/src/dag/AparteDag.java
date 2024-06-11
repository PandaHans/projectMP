package src.dag;

import java.time.LocalDate;

public class AparteDag extends Dag{
    public AparteDag(){
        setGewerkteUren(0f);
        setSavedDate(LocalDate.now());
        setOmschrijving("Basic Omschrijving");
    }
}
