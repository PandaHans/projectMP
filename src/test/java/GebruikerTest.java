import com.mp.projectmp.base.Client;
import com.mp.projectmp.base.Gebruiker;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//pairwise testing
public class GebruikerTest {

    private Gebruiker gebruiker;

    @Before
    public void setUp() {
        gebruiker = new Gebruiker("testUser");
    }

    @org.junit.Test
    public void testAddClient() {
        // Test adding a valid client
        Client validClient = new Client("Client1", 15.0f);
        gebruiker.addClient(validClient);
        assertTrue(gebruiker.getClienten().contains(validClient));

        // Test adding a client with an empty name
        Client emptyNameClient = new Client("", 10.0f);
        gebruiker.addClient(emptyNameClient);
        assertFalse(gebruiker.getClienten().contains(emptyNameClient));

        // Test adding a client with negative kilometers
        Client negativeKmClient = new Client("Client2", -5.0f);
        gebruiker.addClient(negativeKmClient);
        assertFalse(gebruiker.getClienten().contains(negativeKmClient));

        // Test adding a client with an existing name
        Client existingNameClient = new Client("Client1", 20.0f);
        gebruiker.addClient(existingNameClient);
        assertFalse(gebruiker.getClienten().contains(existingNameClient));
    }
}