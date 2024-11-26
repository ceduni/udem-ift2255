import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import com.ift2255a24.IRepository;
import com.ift2255a24.Project;
import com.ift2255a24.ProjectService;

class ProjectRepositoryTest {
    private IRepository<Project> projectRepository;

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<String> mockResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProject() throws IOException, InterruptedException {
        // Arrange
        Project project = new Project("Test Project");

        when(mockResponse.statusCode()).thenReturn(201);
        when(mockResponse.body()).thenReturn("projectJson");
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        // Act
        Project savedProject = projectRepository.save(project);

        // Assert
        assertNotNull(savedProject);
        assertEquals("Test Project", savedProject.getName());

        verify(mockHttpClient, times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    @Test
    void testGetProjectById() throws IOException, InterruptedException {
        // Arrange
        String projectJson = "Project value";

        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(projectJson);
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        // Act
        Optional<Project> fetchedProject = projectRepository.get(1);

        // Assert
        assertTrue(fetchedProject.isPresent());
        assertEquals("Existing Project", fetchedProject.get().getName());

        verify(mockHttpClient, times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    @Test
    void testDeleteProjectById() throws IOException, InterruptedException {
        // Arrange
        when(mockResponse.statusCode()).thenReturn(204);
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        // Act
        boolean isDeleted = projectRepository.delete(1);

        // Assert
        assertTrue(isDeleted);

        verify(mockHttpClient, times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }
}
