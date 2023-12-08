package edu.via.model;

import edu.via.util.ID;

import java.util.ArrayList;

public class ModelManager {

    private ArrayList<Project> projects = new ArrayList<>();

    public void addProject(Project project)
    {
        project.setId(ID.getID());
        projects.add(project);
        System.out.println(projects);
    }

}
