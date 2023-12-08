package edu.via.model;

import edu.via.util.ID;

import java.util.ArrayList;

public class ModelManager
{

    private final ArrayList<Project> projects = new ArrayList<>();

    public void addProject(Project project)
    {
        project.setId(ID.getID());
        projects.add(project);
    }

    public ArrayList<Project> getAllProjects()
    {
        return projects;
    }

    public Project getProjectWithId(int id)
    {
        for (Project project : projects)
        {
            if (project.getId() == id)
            {
                return project;
            }
        }
        throw new IllegalArgumentException("No such element");
    }
}
