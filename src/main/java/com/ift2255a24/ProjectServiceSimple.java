package com.ift2255a24;

import java.util.HashMap;
import java.util.Map;

public class ProjectServiceSimple {
    private final Map<Integer, Project> projects = new HashMap<>();

    public Project createProject(String name) {
        Project project = new Project(name);
        projects.put(project.getId(), project);
        return project;
    }

    public Project getProjectById(int id) {
        return projects.get(id);
    }

    public Project updateProject(int id, String newName) {
        Project project = projects.get(id);
        if (project != null) {
            project.setName(newName);
        }
        return project;
    }

    public boolean deleteProject(int id) {
        return projects.remove(id) != null;
    }
}