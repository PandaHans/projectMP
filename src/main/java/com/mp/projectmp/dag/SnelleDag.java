package com.mp.projectmp.dag;

import java.time.LocalDate;

//Factory method design pattern
public class SnelleDag extends Dag {
    public SnelleDag() {
        setGewerkteUren(6.0f);
        setSavedDate(LocalDate.now());
        setOmschrijving("Super leuk");
    }
}
