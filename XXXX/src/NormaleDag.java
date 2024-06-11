package src;

import java.time.LocalDate;

public class NormaleDag extends Dag{
    public NormaleDag(){
        setGewerkteUren(6.0f);
        setSavedDate(LocalDate.now());
        setOmschrijving("Basic Omschrijving");
    }
}
