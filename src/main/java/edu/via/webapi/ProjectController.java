package edu.via.webapi;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import edu.via.model.ModelManager;
import edu.via.model.Project;

import java.io.IOException;
import java.io.OutputStream;

public class ProjectController implements HttpHandler {


    private final ModelManager modelManager;

    public ProjectController(ModelManager modelManager) {

        this.modelManager = modelManager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); //TODO: Er ikke nødvendigt når vi kun bruger simple requests (https://stackoverflow.com/questions/29954037/why-is-an-options-request-sent-and-can-i-disable-it)
        String requestMethod = exchange.getRequestMethod();
        if(requestMethod.equals("GET"))
        {
            //TODO: DO GET STUFF
        }
        else if(requestMethod.equals("POST"))
        {
            //TODO: DO POST STUFF
            handlePost(exchange);
        }
    }

    private void handlePost(HttpExchange exchange)
    {
        try (OutputStream outputStream = exchange.getResponseBody())
        {
            String projectAsJSON = new String(exchange.getRequestBody().readAllBytes());
            Project project = new Gson().fromJson(projectAsJSON, Project.class);

            modelManager.addProject(project);

            String response = "Created!";
            exchange.sendResponseHeaders(201, response.length());
            outputStream.write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
