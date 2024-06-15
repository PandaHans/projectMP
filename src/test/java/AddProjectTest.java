import com.mp.projectmp.base.Client;
import com.mp.projectmp.base.Project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddProjectTest {

    //random tests
    @Test
    public void testAddProjectValidName() {
        Client client = new Client("John Doe", 10.0f);
        Project project = new Project("Project X", 50.0f);
        client.addProject(project);
        assertTrue(client.getProjecten().contains(project));
    }

    @Test
    public void testAddProjectEmptyName() {
        Client client = new Client("John Doe", 10.0f);
        Project project = new Project("", 50.0f);
        client.addProject(project);
        assertFalse(client.getProjecten().contains(project));
    }

    @Test
    public void testAddProjectDuplicateName() {
        Client client = new Client("John Doe", 10.0f);
        Project project1 = new Project("Project X", 50.0f);
        Project project2 = new Project("Project X", 60.0f);
        client.addProject(project1);
        client.addProject(project2);
        assertEquals(1, client.getProjecten().size());
    }


    //Coverage
    //test CC Condition coverage door elke voorwaarde zowel waar als niet waar te maken.
    @Test
    public void testAddProject_CC() {
        Client client = new Client("John Doe", 10.0f);
        Project project1 = new Project("Project X", 50.0f);
        Project project2 = new Project("Project Y", 60.0f);
        Project projectEmpty = new Project("", 50.0f);
        Project projectInvalidUurLoon = new Project("Project Z", -10.0f);

        client.addProject(projectEmpty);

        client.addProject(projectInvalidUurLoon);

        client.addProject(project1);
        client.addProject(project1);

        client.addProject(project2);
    }

    @Test
    public void testAddProject_DC() {
        Client client = new Client("John Doe", 10.0f);
        Project project1 = new Project("Project X", 50.0f);
        Project project2 = new Project("Project Y", 60.0f);

        client.addProject(project1);

        client.addProject(project1);
    }
    @Test
    public void testAddProject_CDC() {
        Client client = new Client("John Doe", 10.0f);
        Project project1 = new Project("Project X", 50.0f);
        Project project2 = new Project("Project Y", 60.0f);
        Project projectEmpty = new Project("", 50.0f);
        Project projectInvalidUurLoon = new Project("Project Z", -10.0f);

        client.addProject(projectEmpty);

        client.addProject(projectInvalidUurLoon);

        client.addProject(project1);
        client.addProject(project1);

        client.addProject(project2);
    }
    @Test
    public void testAddProject_MCC() {
        Client client = new Client("John Doe", 10.0f);
        Project project1 = new Project("Project X", 50.0f);
        Project project2 = new Project("Project Y", 60.0f);
        Project projectEmpty = new Project("", 50.0f);
        Project projectInvalidUurLoon = new Project("Project Z", -10.0f);

        // Condition1 true, Condition2 false, Condition3 false
        client.addProject(projectEmpty);

        // Condition1 false, Condition2 true, Condition3 false
        client.addProject(projectInvalidUurLoon);

        // Condition1 false, Condition2 false, Condition3 true
        client.addProject(project1);
        client.addProject(project1);

        // Condition1 false, Condition2 false, Condition3 false
        client.addProject(project2);

        // Condition1 true, Condition2 true, Condition3 false
        client.getProjecten().clear();
        client.addProject(projectEmpty);
        client.addProject(projectInvalidUurLoon);
    }


    //MC/DC
    //deze test dekt alle mogelijke paden om te testen
    @Test
    public void testAddProject_MCDC() {
        Client client = new Client("John Doe", 10.0f);
        Project project1 = new Project("Project X", 50.0f);
        Project project2 = new Project("Project Y", 60.0f);
        Project projectEmpty = new Project("", 50.0f);
        Project projectNull = null;
        Project projectInvalidUurLoon = new Project("Project Z", -10.0f);

        // Voorwaarde 1 onwaar, Voorwaarde 2 onwaar, Voorwaarde 3 onwaar
        client.addProject(project1);

        // Voorwaarde 1 waar, Voorwaarde 2 onwaar, Voorwaarde 3 onwaar
        client.addProject(projectEmpty);
        client.addProject(projectNull);

        // Voorwaarde 1 onwaar, Voorwaarde 2 waar, Voorwaarde 3 onwaar
        client.addProject(projectInvalidUurLoon);

        // Voorwaarde 1 onwaar, Voorwaarde 2 onwaar, Voorwaarde 3 waar
        client.addProject(project1);
        client.addProject(project1);

        // Voorwaarde 1 waar, Voorwaarde 2 waar, Voorwaarde 3 onwaar
        client.addProject(projectEmpty);
        client.addProject(projectInvalidUurLoon);
    }
}