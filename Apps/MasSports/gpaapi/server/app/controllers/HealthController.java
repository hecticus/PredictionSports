package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class HealthController extends Controller {

    private static final String VERSION = "1.0.1";

    public Result health() {
        return ok("{\"status\":\"ok\",\"version\":\"" + VERSION + "\"}").as("application/json");
    }
}
