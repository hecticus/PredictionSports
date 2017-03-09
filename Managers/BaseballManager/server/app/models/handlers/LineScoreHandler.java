package models.handlers;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by palenge on 12/30/16.
 */
public class LineScoreHandler {
    private int home;
    private int away;

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public int getAway() {
        return away;
    }

    public void setAway(int away) {
        this.away = away;
    }

    public LineScoreHandler(JsonNode json)
    {
        this.home = json.get("home").asInt();
        this.away = json.get("away").asInt();
    }
}
