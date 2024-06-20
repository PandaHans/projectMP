import com.mp.projectmp.base.Client;
import com.mp.projectmp.base.LoonType;
import com.mp.projectmp.base.Project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddProjectTest {
    // test Equivalentieklassen
    @Test
    public void testAddProject() {
        Client client = new Client("testclient", 10.0f);
        Project project = new Project("testproject", new LoonType("Uurloon", 50.0f));
        client.addProject(project);
        assertTrue(client.getProjecten().contains(project));
    }

    @Test
    public void testAddProjectLegeNaam() {
        Client client = new Client("testclient", 10.0f);
        Project project = new Project("", new LoonType("Uurloon", 50.0f));
        client.addProject(project);
        assertFalse(client.getProjecten().contains(project));
    }
    @Test
    public void testAddProjectMinLoon() {
        Client client = new Client("testclient", 10.0f);
        Project project = new Project("testproject", new LoonType("Uurloon", -10.0f));
        client.addProject(project);
        assertFalse(client.getProjecten().contains(project));
    }

    //test randwaarden
    @Test
    public void testAddProjectLoonRandwaarden() {
        Client client = new Client("testclient", 10.0f);
        Project Loon = new Project("testproject1", new LoonType("Uurloon", 0.0f));
        Project Loonplusss = new Project("testproject2", new LoonType("Uurloon", 10.0f));
        Project Loonplus = new Project("testproject3", new LoonType("Uurloon", .00000000000000000000000000000000001f));
        Project LoonMin = new Project("testproject4", new LoonType("Uurloon", -.0000000000000000000000000000000001f));



        client.addProject(Loon);
        assertFalse(client.getProjecten().contains(Loon));

        client.addProject(Loonplusss);
        assertFalse(client.getProjecten().contains(Loon));

        client.addProject(Loonplus);
        assertTrue(client.getProjecten().contains(Loonplus));

        client.addProject(LoonMin);
        assertFalse(client.getProjecten().contains(LoonMin));
    }

    // pairwise testing
    @Test
    public void testAddProjectPairwise() {
        Client client = new Client("testclient", 10.0f);
        Project validProject = new Project("testproject1", new LoonType("Uurloon", 50.0f));
        Project emptyProject = new Project("", new LoonType("Uurloon", 50.0f));
        Project invalidLoon = new Project("testproject2", new LoonType("Uurloon", -10.0f));
        Project duplicateProject = new Project("testproject3", new LoonType("Uurloon", 60.0f));

        client.addProject(validProject);
        assertTrue(client.getProjecten().contains(validProject));

        client.addProject(emptyProject);
        assertFalse(client.getProjecten().contains(emptyProject));

        client.addProject(invalidLoon);
        assertFalse(client.getProjecten().contains(invalidLoon));

        client.addProject(duplicateProject);
        assertEquals(2, client.getProjecten().size());
    }

    @Test
    public void testAddProject_MCDC() {
        Client client = new Client("testclient", 10.0f);
        Project projectEmpty = new Project("", new LoonType("Uurloon", 50.0f));
        Project projectInvalidLoon = new Project("testproject", new LoonType("Uurloon", -10.0f));
        Project validProject = new Project("testproject", new LoonType("Uurloon", 50.0f));
        Project duplicateProject = new Project("testproject", new LoonType("Uurloon", 60.0f));

        client.addProject(projectEmpty);
        assertFalse(client.getProjecten().contains(projectEmpty));

        client.addProject(projectInvalidLoon);
        assertFalse(client.getProjecten().contains(projectInvalidLoon));

        client.addProject(validProject);
        assertTrue(client.getProjecten().contains(validProject));
        client.addProject(validProject);
        assertEquals(1, client.getProjecten().size());

        client.addProject(duplicateProject);
        assertEquals(1, client.getProjecten().size());
    }
}