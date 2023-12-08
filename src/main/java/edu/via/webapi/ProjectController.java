package edu.via.webapi;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import edu.via.model.ModelManager;
import edu.via.model.Project;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class ProjectController implements HttpHandler
{

    private final ModelManager modelManager;

    public ProjectController(ModelManager modelManager)
    {
        this.modelManager = modelManager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); //Er ikke nødvendigt når vi kun bruger simple requests (https://stackoverflow.com/questions/29954037/why-is-an-options-request-sent-and-can-i-disable-it)
        String requestMethod = exchange.getRequestMethod();
        switch (requestMethod)
        {
            case "GET" ->
            {
                //Check to see if the request is to view a single Project or to see all projects
                if ("/projects".equals(exchange.getRequestURI().toString())) //All projects
                {
                    handleGETAllProjects(exchange);
                } else
                {
                    System.out.println(exchange.getRequestURI().toString());
                    String id = exchange.getRequestURI().toString().replace("/projects/", ""); //A single project with specific ID
                    handleGETSingleProject(exchange, id);
                }
            }
            case "POST" ->
            {
                handlePOST(exchange);
            }
        }
    }

    private void handleGETSingleProject(HttpExchange exchange, String id)
    {
        int idAsInteger = Integer.parseInt(id);
        Project project = modelManager.getProjectWithId(idAsInteger);
        String projectAsJSON = new Gson().toJson(project);

        try (OutputStream outputStream = exchange.getResponseBody())
        {
            exchange.sendResponseHeaders(200, projectAsJSON.length());
            outputStream.write(projectAsJSON.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void handleGETAllProjects(HttpExchange exchange)
    {
        ArrayList<Project> projects = modelManager.getAllProjects();
        String projectsAsJSON = new Gson().toJson(projects);

        try (OutputStream outputStream = exchange.getResponseBody())
        {
            exchange.sendResponseHeaders(200, projectsAsJSON.length());
            outputStream.write(projectsAsJSON.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void handlePOST(HttpExchange exchange)
    {
        try (OutputStream outputStream = exchange.getResponseBody())
        {
            String projectAsJSON = new String(exchange.getRequestBody().readAllBytes());

            Project project = new Gson().fromJson(projectAsJSON, Project.class);
            modelManager.addProject(project);

            String response = "Created!";
            exchange.sendResponseHeaders(201, response.length());
            outputStream.write(response.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
