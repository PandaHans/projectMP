package com.mp.projectmp.dag;

import java.time.LocalDate;

public class NormaleDag extends Dag {
    public NormaleDag() {
        setGewerkteUren(0f);
        setSavedDate(LocalDate.now());
        setOmschrijving("Basic Omschrijving");
    }
}
