package webService.controller;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import webService.Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static spark.Spark.awaitInitialization;
import static spark.Spark.stop;

public class HelloControllerShould {

    private static final String GET_METHOD = "GET";

    @BeforeClass
    public static void setUp() throws Exception {
        new Application().deploy();
        awaitInitialization();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        stop();
    }

    @Test public void
    should_get_a_hello_world_message_when_hello_route_is_requested() throws IOException {
        URL url = new URL("http://localhost:4567/hello");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(GET_METHOD);

        assertThat(connection.getResponseCode(), is(200));
        assertThat(getResponse(connection), is("{\"message\": \"Hello World!\"}"));
    }

    private String getResponse(HttpURLConnection connection) throws IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            return buffer.lines().collect(joining("\n"));
        }
    }

}
