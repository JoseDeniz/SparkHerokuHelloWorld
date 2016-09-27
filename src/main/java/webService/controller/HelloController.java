package webService.controller;

import static spark.Spark.get;

public class HelloController {

    public HelloController() {
        setRoute();
    }

    private void setRoute() {
        CorsFilter.apply();
        get("/hello", (request, response) -> "{\"message\": \"Hello World!\"}");
    }

}
