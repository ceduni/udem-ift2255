import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ift2255a24.IRepository;
import com.ift2255a24.Project;
import com.ift2255a24.ProjectService;

class ProjectServiceTest {
    @Mock
    private IRepository<Project> mockProjectRepository;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProject() {
        // Arrange
        String name = "Un projet";
        Project project = new Project(name);
        when(mockProjectRepository.save(any(Project.class))).thenReturn(project);

        // Act
        Project createdProject = projectService.createProject(name);

        // Assert
        assertNotNull(createdProject, "Le projet créé ne doit pas être null");
        assertEquals(name, createdProject.getName(), "Le nom du projet doit être " + name);

        // Vérification que la méthode save a bien été appelée
        verify(mockProjectRepository, times(1)).save(any(Project.class));
    }

    @Test
    void testGetProjectById() {
        // Arrange
        Project project = new Project("Un bon projet");
        int goodId = 1;
        when(mockProjectRepository.get(goodId)).thenReturn(Optional.of(project));

        try {
            // Act
            Project fetchedProject = projectService.getProjectById(1);

            // Assert
            assertNotNull(fetchedProject);
            assertEquals("Un bon projet", fetchedProject.getName(), "Le nom du projet doit être " + project.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Vérification que la méthode findById a été appelée avec l'ID correct
        verify(mockProjectRepository, times(1)).get(goodId);
    }

    @Test
    void testGetProjectById_NotFound() {
        // Arrange
        int badId = 813749;
        when(mockProjectRepository.get(badId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            projectService.getProjectById(badId);
        });

        // Assert
        assertEquals("Projet non trouvé avec l'ID : " + badId, exception.getMessage());

        // Vérification que la méthode findById a bien été appelée avec l'ID défini
        verify(mockProjectRepository, times(1)).get(badId);
    }

    @Test
    void testUpdateProject() throws Exception {
        // Arrange
        Project existingProject = new Project("Ancien Project");
        String newName = "Nouveau Project";
        when(mockProjectRepository.get(1)).thenReturn(Optional.of(existingProject));
        when(mockProjectRepository.save(existingProject)).thenReturn(existingProject);

        // Act
        Project updatedProject = projectService.updateProject(1, new Project(newName));

        // Assert
        assertEquals(newName, updatedProject.getName());
        verify(mockProjectRepository, times(1)).get(1);
        verify(mockProjectRepository, times(1)).save(existingProject);
    }

    @Test
    void testGetAllProjects() {
        // Arrange
        List<Project> mockProjects = List.of(
                new Project("Project A"),
                new Project("Project B"));
        when(mockProjectRepository.getAll()).thenReturn(mockProjects);

        // Act
        List<Project> allProjects = projectService.getAllProjects();

        // Assert
        assertEquals(mockProjects.size(), allProjects.size());
        assertTrue(allProjects.containsAll(mockProjects));
        verify(mockProjectRepository, times(1)).getAll();
    }

    @Test
    void testSearchProjectsByName() {
        // Arrange
        List<Project> mockProjects = List.of(
                new Project("Alpha"),
                new Project("Beta"));
        when(mockProjectRepository.getAll()).thenReturn(mockProjects);

        // Act
        List<Project> result = projectService.searchProjectsByName("A");

        // Assert
        assertEquals(1, result.size());
        assertEquals("Alpha", result.get(0).getName());
        verify(mockProjectRepository, times(1)).getAll();
    }

    @Test
    void testMultipleInteractions() throws Exception {
        // Arrange
        Project project = new Project("Projet 1-2-3");
        String newName = "Projet 1-2-3-4-5";
        when(mockProjectRepository.get(1)).thenReturn(Optional.of(project));
        when(mockProjectRepository.save(any(Project.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Project updatedProject = projectService.updateProject(1, new Project(newName));
        Project fetchedProject = projectService.getProjectById(1);

        // Assert
        // Vérifier que le projet a été mis à jour correctement
        assertEquals(newName, updatedProject.getName(),
                "Le nom du projet n'a pas été mis à jour correctement");

        // Vérifier que le projet récupéré est cohérent
        assertEquals(updatedProject.getId(), fetchedProject.getId(), "Le projet récupéré ne correspond pas au projet mis à jour");
        assertEquals(updatedProject.getName(), fetchedProject.getName(),"Le nom du projet récupéré ne correspond pas au nom du projet mis à jour");

        verify(mockProjectRepository, times(2)).get(1);
        verify(mockProjectRepository, times(1)).save(any(Project.class));
    }
}
