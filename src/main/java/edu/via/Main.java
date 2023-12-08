package edu.via;

import com.sun.net.httpserver.HttpServer;
import edu.via.model.ModelManager;
import edu.via.webapi.ProjectController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        ModelManager modelManager = new ModelManager();
        System.out.println("Starting server..");

        HttpServer server;
        server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);

        //Bind URL's to controllers
        String URL_Project = "/projects";
        server.createContext(URL_Project, new ProjectController(modelManager));

        server.start();
        System.out.println("Server started at http://localhost:8080/pages/index.html !  <--- click me to open in browser ;)");
    }
}