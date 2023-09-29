package me.playgamesgo.modtweaks;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;

import static spark.Spark.*;

public class Main {
    public static Logger logger = Logger.getLogger("ModTweaks");
    private static final int HTTP_PORT = 8080;

    public static void main(String[] args) {
        logger.setUseParentHandlers(false);

        ConsoleHandler handler = new ConsoleHandler();

        Formatter formatter = new LogFormatter();
        handler.setFormatter(formatter);

        logger.addHandler(handler);

        logger.info("Starting ModTweaks Server...");

        port(HTTP_PORT);

        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));


        get("/", (req, res) -> {
            return null;
        });

        post("/download/*", (req, res) -> {
            return PackGenerator.packGenerator(req.body());
        });


        logger.info("ModTweaks Server started on port " + HTTP_PORT);
    }
}