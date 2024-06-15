package com.mp.projectmp.dag;

import java.time.LocalDate;

//Factory method design pattern
public class NormaleDag extends Dag {
    public NormaleDag() {
        setGewerkteUren(0f);
        setSavedDate(LocalDate.now());
        setOmschrijving("Basic Omschrijving");
    }
}
