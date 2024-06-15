import com.mp.projectmp.dag.Dag;
import com.mp.projectmp.dag.NormaleDag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//Equivalentieklassen
public class AddDagTest {
    @Test
    public void testDag_GeldigeUren() {
        Dag dag = new NormaleDag();
        dag.setGewerkteUren(8.0f); // Geldige uren (positief)
        assertTrue(dag.getGewerkteUren() == 8.0f);
    }

    @Test
    public void testDag_NegativeUren() {
        Dag dag = new NormaleDag();
        dag.setGewerkteUren(-2.5f); // Negatieve uren
        assertFalse(dag.getGewerkteUren() == -2.5f);
    }

    @Test
    public void testDag_NulUren() {
        Dag dag = new NormaleDag();
        dag.setGewerkteUren(0.0f); // Nul uren
        assertTrue(dag.getGewerkteUren() == 0.0f);
    }

    //randwaardes
    @Test
    public void testDag_boven24() {
        Dag dag = new NormaleDag();
        dag.setGewerkteUren(25.0f); // Nul uren
        assertFalse(dag.getGewerkteUren() == 25.0f);
    }
    @Test
    public void testDag_24() {
        Dag dag = new NormaleDag();
        dag.setGewerkteUren(24.0f); // Nul uren
        assertTrue(dag.getGewerkteUren() == 24.0f);
    }

    @Test
    public void testDag_min1() {
        Dag dag = new NormaleDag();
        dag.setGewerkteUren(-1.0f); // Nul uren
        assertFalse(dag.getGewerkteUren() == -1.0f);
    }

}
