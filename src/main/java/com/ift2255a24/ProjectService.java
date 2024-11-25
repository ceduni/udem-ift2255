package com.ift2255a24;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectService {
    private final IRepository<Project> projectRepository;

    public ProjectService(IRepository<Project> projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Crée un nouveau projet avec le nom donné.
     *
     * @param name le nom du projet
     * @return le projet créé
     * @throws IllegalArgumentException si le nom est null ou vide
     */
    public Project createProject(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du projet ne peut pas être null ou vide");
        }

        Project project = new Project(name);

        return projectRepository.save(project);
    }

    /**
     * Met à jour un projet existant avec un nouveau nom.
     *
     * @param id   l'ID du projet
     * @param newProject le projet modifié
     * @return le projet mis à jour
     * @throws Exception si le projet n'existe pas
     * @throws IllegalArgumentException si le nouveau nom est null ou vide
     */
    public Project updateProject(int id, Project newProject) throws Exception {
        if (newProject == null) {
            throw new IllegalArgumentException("Le nouveau projet ne peut pas être null");
        }

        Optional<Project> optionalProject = projectRepository.get(id);
        if (!optionalProject.isPresent()) {
            throw new Exception("Projet non trouvé avec l'ID : " + id);
        }

        Project project = optionalProject.get();
        project.setName(newProject.getName());
        project.setDescription(newProject.getDescription());

        return projectRepository.save(project);
    }

    /**
     * Récupère un projet par son ID.
     *
     * @param id l'ID du projet
     * @return le projet correspondant
     * @throws Exception si le projet n'existe pas
     */
    public Project getProjectById(int id) throws Exception {
        Optional<Project> optionalProject = projectRepository.get(id);
        if (!optionalProject.isPresent()) {
            throw new Exception("Projet non trouvé avec l'ID : " + id);
        }
        return optionalProject.get();
    }

    /**
     * Supprime un projet par son ID.
     *
     * @param id l'ID du projet
     * @throws Exception si le projet n'existe pas
     */
    public void deleteProject(int id) throws Exception {
        if (!projectRepository.delete(id)) {
            throw new Exception("Impossible de supprimer. Projet non trouvé avec l'ID : " + id);
        }
    }

    /**
     * Récupère tous les projets.
     *
     * @return une liste de tous les projets
     */
    public List<Project> getAllProjects() {
        return projectRepository.getAll();
    }

    /**
     * Récupère tous les projets avec un nom correspondant.
     *
     * @param nameQuery une partie du nom du projet à rechercher
     * @return une liste de projets contenant la chaîne de recherche
     */
    public List<Project> searchProjectsByName(String nameQuery) {
        return projectRepository.getAll().stream()
                .filter(project -> project.getName().contains(nameQuery))
                .collect(Collectors.toList());
    }
}
