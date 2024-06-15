package com.mp.projectmp.dag;

public class DagFactory {
    public Dag maakDag(String nieuweDagType) {
        Dag nieuweDag = null;
        if (nieuweDagType.equals("S")) {
            return new SnelleDag();
        } else if (nieuweDagType.equals("N")) {
            return new NormaleDag();
        } else return null;
    }
}
