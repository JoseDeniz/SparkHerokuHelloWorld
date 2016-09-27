package webService;

import spark.Spark;
import webService.controller.HelloController;

import java.util.Optional;

import static spark.Spark.*;

public class Application {

    public Application() {
        setConfig();
    }

    private static void setConfig() {
        setPort();
        staticFileLocation("/public");
    }

    private static void setPort() {
        String port = System.getenv("PORT");
        port(Optional.ofNullable(port).map(Integer::valueOf).orElse(4567));
    }

    public void deploy() {
        new HelloController();
    }

    public static void main(String[] args) {
        new Application().deploy();
    }
}
